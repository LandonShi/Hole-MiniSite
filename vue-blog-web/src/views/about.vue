<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="about">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="show">
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="blog-title">
                        <span>Hi, That's landon</span>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="about-description">
                        <p>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！我是清风明月，坐标杭州。一名在0-1之间循环不止的准攻城狮（...），虽然目前的技术栈更偏向于后端，
                          但前端仍然是我想涉及的领域，想做一名自由且能力棒棒的全栈攻城狮，而不是各方面都浅尝辄止的程序员，这是我未来的一个目标。
                          我喜欢量化细节，喜欢思考探寻问题的本质，热爱头顶上的星空，也热爱奇奇怪怪的冒险。我有时候很固执，偏重理想主义，但我不是一块呆板无趣的方脑壳。
                          我喜欢与率真直爽、有思想的人交流，更喜欢和趣味相投的朋友一起追寻分享生活的美好瞬间。<br>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;That's landon,  希望未来有一天能与你有更深层次的交流，这将是我的荣幸。
                        </p>
                      </div>
                      <div class="about-link-icon">
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/github.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/gitee.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/wechat.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/bilibili.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/weichatpublic.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/zhihu.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/weibo.png" />
                          </a>
                        </div>
                        <div class="icon-tem">
                          <a href="#">
                            <img alt="图标" src="../assets/pipixia.jpg" />
                          </a>
                        </div>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="about-me">
                        <span>本站介绍</span>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="about-description">
                        <p>本站主要是作为站长记录日常创作、阅读、学习等过程中的一些心得感悟。与大家一起分享交流热门话题与认知上的思考与理解。如需获取本站源码，请移步上方公众号获取。</p>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="contact-me">
                        <span>合作联系</span>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="contact-description">
                        <p>或许我们可以组建一个 Team，成为并肩向前的 Partner，一起去寻找一个风口，一起冒险，或者是进行一些合作。留下您的联系方式，或者您主动联系我。期待您的来信。</p>
                      </div>
                    </div>
                  </el-col>
                </el-row>
                <el-row type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10">
                    <div class="main-inner">
                      <div class="contact-me">
                        <span>...</span>
                      </div>
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
  name: 'About',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data() {
    return {
      show: false
    }
  },
  mounted() {
    this.change()
  },
  methods: {
    change() {
      this.show = true
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
    div.about {
        margin-top: 19%;
    }
    div.about .main-inner {
        margin-top: 5%;
    }
    div.about .main-inner .blog-title span {
        font-size: 22px;
        font-weight: 600;
        font-family: alibaba,sans-serif;
        color: #635f5f;
    }
    div.about .main-inner .about-link-icon {
        margin: 2% auto;
        text-align: center;
    }
    div.about .main-inner .about-link-icon .icon-tem {
        display: inline-block;
        margin: 10px;
    }
    div.about .main-inner .about-link-icon .icon-tem img {
        width: 30px;
        height: 30px;
    }
    div.about .main-inner .about-me {
        font-size: 22px;
        font-weight: 600;
        font-family: alibaba,sans-serif;
        color: #635f5f;
    }
    div.about .main-inner .about-description p {
        font-size: 14px;
        font-family: alibaba,sans-serif;
        color: dimgray;
        line-height: 28px;
    }

    div.about .main-inner .contact-me {
        font-size: 22px;
        font-weight: 600;
        font-family: alibaba,sans-serif;
        color: #635f5f;
    }
    div.about .main-inner .contact-description p {
        font-size: 14px;
        font-family: alibaba,sans-serif;
        color: dimgray;
        line-height: 28px;
    }
</style>
