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

    <%@include file="../../include/css.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <%@include file="../../include/sider.jsp"%>


    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">更新账号</h3>
                </div>
                <div class="box-body">
                    <%-- 从哪来回哪去 --%>
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
                                        <input type="checkbox" ${flag ? 'checked' : ''} value="${roles.id}" name="rolesId"> ${roles.rolesName}
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

    </div>
    <!-- /.content-wrapper -->


    <%@include file="../../include/footer.jsp"%>
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

