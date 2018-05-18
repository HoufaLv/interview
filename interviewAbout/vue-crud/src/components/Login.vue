<template>
    <div id="Login">
        <el-form style="margin-top:60px" ref="form" :model="account" label-width="80px">
            <el-form-item label="账户">
                <el-input v-model="account.userName"></el-input>
             </el-form-item>
             <el-form-item label="密码">
                <el-input v-model="account.password"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">登陆</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default{
        name: "Login",
        data(){
            return {
                account:{}
            }
        },
        methods:{
            onSubmit:function() {
                this.$http.post("/login",this.account).then(response=>{
                    // 如果登陆成功
                    if (response.data.status == 'success') {
                        var token = response.data.result;
                        // token 可以存在浏览器的localStorage中去
                        window.localStorage.setItem("jwtToken",token);

                        this.$messge.success("登陆成功");
                        // 登陆成功之后跳转到 home页面去
                        this.$route.push("/home");
                    }else{
                        // 如果登陆失败
                        this.$message.error(response.data.message);
                    }
                }).catch(error=>{
                    this.$message.error(error.message);
                });
            }
        }
    }
</script>

<style>
    /* 尝试使用less 来做css */
    /* .login:{
        width:200px;
    } */
</style>