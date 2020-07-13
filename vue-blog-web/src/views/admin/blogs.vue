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
              <el-page-header content="文章管理" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <!-- 删除等操作 -->
              <el-row>
                <el-col :span="6">
                  <el-button size="small" type="warning" @click="toggleSelection">取消选择</el-button>
                  <el-button size="small" type="danger" @click="removeSelBatch">批量删除</el-button>
                  <el-button @click="jump" size="small" type="primary">写文章</el-button>
                </el-col>
                <el-col :span="9">
                  <el-input size="small" placeholder="请输入搜索内容"></el-input>
                </el-col>
              </el-row>
              <!-- 文章操作 -->
              <el-tabs type="border-card" style="margin-top: 25px" v-model="activeName" @tab-click="handleClick(activeName)">
                <el-tab-pane label="全部文章" name="first">
                  <el-row style="margin-top: 20px">
                    <el-col :span="24">
                      <el-table border ref="multipleTable" :data="blogs.slice((currentPage-1)*pageSize,currentPage*pageSize)" tooltip-effect="dark"
                                style="width: 100%" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="statue" label="状态" width="70">
                          <template slot-scope="scope">
                            <el-tag size="mini" effect="dark" :type="scope.row.statue === 1? 'success': (scope.row.statue === 0? 'warning' : 'danger')">
                              {{ scope.row.statue === 1 ?'发布': (scope.row.statue === 0) ? '草稿': '下架' }}
                            </el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column prop="createDate" label="创建日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.createDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="updateDate" label="修改日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.updateDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="createDate" label="文章名称" width="550">
                          <template slot-scope="scope">
                            {{ scope.row.title }}
                          </template>
                        </el-table-column>
                        <el-table-column prop="tid" label="分类于" width="180">
                          <template slot-scope="scope">
                            {{ scope.row.type.name }}
                          </template>
                        </el-table-column>
                        <el-table-column label="操作" width="150" fixed="right">
                          <template slot-scope="scope">
                            <el-button :disabled="scope.row.statue === -1 || scope.row.statue === 1" @click="handEdit(scope.$index, scope.row)" size="mini" type="primary">编辑</el-button>
                            <el-button :disabled="scope.row.statue === -1" @click="deleteBlog(scope.$index, scope.row)" size="mini" type="danger" >下架</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </el-col>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="已发表" name="second">
                  <el-row style="margin-top: 20px">
                    <el-col :span="24">
                      <el-table border ref="multipleTable" :data="blogs.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                                tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="statue" label="状态" width="70">
                          <template slot-scope="scope">
                            <el-tag size="mini" effect="dark" :type="scope.row.statue === 1? 'success': 'warning'">
                              {{ scope.row.statue === 1 ?'发布': (scope.row.statue === 0) ? '草稿': '下架' }}
                            </el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column prop="recommend" label="推荐" width="70" style="color: rebeccapurple">
                          <template slot-scope="scope">
                            <el-tag size="mini" effect="dark" :type="scope.row.recommend === true? 'success': 'warning'">
                              {{ scope.row.recommend === true ?'已推': '普通' }}
                            </el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column prop="createDate" label="创建日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.createDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="updateDate" label="更新日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.updateDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="title" label="文章名称" width="374">
                          <template slot-scope="scope">
                            {{ scope.row.title | ellipsis }}
                          </template>
                        </el-table-column>
                        <el-table-column prop="tid" label="分类于" width="180">
                          <template slot-scope="scope">
                            {{ scope.row.type.name | ellipsis}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="views" label="阅读量" width="100">
                          <template slot-scope="scope">
                            {{ scope.row.views }}
                          </template>
                        </el-table-column>
                        <el-table-column label="操作" width="280" fixed="right">
                          <template slot-scope="scope">
                            <el-button @click="commonIndex(scope.$index, scope.row)" size="mini" type="success">是否推荐</el-button>
                            <el-button @click="recoverBlog(scope.$index, scope.row)" size="mini" type="primary">恢复为草稿</el-button>
                            <el-button @click="deleteBlog(scope.$index, scope.row)" size="mini" type="danger" >下架</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </el-col>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="草稿箱" name="third">
                  <el-row style="margin-top: 20px">
                    <el-col :span="24">
                      <el-table border ref="multipleTable" :data="blogs.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                                tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="statue" label="状态" width="70">
                          <template slot-scope="scope">
                            <el-tag size="mini" effect="dark" :type="scope.row.statue === 1? 'success': 'warning'">
                              {{ scope.row.statue === 1 ?'发布': (scope.row.statue === 0) ? '草稿': '下架' }}
                            </el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column prop="createDate" label="创建日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.createDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="updateDate" label="更新日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.updateDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="title" label="文章名称" width="374">
                          <template slot-scope="scope">
                            {{ scope.row.title | ellipsis }}
                          </template>
                        </el-table-column>
                        <el-table-column prop="tid" label="分类于" width="180">
                          <template slot-scope="scope">
                            {{ scope.row.type.name | ellipsis}}
                          </template>
                        </el-table-column>
                        <el-table-column label="操作" fixed="right">
                          <template slot-scope="scope">
                            <el-button @click="handEdit(scope.$index, scope.row)" size="mini" type="primary">编辑</el-button>
                            <el-button @click="deleteBlog(scope.$index, scope.row)" size="mini" type="danger" >下架</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </el-col>
                  </el-row>
                </el-tab-pane>
                <el-tab-pane label="回收站" name="four">
                  <el-row style="margin-top: 20px">
                    <el-col :span="24">
                      <el-table border ref="multipleTable" :data="blogs.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                                tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="statue" label="状态" width="70">
                          <template slot-scope="scope">
                            <el-tag size="mini" effect="dark" :type="scope.row.statue === 1? 'success': (scope.row.statue === 0? 'warning' : 'danger')">
                              {{ scope.row.statue === 1 ?'发布': (scope.row.statue === 0) ? '草稿': '下架' }}
                            </el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column prop="createDate" label="创建日期" width="165">
                          <template slot-scope="scope">
                            {{ scope.row.createDate}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="title" label="文章名称" width="374">
                          <template slot-scope="scope">
                            {{ scope.row.title | ellipsis}}
                          </template>
                        </el-table-column>
                        <el-table-column prop="tid" label="分类于" width="180">
                          <template slot-scope="scope">
                            {{ scope.row.type.name | ellipsis}}
                          </template>
                        </el-table-column>
                        <el-table-column label="操作" fixed="right">
                          <template slot-scope="scope">
                            <el-button @click="removeBlog(scope.$index, scope.row)" size="mini" type="danger">删除</el-button>
                            <el-button @click="recoverBlog(scope.$index, scope.row)" size="mini" type="primary">恢复为草稿</el-button>
                            <el-button @click="publishBlog(scope.$index, scope.row)" size="mini" type="success">上架</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </el-col>
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
    name: "blogs",
    components: {
      top,
      left
    },
    data() {
      return {
        multipleSelection: [],
        dialogFormVisible: false,
        currentPage: 1,  //当前所在页
        total: 0,  //当前请求后台图集的总数据量
        pageSize: 10,  //一页显示多少条记录
        activeName: 'first', //默认显示全部列表
        blogs:[],
        selItems: [],
        dustbinData: []
      }
    },
    mounted() {
      this.getAllBlogAndPage()
    },
    methods: {
      //获取全部文章分类数据
      getAllBlogAndPage() {
        const _this = this
        const url = 'blogs?currentPage=' + this.currentPage
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.blogs = resp.data.data.records
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
            this.getAllBlogAndPage()
            break
          case "second":
            this.getBlogByStatue(1);
            break
          case "third":
            this.getBlogByStatue(0);
            break
          case "four":
            this.getBlogByStatue(-1);
            break
        }
      },
      //不同类型获取文章
      getBlogByStatue(statue) {
        const _this = this
        const url = 'blogs/statue?currentPage=' + this.currentPage + '&statue=' + statue
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.blogs = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //新增文章
      jump() {
        this.$router.push({path: '/admin_blog_input'})
      },
      //推荐文章
      commonIndex(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const bid = row.id
        const url = 'blogs/recommend?bid=' + bid
        this.$axios.put(url, {
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
      },
      //编辑文章
      handEdit(index, row) {
        this.$router.push({path: '/admin_blog_input', query: {bid: row.id}});
      },
      //下架文章
      deleteBlog(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const bid = row.id
        const url = 'blogs?bid=' + bid
        this.$axios.put(url, {
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
      },
      //回收站删除文章
      removeBlog(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const bid = row.id
        const url = 'blogs/delete?bid=' + bid
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
      },
      //回收站恢复为草稿
      recoverBlog(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const bid = row.id
        const url = 'blogs/recover?bid=' + bid
        this.$axios.put(url, {
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
      },
      //回收站直接上架
      publishBlog(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const bid = row.id
        const url = 'blogs/publish?bid=' + bid
        this.$axios.put(url, {
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
      },
      //确认操作
      checkDoLink(){
        const confirmDo = confirm("确认要操作文章吗？")
        if(confirmDo)
          return true
        return false
      },
      //取消选中
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      //选中赋值
      handleSelectionChange(val) {
        this.selItems = val;
      },
      //批量删除
      removeSelBatch() {
        if(! this.checkDoLink())
          return
        const selItems = this.selItems
        for (let i = 0; i < selItems.length; i++) {
          //selItems中存储的是一系列数据，但是我们只需要每一行的id
          this.dustbinData.push(selItems[i].id)
        }
        //转换为字符串发送到后台
        const arr = this.dustbinData.toString()
        const url = 'blog_set?arr=' + arr
        const _this = this
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
</style>
