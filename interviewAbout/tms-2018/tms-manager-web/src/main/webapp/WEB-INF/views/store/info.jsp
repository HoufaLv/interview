<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS综合管理系统 | 售票点详情</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <style>
        .photo {
            width: 100%;
            height: 300px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 300px;
        }
    </style>
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
                售票点详情
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">售票点信息</h3>
                    <div class="box-tools">
                        <a href="/ticketstore/${ticketStore.id}/edit" class="btn btn-primary btn-sm"><i class="fa fa-pencil"></i>编辑</a>
                        <a href="javascript:;" rel="${ticketStore.id}" class="btn btn-primary btn-sm" id="delBtn"><i class="fa fa-trash">删除</i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                            <tr>
                                <td class="text-muted">售票点名称</td>
                                <td>${ticketStore.storeName}</td>
                                <td class="text-muted">联系人</td>
                                <td>${ticketStore.storeManager}</td>
                                <td class="text-muted">联系电话</td>
                                <td>${ticketStore.storeTel}</td>
                            </tr>
                            <tr>
                                <td class="text-muted">地址</td>
                                <td>${ticketStore.storeAddress}</td>
                                <td class="text-muted">创建时间</td>
                                <td><fmt:formatDate value="${ticketStore.createTime}"/></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联账号</h3>
                    <div class="box-tools">
                        <a href="javascript:;" rel="${storeAccount.id}" class="btn btn-danger sm" id="banBtn"><i class="fa fa-ban">禁用账号</i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tr>
                            <td>账号</td>
                            <td>${storeAccount.storeAccount}</td>
                            <td>状态</td>
                            <td>${storeAccount.storeState}</td>
                            <td>创建时间</td>
                            <td><fmt:formatDate value="${storeAccount.createTime}"/></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联资质</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="photo">
                                <img src="http://p825m8548.bkt.clouddn.com/${ticketStore.storeManagerAttachment}-previews" alt="">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="photo">
                                <img src="http://p825m8548.bkt.clouddn.com/${ticketStore.storeAttachment}-previews" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });

        $("#banBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要禁用该账户吗？",function (index) {
                layer.close(index);
                $.get("/ticketstore/"+id+"/ban").done(function (result) {
                    if(result.status == 'success'){
                        window.history.go(0);
                    } else if (result.status == error){
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙");
                });
            });
        });

        //删除
        $("#delBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该用户？",function (index) {
                window.location.href = "/ticketstore/"+id+"/del";
            });
        });

    })
</script>
</body>
</html>
