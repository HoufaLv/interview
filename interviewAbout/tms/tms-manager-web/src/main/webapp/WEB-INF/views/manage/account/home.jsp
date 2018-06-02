<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>账号管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../../include/css.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>

    <!-- =============================================== -->

    <%@include file="../../include/sider.jsp" %>


    <!-- =============================================== -->

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <section class="content-header">
            <h1>
                账号管理
            </h1>
        </section>

        <section class="content">
            <%--展示账号--%>
            <div class="box">

                <%--新增账号选项--%>
                <div class="box-header">
                    <div class="box-tools">
                        <a href="/manage/account/new" class="btn btn-success btn-sm">新增账户</a>
                    </div>
                </div>

                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>账号</th>
                            <th>手机号码</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${accountList}" var="account">
                            <tr>
                                <td>${account.accountName}</td>
                                <td>${account.accountMobile}</td>
                                <td>
                                    <c:forEach items="${account.rolesList}" var="roles">
                                        ${roles.rolesName}
                                    </c:forEach>
                                </td>
                                <td>
                                        ${account.accountState}
                                </td>
                                <td>
                                    <fmt:formatDate value="${account.createTime}"/>
                                </td>
                                <td>
                                    <shiro:hasRole name="superAdmin">
                                        <a class="btn btn-success btn-xs"
                                           href="/manage/account/${account.id}/update">修改</a>
                                    </shiro:hasRole>
                                    <a rel="${account.id}" class="btn btn-danger btn-xs delAccount" href="javascirpt:;">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </section>

    </div>
    <!-- /.content-wrapper -->


    <%@include file="../../include/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp" %>

<%-- 引入layer.js --%>
<script src="/static/plugins/layer/layer.js"></script>
<script>

    $(".delAccount").click(function () {
        var id = $(this).attr("rel");
        layer.confirm("确定要删除吗", function (index) {
            layer.close(index);
            $.get("/manage/account/" + id + "/del").done(function (result) {
                if (result.status == 'success') {
                    layer.msg("删除成功");
                    history.go(0);
                } else {
                    layer.msg(result.message);
                }
            }).error(function () {
                layer.msg("服务器忙,请稍后再试")
            });
        })
    });
</script>
</body>
</html>

