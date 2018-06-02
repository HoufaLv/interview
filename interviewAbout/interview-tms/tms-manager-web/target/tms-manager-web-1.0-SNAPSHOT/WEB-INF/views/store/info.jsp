<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 售票点详情</title>
    <%@include file="../include/css.jsp"%>
    <style>
        .photo {
            width: 100%;
            height: 320px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 320px;
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
                        <a href="/ticketstore/${ticketStore.id}/edit" class="btn btn-primary btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                        <a href="javascript:;" rel="${ticketStore.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> 删除</a>
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
                            <td colspan="3">${ticketStore.storeAddress}</td>
                            <td class="text-muted">创建时间</td>
                            <td><fmt:formatDate value="${ticketStore.storeRegisterTime}"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联账号</h3>
                    <div class="box-tools">
                        <a href="javascript:;" rel="${ticketStore.id}" class="btn btn-danger btn-sm"><i class="fa fa-ban"></i> 禁用账号</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tr>
                            <td class="text-muted">账号</td>
                            <td>${storeAccount.storeAccount}</td>
                            <td class="text-muted">状态</td>
                            <td>${storeAccount.storeState}</td>
                            <td class="text-muted">创建时间</td>
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
                                <%--法人身份证--%>

                                    <img src="http://p7h7vnjez.bkt.clouddn.com/${ticketStore.storeManagerIdentity}-preview" alt="法人信息">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="photo">
                                <%--营业执照--%>
                                    <img src="http://p7h7vnjez.bkt.clouddn.com/${ticketStore.storeIdentity}-preview" alt="资质">
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
<script>
    $(function () {

    });
</script>
</body>
</html>
