<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="comment">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="showDraw">
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="total-comments"> 有 {{webcomes.length}} 位小伙伴访问留言 </div>
                  </el-col>
                </el-row>
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="timeline-list">
                      <el-timeline>
                        <el-timeline-item v-for="(com,index) in webcomes.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                                          :key="index" type="primary" :timestamp="com.createDate | formatDate" placement="top">
                          <el-card shadow="hover" body-styl="{ padding: 0}">
                            <h4>{{com.nickname + " "}}</h4>
                            <p>{{com.content}}</p>
                            <p v-for="(rep,index) in com.webreps" :key="index">站长回复： {{rep.content}}</p>
                          </el-card>
                        </el-timeline-item>
                      </el-timeline>
                    </div>
                  </el-col>
                </el-row>
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <el-dialog title="发表一下你的意见" :visible.sync="dialogFormVisible">
                      <el-form :model="webcom" class="dialog-form">
                        <el-form-item class="dialog-form-item" label="邮箱" :label-width="formLabelWidth">
                          <el-input v-model="webcom.email" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item class="dialog-form-item" label="昵称" :label-width="formLabelWidth">
                          <el-input v-model="webcom.nickname" autocomplete="off" maxlength="10" show-word-limit ></el-input>
                        </el-form-item>
                        <el-form-item class="dialog-form-item" label="内容" :label-width="formLabelWidth">
                          <el-input v-model="webcom.content" type="textarea" resize="none" maxlength="120" show-word-limit :rows="3"></el-input>
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button size="mini" @click="dialogFormVisible = false">取 消</el-button>
                        <el-button size="mini" type="primary" @click="sendWebComment">提交</el-button>
                      </div>
                    </el-dialog>
                  </el-col>
                </el-row>
                <el-row class="hidden-md-and-up" :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <el-input class="form-item" v-model="webcom.email">
                      <template class="item-info" slot="prepend">邮箱</template>
                    </el-input>
                    <el-input class="form-item" v-model="webcom.nickname" maxlength="10" show-word-limit>
                      <template class="item-info" slot="prepend">昵称</template>
                    </el-input>
                    <el-input class="form-item" v-model="webcom.content" maxlength="120" show-word-limit >
                      <template class="item-info" slot="prepend">内容</template>
                    </el-input>
                    <div style="text-align: center">
                      <el-button class="btn-clear" size="small">重置</el-button>
                      <el-button @click="sendWebComment" class="btn-submit" size="small">提交</el-button>
                    </div>
                  </el-col>
                </el-row>
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="fun-btn">
                      <el-button @click="dialogFormVisible = true" type="primary" size="mini" plain circle>
                        <i class="el-icon-chat-dot-square" />
                      </el-button>
                    </div>
                  </el-col>
                </el-row>
                <!-- 分页 -->
                <el-row style="margin-top: 20px">
                  <el-col :offset="8" :span="8" style="margin-top: 25px;text-align: center">
                    <el-pagination
                      background
                      @current-change=""
                      :current-page="currentPage"
                      :page-size="pageSize"
                      layout="prev, pager, next"
                      :total="total">
                    </el-pagination>
                  </el-col>
                </el-row>
              </div>
            </transition>
          </el-main>
        </el-container>
      </div>
      <Tool></Tool>
      <Footer></Footer>
    </div>
</template>

<script>
import Top from '../components/top'
import Header from '../components/header'
import Tool from '../components/tool'
import Footer from '../components/footer'
export default {
  name: 'Comment',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data () {
    return {
      dialogTableVisible: false,
      dialogFormVisible: false,
      currentPage: 1,  //当前所在页
      total: 0,  //数据量
      pageSize: 10,  //一页显示多少条记录
      formLabelWidth: '120px',
      webcom: {
        nickname:'',
        email:'',
        content:''
      },
      webcomes:[],
      showDraw: false
    }
  },
  mounted() {
    this.getWebcomList()
    this.change()
  },
  methods: {
    change() {
      this.showDraw = true
    },
    //获取评论列表
    getWebcomList() {
      const _this = this
      const url = 'webcomes/rep?currentPage=' + this.currentPage
      this.$axios.get(url).then(resp => {
        _this.webcomes = resp.data.data.records
        _this.currentPage = resp.data.data.current
        _this.total = resp.data.data.total
        _this.pageSize = resp.data.data.size
      })
    },
    //发布
    sendWebComment() {
      if(this.webcom.email.length === 0) {
        this.$notify({
          title: '失败',
          message: '邮箱为空',
          type: 'error'
        })
        return
      }
      const myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
      if(!myReg.test(this.webcom.email)) {
        this.$notify({
          title: '失败',
          message: '邮箱格式错误',
          type: 'error'
        })
        return
      }
      if(this.webcom.nickname.length === 0) {
        this.$notify({
          title: '失败',
          message: '昵称为空',
          type: 'error'
        })
        return
      }
      if(this.webcom.content.length === 0) {
        this.$notify({
          title: '失败',
          message: '内容为空',
          type: 'error'
        })
        return
      }
      const _this = this
      this.$axios.post("webcomes", this.webcom).then(resp => {
        const data = resp.data.data
        _this.$notify({
          title: '成功',
          message: data,
          type: 'success'
        });
        _this.webcom.content = null
        _this.webcom.nickname = null
        _this.webcom.email = null
      })
    },
  }
}
</script>

<style scoped>
    .fadeInDown-enter-active {
      transition: all 1.9s ease;
    }

    .fadeInDown-enter {
      transform: translateY(10px);
      opacity: 0;
    }
    div.comment {
        margin-top: 19%;
    }
    div.comment .total-comments {
        color: #9a9393;
        text-align: center;
        font-size: 14px;
        font-family: alibaba,serif;
    }
    div.comment .fun-btn {
        margin-top: 5%;
    }
    div.comment .timeline-list {
        margin-top: 5%;
    }
    div.comment .timeline-list p {
        font-family: alibaba,sans-serif;
        margin-top: 10px;
        color: gray;
        font-size: 13px;
    }
    div.comment .dialog-form {
    }
    div.comment .dialog-form .dialog-form-item {
        margin-left: -10%;
    }
    div.comment .form-item {
        margin-top: 3%;
    }
    div.comment .btn-clear {
        margin-top: 3%;
        margin-right: 3%;

    }
    div.comment .btn-submit {
        margin-top: 3%;
        margin-left: 3%;
    }
    @media only screen and (max-width: 986px) {
        div.comment .fun-btn {
            display: none;
        }
    }

    @media only screen and (min-width: 768px) and (max-width: 1024px){
       div.comment .total-comments {
           margin-top: 5%;
       }
    }
</style>
