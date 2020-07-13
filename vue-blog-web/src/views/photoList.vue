<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="photoList">
        <el-container>
          <el-main>
            <transition name="fadeInDown">
              <div v-if="show">
                <el-row v-for="(photo,index) in atlas.photos":key="index" type="flex" justify="center" style="margin-top: 2%">
              <el-col :xs="24" :sm="20" :md="16" :lg="14" :xl="10">
                <el-divider>{{photo.name}}  {{photo.createDate | formatDate}}</el-divider>
                <el-image :src="'http://localhost:8090/img/photo'+ atlas.id +'/'+ photo.id +'.jpg'"></el-image>
                <el-alert :title="photo.description" :closable="false"
                          style="margin-top: 2%;background: white;color: #666"></el-alert>
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
    name: "photoList",
    components: {
      Top,
      Header,
      Tool,
      Footer
    },
    data() {
      return {
        atlas:'',
        show: false
      }
    },
    mounted() {
      this.getPhotos()
      this.change()
    },
    methods: {
      change() {
        this.show = true
      },
      //获取文章
      getPhotos() {
        const aid = this.$route.query.aid;
        if(aid == null)
          return
        const _this = this
        const url = "atlas/" + aid
        this.$axios.get(url).then(reps=>{
          _this.atlas = reps.data.data
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
  div.photoList {
    margin-top: 19%;
  }
</style>
