<template>
  <div class="left">
    <el-aside width="200px">
      <el-menu :default-openeds="['1', '12']">
        <el-menu-item index="1">
          <template slot="title">
            <router-link to="/admin_person_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-user"></i> 个人中心
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="2">
          <template slot="title">
            <router-link to="/admin_chart" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-s-data"></i> 数据中心
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="3">
          <template slot="title">
            <router-link to="/admin_photos_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-picture"></i> 图集管理</router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="4">
          <template slot="title">
            <router-link to="/admin_tags_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-price-tag"></i> 标签管理</router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="5">
          <template slot="title">
            <router-link to="/admin_category_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-menu"></i> 分类管理</router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="6">
          <template slot="title">
            <router-link to="/admin_blogs_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-notebook-1"></i> 文章管理
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="7">
          <template slot="title">
            <router-link to="/admin_comments_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-s-comment"></i> 评论管理
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="8">
          <template slot="title">
            <router-link to="/admin_messages_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-message"></i> 留言管理
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="9">
          <template slot="title">
            <router-link to="/admin_system_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-setting"></i> 系统设置
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="10">
          <template slot="title">
            <router-link to="/admin_link_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-link"></i> 友情链接
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="11">
          <template slot="title">
            <router-link to="/admin_system_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-setting"></i> 主题配色
            </router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="12">
          <template slot="title">
            <router-link to="/admin_system_list" style="color: #1f1d1d;text-decoration: none">
              <i class="el-icon-setting"></i> 系统测试
            </router-link>
          </template>
        </el-menu-item>
      </el-menu>
    </el-aside>
  </div>
</template>

<script>
export default {
  name: 'left'
}
</script>
<style scoped>

</style>
