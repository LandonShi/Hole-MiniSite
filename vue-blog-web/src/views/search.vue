<template>
    <div>
      <Top></Top>
      <Header></Header>
      <div class="search">
        <el-container>
          <el-main>
            <!-- 搜索框 -->
            <el-row type="flex" justify="center">
              <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                <div class="search-wrap">
                  <el-input class="search-wrap-item" suffix-icon="el-icon-search"></el-input>
                </div>
              </el-col>
            </el-row>
            <!-- 快捷搜索标签 -->
            <el-row type="flex" justify="center">
              <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                <div class="search-tags">
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      标签一
                    </a>
                  </el-tag>
                  <el-tag class="search-wrap-tag">
                    <a href="#">
                      ...
                    </a>
                  </el-tag>
                </div>
              </el-col>
            </el-row>
            <!-- 搜索数目 -->
            <el-row type="flex" justify="center">
              <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                <div class="search-count">
                  <i class="el-icon-more" /><span>查询到 8 条结果</span>
                </div>
              </el-col>
            </el-row>
            <!-- 搜索结果 -->
            <el-row type="flex" justify="center">
              <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="10">
                <el-alert class="search-item-show" type="success" :closable="false">
                  <a href="#"></a>
                </el-alert>
                <el-alert class="search-item-show" type="success" :closable="false">
                  <a href="#"></a>
                </el-alert>

              </el-col>
            </el-row>
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
  name: 'Search',
  components: {
    Top,
    Header,
    Tool,
    Footer
  }
}
</script>

<style scoped>
    div.search {
        margin-top: 15%;
    }
    div.search .search-wrap {
        text-align: center;
        margin: 5% auto;
    }
    div.search .search-wrap .search-wrap-item {
        width: 75%;
    }

    div.search .search-tags {
        text-align: center;
    }
    div.search .search-tags .search-wrap-tag {
        margin: 8px;
    }
    div.search .search-tags .search-wrap-tag a {
        text-decoration: none;
        font-family: alibaba,sans-serif;
        color: #ada7a7;
    }
    div.search .search-tags .search-wrap-tag a:hover {
        color: #409eff;
    }

    div.search .search-count {
        margin-top: 3%;
    }
    div.search .search-count span {
        font-family: alibaba,sans-serif;
        font-size: 13px;
        font-weight: 600;
        color: #9e9999;
    }
    div.search .search-count i {
        color: #41e241;
        margin-right: 3%;
    }
    div.search .search-item-show {
        margin-top: 3%;
    }
</style>
