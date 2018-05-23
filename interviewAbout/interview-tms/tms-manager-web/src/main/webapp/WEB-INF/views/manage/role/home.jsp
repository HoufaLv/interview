<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>tms 角色管理</title>
    <%@include file="../../include/css.jsp" %>
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>角色管理</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <c:if test="${not empty message}">
                    <p class="text-danger login-box-msg">${message}</p>
                </c:if>

                <div class="box-header">
                    <h3 class="box-title">角色列表</h3>
                    <div class="box-tools">
                        <a href="/manage/roles/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增角色</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <tbody>
                        <%--一层循环遍历rolesList--%>
                        <c:forEach items="${rolesList}" var="role">
                            <tr class="bg-blue-active">
                                <td>角色名称：<strong>${role.rolesName}</strong></td>
                            </tr>
                            <c:forEach items="${role.permissionList}" var="permission">
                                <td class="fa fa-circle">${permission.permissionName}</td>
                            </c:forEach>
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
</body>
</html>
