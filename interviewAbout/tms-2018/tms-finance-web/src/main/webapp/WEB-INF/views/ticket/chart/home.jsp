<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票统计</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_chart"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">年票统计</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">总数量</span>
                                <h5 class="description-header">${resultMap.total}</h5>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">已入库</span>
                                <h5 class="description-header">${resultMap.in_num}</h5>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">已下发</span>
                                <h5 class="description-header">${resultMap.out_num}</h5>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">已销售</span>
                                <h5 class="description-header">${resultMap.sale_num}</h5>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">已挂失</span>
                                <h5 class="description-header">${resultMap.lost_num}</h5>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <span class="description-text">已过期</span>
                                <h5 class="description-header">${resultMap.outtime_num}</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Main content -->
        <section class="content">
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
</body>
</html>
