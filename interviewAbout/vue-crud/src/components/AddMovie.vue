<template>
    <div id="AddMovie">
          <el-button @click='list' type="primary" plain>返回列表</el-button>
        
        <el-form style="margin-top:60px" ref="form" :model="movie" label-width="80px">
             <el-form-item label="电影名称">
                <el-input v-model="movie.title"></el-input>
             </el-form-item>
             <el-form-item label="评分">
                <el-input v-model="movie.rate"></el-input>
            </el-form-item>
            <el-form-item label="发行时间">
                <el-input v-model="movie.releaseYear"></el-input>
            </el-form-item>
            <el-form-item label="上映时间">
                <el-input v-model="movie.sendTime"></el-input>
            </el-form-item>
            <el-form-item label="导演">
                <el-input v-model="movie.director"></el-input>
            </el-form-item>
             <el-form-item>
                <el-button type="primary" @click="onSubmit">新增电影</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>

import api from '../const/url'

    export default {
        // 这个name 和根元素的name 保持一致
        name: 'AddMovie',
        data(){
            return {
                movie:{}
            }
        },
        methods:{
            list : function() {
                this.$router.push("/");
            },
            // 当点击新增电影的时候触发这个方法
            onSubmit : function(){
                this.$http.post(api.AddMovie,this.movie).then(response => {
                    if (response.data.status == "success") {
                        this.$message({
                            message : "新增成功",
                            type:"success"
                        });
                        this.$router.push("/");
                    }
                }).catch(error => {
                    // 如果出现异常
                    this.$message.error("系统提示:" + error.message);
                });
            }
        }
    }
</script>