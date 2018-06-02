<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票查询</title>
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="search_ticket"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">


        <!-- Main content -->
        <section class="content">
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" class="form-control" name="ticketNum" placeholder="年票号码" value="${param.ticketNum}">
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <c:if test="${not empty message or not empty ticket}">
                <div class="box">
                    <div class="box-body">
                        <c:if test="${not empty message}">
                            <div class="alert alert-info">${message}</div>
                        </c:if>
                        <ul class="list-group">
                            <li class="list-group-item">年票号码：${ticket.ticketNum}</li>
                            <li class="list-group-item">年票状态：${ticket.ticketState}</li>
                            <li class="list-group-item">下发时间：${ticket.ticketOutTime}</li>
                            <li class="list-group-item">
                                有效期：<fmt:formatDate value="${ticket.ticketValidityStart}"/>/<fmt:formatDate value="${ticket.ticketValidityEnd}"/></li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
</body>
</html>
