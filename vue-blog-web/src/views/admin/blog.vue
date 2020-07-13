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
              <el-page-header content="写文章" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row :gutter="20" style="margin-top: 20px">
                <el-col :span="12">
                  <el-input v-model="blog.title" placeholder="请输入标题"></el-input>
                </el-col>
                <el-col :span="4">
                  <el-select v-model="blog.tid" placeholder="请选择文章所属分类" style="width: 190px;">
                    <el-option v-for="(type,index) in types" :key="type.name" :label="type.name" :value="type.id"></el-option>
                  </el-select>
                </el-col>
                <el-col :span="8">
                  <el-select multiple collapse-tags v-model="blog.tags" placeholder="请选择标签" style="width: 250px;">
                    <el-option v-for="(tag,index) in tags" :key="tag.id" :label="tag.name" :value="tag.id"></el-option>
                  </el-select>
                </el-col>
              </el-row>
              <el-row :gutter="20" style="margin-top: 20px">
                <el-col :span="24">
                  <el-input v-model="blog.description" type="textarea" :rows="2" placeholder="给文章写一个简短概括吧"></el-input>
                </el-col>
              </el-row>
              <el-row :gutter="20" style="margin-top: 20px">
                <div id="editor">
                  <mavon-editor v-model="blog.content" ref=md style="height: 100%;width: 100%;"
                                :codeStyle="codeStyle" :ishljs="true" @imgAdd="imgAdd" @imgDel="imgDel">
                  </mavon-editor>
                </div>
              </el-row>
              <el-row :gutter="20" style="margin-top: 20px; margin-left: 15px">
                <el-button @click="sendBlog(0)">保存为草稿</el-button>
                <el-button @click="sendBlog(1)" type="primary">直接发布</el-button>
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
    name: "blog",
    components: {
      top,
      left
    },
    data() {
      return {
        types:[],  //下拉框分类
        tags:[], //下拉标签
        blog: {
          id:'',
          tid: '',
          title: '',
          description:'',
          content:'',
          statue: '',
          tags:[]  //存储选中的标签集合
        },
        markdownOption:{
          bold: true, // 粗体
          italic: true, // 斜体
          header: true, // 标题
          underline: true, // 下划线
          strikethrough: true, // 中划线
          mark: true, // 标记
          superscript: true, // 上角标
          subscript: true, // 下角标
          quote: true, // 引用
          ol: true, // 有序列表
          ul: true, // 无序列表
          link: true, // 链接
          imagelink: true, // 图片链接
          code: true, // code
          table: true, // 表格
          fullscreen: false, // 全屏编辑
          readmodel: false, // 沉浸式阅读
          htmlcode: true, // 展示html源码
          help: true, // 帮助
          /* 1.3.5 */
          undo: true, // 上一步
          redo: true, // 下一步
          trash: true, // 清空
          save: false, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: true, // 导航目录
          /* 2.1.8 */
          alignleft: true, // 左对齐
          aligncenter: true, // 居中
          alignright: true, // 右对齐
          /* 2.2.1 */
          subfield: true, // 单双栏模式
          preview: false // 预览
        },
        codeStyle:'monokai-sublime',
      }
    },
    mounted() {
      this.getType()
      this.getTags()
      this.getBlog()
      this.getSelTags()
    },
    methods: {
      //获取文章
      getBlog() {
        this.blog.id = this.$route.query.bid;
        if(this.blog.id == null)
          return
        const _this = this
        const url = "blogs/" + this.blog.id
        this.$axios.get(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(reps=>{
          _this.blog = reps.data.data
        })
      },
      //获取标签
      getSelTags() {
        this.blog.id = this.$route.query.bid;
        if(this.blog.id == null)
          return
        const _this = this
        const url = "tags/" + this.blog.id
        this.$axios.get(url,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(reps=>{
          _this.blog.tags = reps.data.data
        })
      },
      //下拉获取分类
      getType() {
        const _this = this
        const url = 'types?currentPage=' + 1
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
      //获取下拉标签
      getTags() {
        const _this = this
        const url = 'tags/list'
        this.$axios.get(url, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          _this.tags = resp.data.data
        })
      },
      //发布文章
      sendBlog(statue) {
        this.blog.statue = statue;
        const _this = this
        const url = 'blogs'
        this.$axios.post(url, this.blog,{
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(resp => {
          const data = resp.data.data
          _this.$notify({
            title: '成功',
            message: data,
            type: 'success'
          });
        })
      },
      //文本编辑器上传图片
      imgAdd(pos, $file){
        const _this = this
        //第一步将图片上传到服务器
        const formData = new FormData()
        formData.append('image', $file)
        this.$axios.post("/blogs/upload", formData,{
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(resp=> {
          const json = resp.data;
          if (json.status === 'success') {
            /*_this.$refs.md.$imglst2Url([[pos, json.data]])*/
            _this.$refs.md.$img2Url(pos, url);
          } else {
            _this.$message({type: json.status, message: json.data});
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>
