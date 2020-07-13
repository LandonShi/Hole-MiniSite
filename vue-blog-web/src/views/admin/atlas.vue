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
                <el-page-header content="图集管理" style="margin-top: 15px"></el-page-header>
              </el-header>
              <el-main class="main-item" style="background-color: whitesmoke">
                <el-row>
                  <el-col :span="6">
                    <el-button :disabled="this.selItems.length === 0" @click="toggleSelection" size="small" type="warning">取消选择</el-button>
                    <el-button :disabled="this.selItems.length === 0" @click="removeSelBatch" size="small" type="danger">批量删除</el-button>
                    <el-button @click="dialogFormVisible_atlas_add = true" size="small" type="success">添加图集</el-button>
                  </el-col>
                  <el-col :span="9">
                    <el-input size="small" placeholder="请输入搜索内容"></el-input>
                  </el-col>
                </el-row>
                <el-row style="margin-top: 20px">
                  <el-table @expand-change="expandChange" :data="atlases" style="width: 100%" tooltip-effect="dark"
                            @selection-change="handleSelectionChange" ref="multipleTable" v-loading="loading">
                    <!-- 展开行 -->
                    <el-table-column type="expand">
                      <template slot-scope="props">
                        <el-form v-for="(photo, index) in photos" :key="photo.id" label-position="left" inline class="demo-table-expand">
                          <el-form-item label="图片编号">
                            <span>{{photo.id}}</span>
                          </el-form-item>
                          <el-form-item label="图片名称">
                            <span>{{photo.name}}</span>
                          </el-form-item>
                          <el-form-item label="图片简介">
                            <span>{{photo.description}}</span>
                          </el-form-item>
                          <el-form-item label="可选操作">
                            <span>
                              <el-button @click="showImagePre(photo.id)" size="mini" type="primary">预览</el-button>
                              <el-button @click="deleteBean(photo.id)" size="mini" type="danger">删除</el-button>
                            </span>
                          </el-form-item>
                          <el-divider></el-divider>
                        </el-form>
                      </template>
                    </el-table-column>
                    <!-- 数据展示与操作 -->
                    <el-table-column width="55" type="selection"></el-table-column>
                    <el-table-column width="100" label="图集编号" prop="createDate">
                      <template slot-scope="scope">
                        <span>{{ scope.row.id}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column width="100" label="创建时间" prop="createDate">
                      <template slot-scope="scope">
                        <span>{{ scope.row.createDate | formatDate}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column width="210" label="名称" prop="name"></el-table-column>
                    <el-table-column width="455" label="描述" prop="description">
                      <template slot-scope="scope">
                        <span>{{ scope.row.description | ellipsis}}</span>
                      </template>
                    </el-table-column>
                    <el-table-column width="270" label="操作">
                      <template slot-scope="scope">
                        <el-button @click="openDialogUploadSingle(scope.$index, scope.row)" size="mini" type="success">上传</el-button>
                        <el-button @click="handleEdit(scope.$index, scope.row)" size="mini" type="primary">编辑</el-button>
                        <el-button @click="deleteAtlas(scope.$index, scope.row)" size="mini" type="danger" >删除</el-button>
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
                <!-- 增加图集 -->
                <el-dialog title="新增图集" :visible.sync="dialogFormVisible_atlas_add">
                  <el-form>
                    <el-form-item label="名称" :label-width="formLabelWidth">
                      <el-input v-model="atlas.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="简介" :label-width="formLabelWidth">
                      <el-input type="textarea" v-model="atlas.description" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="操作" :label-width="formLabelWidth">
                      <el-button @click="addAtlas" type="success" size="mini" autocomplete="off">提交</el-button>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_atlas_add = false">取 消</el-button>
                    <el-button type="primary" @click="dialogFormVisible_atlas_add = false">确 定</el-button>
                  </div>
                </el-dialog>
                <!-- 修改图集 -->
                <el-dialog title="修改图集" :visible.sync="dialogFormVisible_atlas_put">
                  <el-form>
                    <el-form-item label="名称" :label-width="formLabelWidth">
                      <el-input v-model="atlas.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="简介" :label-width="formLabelWidth">
                      <el-input type="textarea" v-model="atlas.description" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="操作" :label-width="formLabelWidth">
                      <el-button @click="updateAtlas" type="success" size="mini" autocomplete="off">更 新</el-button>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_atlas_put = false">取 消</el-button>
                    <el-button type="primary" @click="dialogFormVisible_atlas_put = false">确 定</el-button>
                  </div>
                </el-dialog>
                <!-- 上传图片 -->
                <el-dialog drag="true" title="上传图片" :visible.sync="dialogFormVisible_upload_single">
                  <el-form>
                    <el-form-item label="图片名称" :label-width="formLabelWidth">
                      <el-input v-model="photo.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="图片描述" :label-width="formLabelWidth">
                      <el-input type="textarea" v-model="photo.description" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="可选操作" :label-width="formLabelWidth">
                      <input id="categoryPic" accept="image/*" type="file" name="image" @change="getFile($event)" />
                      <el-button style="margin-left: 10px;" size="small" type="success" @click="uploadPhoto">开始上传</el-button>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_upload_single = false">取 消</el-button>
                    <el-button @click="dialogFormVisible_upload_single = false" type="primary" >确 定</el-button>
                  </div>
                </el-dialog>
                <!-- 图像预览-->
                <el-dialog title="图像预览" :visible.sync="dialogVisibleImagePre" width="50%">
                   <span>
                      <el-image style="width: 100%; height: 100%" :src="'http://localhost:8090/img/photo'+atlasID+'/'+photoID+'.jpg'"></el-image>
                   </span>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisibleImagePre = false">取 消</el-button>
                    <el-button type="primary" @click="dialogVisibleImagePre = false">确 定</el-button>
                  </span>
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
    name: "atlas",
    components: {
      top,
      left
    },
    data() {
      return {
        dialogVisibleImagePre: false,
        dialogFormVisible_atlas_add: false,
        dialogFormVisible_atlas_put: false,
        dialogFormVisible_upload_single: false,
        dialogFormVisible: false,
        dialogVisible: false,
        disabled: false,
        formLabelWidth: '120px',
        file: null,  //前台上传的文件暂时保存在这里
        currentPage: 1,  //当前所在页
        total: 0,  //当前请求后台图集的总数据量
        pageSize: 5,  //一页显示多少条记录
        atlases: [],  //存放图集集合
        photos:[],  //存放一个图集下的图片集合
        photo:{ name: '', description: '', atlasID: '' },
        atlas: { name: '', description: '' },
        atlasID:'',  //用于删除图片时传递图集id
        photoID:'',  //用户回显图片
        selItems: [],  //选中的选项
        dustbinData: []  //存储选中的数据
      }
    },
    mounted() {
      this.getAtlasesAndPage(1)
    },
    methods: {
      //获取分页数据
      getAtlasesAndPage() {
        const _this = this
        const url = 'atlas?currentPage=' + this.currentPage
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.atlases = resp.data.data.records
          _this.currentPage = resp.data.data.current
          _this.total = resp.data.data.total
          _this.pageSize = resp.data.data.size
        })
      },
      //翻页
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loading = true;
        this.getAtlasesAndPage(currentPage);
      },
      //增加图集
      addAtlas() {
        const _this = this
        const url = 'atlas'
        this.$axios.post(url, this.atlas, {
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
          _this.atlas.name = null
          _this.atlas.description = null
          _this.getAtlasesAndPage(1)
        })
      },
      //删除图集
      deleteAtlas(index, row) {
        if(! this.checkDeleteLink())
          return
        const _this = this
        const aid = row.id
        const url = 'atlas?aid=' + aid
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
          _this.getAtlasesAndPage(1)
        })
      },
      //打开编辑图集
      handleEdit(index, row) {
        const _this = this
        const aid = row.id
        const url = 'atlas/' + aid
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.atlas = resp.data.data
          _this.dialogFormVisible_atlas_put = true
        })
      },
      //执行更新图集
      updateAtlas() {
        const _this = this
        const url = 'atlas'
        this.$axios.put(url, this.atlas, {
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
          _this.atlas.name = null
          _this.atlas.description = null
          _this.getAtlasesAndPage(1)
        })
      },
      //打开上传图片模态框
      openDialogUploadSingle(index, row) {
        this.dialogFormVisible_upload_single = true
        this.photo.atlasID = row.id
      },
      //获取文件对象
      getFile: function (event) {
        this.file = event.target.files[0];
      },
      //执行上传操作
      uploadPhoto() {
        const _this = this
        const url = 'photo'
        let formData = new FormData()
        formData.append('aid', this.photo.atlasID)
        formData.append('name', this.photo.name)
        formData.append('description', this.photo.description)
        formData.append('image', this.file)
        this.$axios.post(url, formData, {
          headers: {
            "Authorization": localStorage.getItem("token"),
            "Content-Type": "multipart/form-data",
          }
        }).then(resp => {
          const data = resp.data.data;
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          });
          _this.photo.name = null
          _this.photo.description = null
          _this.file = null
          _this.getAtlasesAndPage(1)
        })
      },
      //展开每一图集，获取该图集下的图片集合
      expandChange(row, expandedRows) {
        const _this = this
        const aid = row.id
        const url = 'photo?aid=' + aid
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.photos = resp.data.data
          _this.atlasID = aid
        })
      },
      //弹出图片预览模态框
      showImagePre(id) {
        this.dialogVisibleImagePre = true;
        this.photoID = id
      },
      //删除图片
      deleteBean(id) {
        if(! this.checkDeleteLink())
          return
        const _this = this
        const url = 'photo?pid=' + id + '&aid=' + this.atlasID
        this.$axios.delete(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.photos = resp.data.data
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          })
          _this.getAtlasesAndPage(1)
        })
      },
      //确实是否要删除
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
        const url = 'atlases?arr=' + arr
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
          _this.getAtlasesAndPage(1)
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
    width: 50%;
  }
</style>
