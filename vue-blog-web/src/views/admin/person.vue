<template>
  <div style="background: white">
    <el-container>
      <el-header style="padding: 0;margin: 0">
        <top></top>
      </el-header>
      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px">
          <left></left>
        </el-aside>
        <el-main class="main-wrap">
          <el-container>
            <el-header>
              <el-page-header content="个人中心" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row :gutter="20">
                <el-col :span="6">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <span>
                          <el-badge :value="personValueVO.webcomes.length" class="item">
                            <el-button>新的留言</el-button>
                          </el-badge>
                        </span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="6">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <span>
                          <el-badge :value="personValueVO.comments.length" class="item">
                            <el-button>有新的评论</el-button>
                          </el-badge>
                        </span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="12">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <span>
                          <el-button @click="dialogFormVisible_one = true">换头像</el-button>
                          <el-button @click="dialogFormVisible_two = true">换昵称</el-button>
                          <el-button @click="dialogFormVisible_three = true">换签名</el-button>
                        </span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <!-- 内容 -->
              <el-row :gutter="20" style="margin-top: 15px">
                <el-col :span="24">
                  <el-table :data="tableData">
                    <el-table-column label="ID" prop="id"></el-table-column>
                    <el-table-column label="登入时间" prop="loginDate"></el-table-column>
                    <el-table-column label="退出时间" prop="loginDate"></el-table-column>
                    <el-table-column label="设备类型" prop="typeDevice"></el-table-column>
                    <el-table-column label="登录IP地址" prop="addressIP"></el-table-column>
                  </el-table>
                </el-col>
              </el-row>
              <!-- 分页 -->
              <el-row style="margin-top: 20px">
                <el-col :offset="8" :span="8" style="margin-top: 25px;text-align: center">
                  <el-pagination
                    background layout="prev, pager, next"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    :total="total"
                    @current-change="currentChange">
                  </el-pagination>
                </el-col>
              </el-row>
              <!-- 修改头像地址框 -->
              <el-dialog title="修改头像" :visible.sync="dialogFormVisible_one">
                <el-form>
                  <el-form-item label="可选操作" :label-width="formLabelWidth">
                    <input accept="image/*" type="file" name="image" @change="getFile($event)" />
                    <el-button style="margin-left: 10px;" size="small" type="success" @click="changeAvatar">开始上传</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_one = false">取 消</el-button>
                  <el-button type="primary" @click="dialogFormVisible_one = false">确 定</el-button>
                </div>
              </el-dialog>
              <!-- 修改昵称 -->
              <el-dialog title="修改昵称" :visible.sync="dialogFormVisible_two">
                <el-form>
                  <el-form-item label="新的昵称" :label-width="formLabelWidth">
                    <el-input v-model="nickname"  autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth">
                    <el-button @click="changeNickname" type="success" size="mini" autocomplete="off">提交</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_two = false">取 消</el-button>
                  <el-button type="primary" @click="dialogFormVisible_two = false">确 定</el-button>
                </div>
              </el-dialog>
              <!-- 修改签名 -->
              <el-dialog title="修改签名" :visible.sync="dialogFormVisible_three">
                <el-form>
                  <el-form-item label="新的签名" :label-width="formLabelWidth">
                    <el-input v-model="description"  autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item :label-width="formLabelWidth">
                    <el-button @click="changeDescription" type="success" size="mini" autocomplete="off">提交</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_three = false">取 消</el-button>
                  <el-button type="primary" @click="dialogFormVisible_three = false">确 定</el-button>
                </div>
              </el-dialog>
            </el-main>
          </el-container>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import top from "../../components/admin/top"
  import left from "../../components/admin/left"
  export default {
    name: "person",
    components: {
      top,
      left
    },
    data() {
      return {
        formLabelWidth: '120px',
        currentPage: 1,  //当前所在页
        total: 0,  //数据量
        pageSize: 5,  //一页显示多少条记录
        nickname: '',
        description: '',
        labelWidth: 120,
        dialogFormVisible_one: false,  //是否打开对话框
        dialogFormVisible_two: false,
        dialogFormVisible_three: false,
        tableData: [],
        file: null,  //前台上传的文件暂时保存在这里
        personValueVO: ''
      }
    },
    mounted() {
      this.getLogAndPage(1)
      this.getMessage()
    },
    methods:{
      //获取新留言与评论
      getMessage() {
        const _this = this
        this.$axios.get('webcomes/unrep', {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.personValueVO = resp.data.data
        })
      },
      //登录日志分页
      getLogAndPage(currentPage) {
        const _this = this
        const url = 'log?currentPage=' + this.currentPage
        this.$axios.get(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp=>{
          _this.tableData = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //获取文件对象
      getFile(event) {
        this.file = event.target.files[0];
      },
      //执行上传操作
      changeAvatar() {
        const _this = this
        const url = 'user'
        let formData = new FormData()
        formData.append('image', this.file)
        this.$axios.post(url, formData, {
          headers: {
            "Authorization": localStorage.getItem("token"),
            "Content-Type": "multipart/form-data"
          }
        }).then(resp => {
          const data = resp.data.data;
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          });
          _this.file = null
        })
      },
      //更换昵称
      changeNickname() {
        const _this = this
        if(this.nickname.length === 0)
          return;
        const url = 'user/nickname?nickname=' + this.nickname
        this.$axios.put(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp=>{
          _this.$notify({
            title: '成功',
            message: '修改成功',
            type: 'success'
          });
        })
      },
      //更换简介
      changeDescription() {
        const _this = this
        if(this.description.length === 0)
          return;
        const url = 'user/description?description=' + this.description
        this.$axios.put(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp=>{
          _this.$notify({
            title: '成功',
            message: '修改成功',
            type: 'success'
          });
        })
      },
      //翻页
      currentChange(currentPage){
        this.currentPage = currentPage;
        this.loading = true;
        this.getLogAndPage(currentPage);
      },
    }
  }
</script>

<style scoped>
  .el-card {
    border-top: solid 4px dodgerblue;
  }
  .el-card:hover {
    border: solid 1px lightgray;
  }
.table-info tr {
  width: 75%;
  border: solid 1px;
  text-align: center;
}
.table-info tr td {
  width: 15%;
}
.wrap-info {
  text-align: center;
}
.wrap-item {
  display: inline-block;
}
.wrap-item i {
  font-size: 18px;
  color: #6c6767;
}
.wrap-item i:hover {
  color: #64dc28;
}
.wrap-item span {
  font-size: 18px;
  font-weight: 600;
  font-family: alibaba,sans-serif;
  color: #6f6969;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
