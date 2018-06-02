<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 账号管理</title>
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
            <c:if test="${not empty message}">
                <p class="login-box-msg text-danger">"Congratulations! ${message}"</p>
            </c:if>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="nameMobile" placeholder="账号 或 手机号" class="form-control" value="${param.nameMobile}">
                        <select name="rolesId" class="form-control">
                            <option value="">所有账号</option>
                            <c:forEach items="${rolesList}" var="roles">
                                <option value="${roles.id}" ${param.rolesId == roles.id?'selected':''}>${roles.rolesName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">账号列表</h3>
                    <%--<shiro:hasPermission name="account:add"></shiro:hasPermission>--%>
                        <div class="box-tools">
                            <a href="/manage/account/new" class="btn btn-success btn-sm">
                                <i class="fa fa-plus"></i>新增账号
                            </a>
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
                                    <td>${account.accountState}</td>
                                    <td>
                                        <fmt:formatDate value="${account.createTime}"></fmt:formatDate>
                                    </td>
                                    <td>
                                        <a href="/manage/account/${account.id}/edit"><i class="fa fa-pencil"></i></a>
                                        <a class="delLink" rel="${account.id}" href="javascript:;"><i class="fa fa-trash"></i></a>
                                    </td>
                                </tr>
                                
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
    $(function(){
        //删除
        $(".delLink").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该用户？",function (index) {
                layer.close(index);
                $.get("/manage/account/"+id+"/del").done(function (result) {
                    if(result.status == 'success') {
                        window.history.go(0);
                    } else {
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
