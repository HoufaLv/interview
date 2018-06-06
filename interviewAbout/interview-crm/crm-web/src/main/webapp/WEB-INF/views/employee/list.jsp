<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <%@include file="../include/css.jsp"%>

    <link rel="stylesheet" href="/static/plugins/tree/css/metroStyle/metroStyle.css">
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-2">
                    <div class="box">
                        <div class="box-body">
                            <button id="addDept" class="btn btn-default">添加部门</button>
                            <ul id="ztree" class="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">员工管理</h3>
                            <div class="box-tools pull-right">
                                <button id="addAccountBtn" type="button" class="btn btn-box-tool"  title="Collapse">
                                    <i class="fa fa-plus"></i> 添加员工</button>
                            </div>
                        </div>
                        <div class="box-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>部门</th>
                                    <th>手机</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>

    <div class="modal fade" id="addAccountModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加新员工</h4>
                </div>
                <div class="modal-body">
                    <form id="addAccountForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <label>密码（默认密码123123）</label>
                            <input type="password" class="form-control" name="password" value="000000">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" class="form-control" name="mobile">
                        </div>
                        <div class="form-group">
                            <label>部门</label>
                            <div id="deptArea"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveAccountBtn">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<%--前端树形插件--%>
<script src="/static/plugins/tree/js/jquery.ztree.all.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/validate/jquery.validate.js"></script>
<script>
    $(function(){
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            async:{
                enable:true,
                url:"/manage/account/depts.json"
            },
            callback:{
                onClick:function(event,treeId,treeNode,clickFlag){
                    layer.msg(treeNode.name);
                }
            }
        };

        var tree = $.fn.zTree.init($("#ztree"), setting);

        //点击按钮的时候,弹出框,输入部门,后台添加
        $("#addDept").click(function () {
            layer.prompt({title:"请输入部门名称"},function (text, index) {
                layer.close(index);
                layer.msg("添加部门: " + text);

                $.post("/employee/dept/new",{"deptName":text}).done(function (data) {
                    //如果data.state == 'success'
                    if (data.state == 'success'){
                        layer.msg("添加成功");
                        tree.reAsyncChildNodes(null, "refresh");
                    }else{
                        layer.msg(data.message);
                    }
                }).error(function () {
                    layer.msg("服务器异常,请稍后再试");
                });
            })
        });

        //添加员工
        $("#addAccountBtn").click(function(){
            //ajax加载最新的部门列表
            $("#deptArea").html("");
            $.post("/manage/account/depts.json").done(function(data){
                if(!data.length || data.length == 1) {
                    layer.msg("请先创建部门");
                    return;
                }

                for(var i = 0;i < data.length;i++) {
                    var obj = data[i];
                    if(obj.id != 1000) {
                        var html = '<label  class="checkbox-inline"><input type="checkbox" name="deptId" value="' + obj.id + '"> ' + obj.name + '</label>';
                        $(html).appendTo($("#deptArea"));
                    }
                }

                $("#addAccountModal").modal({
                    show:true,
                    backdrop:'static'
                });
            }).error(function(){
                layer.msg("服务器异常");
            });
        });

        $("#saveAccountBtn").click(function () {
            $("#addAccountForm").submit();
        });

        $("#addAccountForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                userName:{
                    required:true
                },
                password:{
                    required:true
                },
                mobile:{
                    required:true
                },
                deptId:{
                    required:true
                }
            },
            messages:{
                userName:{
                    required:"请输入账号"
                },
                password:{
                    required:"请输入密码"
                },
                mobile:{
                    required:"请输入手机号码"
                },
                deptId:{
                    required:"至少选择一个部门"
                }
            },
            submitHandler:function(){
                $.post("/manage/account/new",$("#addAccountForm").serialize()).done(function(data){
                    if(data.state == 'success') {
                        $("#addAccountForm")[0].reset();
                        $("#addAccountModal").modal('hide');
                        layer.msg("添加成功");
                    } else {
                        layer.msg(data.message);
                    }
                }).error(function(){
                    layer.msg("服务器异常");
                });
            }
        });

    });
</script>
</body>
</html>
