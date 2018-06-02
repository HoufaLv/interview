<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 新增年票下发</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_out"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>新增年票下发</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>下发日期</label>
                            <input type="text" class="form-control" value="${today}" disabled>
                        </div>
                        <div class="form-group">
                            <label>下发网点</label>
                            <select name="storeAccountId" class="form-control">
                                <c:forEach items="${ticketStoreList}" var="store">
                                    <option value="${store.id}">${store.storeName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>起始票号</label>
                            <input type="text" class="form-control" name="beginTicketNum">
                        </div>
                        <div class="form-group">
                            <label>截止票号</label>
                            <input type="text" class="form-control" name="endTicketNum">
                        </div>
                        <div class="form-group">
                            <label>单价/张</label>
                            <input type="text" class="form-control" name="price">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="saveBtn" class="btn btn-primary pull-right">保存</button>
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
    });
</script>
</body>
</html>
