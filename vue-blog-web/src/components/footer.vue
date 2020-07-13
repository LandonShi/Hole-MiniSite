<template>
    <div class="footer">
        <el-container>
            <el-footer>
                <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="8">
                        <div class="footer-inner">
                            <div class="copyright">
                                <span>©</span>
                                <span>2020</span>
                                <span> 清风明月 </span>
                                <span>皖ICP备19017143</span>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </el-footer>
        </el-container>
    </div>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style scoped>
    div.footer .footer-inner {
      text-align: center;
    }
    div.footer .footer-inner i {
      color: #41e241;
      font-size: 15px;
    }
    div.footer .footer-inner .copyright span {
      color: darkgray;
      font-size: 13px;
      padding: 2px;
    }
</style>
