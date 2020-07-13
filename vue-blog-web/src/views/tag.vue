<template>
  <div>
      <Top></Top>
      <Header></Header>
      <div class="tag">
      <el-container>
        <el-main>
          <transition name="fadeInDown">
            <div v-if="show">
              <el-row :gutter="20" type="flex" justify="center">
                <el-col :span="10">
                  <div class="total-tags">目前总计 {{tags.length}} 个标签</div>
                </el-col>
              </el-row>
              <el-row :gutter="20" type="flex" justify="center">
                <div class="cloud-tags">
                  <div class="tag-wrap" v-for="(tag,index) in tags" :key="index">
                    <a :style="{'font-size': (tag.blogList.length>2 ? '22px' : '12px'),
                    'color': (tag.blogList.length>2 ? '#666':'#cccccc')}" :href="'tagList?tagID=' + tag.id">
                      {{tag.name}}
                    </a>
                  </div>
                </div>
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
  name: 'Tag',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data() {
    return{
      tags:[],
      show: false
    }
  },
  mounted() {
    this.getTypes()
    this.change()
  },
  methods: {
    change() {
      this.show = true
    },
    getTypes() {
      const _this = this
      const url = 'tags/fore'
      this.$axios.get(url).then(resp => {
        _this.tags = resp.data.data
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
  div.tag {
    margin-top: 19%;
  }
  div.tag .total-tags {
    color: #9a9393;
    text-align: center;
    font-size: 14px;
    font-family: alibaba,serif;
  }
  div.tag .cloud-tags {
    text-align: center;
    width: 48%;
    margin: 0 auto;
  }
  div.tag .cloud-tags .tag-wrap {
    display: inline-block;
    margin-top: 6%;
  }
  div.tag .cloud-tags .tag-wrap a {
    text-decoration: none;
    border-bottom: solid 1px #a19a9a;
    padding-bottom: 5px;
    margin: 10px;
  }
  div.tag .cloud-tags .tag-wrap a:hover {
    text-decoration: none;
    border-bottom: solid 2px #41e241;
    padding-bottom: 5px;
  }

  @media only screen and (min-width:280px) and (max-width:768px) {
    div.tag {
      margin-top: 35%;
    }
    div.tag .cloud-tags {
      text-align: center;
      width: 100%;
      margin: 0 auto;
    }
    div.tag .cloud-tags .tag-wrap {
      display: inline-block;
      margin-top: 6%;
    }
    div.tag .cloud-tags .tag-wrap a {
      text-decoration: none;
      font-size: 12px;
      color: #ccc;
      border-bottom: solid 1px #ccc;
      padding-bottom: 5px;
    }
    div.tag .cloud-tags .tag-wrap a:hover {
      text-decoration: none;
      color: orange;
      border-bottom: solid 2px orange;
      padding-bottom: 5px;
    }
  }
  @media only screen and (min-width:768px) and (max-width: 1024px){
    div.tag {
      margin-top: 29%;
    }
    div.tag .cloud-tags {
      text-align: center;
      width: 70%;
      margin: 0 auto;
    }
    div.tag .cloud-tags .tag-wrap {
      display: inline-block;
      margin-top: 6%;
    }
    div.tag .cloud-tags .tag-wrap a {
      text-decoration: none;
      font-size: 12px;
      color: #cccccc;
      border-bottom: solid 1px #a19a9a;
      padding-bottom: 5px;
      margin: 10px;
    }
    div.tag .cloud-tags .tag-wrap a:hover {
      text-decoration: none;
      color: orange;
      border-bottom: solid 2px orange;
      padding-bottom: 5px;
    }
  }
</style>
