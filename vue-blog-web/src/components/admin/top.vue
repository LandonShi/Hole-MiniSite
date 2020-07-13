<template>
  <div class="top">
    <el-container>
      <el-header class="homeHeader">
        <div class="title">
          <img alt="头像" src="../../assets/manager.png" />
          <span>后台管理</span>
        </div>
        <div style="margin-right: 15px">
          <el-dropdown class="userInfo">
            <span class="el-dropdown-link">
              {{user.nickname}}
              <i><img :src="'http://localhost:8090/img/avatar/'+ user.id +'.jpg'" alt="头像"></i>
              <el-link @click="logout">退出</el-link>
            </span>
          </el-dropdown>
        </div>
      </el-header>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'top',
  data() {
    return{
      user:{
        id:'',
        username: '',
        nickname: '',
        avatar: ''
      }
    }
  },
  methods: {
    logout() {
      const _this = this
      this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO")
        _this.$router.push("/login")
      })
    }
  },
  mounted() {
    const url = 'user'
    const _this = this
    this.$axios.get(url,{
      headers: {
        "Authorization": localStorage.getItem("token")
      }
    }).then(res => {
      _this.user = res.data.data
    })
  },
  created() {
    if(this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.nickname = this.$store.getters.getUser.nickname
      this.user.avatar = this.$store.getters.getUser.avatar
      this.hasLogin = true
    }
  }
}
</script>

<style scoped>
  .homeHeader {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 15px;
    box-sizing: border-box;
    border-bottom: 4px solid lightgray;
  }

  .homeHeader .title {
    width: 9%;
    text-align: center;
    margin-left: 7px;
  }

  .homeHeader .title img {
    width: 26px;
    vertical-align: middle;
    padding-bottom: 8px;
    display: inline-block;
  }
  .homeHeader .title span {
    font-size: 23px;
    font-family: alibaba,sans-serif;
    color: black;
    display: inline-block;
    margin-left: 4%;
  }

  .homeHeader .userInfo {
    cursor: pointer;
  }

  .el-dropdown-link span {
    font-family: alibaba,sans-serif;
  }
  .el-dropdown-link img {
    width: 35px;
    height: 35px;
    border-radius: 24px;
    margin-left: 12px;
    margin-right: 12px;
  }

  .el-dropdown-link {
    display: flex;
    align-items: center;
  }
</style>
