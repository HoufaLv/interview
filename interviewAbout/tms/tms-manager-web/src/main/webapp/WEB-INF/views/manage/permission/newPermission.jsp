<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../../include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>

    <%@include file="../../include/sider.jsp" %>


    <div class="content-wrapper">
        <%-- 新增权限 --%>
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3>新增权限</h3>
                    <div class="box-tools">
                        <a href="/manage/permission" class="btn btn-primary btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <%-- 不写action 就是从哪里来,回到哪个路径中去 --%>
                    <form method="post" id="addPermissionForm">
                        <div class="form-group">
                            <h4>权限名称</h4>
                            <input type="text" name="permissionName" class="form-control">
                        </div>
                        <div class="form-group">
                            <h4>权限代号</h4>
                            <input type="text" name="permissionCode" class="form-control">
                        </div>
                        <div class="form-group">
                            <h4>权限类型</h4>
                            <%-- 使用select 来显示所有的权限类型 --%>
                            <select name="permissionType" class="form-control">
                                <option value="菜单">菜单</option>
                                <option value="按钮">按钮</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <h4>资源URL</h4>
                            <input type="text" name="permissionUrl" class="form-control">
                        </div>
                        <div class="form-group">
                            <h4>父权限</h4>
                            <select name="parentId" class="form-control">
                                <option value="0">顶级菜单</option>
                                <c:forEach items="${permissionList}" var="permission">
                                    <option value="${permission.id}">${permission.permissionName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>

        </section>

    </div>

    <%@include file="../../include/footer.jsp" %>
</div>


<%@include file="../../include/js.jsp" %>
<script>
$(function () {
    $("#saveBtn").click(function () {
        $("#addPermissionForm").submit();
    });
});
</script>
</body>
</html>

