<template>
    <div class="top">
        <el-row>
            <el-col :xs="24" :sm="24" :md="24">
                <div class="top-band"></div>
            </el-col>
        </el-row>
        <el-row type="flex" class="tac hidden-sm-and-up" justify="center">
            <el-col :xs="24" :sm="24" :md="24">
                <el-collapse accordion>
                    <el-collapse-item>
                        <template slot="title">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="header-icon el-icon-s-unfold"></i>
                        </template>
                        <div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-s-home" />首页</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-s-marketing" />归档</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-collection" />分类</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-collection-tag" />标签</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-picture" />图集</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-user-solid" />关于我们</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-chat-dot-square" />留言</a>
                            </div>
                            <div class="nav-toggle-nav">
                                <a href="#"><i class="el-icon-search" />搜索</a>
                            </div>
                        </div>
                    </el-collapse-item>
                </el-collapse>
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
  name: 'Top'
}
</script>

<style scoped>
    div.top-band {
        width: 100%;
        height: 3px;
        background: #000;
    }
    div.top i {
        color: #41e241;
        font-size: 20px;
    }
    div.nav-toggle-nav {
        margin-left: 6%;
        margin-top: 2%;
    }
    div.nav-toggle-nav a {
        text-decoration: none;
        font-size: 14px;
        color: gray;
    }
    div.nav-toggle-nav a i {
        color: #41e241;
        margin-right: 2%;
    }
</style>
