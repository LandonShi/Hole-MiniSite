<template>
    <div class="header">
        <el-container>
            <el-header>
                <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="8">
                        <transition name="slide-fade">
                            <div v-if="show">
                              <div class="header-inner">
                                <a href="#">
                                   <span class="icon-title">
                                     <img style="width: 35px" alt="图标" src="../assets/sun.png" />{{user.nickname}}
                                   </span>
                                </a>
                              </div>
                            </div>
                        </transition>
                        <transition name="slide-fade-s">
                            <div v-if="show">
                                <div class="short-info">
                                    <p class="resume-info">{{user.description}}</p>
                                </div>
                            </div>
                        </transition>
                        <transition name="slide-fade">
                            <div v-if="show">
                                <div class="nav-list">
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/index" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-s-home"  />
                                        <div class="nav-item-span">
                                          <span>首页</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/type" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-s-grid" />
                                        <div class="nav-item-span">
                                          <span>分类</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/tag" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-s-flag" />
                                        <div class="nav-item-span">
                                          <span>标签</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/photo" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-picture" />
                                        <div class="nav-item-span">
                                          <span>图册</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/archive" style="color: #9e9999;text-decoration: navajowhite;">
                                      <div class="nav-item">
                                        <i class="el-icon-s-data" />
                                        <div class="nav-item-span">
                                          <span>归档</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info" target="_blank">
                                    <router-link to="/about" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-user-solid" />
                                        <div class="nav-item-span">
                                          <span style="margin-top: 5px">关于</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                  <div class="nav-wrap">
                                  <el-link type="info">
                                    <router-link to="/comment" style="color: #9e9999;text-decoration: navajowhite">
                                      <div class="nav-item">
                                        <i class="el-icon-s-comment" />
                                        <div class="nav-item-span">
                                          <span>留言</span>
                                        </div>
                                      </div>
                                    </router-link>
                                  </el-link>
                                </div>
                                 <!-- <div class="nav-wrap">
                                      <el-link type="info">
                                        <router-link to="/search" style="color: #9e9999;text-decoration: navajowhite">
                                          <div class="nav-item">
                                            <i class="el-icon-question" />
                                            <div class="nav-item-span">
                                              <span>搜索</span>
                                            </div>
                                          </div>
                                        </router-link>
                                      </el-link>
                                </div>-->
                            </div>
                        </div>
                        </transition>
                      <div class="site-toggle-nav"></div>
                    </el-col>
                </el-row>
            </el-header>
        </el-container>
    </div>
</template>

<script>
export default {
  name: 'Header',
  data() {
    return {
      user:'',
      show: false
    }
  },
  mounted() {
    this.getUser()
    this.change()
  },
  methods: {
    getUser() {
      const url = 'user'
      const _this =this
      this.$axios.get(url).then(resp=>{
        _this.user = resp.data.data
      })
    },
    change() {
      this.show = true
    }
  },
}
</script>

<style scoped>
    .slide-fade-enter-active {
      transition: all 1.9s ease;
    }

    .slide-fade-enter {
      transform: translateX(10px);
      opacity: 0;
    }
    .slide-fade-s-enter-active {
      transition: all 2.8s ease;
    }
    .slide-fade-s-enter {
      transform: translateX(10px);
      opacity: 0;
    }
    div.header {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    div.header .header-inner {
        text-align: center;
        margin-top: 15%;
    }
    div.header .header-inner a {
        text-decoration: none;
        color: #4e4c4c;
        margin: 0 auto;
    }
    div.header .header-inner a:hover {
        color: #11d711;
    }
    div.header .header-inner .icon-title {
        font-size: 34px;
        font-family: alibaba,serif;
        font-weight: 500;
        margin-right: 10px;
        text-align: center;
    }
    div.header .header-inner .icon-title i {
        margin-right: 1%;
    }
    div.header .short-info {
        color: #a7acb1;
        font-size: 13px;
        margin-top: 2%;
        text-align: center;
    }
    div.header .nav-list {
        margin: 5% auto;
        text-align: center;
        display: flex;
        width: 55%;
    }
    div.header .nav-list .nav-wrap {
        margin: 0 auto;
        width: 10%;
        float: left;
        text-align: center;
    }
    div.header .nav-list .nav-wrap .nav-item:hover {
        color: #41e241;
    }
    div.header .nav-list .nav-wrap .nav-item i {
        font-size: 20px;
        color: #3d3939;
    }
    div.header .nav-list .nav-wrap .nav-item .nav-item-span {
        margin-top: 4px;
    }
    div.header .nav-list .nav-wrap .nav-item .nav-item-span span {
        font-size: 13px;
        font-family: alibaba,sans-serif;
    }

    @media only screen and (min-width: 280px) and (max-width: 767px){
        div.header .nav-list {
            display: none;
        }
        div.header .header-inner .icon-title {
            font-size: 28px;
            font-family: alibaba,serif;
            font-weight: 500;
            margin-right: 10px;
            text-align: center;
        }
    }
    @media only screen and (min-width: 1024px){
        div.header .header-inner {
            text-align: center;
            margin-top: 13%;
        }
    }

  .el-link {
    padding-bottom: 5px;
  }

</style>
