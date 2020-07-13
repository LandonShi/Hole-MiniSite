<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="detail">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="showDraw">
                <!-- 标题 -->
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="main-inner">
                      <div class="blog-title">
                        <span class="blog-title-info">{{ blog.title}}</span>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <!-- 作者文章信息 -->
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="blog-mini">
                      <span>发表于 {{ blog.createDate | formatDate}}</span>
                      <el-divider direction="vertical"></el-divider>
                      <span>作者 {{blog.user.nickname}}</span>
                      <el-divider direction="vertical"></el-divider>
                      <span>分类于 <a :href="'typeList?tid=' + blog.type.id">{{ blog.type.name}}</a></span>
                      <el-divider direction="vertical"></el-divider>
                      <span>阅读量 {{ blog.views}}</span>
                    </div>
                  </el-col>
                </el-row>
                <!-- 正文内容 -->
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="blog-detail">
                      <div class="content markdown-body" v-html="blog.content"></div>
                    </div>
                  </el-col>
                </el-row>
                <!-- 评论列表 -->
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                    <div class="comment-count">
                      <el-button @click="dialogFormVisible = true" class="sort-btn comment-btn" size="mini" plain circle>
                        <i class="el-icon-chat-dot-square" />
                      </el-button>
                      <span>本文总计 {{blog.comments.length}} 条评论</span>
                    </div>
                    <div class="comment-list">
                      <el-collapse v-model="activeName" accordion v-for="(comment,index) in blog.comments" :key="comment.id">
                        <el-collapse-item :name="comment.id">
                          <template slot="title"> {{comment.nickname}} </template>
                          <div class="list-item">
                            {{comment.content}}
                          </div>
                          <div class="list-item" style="margin-left: 3%" v-for="(reply,index) in comment.replies" :key="reply.id">
                            <span>站长回复:</span>
                            {{reply.content}}
                          </div>
                        </el-collapse-item>
                      </el-collapse>
                    </div>
                  </el-col>
                </el-row>
                <!-- 评论 -->
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="10" :xl="10">
                    <el-dialog title="发表一下你的意见" :visible.sync="dialogFormVisible">
                      <el-form :model="comment" class="dialog-form">
                        <el-form-item class="dialog-form-item" label="邮箱" :label-width="formLabelWidth">
                          <el-input v-model="comment.email" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item class="dialog-form-item" label="昵称" :label-width="formLabelWidth">
                          <el-input v-model="comment.nickname" autocomplete="off" maxlength="10" show-word-limit ></el-input>
                        </el-form-item>
                        <el-form-item class="dialog-form-item" label="内容" :label-width="formLabelWidth">
                          <el-input v-model="comment.content" type="textarea" resize="none" maxlength="120" show-word-limit :rows="4"></el-input>
                        </el-form-item>
                      </el-form>
                      <div slot="footer" class="dialog-footer">
                        <el-button size="mini" @click="dialogFormVisible = false">取 消</el-button>
                        <el-button size="mini" type="primary" @click="postComment">提交</el-button>
                      </div>
                    </el-dialog>
                  </el-col>
                </el-row>
                <!-- 评论 响应式 -->
                <el-row class="hidden-md-and-up" :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="10" :xl="10">
                    <el-input class="form-item" v-model="comment.email">
                      <template class="item-info" slot="prepend">邮箱</template>
                    </el-input>
                    <el-input class="form-item" v-model="comment.nickname" maxlength="10" show-word-limit>
                      <template class="item-info" slot="prepend">昵称</template>
                    </el-input>
                    <el-input class="form-item" v-model="comment.content" maxlength="120" show-word-limit >
                      <template class="item-info" slot="prepend">内容</template>
                    </el-input>
                    <div style="text-align: center;margin-top: 3%">
                      <el-button class="btn-clear" size="small">重置</el-button>
                      <el-button class="btn-submit"size="small" @click="postComment">提交</el-button>
                    </div>
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
  import Tool from '../components/tool'
  import Header from "../components/header"
  import Footer from "../components/footer"
  import 'github-markdown-css/github-markdown.css' // 然后添加样式markdown-body
  export default {
    name: 'Detail',
    components: {
      Top,
      Header,
      Tool,
      Footer
    },
    data() {
      return {
        blog: '',
        dialogFormVisible: false,
        formLabelWidth: '120px',
        comment: {
          email:'',
          nickname:'',
          content:'',
          bid:''
        },
        activeName:'',
        replies:[],
        showDraw: false
      }
    },
    mounted() {
      this.getBlog()
      this.addView()
      this.change()
    },
    methods: {
      change() {
        this.showDraw = true
      },
      //文章详情
      getBlog() {
        const bid = this.$route.query.bid;
        const _this = this
        const url = 'blogs/' + bid
        this.$axios.get(url).then(resp => {
          _this.blog = resp.data.data
          const MarkdownIt = require('markdown-it'),
          md = new MarkdownIt();
          _this.blog.content = md.render(_this.blog.content)
        })
      },
      //++
      addView() {
        const bid = this.$route.query.bid;
        const _this = this
        const url = 'views/' + bid
        this.$axios.post(url).then(resp => {
          const data = resp.data.data
          console.log(data)
        })
      },
      //发送评论
      postComment() {
        if(this.comment.email.length === 0) {
          this.$notify({
            title: '失败',
            message: '邮箱为空',
            type: 'error'
          })
          return
        }
        const myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
        if(!myReg.test(this.comment.email)) {
          this.$notify({
            title: '失败',
            message: '邮箱格式错误',
            type: 'error'
          })
          return
        }
        if(this.comment.nickname.length === 0) {
          this.$notify({
            title: '失败',
            message: '昵称为空',
            type: 'error'
          })
          return
        }
        if(this.comment.content.length === 0) {
          this.$notify({
            title: '失败',
            message: '内容为空',
            type: 'error'
          })
          return
        }
        this.comment.bid = this.$route.query.bid;
        const _this = this
        this.$axios.post('comments', this.comment).then(resp => {
          const data = resp.data.data;
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          })
          _this.comment.email = null
          _this.comment.nickname = null
          _this.comment.content = null
        })
      }
    },
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
    div.detail {
        margin-top: 19%;
    }
    div.detail .main-inner {
        text-align: center;
    }
    div.detail .main-inner .blog-title {
        margin: 0 auto;
    }
    div.detail .main-inner .blog-title .blog-title-info {
        font-size: 26px;
        font-weight: 600;
        font-family: alibaba,serif;
    }

    div.detail .blog-mini {
        margin: 14px auto;
        text-align: center;
    }
    div.detail .blog-mini span {
        color: gray;
        font-size: 12px;
        font-family: alibaba,serif;
    }
    div.detail .blog-mini a {
        color: gray;
    }

    div.detail .blog-detail {
        margin: 60px auto;
    }
    div.detail .blog-detail p {
        color: gray;
        font-size: 14px;
        line-height: 25px;
    }

    div.detail .comment-count {
        border-left: solid 7px #aaa3a3;
        margin-top: 6%;
    }
    div.detail .comment-count span {
        margin-left: 1%;
        font-family: alibaba,sans-serif;
        font-size: 14px;
        font-weight: 600;
    }
    div.detail .comment-count .sort-btn {
        margin-left: 2%;
    }

    div.detail .comment-list {
        margin: 5%;
        font-family: alibaba,sans-serif;
    }
    div.detail .comment-list .list-item {
        margin-top: 4%;
    }
    div.detail .comment-list .list-item span {
        color: orangered;
        font-weight: 600;
        font-size: 13px;
    }

    .form-item {
      margin-top: 2%;
    }

    @media only screen and (max-width: 986px) {
      .comment-btn {
        display: none;
      }
    }
</style>
