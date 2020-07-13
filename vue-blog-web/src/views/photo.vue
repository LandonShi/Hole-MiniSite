<template>
  <div>
      <Top></Top>
      <Header></Header>
      <div class="photo">
        <el-container>
        <el-main>
          <transition name="fadeInDown">
            <div v-if="show">
              <el-row type="flex" justify="center">
                <el-col :xs="24" :sm="20" :md="16" :lg="14" :xl="10">
                  <div class="title-carousel">
                    <span> 今日上新 {{ atlas.name }} </span>
                  </div>
                </el-col>
              </el-row>
              <!-- 最新上架 -->
              <el-row type="flex" justify="center">
                <el-col :xs="24" :sm="20" :md="16" :lg="14" :xl="10">
                  <el-card class="box-card">
                    <el-carousel autoplay loop class="carousel-item" :interval="5000" height="450px" arrow="hover">
                      <el-carousel-item v-for="(photo,index) in atlas.photos" :key="index">
                        <el-image style="width: 100%; height: 100%" fit="contain" :src="'http://localhost:8090/img/photo'+ atlas.id +'/'+photo.id +'.jpg'" />
                      </el-carousel-item>
                    </el-carousel>
                  </el-card>
                </el-col>
              </el-row>
              <el-row type="flex" justify="center">
                <el-col :xs="24" :sm="20" :md="16" :lg="14" :xl="10">
                  <el-divider style="font-family: alibaba,sans-serif;" content-position="right"><i class="el-icon-sunny"></i> 记录美好的瞬间</el-divider>
                </el-col>
              </el-row>
              <!-- 图集列表 -->
              <div class="show-photo">
                <div class="show-photo-wrap" v-for="(atlas,index) in atlases" :key="index">
                  <el-row class="show-photo-item" type="flex" justify="center">
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                      <el-card shadow="hover" class="card-item" :body-style="{ padding: '0px'}">
                        <el-image class="item-img" fit="contain" :src="'http://localhost:8090/img/photo'+ atlas.id +'/'+ atlas.photos[0].id +'.jpg'"></el-image>
                        <div class="item-info">
                          <a target="_blank" :href="'photoList?aid=' + atlas.id">{{atlas.name}} <i class="el-icon-position"/></a>
                          <p>{{atlas.description}}</p>
                          <el-alert class="item-btn" type="success" :closable="false" center>
                            {{atlas.createDate | formatDate}}
                          </el-alert>
                        </div>
                      </el-card>
                    </el-col>
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
  name: 'Photo',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data () {
    return {
      atlas:'',
      atlases:[],
      show: false,
      currentPage: 1,  //当前所在页
      total: 0,  //当前请求后台图集的总数据量
      pageSize: 10,  //一页显示多少条记录
    }
  },
  mounted() {
    this.getPhoto()
    this.getAtlases()
    this.change()
  },
  methods: {
    //过渡动画
    change() {
      this.show = true
    },
    //获取最新一次图集
    getPhoto() {
      const _this = this
      const url = 'atlas/recently'
      this.$axios.get(url).then(resp => {
        _this.atlas = resp.data.data
      })
    },
    //获取图集列表
    getAtlases() {
      const _this = this
      const url = 'atlas/all?currentPage=' + this.currentPage
      this.$axios.get(url).then(resp => {
        _this.atlases = resp.data.data
        _this.currentPage = resp.data.data.current
        _this.total = resp.data.data.total
        _this.pageSize = resp.data.data.size
      })
    },
    //分页-翻页
    handleCurrentChange(val) {
      this.currentPage = val;
    }
  }
}
</script>

<style scoped>
    .fadeInDown-enter-active {
      transition: all 2.5s ease;
    }
    .fadeInDown-enter {
      transform: translateY(10px);
      opacity: 0;
    }
    .el-card:hover {
      transition: all 2.5s ease;
      transform: translateY(-10px);
    }
    div.photo {
        margin-top: 19%;
    }
    div.photo .title-carousel {
        text-align: center;
        margin-bottom: 2%;
    }
    div.photo .title-carousel span {
        font-size: 14px;
        color: #d2c5c5;
        font-family: alibaba,sans-serif;
    }
    div.photo .title-carousel span:hover {
        color: #41e241;
    }

    div.photo .card-item {
      margin: 10px;
      border-radius: 15px;
    }
    div.photo .card-item a {
      color: black;
      font-size: 15px;
      font-weight: 600;
      text-decoration: none;
    }
    div.photo .card-item a:hover {
      color: #08af08;
    }
    div.photo .card-item .item-img {
      border-top-left-radius: 15px;
      border-top-right-radius: 15px;
      width: 100%;
    }
    div.photo .card-item .item-info {
        padding: 14px;
        text-align: center
    }
    div.photo .card-item .item-info i {
        color: #41e241;
    }
    div.photo .card-item .item-info p {
        margin-top: 8px;
        color: #bbb8b8;
        font-size: 12px;
        line-height: 19px;
    }
    div.photo .card-item .item-info .item-btn {
        margin-top: 8px;
        font-size: 12px;
        font-family: alibaba,sans-serif;
    }

    @media only screen and (min-width: 1200px) {
        div.photo .show-photo {
          margin: 2% auto;
          text-align: center;
          width: 68%;
        }
        div.photo .show-photo .show-photo-wrap {
          display: inline-block;
          width: 33%;
        }
    }
    @media only screen and (min-width: 1025px) and (max-width: 1199px){
        div.photo .show-photo {
            margin: 2% auto;
            text-align: center;
            width: 80%;
        }
        div.photo .show-photo .show-photo-wrap {
            display: inline-block;
            width: 33%;
        }
        div.photo .show-photo .show-photo-wrap .show-photo-item {
              width: 100%;
        }
    }
    @media only screen and (min-width: 768px) and (max-width: 1024px){
        div.photo .show-photo {
            margin: 0 auto;
            text-align: center;
            width: 80%;
        }
        div.photo .show-photo .show-photo-wrap {
            display: inline-block;
            width: 50%;
        }
        div.photo .show-photo .show-photo-wrap .show-photo-item {
            width: 100%;
        }
    }
    @media only screen and (min-width: 280px) and (max-width: 767px){
        div.photo .show-photo {
            margin: 0 auto;
            text-align: center;
            width: 100%;
        }
        div.photo .show-photo .show-photo-wrap {
            display: inline-block;
            width: 100%;
        }
    }
</style>
