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
              <el-page-header content="分类管理" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row>
                <el-col :span="6">
                  <el-button :disabled="this.selItems.length === 0" @click="toggleSelection" size="small" type="warning">取消选择</el-button>
                  <el-button :disabled="this.selItems.length === 0" @click="removeSelBatch" size="small" type="danger">批量删除</el-button>
                  <el-button @click="dialogFormVisible_type_add = true" size="small" type="success">添加分类</el-button>
                </el-col>
                <el-col :span="9">
                  <el-input size="small" placeholder="请输入搜索内容"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-top: 20px">
                <el-table border :data="types.slice((currentPage-1)*pageSize,currentPage*pageSize)" style="width: 100%" tooltip-effect="dark"
                          @selection-change="handleSelectionChange" ref="multipleTable" v-loading="loading">
                  <!-- 数据展示与操作 -->
                  <el-table-column width="55" type="selection"></el-table-column>
                  <el-table-column width="80" label="分类编号">
                    <template slot-scope="scope">
                      <span>{{ scope.row.id}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="180" label="创建时间" prop="createDate">
                    <template slot-scope="scope">
                      <span>{{ scope.row.createDate}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="300" label="分类名称" prop="name"></el-table-column>
                  <el-table-column width="80" label="应用项目" prop="blogList">
                    <template slot-scope="scope">
                      <span>{{ scope.row.blogList.length}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="543" label="可选操作" fixed="right">
                    <template slot-scope="scope">
                      <el-button @click="handleEdit(scope.$index, scope.row)" size="mini" type="primary">编辑</el-button>
                      <el-button @click="deleteType(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-row>
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
              <!-- 增加分类 -->
              <el-dialog title="新增分类" :visible.sync="dialogFormVisible_type_add">
                <el-form>
                  <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="type.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="操作" :label-width="formLabelWidth">
                    <el-button @click="addType" type="success" size="mini" autocomplete="off">提交</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_type_add = false">取 消</el-button>
                  <el-button @click="dialogFormVisible_type_add = false" type="primary">确 定</el-button>
                </div>
              </el-dialog>
              <!-- 修改分类 -->
              <el-dialog title="修改分类" :visible.sync="dialogFormVisible_type_put">
                <el-form>
                  <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="type.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="操作" :label-width="formLabelWidth">
                    <el-button @click="updateType" type="success" size="mini" autocomplete="off">更 新</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_type_put = false">取 消</el-button>
                  <el-button type="primary" @click="dialogFormVisible_type_put = false">确 定</el-button>
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
      name: "category",
      components: {
        top,
        left
      },
      data() {
        return {
          dialogFormVisible_type_add: false,
          dialogFormVisible_type_put: false,
          dialogFormVisible: false,
          dialogVisible: false,
          disabled: false,
          formLabelWidth: '120px',
          currentPage: 1,  //当前所在页
          total: 0,  //当前请求后台图集的总数据量
          pageSize: 5,  //一页显示多少条记录
          type: { id:0, name:''},
          types: [],
          selItems: [],
          dustbinData: []
        }
      },
      mounted() {
        this.getTypeAndPage()
      },
      methods: {
        //获取分页数据
        getTypeAndPage() {
          const _this = this
          const url = 'types?currentPage=' + this.currentPage
          this.$axios.get(url, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            _this.types = resp.data.data.records
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
        //增加分类
        addType() {
          const _this = this
          const url = 'types'
          this.$axios.post(url, this.type, {
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
            _this.type.name = null
            _this.getTypeAndPage(1)
          })
        },
        //删除分类
        deleteType(index, row) {
          if(! this.checkDeleteLink())
            return
          const _this = this
          const tid = row.id
          const url = 'types?tid=' + tid
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
            _this.getTypeAndPage(1)
          })
        },
        //打开编辑分类模态框
        handleEdit(index, row) {
          const _this = this
          const tid = row.id
          const url = 'types/' + tid
          this.$axios.get(url, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            _this.type = resp.data.data
            _this.dialogFormVisible_type_put = true
          })
        },
        //更新分类
        updateType() {
          const _this = this
          const url = 'types'
          this.$axios.put(url, this.type, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(resp => {
            const data = resp.data.data;
            _this.$notify({
              title: '成功',
              message: data,
              type: 'success'
            });
            _this.type.name = null
            _this.getTypeAndPage(1)
          })
        },
        //确认是否要删除
        checkDeleteLink(){
          const confirmDelete = confirm("确认要删除？")
          if(confirmDelete)
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
          if(! this.checkDeleteLink())
            return
          const selItems = this.selItems
          for (let i = 0; i < selItems.length; i++) {
            //selItems中存储的是一系列数据，但是我们只需要每一行的id
            this.dustbinData.push(selItems[i].id)
          }
          //转换为字符串发送到后台
          const arr = this.dustbinData.toString()
          const url = 'typeset?arr=' + arr
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
            _this.getTypeAndPage(1)
          })
        }
      }
    }
</script>

<style scoped>

</style>
