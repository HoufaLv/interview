<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>售票点缴费-财务模块</title>
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
                <%-- 财务账号登陆,将所有下发记录查出来,显示,然后缴费,添加缴费记录 --%>
                <small>售票点缴费-财务模块</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <%--搞一个动态搜索框出来--%>
            <div class="box">
                <div class="box-body">
                    <form class="form-inline">
                        <select name="state" class="form-control">
                            <option ${param.state == '未支付' ? 'selected' : ''} value="未支付">未支付</option>
                            <option ${param.state == '已支付' ? 'selected' : ''} value="已支付">已支付</option>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>

            <%--展示下发的年片记录--%>
            <div class="box">
                <div class="box-header">
                    <h4>缴费记录</h4>
                </div>

                <table class="table">
                    <thead>
                    <tr>
                        <th>下发时间</th>
                        <th>下发网点</th>
                        <th>内容</th>
                        <th>起始票号</th>
                        <th>截至票号</th>
                        <th>数量</th>
                        <th>单价</th>
                        <th>总价</th>
                        <th>支付方式</th>
                        <th>下发人</th>
                        <th>状态</th>
                        <th>收款人</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${empty page.list}">
                        <tr>
                            <td colspan="13">暂无记录</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${page.list}" var="record">
                        <tr>
                            <td><fmt:formatDate value="${record.createTime}"/></td>
                            <td>${record.storeAccountName}</td>
                            <td>${record.content}</td>
                            <td>${record.beginTicketNum}</td>
                            <td>${record.endTicketNum}</td>
                            <td>${record.totalNum}</td>
                            <td>${record.price}</td>
                            <td>${record.totalprice}</td>
                            <td>${record.payType}</td>
                            <td>${record.outAccountName}</td>
                            <td>
                                <span class="label ${record.state == '未支付' ? 'label-danger' : 'label-success'}">${record.state}</span>
                            </td>
                            <td>${record.financeAccountName}</td>
                            <td>
                                <c:if test="${record.state == '未支付'}">
                                    <a href="/finance/ticket/${record.id}/pay" class="btn btn-sm btn-success">支付</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--如果页码大于1 页得话,显示分页条--%>
                <c:if test="${page.pageSize>1}">
                    <ul id="pagination-demo" class="pagination pull-right"></ul>
                </c:if>
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
    /* 分页插件展示 */
    $(function () {
        $('#pagination-demo').twbsPagination({
            totalPages:${page.total},
            visiblePages: 10,
            first:'首页',
            last:'末页',
            prev:'←',
            next:'→',
            href:"?p={{number}}"
        });
    });
</script>
</body>
</html>
