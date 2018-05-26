<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>tms - 账号修改</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Blank page
                <small>it all starts here</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">编辑账号</h3>
                </div>
                <div class="box-body">
                    <form method="post" class="saveForm">
                        <input type="hidden" name="id" value="${account.id}">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" class="form-control" name="accountName" value="${account.accountName}">
                        </div>
                        <div class="form-group">
                            <label>手机号码(用于登录)</label>
                            <input type="text" class="form-control" name="accountMobile" value="${account.accountMobile}">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <!-- 为什么要将所有的角色信息和当前帐号的角色信息一起传过来 -->
                            <!-- 一次for 循环,展示所有的角色,第二次for循环,展示当前账户对应的角色 -->

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
                                        <input type="checkbox" ${flag ? 'checked' : ''} value="${roles.id}" name="rolesIds"> ${roles.rolesName}
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
    });
</script>
</body>
</html>
