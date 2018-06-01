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
                                <button type="button" class="btn btn-box-tool"  title="Collapse">
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
                                <tr>
                                    <td>tom</td>
                                    <td>开发部</td>
                                    <td>153223234522</td>
                                    <td>
                                        <a href="">禁用</a>
                                        <a href="">删除</a>
                                        <a href="">编辑</a>
                                    </td>
                                </tr>
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
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<%--前端树形插件--%>
<script src="/static/plugins/tree/js/jquery.ztree.all.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
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
    });
</script>
</body>
</html>
