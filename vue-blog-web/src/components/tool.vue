<template>
    <div class="tool">
        <el-backtop class="tool-top" :bottom="80">
            <i class="el-icon-caret-top" />
        </el-backtop>
        <el-button class="aside-btn" circle  @click="drawer = true">
            <i class="el-icon-s-promotion" />
        </el-button>
        <el-drawer :visible.sync="drawer" size="20%" :modal="false" :show-close="false">
            <div class="avatar-show">
                <el-avatar :size="110" :src="'http://localhost:8090/img/avatar/'+ user.id +'.jpg'"></el-avatar>
            </div>
            <div class="title-show">
                <el-row :gutter="20" type="flex" justify="center">
                    <el-col>
                        <div class="drawer-title">
                            <span>{{user.nickname}}
                                <i style="color: #409EFF;font-size: 15px;font-weight: 500" class="el-icon-male" />
                            </span>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="all-show">
                <el-row :gutter="20" type="flex" justify="center">
                    <el-col :span="20" :offset="2">
                        <div class="all-item">
                            <a href="#">
                              {{countTotal.blogCount}}<br>
                                <span>日志</span>
                            </a>
                        </div>
                        <div class="all-item">
                            <a href="#">
                              {{countTotal.typeCount}}<br>
                                <span>分类</span>
                            </a>
                        </div>
                        <div class="all-item">
                            <a href="#">
                              {{countTotal.tagCount}}<br>
                                <span>标签</span>
                            </a>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="about-show">
                <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="24" :md="24" :lg="20" >
                        <el-button size="mini" type="primary" class="link-left-btn" plain>
                            <i class="el-icon-chat-dot-round" />&nbsp;&nbsp;&nbsp;微信联系我
                        </el-button>
                        <el-button size="mini" type="success" class="link-right-btn" plain>
                            <i class="el-icon-s-comment" />&nbsp;&nbsp;&nbsp;邮箱联系我
                        </el-button>
                    </el-col>
                </el-row>
            </div>
            <div class="link-show">
                <el-row type="flex" justify="center">
                    <el-col :span="20" :md="19">
                        <el-tag class="tag-link">友情链接云</el-tag>
                        <el-tag v-for="(link,index) in countTotal.links" :key="link.name" class="tag-link" type="success">
                            <a target="_blank" :href="link.address">{{ link.name}}</a>
                        </el-tag>
                        <el-tag class="tag-link" type="warning">
                            <a href="#">...</a>
                        </el-tag>
                    </el-col>
                </el-row>
            </div>
        </el-drawer>
    </div>
</template>

<script>
export default {
  name: 'Tool',
  data () {
    return {
      drawer: false,
      user:'',
      countTotal: ''
    }
  },
  mounted() {
    this.getUser()
    this.getCount()
  },
  methods: {
    getUser() {
      const url = 'user'
      const _this =this
      this.$axios.get(url).then(resp=>{
        _this.user = resp.data.data
      })
    },
    getCount() {
      const url = 'count'
      const _this =this
      this.$axios.get(url).then(resp=>{
        _this.countTotal = resp.data.data
      })
    }
  },
}
</script>

<style scoped>
    div.tool {
        overflow-y: scroll;
    }
    div.tool .aside-btn {
        position:fixed;
        bottom: 30px;
        right: 41px;
        border: none;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
    }
    div.tool .aside-btn i {
        color: gray;
    }
    div.tool .avatar-show {
        text-align: center;
        margin-top: -8%;
    }
    div.tool .title-show .drawer-title {
        margin-top: 3%;
        text-align: center;
        color: white;
    }
    div.tool .title-show .drawer-title span {
        font-size: 15px;
        font-family: alibaba,serif;
    }

    div.tool .all-show {
        margin-top: 8%;
        text-align: center;
        margin-bottom: 8%;
    }
    div.tool .all-show .all-item {
        float: left;
        width: 25%;
        padding: 5px;
    }
    div.tool .all-show .all-item a {
        text-decoration: none;
        font-size: 18px;
        font-weight: 600;
        color: #bfbaba;
    }
    div.tool .all-show .all-item a:hover {
        color: white;
    }
    div.tool .all-show .all-item a span {
        font-size: 13px;
    }

    div.tool .about-show {
        margin-top: 4%;
    }
    div.tool .link-show {
        margin-top: 4%;
    }
    div.tool .link-show .tag-link {
        margin: 5px;
    }
    div.tool .link-show .tag-link a {
        text-decoration: none;
        color: lightslategray;
    }

    @media only screen and (max-width: 768px) {
        div.tool .aside-btn {
            display: none;
        }
    }

    @media only screen and (min-width: 1024px) and (max-width: 1200px) {
        div.about-show {
            text-align: center;
            width: 100%;
        }
        div.about-show .link-right-btn {
            margin-left: -1px;
            margin-top: 5%;
            margin-bottom: 5%;
        }
    }
</style>
