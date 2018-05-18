<template>
    <div id="home">
        <!-- 添加电影 -->
        <el-button @click='addMovie' type="primary" plain>添加电影</el-button>
        <el-table
      :data="movies"
      style="width: 100%">
            <el-table-column
                prop="title"
                label="电影名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="rate"
                label="评分"
                width="180">
            </el-table-column>
            <el-table-column
                prop="releaseYear"
                label="发行时间">
            </el-table-column>
            <el-table-column
                prop="sendTime"
                label="上映时间">
            </el-table-column>
            <el-table-column
                prop="director"
                label="导演">
            </el-table-column>
    </el-table>
    </div>
</template>

<script>

// 导入const中的对象,命名为api
import api from '../const/url'

    export default{
        name:'home',
        data(){
            return {
                // 取movie 数据就是给这个数组添加值
                movies: []
            }
        },
        methods:{
            addMovie :function(){
                // 使用$route.push 方法跳转到另外一个路由去,在那个路由中配置相关路径
                // 先在路由中找到 path ,再跳转到相应的components中去
                this.$router.push("/new")
            }
        },
        mounted:function () {
            // 将会去请求这个路径
            this.$http.get(api.movieHome).then(response=>{
                // 获取到响应的对象
                this.movies = response.data.result;
            }).catch(error=>{
                // 如果有错误,提示消息
                this.$message.error("系统提示: " + error.message);
            });
        }
    }
</script>
<style>

</style>

