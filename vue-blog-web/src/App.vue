<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    list-style-type:none;
  }
  body {
    width: 100%;
    overflow-y: auto !important;
    padding-right: 0 !important;
  }
  .el-drawer {
    background: #1f1f23 !important;
  }
</style>
