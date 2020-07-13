<template>
    <div>
        <Top></Top>
        <Header></Header>
        <div class="archive">
          <el-container>
            <el-main>
              <transition name="fadeInDown">
                <div v-if="showDraw">
                  <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="14" :md="12" :lg="10" :xl="8">
                      <div class="block">
                        <el-timeline>
                          <el-timeline-item class="archive-item">
                            <div class="archive-item-wrap" style="border: none">
                              <span>太棒了!  目前共计 {{blogs.length}} 篇日志。 请继续努力。</span> &nbsp;
                            </div>
                          </el-timeline-item>
                          <el-timeline-item class="archive-item">
                            <div class="archive-item-wrap" style="border: none">
                              <div v-if="showDraw">
                                <span class="wrap-item">2020</span>
                              </div>
                            </div>
                          </el-timeline-item>
                          <el-timeline-item class="archive-item" v-for="(blog,index) in blogs" :key="index">
                            <div class="archive-item-wrap">
                              <span style="font-size: 12px">{{blog.createDate | formatDate}}</span> &nbsp;
                              <a :href="'detail?bid=' + blog.id">{{blog.title}}</a>
                            </div>
                          </el-timeline-item>
                        </el-timeline>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row type="flex" justify="center" style="text-align: center; margin-top: 2%">
                    <el-col :xs="24" :sm="14" :md="12" :lg="10" :xl="8">
                      <div class="pagination-inner">
                        <el-pagination
                          background layout="prev, pager, next"
                          :current-page="currentPage"
                          :page-size="pageSize"
                          :total="total"
                          @current-change="currentChange">
                        </el-pagination>
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
  name: 'Archive',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data() {
    return {
      showDraw: false,
      blogs: [],
      currentPage: 1,  //当前所在页
      total: 0,  //当前请求后台图集的总数据量
      pageSize: 10,  //一页显示多少条记录
    }
  },
  mounted() {
    this.change()
    this.getAllBlogAndPage()
  },
  methods: {
    change() {
      this.showDraw = true
    },
    getAllBlogAndPage() {
      const _this = this
      const url = 'blogs/command?currentPage=' + this.currentPage
      this.$axios.get(url).then(resp => {
        _this.blogs = resp.data.data.records
        _this.currentPage = resp.data.data.current
        _this.total = resp.data.data.total
        _this.pageSize = resp.data.data.size
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
    .fadeInDown-enter-active {
      transition: all 1.9s ease;
    }

    .fadeInDown-enter {
      transform: translateY(10px);
      opacity: 0;
    }
    .wrap-item {
      font-size: 20px;
      font-family: alibaba,sans-serif;
      font-weight: 600;
    }
    div.archive {
      margin-top: 19%;
    }
    div.archive .block {
      padding: 8px;
    }
    div.archive .block .archive-item {
      font-family: alibaba,sans-serif;
      margin-top: 2%;
    }
    div.archive .block .archive-item .archive-item-wrap {
      border-bottom: 1px dashed transparent;
      background: linear-gradient(white,white) padding-box,
      repeating-linear-gradient(-99deg,#ccc 0, #ccc 0.55em,white 0,white 0.85em);
    }
    div.archive .block .archive-item .archive-item-wrap:hover {
      border-bottom: 1px dashed transparent;
      background: linear-gradient(white,white) padding-box,
      repeating-linear-gradient(-99deg, #4e4848 0, #4e4848 0.55em,white 0,white 0.85em);
    }
    div.archive .block .archive-item .archive-item-wrap a {
      color: #807c7c;
      text-decoration: none;
      font-size: 16px;
    }

    @media only screen and (min-width:280px) and (max-width:768px) {
      div.archive {
        margin-top: 30%;
      }
    }
    @media only screen and (min-width:768px) and (max-width:1200px) {
      div.archive {
        margin-top: 30%;
      }
    }


</style>
