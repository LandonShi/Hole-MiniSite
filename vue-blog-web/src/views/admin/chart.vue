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
              <el-page-header content="数据中心" style="margin-top: 15px"></el-page-header>
            </el-header>
            <el-main class="main-item" style="background-color: whitesmoke">
              <el-row>
                <div class="line-drive">站点信息</div>
              </el-row>
              <el-row style="margin-top: 25px;margin-bottom: 25px" :gutter="12">
                <el-col :span="3">
                  <el-card class="card-item" shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-s-comment" />
                      </div>
                      <div class="wrap-item">
                        <span>255</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-message" />
                      </div>
                      <div class="wrap-item">
                        <span>520</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-picture" />
                      </div>
                      <div class="wrap-item">
                        <span>15</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-menu" />
                      </div>
                      <div class="wrap-item">
                        <span>25</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-price-tag" />
                      </div>
                      <div class="wrap-item">
                        <span>289</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-link" />
                      </div>
                      <div class="wrap-item">
                        <span>20</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-view" />
                      </div>
                      <div class="wrap-item">
                        <span>2000</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="3">
                  <el-card shadow="hover">
                    <div class="wrap-info">
                      <div class="wrap-item">
                        <i class="el-icon-aim" />
                      </div>
                      <div class="wrap-item">
                        <span>20</span>
                      </div>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-row>
                <div class="line-drive">用户浏览器资源分布与访问时间段分布</div>
              </el-row>
              <el-row class="el-chart">
                <el-col :span="12">
                  <div id="chartPie" style="width:100%; height:400px;"></div>
                </el-col>
                <el-col :span="12">
                  <div id="chartBar" style="width:100%; height:400px;"></div>
                </el-col>
              </el-row>
              <el-row>
                <div class="line-drive">活跃分布</div>
              </el-row>
              <el-row class="el-chart">
                <el-col :span="24">
                  <div id="chartLine" style="width:100%; height:400px;"></div>
                </el-col>
              </el-row>
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
import echarts from 'echarts'

export default {
  name: "chart",
  components: {
    top,
    left
  },
  data() {
    return {
      chartColumn: null,
      chartBar: null,
      chartLine: null,
      chartPie: null,
      website: ''
    }
  },
  mounted: function () {
    this.drawCharts()
    this.getWebSitInfo()
  },
  methods: {
    drawLineChart() {
      this.chartLine = echarts.init(document.getElementById('chartLine'));
      this.chartLine.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['用户活跃度']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['1', '2', '3', '4', '5', '6', '7','8','9','10','11','12','13','14','15','16','17','18','19','20','21',
            '22','22','23','24','25','26','27','28','29','30','31']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '用户活跃度',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210
              , 132, 101, 134, 90, 230, 210]
          }
        ],
        color:['#2bd20e']
      });
    },
    drawPieChart() {
      this.chartPie = echarts.init(document.getElementById('chartPie'));
      this.chartPie.setOption({
        title: {
          text: '浏览器分布',
          subtext: '截止2018-10-10',
          x: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['Chrome', 'FireFox', 'IE', 'Edge', '其他']
        },
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              {value: 1548, name: 'Chrome'},
              {value: 310, name: 'FireFox'},
              {value: 234, name: 'IE'},
              {value: 135, name: 'Edge'},
              {value: 335, name: '其他'}
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(36,31,31,0.5)'
              }
            }
          }
        ],
        color:['#0a8dee','#ff2e89', '#44ff5c', '#31cbff','#ff8687','#33332e','#72a0ff']
      });
    },
    drawBarChart() {
      this.chartBar = echarts.init(document.getElementById('chartBar'));
      this.chartBar.setOption({
        color: ['#f35a07'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: ['6-7', '7-8', '8-9', '9-10', '10-11', '11-12', '12-13', '12-13', '13-14'
              , '14-15', '15-16', '16-17', '17-18', '18-19', '19-20', '20-21', '21-22', '22-23', '23-24', '0-1', '1-2'
              , '2-3', '3-4', '4-5', '5-6'],
            axisTick: {
              alignWithLabel: true
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '直接访问',
            type: 'bar',
            barWidth: '60%',
            data: [10, 52, 200, 334, 390, 330, 220,10, 52, 200, 334, 390, 330, 220,10, 52, 200, 334, 390, 330, 220,
              10, 52, 200,150]
          }
        ]
      });
    },
    drawCharts() {
      this.drawLineChart()
      this.drawPieChart()
      this.drawBarChart()
    },
    getWebSitInfo() {
      const _this = this
      this.$axios.get('website', {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(resp => {
        _this.website = resp.data.data
      })
    }
  },
  updated: function () {
    this.drawCharts()
  }
}
</script>

<style scoped>
  .el-row {
    margin-top: 12px;
  }
  .el-card {
    border-top: solid 4px dodgerblue;
  }
  .el-card:hover {
    border: solid 1px lightgray;
  }
  .wel-info span {
    float: left;
  }
  .wel-info b {
    padding-left: 8px;
    line-height: 32px;
  }
  .wel-info a {
    padding-left: 15px;
    color: #3186c8;
  }
  .wel-info a:hover {
    color: #F60;
  }
  .wel-info i {
    font-style: normal;
    padding-left: 8px;
    line-height: 32px;
    font-family: alibaba,sans-serif;
  }
  .el-chart {
    padding: 2%;
  }
  .wrap-info {
    text-align: center;
  }
  .wrap-item {
    display: inline-block;
  }
  .wrap-item i {
    font-size: 18px;
    color: #c6bbbb;
  }
  .wrap-item i:hover {
    color: dodgerblue;
  }
  .wrap-item span {
    font-size: 22px;
    font-weight: 600;
    font-family: alibaba,sans-serif;
    color: lightgray;
  }
  .wrap-item span:hover {
    color: dodgerblue;
  }
  .line-drive {
    height: 30px;
    width: 100%;
    border-left: solid 5px dodgerblue;
    border-bottom-left-radius: 5px;
    border-top-left-radius: 5px;
    background-color: white;
    font-size: 16px;
    font-family: alibaba,sans-serif;
    font-weight: 600;
    color: #5f5b5b;
    padding-top: 7px;
    padding-left: 10px;
  }

</style>
