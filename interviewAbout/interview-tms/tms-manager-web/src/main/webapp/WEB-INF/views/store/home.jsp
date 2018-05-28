<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 售票点管理</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="ticket_store"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                售票点管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <c:if test="${not empty message}">
                    <p class="login-box-msg text-danger">${message}</p>
                </c:if>
                <div class="box-header">
                    <h3 class="box-title">销售点列表</h3>
                    <div class="box-tools">
                        <a href="/ticketstore/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增销售点</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>销售点名称</th>
                                <th>联系人</th>
                                <th>联系电话</th>
                                <th></th>
                            </tr>
                        </thead>
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
</body>
</html>
