<template>
  <div style="background: white">
    <el-container>
      <el-header style="padding: 0;margin: 0">
        <top></top>
      </el-header>
      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px">
          <left></left>
        </el-aside>
        <el-main class="main-wrap">
          <el-container>
            <el-header>
              <el-page-header content="系统设置" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>系统信息</span>
                </div>
                <div class="text item">
                  <el-table :data="tableData" border style="width: 100%">
                    <el-table-column prop="date" label="获取日期" width="280"></el-table-column>
                    <el-table-column prop="key" label="属性" width="280"></el-table-column>
                    <el-table-column prop="value" label="属性值"></el-table-column>
                  </el-table>
                </div>
              </el-card>
            </el-main>
          </el-container>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import top from "../../components/admin/top"
  import left from "../../components/admin/left"
  export default {
    name: "system",
    components: {
      top,
      left
    },
    data() {
      return {
        tableData: [
          {
            date: '2016-05-02',
            key: '服务器计算机名',
            value: 'http://127.0.0.1/'
          },
          {
            date: '2016-05-02',
            key: '服务器IP地址',
            value: '192.168.1.1'
          },
          {
            date: '2016-05-02',
            key: '服务器域名',
            value: 'www.xxx.net'
          },
          {
            date: '2016-05-02',
            key: '服务器端口',
            value: '80'
          },
          {
            date: '2016-05-02',
            key: '服务器IIS版本',
            value: 'Microsoft-IIS/6.0'
          },
          {
            date: '2016-05-02',
            key: '本文件所在文件夹',
            value: 'D:\\WebSite\\'
          },
          {
            date: '2016-05-02',
            key: '服务器操作系统',
            value: 'Microsoft Windows NT 5.2.3790 Service Pack 2'
          },
          {
            date: '2016-05-02',
            key: '系统所在文件夹',
            value: 'C:\\WINDOWS\\system32'
          },
          {
            date: '2016-05-02',
            key: '服务器脚本超时时间',
            value: '30000秒'
          },
          {
            date: '2016-05-02',
            key: '服务器的语言种类',
            value: 'Chinese (People\'s Republic of China)'
          },
          {
            date: '2016-05-02',
            key: '系统使用数据库',
            value: 'MySQL 5.5'
          }
        ]
      }
    }
  }
</script>

<style scoped>
  .el-row {
    font-size: 14px;
    font-family: alibaba,sans-serif;
  }
</style>
