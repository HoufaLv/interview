<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>票务首页</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <%-- 该指令会传递需要的参数 --%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <small>it all starts here</small>
            </h1>
        </section>


        <section class="content">

        <div class="box">
            <div class="box-header">
                <h4 class="box-title">新增年票入库</h4>
                <div class="box-tools">
                    <a href="/ticket/storage/new" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> 新增入库</a>
                </div>
            </div>

            <%-- 做显示所有记录 --%>
            <div class="box-body">
                <%-- 如果有message 的话,显示出来 --%>
                <c:if test="${not empty message}">
                    <div class="alert alert-info">${message}</div>
                </c:if>

                <%-- 用一个表格显示所有的入库记录啥的 --%>
                <table class="table">
                    <thead>
                        <th>入库时间</th>
                        <th>内容</th>
                        <th>起始票号</th>
                        <th>截至票号</th>
                        <th>数量</th>
                        <th>入库人</th>
                        <th></th>
                    </thead>

                    <tbody>
                        <%-- 如果没有入库信息的话,就显示一个空提示 --%>
                        <c:if test="${empty pageInfo.list}">
                            <tr>
                                <td colspan="7">暂时没有记录</td>
                            </tr>
                        </c:if>

                        <%-- 迭代记录 --%>
                        <c:forEach items="${pageInfo.list}" var="record">
                            <tr>
                                <td><fmt:formatDate value="${record.createTime}"></fmt:formatDate></td>
                                <td>${record.content}</td>
                                <td>${record.beginTicketNum}</td>
                                <td>${record.endTicketNum}</td>
                                <td>${record.totalNum}</td>
                                <td>${record.accountName}</td>
                                <td>
                                    <a href="javascript:;" class="btn btn-xs btn-danger delBtn" rel="${record.id}">删除入库记录</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot></tfoot>
                </table>
                    <%-- 如果总页数大于1的话,开启分页效果 --%>
                    <c:if test="${pageInfo.pages > 1}">
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </c:if>
            </div>
        </div>

        </section>

    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/page/jquery.twbsPagination.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    /* 使用js 控制表单提交 */
    $(function () {
        /* 分页效果 */
        $("#pagination-demo").twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePages: 10,
            first:'首页',
            last:'末页',
            prev:'←',
            next:'→',
            href:"?p={{number}}"
        });

        /* 完成删除效果 */
        $(".delBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除此入库记录?",function (index) {
                layer.close(index);
                window.location.href = "/ticket/storage/"+id+"/del";
            })
        });
    });
</script>
</body>
</html>
