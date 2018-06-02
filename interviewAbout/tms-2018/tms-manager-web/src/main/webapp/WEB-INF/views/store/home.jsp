<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 售票点管理</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="manage_store"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                售票点管理
            </h1>
            <c:if test="${not empty message}">
                <p class="login-box-msg text-danger">"Congratulations! ${message}"</p>
            </c:if>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form method="get" class="form-inline">
                        <input type="text" name="storeName" class="form-control" placeholder="售票点名称" value="${param.storeName}">
                        <input type="text" name="storeManager" class="form-control" placeholder="联系人" value="${param.storeManager}">
                        <input type="text" name="storeTel" class="form-control" placeholder="联系电话" value="${param.storeTel}">
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">销售点管理</h3>
                    <%--<shiro:hasPermission name="account:add"></shiro:hasPermission>--%>
                        <div class="box-tools">
                            <a href="/ticketstore/new" class="btn btn-success btn-sm">
                                <i class="fa fa-plus"></i>新增售票点
                            </a>
                        </div>

                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>销售点名称</th>
                            <th>联系人</th>
                            <th>联系电话</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pageInfo.list}" var="store">
                                <tr>
                                    <td><a href="/ticketstore/${store.id}/info">${store.storeName}</a></td>
                                    <td>${store.storeManager}</td>
                                    <td>${store.storeTel}</td>
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

<%@include file="../include/js.jsp"%>
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
