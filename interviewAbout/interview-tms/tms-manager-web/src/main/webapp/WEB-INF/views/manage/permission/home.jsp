<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <%@include file="../../include/css.jsp" %>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>权限管理</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <c:if test="${not empty message}">
                    <p class="login-box-msg text-danger">${message}</p>
                </c:if>
                <div class="box-header">
                    <h3 class="box-title">权限列表</h3>
                    <div class="box-tools">
                        <a href="/manage/permission/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增权限</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <tr>
                            <th>权限名称</th>
                            <th>权限代号</th>
                            <th>类型</th>
                            <th>#</th>
                        </tr>

                        <%--<thead>
                            <tr>
                                <th>权限名称</th>
                                <th>权限代号</th>
                                <th>类型</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="treegrid-1 ">
                                <td>权限管理</td>
                                <td>permission:home</td>
                                <td>菜单</td>
                            </tr>
                            <tr class="treegrid-2 treegrid-parent-1">
                                <td>添加权限</td>
                                <td>permission:add</td>
                                <td>按钮</td>
                            </tr>
                            <tr class="treegrid-3 treegrid-parent-1">
                                <td>修改权限</td>
                                <td>permission:update</td>
                                <td>按钮</td>
                            </tr>--%>

                        <c:forEach items="${permissionList}" var="permission">
                            <c:choose>
                                <%--展示--%>
                                <c:when test="${permission.parentId == 0}">
                                    <tr class="treegrid-${permission.id} treegrid-expanded">
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a href="/manage/permission/${permission.id}/update">修改</a>
                                            <a href="javascript:;" rel="${permission.id}">删除</a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <%--根据 treegrid-parent 来决定自己是不是子元素--%>
                                    <tr class="treegrid-${permission.id} treegrid-expanded treegrid-parent-${permission.parentId}">
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a href="/manage/permission/${permission.id}/update">修改</a>
                                            <a href="javascript:;" rel="${permission.id}">删除</a>
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

<%@include file="../../include/js.jsp" %>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
    $(function () {
        $('.tree').treegrid();


    });
</script>

</body>
</html>
