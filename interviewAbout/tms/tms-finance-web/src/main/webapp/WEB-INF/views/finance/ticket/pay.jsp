<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
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
                <small>缴费相关</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box-header">
                <h4 class="box-title">售票点票段缴费</h4>
            </div>
            <div class="box-body">
                <form id="saveForm" method="post">
                    <input type="hidden" name="id" value="${record.id}">
                    <div class="form-group">
                        <label>缴费金额</label>
                        <input type="text" class="form-control" disabled value="${record.totalprice}">
                    </div>
                    <div class="form-group">
                        <label>支付方式(目前仅支持现金和刷卡)</label>
                        <select name="payType" class="form-control">
                            <option value="现金">现金</option>
                            <option value="刷卡">刷卡</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="box-footer">
                <button class="btn btn-primary pull-right" id="saveBtn">支付</button>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            layer.confirm("确定要支付吗?",function (index) {
                layer.close(index);
                $("#saveForm").submit();
            });
        });
    });
</script>
</body>

</html>
