<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="type">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="show">
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :span="10">
                    <div class="total-types">目前总计 {{types.length}} 个分类</div>
                  </el-col>
                </el-row>
                <el-row :gutter="20" type="flex" justify="center">
                  <el-col :xs="24" :sm="20" :md="16" :lg="11" :xl="10" class="type-row">
                    <el-tag class="tag-type" type="info" v-for="(type,index) in types" :key="type.id">
                      <a :href="'typeList?tid=' + type.id">
                        {{type.name + "  " + type.blogList.length }}
                      </a>
                    </el-tag>
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
  name: 'Type',
  components: {
    Top,
    Header,
    Tool,
    Footer
  },
  data() {
    return{
      types:[],
      currentPage: 1,  //当前所在页
      total: 0,  //当前请求后台图集的总数据量
      pageSize: 5,  //一页显示多少条记录
      show: false
    }
  },
  mounted() {
    this.getTypes()
    this.change()
  },
  methods: {
    //过渡动画
    change() {
      this.show = true
    },
    //获取分类
    getTypes() {
      const _this = this
      const url = 'types?currentPage=' + this.currentPage
      this.$axios.get(url).then(resp => {
        _this.types = resp.data.data.records
      })
    },
    /*//随机生成颜色
    randomRgb(item) {
      let R = Math.floor(Math.random() * 255);
      let G = Math.floor(Math.random() * 255);
      let B = Math.floor(Math.random() * 255);
      return {
        color: 'rgb(' + R + ',' + G + ',' + B + ')'
      }
    }*/
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
    div.type {
      margin-top: 19%;
    }
    div.type .total-types {
      color: #9a9393;
      text-align: center;
      font-size: 14px;
      font-family: alibaba,serif;
    }
    div.type .type-row {
      text-align: center;
      margin-top: 2%;
    }
    div.type .type-row .tag-type {
      margin: 10px;
      border: none;
    }
    div.type .type-row .tag-type a {
      text-decoration: none;
      font-size: 13px;
      font-family: alibaba,sans-serif;
      transition: all 0.8s;
      color: #666;
    }
    div.type .type-row .tag-type a:hover {
      color: #41e241;
      font-size: 12px;
      transform: scale(1.4);
    }

    @media only screen and (min-width:280px) and (max-width:768px) {
      div.type {
        margin-top: 35%;
      }
    }
    @media only screen and (min-width:768px) and (max-width: 1024px){
      div.type {
        margin-top: 29%;
      }
    }
</style>
