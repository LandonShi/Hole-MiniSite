<template>
    <div class="login">
        <el-form class="login-container" label-position="left" label-width="0px" v-loading="loading">
            <h3 class="login_title">后台登录</h3>
            <el-form-item prop="account">
                <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item prop="checkPass">
                <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
            <el-form-item class="wrap-btn">
                <el-button class="login-btn" type="primary" @click="login">登  录</el-button>
                <el-button class="login-btn" type="danger">重  置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
export default {
  name: 'login',
  data () {
    return {
      checked: true,
      loginForm: {
        username: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    login () {
      //校验
      if (this.loginForm.username.length === 0) {
        this.$notify.error({
          title: '错误', message: '请输入账号'
        })
        return
      }
      if (this.loginForm.password.length === 0) {
        this.$notify.error({
          title: '错误', message: '请输入密码'
        })
        return
      }
      this.loading = true
      const _this = this
      this.$axios.post('login', this.loginForm).then(resp => {
        const jwt = resp.headers['authorization']
        const userInfo = resp.data.data
        _this.$store.commit("SET_TOKEN", jwt)
        _this.$store.commit("SET_USER_INFO", userInfo)
        _this.$router.push("/admin_person_list")
      })
    }
  }
}
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 150px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: ghostwhite;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0 auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0 0 35px 0;
    text-align: left;
  }
  div.login .wrap-btn {
    width: 100%;
    text-align: center;
  }

  div.login .wrap-btn .login-btn {
    margin: 0 auto;
    width:40%;
  }
</style>
