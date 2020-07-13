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
              <el-page-header content="评论管理" style="margin-top: 15px"></el-page-header>
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
                <el-tab-pane label="全部评论" name="first">
                  <el-row style="margin-top: 20px">
                    <el-table @expand-change="expandChange" border ref="multipleTable" :data="comments" tooltip-effect="dark"
                              style="width: 100%" :expand-row-keys="expands" :row-key='getRowKeys'>
                      <!-- 展开行 -->
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="昵称： ">
                              <span>
                                {{comment.nickname}}
                              </span>
                            </el-form-item>
                            <el-form-item label="邮箱： ">
                              <span>
                                {{comment.email}}
                              </span>
                            </el-form-item>
                            <el-form-item label="内容： ">
                              <span>
                                {{comment.content}}
                              </span>
                            </el-form-item>
                            <el-form-item v-if="comment.statue === true"  label="站长回复： ">
                              <span style="color: orangered" v-for="(reply,index) in comment.replies" :key="reply.id">
                                {{reply.content}} <br>
                              </span>
                            </el-form-item>
                            <el-form-item label="操作： ">
                              <span>
                                <el-row :gutter="20">
                                  <el-col :span="12">
                                    <el-button @click="postReply(comment.id)" size="mini" type="primary">提交回复</el-button>
                                  </el-col>
                                </el-row>
                              </span>
                            </el-form-item>
                            <el-input v-model="reply.content" resize="none" style="margin-top: 15px" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
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
                      <el-table-column width="374" label="文章名称" prop="title">
                        <template slot-scope="scope">
                          {{ scope.row.blog.title | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="485" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="280" label="操作" fixed="right">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                          <el-button @click="jump(scope.row.blog.id)" size="mini" type="primary">查看文章</el-button>
                          <el-button @click="defaultRe(scope.row.blog.id, scope.row)" size="mini" type="warning">默认回复</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="未回复" name="second">
                  <el-row style="margin-top: 20px">
                    <el-table @expand-change="expandChange" border ref="multipleTable" :data="comments.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                              tooltip-effect="dark"
                              style="width: 100%" :expand-row-keys="expands" :row-key='getRowKeys'>
                      <!-- 展开行 -->
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="昵称： ">
                              <span>
                                {{comment.nickname}}
                              </span>
                            </el-form-item>
                            <el-form-item label="邮箱： ">
                              <span>
                                {{comment.email}}
                              </span>
                            </el-form-item>
                            <el-form-item label="内容： ">
                              <span>
                                {{comment.content}}
                              </span>
                            </el-form-item>
                            <el-form-item label="操作： ">
                              <span>
                                <el-row :gutter="20">
                                  <el-col :span="12">
                                    <el-button @click="postReply(comment.id)" size="mini" type="primary">提交回复</el-button>
                                  </el-col>
                                </el-row>
                              </span>
                            </el-form-item>
                            <el-input v-model="reply.content" resize="none" style="margin-top: 15px" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
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
                      <el-table-column width="374" label="文章名称" prop="title">
                        <template slot-scope="scope">
                          {{ scope.row.blog.title | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="485" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="280" label="操作" fixed="right">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                          <el-button @click="jump(scope.row.blog.id)" size="mini" type="primary">查看文章</el-button>
                          <el-button @click="defaultRe(scope.row.blog.id, scope.row)" size="mini" type="warning">默认回复</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="已回复" name="third">
                  <el-row style="margin-top: 20px">
                    <el-table @expand-change="expandChange" border ref="multipleTable" :data="comments" tooltip-effect="dark"
                              style="width: 100%" :expand-row-keys="expands" :row-key='getRowKeys'>
                      <!-- 展开行 -->
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="昵称： ">
                              <span>
                                {{comment.nickname}}
                              </span>
                            </el-form-item>
                            <el-form-item label="邮箱： ">
                              <span>
                                {{comment.email}}
                              </span>
                            </el-form-item>
                            <el-form-item label="内容： ">
                              <span>
                                {{comment.content}}
                              </span>
                            </el-form-item>
                            <el-form-item label="站长回复： ">
                             <span style="color: orangered" v-for="(reply,index) in comment.replies" :key="reply.id">
                               {{reply.content}} <br>
                             </span>
                            </el-form-item>
                            <el-form-item label="操作： ">
                              <span>
                                <el-row :gutter="20">
                                  <el-col :span="12">
                                    <el-button @click="postReply(comment.id)" size="mini" type="primary">提交回复</el-button>
                                  </el-col>
                                </el-row>
                              </span>
                            </el-form-item>
                            <el-input v-model="reply.content" resize="none" style="margin-top: 15px" type="textarea" :rows="2" placeholder="请输入内容"></el-input>
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
                      <el-table-column width="374" label="文章名称" prop="title">
                        <template slot-scope="scope">
                          {{ scope.row.blog.title | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="485" label="内容" prop="content">
                        <template slot-scope="scope">
                          {{ scope.row.content | ellipsis}}
                        </template>
                      </el-table-column>
                      <el-table-column width="280" label="操作" fixed="right">
                        <template slot-scope="scope">
                          <el-button @click="deleteComment(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                          <el-button @click="jump(scope.row.blog.id)" size="mini" type="primary">查看文章</el-button>
                          <el-button @click="defaultRe(scope.row.blog.id, scope.row)" size="mini" type="warning">默认回复</el-button>
                        </template>
                      </el-table-column>
                    </el-table>
                  </el-row>
                </el-tab-pane>
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
              </el-tabs>
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
      name: "comments",
      components: {
        top,
        left
      },
      data() {
        return {
          comments:[],
          activeName: 'first',
          currentPage: 1,  //当前所在页
          total: 0,  //当前请求后台图集的总数据量
          pageSize: 5,  //一页显示多少条记录
          comment:'',
          expands: [],//只展开一行放入当前行id,
          blogID:'',
          reply:{
            content:'',
            cid:'',
            bid:''
          }
        }
      },
      mounted() {
        this.getComment()
      },
      methods:{
        //获取全部列表
        getComment() {
          const _this = this
          const url = 'comments?currentPage=' + this.currentPage
          this.$axios.get(url, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            _this.comments = resp.data.data.records
            _this.currentPage = resp.data.data.current //当前所在页
            _this.total = resp.data.data.total //当前请求后台图集的总数据量
            _this.pageSize = resp.data.data.size  //一页显示多少条记录
          })
        },
        //获取状态列表
        getStatueComment(statue) {
          const _this = this
          const url = 'comments/statue?currentPage=' + this.currentPage + '&statue=' + statue
          this.$axios.get(url, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            _this.comments = resp.data.data.records
            _this.currentPage = resp.data.data.current
            _this.total = resp.data.data.total
            _this.pageSize = resp.data.data.size
          })
        },
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
              this.getComment()
              break
            case "second":
              this.getStatueComment(0);
              break
            case "third":
              this.getStatueComment(1);
              break
          }
        },
        //展开事件
        expandChange(row, expandedRows) {
          this.blogID = row.id
          const _this = this
          //只展开一行
          if (expandedRows.length) {//说明展开了
            _this.expands = []
            if (row)
              _this.expands.push(row.id)//只展开当前行id
          } else  //说明收起了
            _this.expands = []
          const cid = row.id
          const url = 'comments/' + cid
          this.$axios.get(url, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            _this.comment = resp.data.data
          })
        },
        //获取每一行的展开id
        getRowKeys(row){
          return row.id
        },
        //文章跳转
        jump(bid) {
          if(! this.checkDoLink())
            return
          this.$router.push({path: '/detail', query: {bid: bid}});
        },
        //确认操作
        checkDoLink(){
          const confirmDo = confirm("确认执行操作吗？")
          if(confirmDo)
            return true
          return false
        },
        //提交回复
        postReply(cid) {
          if(this.reply.content.length === 0) {
            this.$notify({
              title: '提交失败',
              message: '回复内容为空',
              type: 'error'
            })
            return
          }
          this.reply.bid = this.blogID
          this.reply.cid = cid
          const _this = this
          this.$axios.post("replies", this.reply, {
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
            _this.reply.content = null
            _this.blogID = null
          })
        },
        //默认回复
        defaultRe(cid,row) {
          this.reply.bid = this.blogID
          this.reply.cid = row.id
          this.reply.content = '感谢关注，晚点会来回复您'
          const _this = this
          this.$axios.post("replies", this.reply, {
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
            _this.reply.content = null
            _this.blogID = null
          })
        },
        //删除回复
        deleteComment(index, row) {
          if(! this.checkDoLink())
            return
          const _this = this
          const cid = row.id
          const url = 'comments/' + cid
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
