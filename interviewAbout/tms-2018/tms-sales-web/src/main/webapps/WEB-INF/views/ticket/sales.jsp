<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票办理</title>
    <%@include file="../include/css.jsp"%>
    <style>
        .form-control {
            width: 300px;
        }
        .photo {
            height: 250px;
            border: 2px dashed #ccc;
        }
    </style>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="ticket_sales_new"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
         <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">年票办理</h3>
                </div>
                <div class="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" name="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>年龄</label>
                            <input type="text" name="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <select name="" class="form-control">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>联系电话</label>
                            <input type="text" name="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>住址</label>
                            <input type="text" name="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <input type="text" name="" class="form-control">
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="photo"></div>
                            </div>
                            <div class="col-md-4">
                                <div class="photo"></div>
                            </div>
                            <div class="col-md-4">
                                <div class="photo"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right">保存</button>
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
