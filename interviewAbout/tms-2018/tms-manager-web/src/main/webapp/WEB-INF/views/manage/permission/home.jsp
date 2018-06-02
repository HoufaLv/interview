<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 权限管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                权限管理
            </h1>
            <c:if test="${not empty message}">
                <p class="login-box-msg text-danger">"Congratulations! ${message}"</p>
            </c:if>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">权限列表</h3>
                    <div class="box-tools">
                        <a href="/manage/permission/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增权限</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <thead>
                        <tr>
                            <th>权限名称</th>
                            <th>权限代号</th>
                            <th>资源url</th>
                            <th>类型</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${permissionList}" var="permission">
                            <c:choose>
                                <c:when test="${permission.parentId == 0}">
                                    <tr class="treegrid-${permission.id} treegrid-expanded">
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.url}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a class="btn btn-primary btn-xs" href="/manage/permission/${permission.id}/edit" title="编辑"><i class="fa fa-pencil"></i></a>
                                            <a class="btn btn-danger btn-xs delLink" rel="${permission.id}" href="javascript:;" title="删除"><i class="fa fa-trash"></i></a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr class="treegrid-${permission.id} treegrid-expanded treegrid-parent-${permission.parentId}">
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.url}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a class="btn btn-primary btn-xs" href="/manage/permission/${permission.id}/edit" title="编辑"><i class="fa fa-pencil"></i></a>
                                            <a class="btn btn-danger btn-xs delLink" rel="${permission.id}" href="javascript:;" title="删除"><i class="fa fa-trash"></i></a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $('.tree').treegrid();
        $(".delLink").click(function () {
           var id = $(this).attr("rel");
           layer.confirm("确定要删除吗？",function (index) {
               layer.close(index);
               $.get("/manage/permission/"+id+"/del").done(function (result) {
                   if(result.status=='success'){
                       history.go(0);
                   }else{
                       layer.msg(result.message);
                   }
               }).error(function () {
                   layer.msg("服务器忙");
               });
           })
        });
    });


</script>
</body>
</html>
