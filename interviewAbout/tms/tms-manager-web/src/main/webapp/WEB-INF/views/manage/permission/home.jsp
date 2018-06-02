<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Blank Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../../include/css.jsp" %>
    <link rel="stylesheet" href="/static/bower_components/maxazan-jquery-treegrid-447d662/css/jquery.treegrid.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">

<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>

    <%--使用动态包含,可以传递参数--%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"></jsp:param>
    </jsp:include>


    <%-- 右侧内容部分 --%>
    <div class="content-wrapper">
        <%-- 好像一个panel 一样 --%>
        <section class="content-header">
            <h3>权限管理</h3>
        </section>

        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">权限列表</h3>
                    <a href="/manage/permission/new" class="btn btn-success btn-sm pull-right"><i
                            class="fa fa-plus"></i> 新增权限</a>
                </div>

                <%-- 将权限以父子关系展示出来 --%>
                <%-- 下面是内容区域 --%>
                <div class="box-body">
                    <table class="table tree">
                        <thead>

                            <th>权限名称</th>
                            <th>权限代号</th>
                            <th>url</th>
                            <th>类型</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                        <%--展示从后台查出来的权限List--%>
                       <%-- <c:forEach items="${permissionList}" var="permission">
                            <h3>${permission.id}</h3>
                        </c:forEach>--%>
                        <c:forEach items="${permissionList}" var="permission">
                            <c:choose>
                                <c:when test="${permission.parentId == 0}">
                                    <tr class="treegrid-${permission.id} treegrid-expanded">
                                        <%--<td>
                                            <input type="checkbox" name="permissionId" value="${permission.id}">
                                        </td>--%>
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.permissionUrl}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a href="#" class="btn-sm btn-primary">编辑</a>
                                            <a href="#" rel="${permission.id}" class="btn-sm btn-danger delPermission">删除</a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr class="treegrid-${permission.id} treegrid-expanded treegrid-parent-${permission.parentId}">
                                        <%--<td>
                                            <input type="checkbox" name="permissionId"  value="${permission.id}">
                                        </td>--%>
                                        <td>${permission.permissionName}</td>
                                        <td>${permission.permissionCode}</td>
                                        <td>${permission.permissionUrl}</td>
                                        <td>${permission.permissionType}</td>
                                        <td>
                                            <a href="/manage/permission/${permission.id}/update" class="btn-sm btn-primary">编辑</a>
                                            <a href="#" rel="${permission.id}" class="btn-sm btn-danger delPermission">删除</a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                            <%--测试 树形结构--%>
                            <%--<tr class="treegrid-1">
                                <td>权限管理</td>
                                <td>permission : home</td>
                                <td></td>
                                <td><a href="">删除</a> <a href="">修改</a></td>
                            </tr>
                            <tr class="treegrid-2 treegrid-parent-1">
                                <td>添加权限</td>
                                <td>permission : add</td>
                                <td></td>
                                <td><a href="">删除</a> <a href="">修改</a></td>
                            </tr>
                            <tr class="treegrid-3 treegrid-parent-1">
                                <td>修改权限</td>
                                <td>permission : edit</td>
                                <td></td>
                                <td><a href="">删除</a> <a href="">修改</a></td>
                            </tr>--%>
                        </tbody>
                        <tfoot></tfoot>
                    </table>

                </div>
            </div>


        </section>

    </div>

    <%-- 底部部分 --%>
    <%@include file="../../include/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp" %>
<%-- 引入treegrid.js --%>
<script src="/static/bower_components/maxazan-jquery-treegrid-447d662/js/jquery.treegrid.js"></script>
<script src="/static/bower_components/maxazan-jquery-treegrid-447d662/js/jquery.treegrid.bootstrap3.js"></script>
<%-- 引入layer.js --%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $('.tree').treegrid();

        /* 完成删除功能 */
        $(".delPermission").click(function () {
            /* 获取要删除的id */
            var id = $(this).attr("rel");
            layer.confirm("将会删除权限",function (index) {
                layer.close(index);
                $.get("/manage/permission/"+id+"/del").done(function (result) {
                    if(result.status == 'success') {
                        /* 刷新页面 */
                        /*layer.msg("删除成功");*/
                        history.go(0);
                    } else {
                        /* 显示状态信息 */
                        layer.msg(result.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙");
                });
            })
        });
    });
</script>

</body>
</html>

