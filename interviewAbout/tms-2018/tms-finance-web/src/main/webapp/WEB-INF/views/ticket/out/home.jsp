<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票下发</title>
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
            <h1>年票下发</h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">下发列表</h3>
                    <div class="box-tools">
                        <a href="/ticket/out/new" class="btn btn-sm btn-success"><i class="fa fa-plus"></i>新增下发</a>
                    </div>
                </div>
                <div class="box-body">
                    <c:if test="${not empty message}">
                        <div class="alert alert-info">${message}</div>
                    </c:if>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>下发时间</th>
                                <th>下发网点</th>
                                <th>内容</th>
                                <th>起始票号</th>
                                <th>截止票号</th>
                                <th>数量</th>
                                <th>单价</th>
                                <th>总价</th>
                                <th>支付方式</th>
                                <th>下发人</th>
                                <th>状态</th>
                                <th>收款人</th>
                                <th>#</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty pageInfo.list}">
                            <tr>
                                <td colspan="7">暂无记录</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pageInfo.list}" var="record">
                            <tr>
                                <td><fmt:formatDate value="${record.createTime}"/></td>
                                <td>${record.storeAccountName}</td>
                                <td>${record.content}</td>
                                <td>${record.beginTicketNum}</td>
                                <td>${record.endTicketNum}</td>
                                <td>${record.totalNum}</td>
                                <td>${record.price}</td>
                                <td>${record.totalPrice}</td>
                                <td>${record.payType}</td>
                                <td>${record.outAccountName}</td>
                                <td>
                                    <span class="label ${record.state == '未支付'?'label-danger':'label-success'}">${record.state}</span>
                                </td>
                                <td>${record.financeAccountName}</td>
                                <td>
                                    <c:if test="${record.state == '未支付'}">
                                        <a href="javascript:;" rel="${record.id}" class="del_link" title="取消"><i class="fa fa-trash text-danger"></i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${pagaInfo.pages > 1}">
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/page/jquery.twbsPagination.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //删除年票下发,注意：放分页下面就不管用了
        $(".del_link").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该下发记录么？",function (index) {
                layer.close(index);
                window.location.href = "/ticket/out/"+id+"/del";
            })
        });

        $('#pagination-demo').twbsPagination({
            totalPages:${page.pages},
            visiblePages:10,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}"
        });


    });

</script>
</body>
</html>
