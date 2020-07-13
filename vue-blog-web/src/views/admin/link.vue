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
              <el-page-header content="友情链接" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row>
                <el-col :span="6">
                  <el-button @click="toggleSelection" size="small" type="warning">取消选择</el-button>
                  <el-button @click="removeSelBatch" size="small" type="danger">批量删除</el-button>
                  <el-button @click="dialogFormVisible_link_add = true" size="small" type="success">添加外链</el-button>
                </el-col>
              </el-row>
              <el-row style="margin-top: 20px">
                <el-table :data="links" style="width: 100%" tooltip-effect="dark"
                          @selection-change="handleSelectionChange" ref="multipleTable" v-loading="loading">
                  <!-- 数据展示与操作 -->
                  <el-table-column width="55" type="selection"></el-table-column>
                  <el-table-column width="70" label="编号">
                    <template slot-scope="scope">
                      <span>{{ scope.row.id}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="100" label="状态">
                    <template slot-scope="scope">
                      <span>{{ scope.row.statue === true ? '启用': '禁用'}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="120" label="创建时间" prop="createDate">
                    <template slot-scope="scope">
                      <span>{{ scope.row.createDate | formatDate}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="300" label="名称" prop="name">
                    <template slot-scope="scope">
                      <span>{{ scope.row.name }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="300" label="地址" prop="address">
                    <template slot-scope="scope">
                      <span>{{ scope.row.address }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column width="270" label="可选操作">
                    <template slot-scope="scope">
                      <el-button @click="handleEdit(scope.$index, scope.row)" size="mini" type="primary">编辑</el-button>
                      <el-button @click="handleStatue(scope.$index, scope.row)" size="mini" type="success">启用 | 禁用</el-button>
                      <el-button @click="deleteLink(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
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
              <!-- 增加链接 -->
              <el-dialog title="新增链接" :visible.sync="dialogFormVisible_link_add">
                <el-form>
                  <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="link.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="链接地址" :label-width="formLabelWidth">
                    <el-input v-model="link.address" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="操作" :label-width="formLabelWidth">
                    <el-button @click="addLink" type="success" size="mini" autocomplete="off">提交</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_link_add = false">取 消</el-button>
                  <el-button @click="dialogFormVisible_link_add = false" type="primary">确 定</el-button>
                </div>
              </el-dialog>
              <!-- 编辑链接 -->
              <el-dialog title="编辑链接" :visible.sync="dialogFormVisible_link_edit">
                <el-form>
                  <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="link.name" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="链接地址" :label-width="formLabelWidth">
                    <el-input v-model="link.address" autocomplete="off"></el-input>
                  </el-form-item>
                  <el-form-item label="操作" :label-width="formLabelWidth">
                    <el-button @click="updateLink" type="success" size="mini" autocomplete="off">提交</el-button>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible_link_edit = false">取 消</el-button>
                  <el-button @click="dialogFormVisible_link_edit = false" type="primary">确 定</el-button>
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
    name: "link",
    components: {
      top,
      left
    },
    data() {
      return {
        multipleSelection: [],
        dialogFormVisible_link_add: false,
        dialogFormVisible_link_edit: false,
        formLabelWidth: '120px',
        currentPage: 1,  //当前所在页
        total: 0,  //当前请求后台图集的总数据量
        pageSize: 5,  //一页显示多少条记录
        link: { id:0, name:'', address:''},
        links: [],
        selItems: [],
        dustbinData: []
      }
    },
    mounted() {
      this.getLinkAndPage(1)
    },
    methods: {
      //获取分页数据
      getLinkAndPage() {
        const _this = this
        const url = 'links?currentPage=' + this.currentPage
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.links = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //翻页事件
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loading = true;
        this.getLinkAndPage(currentPage);
      },
      //增加外链
      addLink() {
        const _this = this
        const url = 'links'
        if(this.link.name.length === 0) {
          alert("请输入名称")
          return
        }
        if(this.link.address.length === 0) {
          alert("请输入地址")
          return
        }
        this.$axios.post(url, this.link, {
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
          _this.link.name = null
          _this.link.address = null
          _this.getLinkAndPage(1)
        })
      },
      //打开编辑外链模态框
      handleEdit(index, row) {
        const _this = this
        const lid = row.id
        const url = 'links/' + lid
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.link = resp.data.data
          _this.dialogFormVisible_link_edit = true
        })
      },
      //修改外链接
      updateLink() {
        const _this = this
        const url = 'links'
        if(this.link.name.length === 0) {
          alert("请输入名称")
          return
        }
        if(this.link.address.length === 0) {
          alert("请输入地址")
          return
        }
        this.$axios.put(url, this.link, {
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
          _this.link.name = null
          _this.link.address = null
          _this.getLinkAndPage(1)
        })
      },
      //启用 / 禁用
      handleStatue(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const lid = row.id
        const url = 'links/' + lid
        this.$axios.put(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          const data = resp.data.data
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          })
          _this.getLinkAndPage(1)
        })
      },
      //删除链接
      deleteLink(index, row) {
        if(! this.checkDoLink())
          return
        const _this = this
        const lid = row.id
        const url = 'links/' + lid
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
          _this.getLinkAndPage(1)
        })
      },
      //确认操作
      checkDoLink(){
        const confirmDo = confirm("确认执行操作吗？")
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
        const url = 'links_set?arr=' + arr
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
          _this.getLinkAndPage(1)
        })
      }
    }
  }
</script>

<style scoped>
</style>
