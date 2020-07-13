<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="tag-list">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="showDraw">
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="10" :md="8" :lg="11" :xl="8">
                    <div class="tag-title">
                      <span class="title-icon"><i class="el-icon-tickets"/></span>
                      <span class="title-header">{{bean.name}}</span>
                      <el-divider direction="vertical"></el-divider>
                      <span class="title-flag">标签  {{bean.blogList.length}}</span>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="10" :md="8" :lg="11" :xl="8">
                    <div class="block">
                      <el-timeline :reverse="reverse">
                        <el-timeline-item class="tag-item" v-for="(blog,index) in bean.blogList" :key="index"
                                          :color="index%2===1?'#0bbd87':'orange'">
                          <div class="tag-item-wrap">
                            <span style="font-size: 12px">{{blog.createDate | formatDate}}</span> &nbsp;
                            <a :href="'detail?bid=' + blog.id">{{blog.title}}</a>
                          </div>
                        </el-timeline-item>
                      </el-timeline>
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
  import Header from '../components/header'
  import Tool from '../components/tool'
  import Footer from '../components/footer'
  export default {
    name: "tagList",
    components: {
      Top,
      Header,
      Tool,
      Footer
    },
    data () {
      return {
        reverse: true,
        bean: '',
        showDraw: false
      }
    },
    mounted() {
      this.getTagInBlog()
      this.change()
    },
    methods: {
      change() {
        this.showDraw = true
      },
      //获取文章
      getTagInBlog() {
        const tagID = this.$route.query.tagID;
        if(tagID == null)
          return
        const _this = this
        const url = "bless/" + tagID
        this.$axios.get(url).then(reps=>{
          _this.bean = reps.data.data
        })
      }
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
  div.tag-list {
    margin-top: 19%;
  }
  div.tag-list .tag-title {
    margin-bottom: 15px;
  }
  div.tag-list .tag-title .title-icon {
    font-size: 18px;
    color: gray;
    font-weight: 600;
    margin-right: 17px;
  }
  div.tag-list .tag-title .title-header {
    font-size: 20px;
    font-weight: 600;
    color: #383535;
  }
  div.tag-list .tag-title .title-flag {
    font-size: 15px;
    font-weight: 600;
    color: lightgray;
  }
  div.tag-list .block {
    margin-top: 5%;
    padding: 8px;
  }
  div.tag-list .block .tag-item {
    font-family: alibaba,sans-serif;
  }

  div.tag-list .block .tag-item .tag-item-wrap {
    border-bottom: 1px dashed transparent;
    background: linear-gradient(white,white) padding-box,
    repeating-linear-gradient(-99deg,#ccc 0, #ccc 0.55em,white 0,white 0.85em);
  }
  div.tag-list .block .tag-item .tag-item-wrap:hover {
    border-bottom: 1px dashed transparent;
    background: linear-gradient(white,white) padding-box,
    repeating-linear-gradient(-99deg, #4e4848 0, #4e4848 0.55em,white 0,white 0.85em);
  }
  div.tag-list .block .tag-item .tag-item-wrap a {
    color: #807c7c;
    text-decoration: none;
    font-size: 16px;
  }

  @media only screen and (min-width:280px) and (max-width:768px) {
    div.tag-list {
      margin-top: 30%;
    }
  }
  @media only screen and (min-width:768px) and (max-width:1200px) {
    div.tag-list {
      margin-top: 30%;
    }
  }
</style>
