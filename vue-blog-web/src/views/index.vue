<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="main">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="show">
                <div v-for="(blog,index) in blogs.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="index">
                  <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                      <div class="main-inner">
                        <div class="blog-title">
                          <el-link>
                            <span class="blog-title-info">{{ blog.title}}</span>
                          </el-link>
                        </div>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                      <div class="blog-mini">
                        <span>发表于: {{ blog.createDate | formatDate}}</span>
                        <el-divider direction="vertical"></el-divider>
                        <span>作者: {{ blog.user.nickname}}</span>
                        <el-divider direction="vertical"></el-divider>
                        <span>分类于:
                          <a :href="'typeList?tid=' + blog.type.id" target="_blank">{{ blog.type.name}}</a>
                        </span>
                        <el-divider direction="vertical"></el-divider>
                        <span>阅读量：{{ blog.views}}</span>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                      <div class="blog-detail">
                        <p>{{blog.description}}</p>
                        <div class="blog-detail-btn">
                          <el-button @click="read(blog.id)" type="info" class="read-btn" size="small" plain>
                            点击阅读 <i class="el-icon-paperclip" />
                          </el-button>
                        </div>
                      </div>
                    </el-col>
                  </el-row>
                  <el-row type="flex" justify="center">
                    <div class="blog-end">
                      <el-divider class="blog-end-divider"></el-divider>
                    </div>
                  </el-row>
                </div>
                <el-row type="flex" justify="center" style="text-align: center; margin-top: 2%">
                  <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="8">
                    <div class="pagination-inner">
                      <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-size="pageSize"
                        layout="prev, pager, next"
                        :total="total">
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
import Tool from '../components/tool'
import Footer from '../components/footer'
import Header from '../components/header'

export default {
  name: 'Index',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data() {
    return {
      blogs: [],
      currentPage: 1,  //当前所在页
      total: 0,  //当前请求后台图集的总数据量
      pageSize: 10,  //一页显示多少条记录
      show: false
    }
  },
  mounted() {
    this.getAllBlogAndPage()
    this.change()
  },
  methods: {
    change() {
      this.show = true
    },
    //获取全部文章分类数据
    getAllBlogAndPage() {
      const _this = this
      const url = 'blogs/publish?currentPage=' + this.currentPage
      this.$axios.get(url).then(resp => {
        _this.blogs = resp.data.data.records
        _this.currentPage = resp.data.data.current
        _this.total = resp.data.data.total
        _this.pageSize = resp.data.data.size
      })
    },
    //分页-翻页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //阅读跳转
    read(bid) {
      this.$router.push({path: '/detail', query: {bid: bid}});
    }
  },
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
  div.main {
    margin-top: 15%;
  }
  div.main .main-inner {
    text-align: center;
  }
  div.main .main-inner .blog-title {
    margin: 0 auto;
  }
  div.main .main-inner .blog-title .blog-title-info {
    font-size: 26px;
    font-weight: 600;
    font-family: alibaba,serif;
  }
  div.main .main-inner .blog-title .blog-title-info:hover {
    color: #41e241;
  }

  div.main .blog-mini {
    margin: 14px auto;
    text-align: center;
  }
  div.main .blog-mini span {
    color: gray;
    font-size: 12px;
    font-family: alibaba,serif;
  }
  div.main .blog-mini a {
    color: gray;
  }

  div.main .blog-detail {
    margin: 40px auto;
  }
  div.main .blog-detail p {
    color: gray;
    font-size: 14px;
    line-height: 25px;
  }

  div.main .blog-detail .blog-detail-btn {
    margin: 5% auto;
    text-align: center;
  }
  div.main .blog-detail .blog-detail-btn .read-btn {
    border: solid 1px;
  }

  div.main .blog-end {
    width: 5%;
    margin: 0 auto;
  }
  div.main .blog-end .blog-end-divider {
    border: solid 1px lightgray;
    background: lightgray;
  }

  @media only screen and (min-width: 280px) {
    div.main {
      margin-top: 38%;
    }
    div.main .main-inner .blog-title .blog-title-info {
      font-size: 15px;
      font-weight: 600;
      font-family: alibaba,serif;
    }
  }
  @media only screen and (min-width: 320px) {
    div.main {
      margin-top: 38%;
    }
    div.main .main-inner .blog-title .blog-title-info {
      font-size: 15px;
      font-weight: 600;
      font-family: alibaba,serif;
    }
  }
  @media only screen and (min-width: 375px) {
    div.main {
      margin-top: 38%;
    }
    div.main .main-inner .blog-title .blog-title-info {
      font-size: 22px;
      font-weight: 600;
      font-family: alibaba,serif;
    }
  }
  @media only screen and (min-width: 767px) {
    div.main {
      margin-top: 38%;
    }
  }
  @media only screen and (min-width: 1024px) {
    div.main {
      margin-top: 30%;
    }
  }

  @media only screen and (min-width:1200px) {
    div.main {
      margin-top: 19%;
    }
    div.main .main-inner .blog-title .blog-title-info {
      font-size: 27px;
      font-weight: 600;
      font-family: alibaba,serif;
    }
  }
</style>
