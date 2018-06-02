<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 账号编辑</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                账号管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">编辑账号</h3>
                    <div class="box-tools">
                        <a href="/manage/account" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" class="saveForm">
                        <input type="hidden" name="id" value="${account.id}">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" class="form-control" name="accountName" value="${account.accountName}">
                        </div>
                        <div class="form-group">
                            <label>手机号码（用于登录）</label>
                            <input type="text" class="form-control" name="accountMobile" value="${account.accountMobile}">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <c:forEach items="${rolesList}" var="roles">
                                    <c:set var="flag" value="false"/>
                                    <c:forEach items="${accountRolesList}" var="accountRoles">
                                        <c:choose>
                                            <c:when test="${roles.id == accountRoles.id}">
                                                <c:set var="flag" value="true"/>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                    <div class="checkbox-inline">
                                        <input type="checkbox" value="${roles.id}" ${flag == true?'checked':''} name="rolesIds">${roles.rolesName}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
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
            $(".saveForm").submit();
        });
    })
</script>
</body>
</html>
