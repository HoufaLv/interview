<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <%@include file="../../include/css.jsp" %>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp" %>

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

        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增入库</h3>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>入库时间</label>
                            <input type="text" class="form-control" disabled value="${time}">
                        </div>
                        <div class="form-group">
                            <label>起始票号</label>
                            <input type="text" class="form-control" name="beginTicketNum" id="beginNum">
                        </div>
                        <div class="form-group">
                            <label>截至票号</label>
                            <input type="text" class="form-control" name="endTicketNum" id="endNum">
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <div class="box-footer">
                        <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
                    </div>
                </div>

            </div>
        </section>

    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp" %>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    });
</script>
</body>
</html>
