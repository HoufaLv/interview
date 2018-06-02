<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 权限编辑</title>
    <%@include file="../../include/css.jsp"%>
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
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">修改权限</h3>
                    <div class="box-tools">
                        <a href="/manage/permission" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>权限名称</label>
                            <input type="hidden" name="id" class="form-control" value="${permission.id}">
                            <input type="text" name="permissionName" class="form-control" value="${permission.permissionName}">
                        </div>
                        <div class="form-group">
                            <label>权限代号</label>
                            <input type="text" name="permissionCode" class="form-control" value="${permission.permissionCode}">
                        </div>
                       <%-- <div class="form-group">
                            <label>权限类型</label>
                            <select name="permissionType" class="form-control">
                                <option value="菜单" ${permission.permissionType=="菜单"?'selected':''}>菜单</option>
                                <option value="按钮" ${permission.permissionType=="按钮"?'selected':''}>按钮</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>父权限</label>
                            <select name="parentId" class="form-control">
                                <option value="0">顶级菜单</option>
                                <c:forEach items="${permissionList}" var="permission">
                                    <option value="${permission.id}">${permission.permissionName}</option>
                                </c:forEach>
                            </select>
                        </div>--%>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    })
</script>
</body>
</html>
