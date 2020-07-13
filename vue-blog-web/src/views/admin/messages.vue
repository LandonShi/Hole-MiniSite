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
              <el-page-header content="留言管理" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row>
                <el-col :span="4">
                  <el-button size="small" type="warning">取消选择</el-button>
                  <el-button size="small" type="danger">批量删除</el-button>
                </el-col>
                <el-col :span="9">
                  <el-input size="small" placeholder="请输入搜索内容"></el-input>
                </el-col>
              </el-row>
              <el-tabs type="border-card" style="margin-top: 25px" v-model="activeName" @tab-click="handleClick(activeName)">
                <el-tab-pane label="全部留言" name="first">
                  <el-row style="margin-top: 20px">
                    <el-table border :data="webcomes" ref="multipleTable" tooltip-effect="dark" style="width: 100%">
                      <!-- 数据展示与操作 -->
                      <el-table-column type="selection" width="50"></el-table-column>
                      <el-table-column width="60" label="编号" prop="statue">
                        <template slot-scope="scope">
                          {{ scope.row.id }}
                        </template>
                      </el-table-column>
                      <el-table-column width="70" label="状态" prop="statue">
                        <template slot-scope="scope">
                          <el-tag size="mini" effect="dark" :type="scope.row.statue === true? 'success': 'danger'">
                            {{ scope.row.statue === true ?'已回': '未回' }}
                          </el-tag>
                        </template>
                      </el-table-column>
                      <el-table-column width="165" label="时间" prop="createDate">
                        <template slot-scope="scope">
                          {{ scope.row.createDate}}
                        </template>
                      </el-table-column>
                      <el-table-column width="685" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="177" label="操作">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="未回复" name="second">
                  <el-row style="margin-top: 20px">
                    <el-table @expand-change="expandChange" border ref="multipleTable" :data="webcomes" tooltip-effect="dark"
                              style="width: 100%" :expand-row-keys="expands" :row-key='getRowKeys'>
                      <!-- 展开行 -->
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="昵称： ">
                              <span>
                                {{webcom.nickname}}
                              </span>
                            </el-form-item>
                            <el-form-item label="邮箱： ">
                              <span>
                                {{webcom.email}}
                              </span>
                            </el-form-item>
                            <el-form-item label="内容： ">
                              <span>
                                {{webcom.content}}
                              </span>
                            </el-form-item>
                            <el-form-item label="操作： ">
                              <span>
                                <el-row :gutter="20">
                                  <el-col :span="12">
                                    <el-button @click="postReply(webcom.id)" size="mini" type="primary">提交回复</el-button>
                                  </el-col>
                                </el-row>
                              </span>
                            </el-form-item>
                            <el-input v-model="webrep.content" resize="none" style="margin-top: 15px" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
                          </el-form>
                        </template>
                      </el-table-column>
                      <!-- 数据展示与操作 -->
                      <el-table-column type="selection" width="50"></el-table-column>
                      <el-table-column width="60" label="编号" prop="statue">
                        <template slot-scope="scope">
                          {{ scope.row.id }}
                        </template>
                      </el-table-column>
                      <el-table-column width="70" label="状态" prop="statue">
                        <template slot-scope="scope">
                          <el-tag size="mini" effect="dark" :type="scope.row.statue === true? 'success': 'danger'">
                            {{ scope.row.statue === true ?'已回': '未回' }}
                          </el-tag>
                        </template>
                      </el-table-column>
                      <el-table-column width="165" label="时间" prop="createDate">
                        <template slot-scope="scope">
                          {{ scope.row.createDate}}
                        </template>
                      </el-table-column>
                      <el-table-column width="685" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="127" label="操作">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="已回复" name="third">
                  <el-row style="margin-top: 20px">
                    <el-table @expand-change="expandChange" border ref="multipleTable" :data="webcomes"
                              tooltip-effect="dark"
                              style="width: 100%" :expand-row-keys="expands" :row-key='getRowKeys'>
                      <!-- 展开行 -->
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="昵称： ">
                              <span>
                                {{webcom.nickname}}
                              </span>
                            </el-form-item>
                            <el-form-item label="邮箱： ">
                              <span>
                                {{webcom.email}}
                              </span>
                            </el-form-item>
                            <el-form-item label="内容： ">
                              <span>
                                {{webcom.content}}
                              </span>
                            </el-form-item>
                            <el-form-item label="站长回复： ">
                             <span style="color: orangered" v-for="(rep,index) in webcom.webreps" :key="index">
                               {{rep.content}} <br>
                             </span>
                            </el-form-item>
                            <el-form-item label="操作： ">
                              <span>
                                <el-row :gutter="20">
                                  <el-col :span="12">
                                    <el-button @click="postReply(webcom.id)" size="mini" type="primary">提交回复</el-button>
                                  </el-col>
                                </el-row>
                              </span>
                            </el-form-item>
                            <el-input v-model="webrep.content" resize="none" style="margin-top: 15px" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
                          </el-form>
                        </template>
                      </el-table-column>
                      <!-- 数据展示与操作 -->
                      <el-table-column type="selection" width="50"></el-table-column>
                      <el-table-column width="60" label="编号" prop="statue">
                        <template slot-scope="scope">
                          {{ scope.row.id }}
                        </template>
                      </el-table-column>
                      <el-table-column width="70" label="状态" prop="statue">
                        <template slot-scope="scope">
                          <el-tag size="mini" effect="dark" :type="scope.row.statue === true? 'success': 'danger'">
                            {{ scope.row.statue === true ?'已回': '未回' }}
                          </el-tag>
                        </template>
                      </el-table-column>
                      <el-table-column width="165" label="时间" prop="createDate">
                        <template slot-scope="scope">
                          {{ scope.row.createDate}}
                        </template>
                      </el-table-column>
                      <el-table-column width="685" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="100" label="操作" fixed="right">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
              </el-tabs>
              <!-- 分页 -->
              <el-row style="margin-top: 20px">
                <el-col :offset="8" :span="8" style="margin-top: 25px;text-align: center">
                  <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                  </el-pagination>
                </el-col>
              </el-row>
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
    name: "messages",
    components: {
      top,
      left
    },
    data() {
      return {
        multipleTable: [],
        activeName: 'first',
        currentPage: 1,  //当前所在页
        total: 0,  //当前请求后台图集的总数据量
        pageSize: 10,  //一页显示多少条记录
        webcomes:[],
        expands: [],//只展开一行放入当前行id,
        webcom: {
          id: 0,
          content: ''
        },
        webrep: {
          id: 0,
          content: '',
          comID:''
        }
      }
    },
    mounted() {
      this.getWebcomList()
    },
    methods: {
      //分页-改变每页显示记录条数
      handleSizeChange(val) {
        this.currentPage = 1;
        this.pageSize = val;
      },
      //分页-翻页
      handleCurrentChange(val) {
        this.currentPage = val;
      },
      //TAB栏事件
      handleClick(activeName) {
        switch (activeName) {
          case "first":
            this.getWebcomList()
            break
          case "second":
            this.getUnReply(0);
            break
          case "third":
            this.getUnReply(1);
            break
        }
      },
      //获取评论列表
      getWebcomList() {
        const _this = this
        const url = 'webcomes?currentPage=' + this.currentPage
        this.$axios.get(url).then(resp => {
          _this.webcomes = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //发送站长评论
      postReply(comID) {
        if(this.webrep.content.length===0) {
          alert("输入为空")
          return
        }
        this.webrep.comID = comID
        const _this = this
        this.$axios.post('webrepes',this.webrep,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          const data = resp.data.data;
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          })
          _this.webrep.content = null
        })
      },
      //状态列表
      getUnReply(statue) {
        const _this = this
        const url = 'webcomes/statue?currentPage=' + this.currentPage + "&statue=" + statue
        this.$axios.get(url).then(resp => {
          _this.webcomes = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //获取每一行的展开id
      getRowKeys(row){
        return row.id
      },
      //展开事件
      expandChange(row, expandedRows) {
        this.webcom.id = row.id
        const _this = this
        //只展开一行
        if (expandedRows.length) {//说明展开了
          _this.expands = []
          if (row)
            _this.expands.push(row.id)//只展开当前行id
        } else  //说明收起了
          _this.expands = []
        const comid = row.id
        const url = 'webcomes/info/' + comid
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.webcom = resp.data.data
        })
      },
      //确认操作
      checkDoLink(){
        const confirmDo = confirm("确认执行操作吗？")
        if(confirmDo)
          return true
        return false
      },
      //删除回复
      deleteComment(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const comID = row.id
        const url = 'webcomes/' + comID
        this.$axios.delete(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          const data = resp.data.data;
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          })
        })
      }
    }
  }
</script>

<style scoped>
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
    width: 100%;
  }
</style>
