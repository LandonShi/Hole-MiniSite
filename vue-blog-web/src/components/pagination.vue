<template>
    <div class="pagination">
        <el-container>
            <el-main>
                <el-row type="flex" justify="center">
                    <el-col :xs="24" :sm="20" :md="16" :lg="12" :xl="8">
                        <div class="pagination-inner">
                            <el-pagination background layout="prev, pager, next" :total="50"></el-pagination>
                        </div>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
  name: 'Pagination'
}
</script>

<style scoped>
    div.pagination .pagination-inner {
        margin: 0 auto;
        text-align: center;
    }
</style>
