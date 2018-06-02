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

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>

    <%@include file="../../include/sider.jsp" %>


    <div class="content-wrapper">
        <%-- 新增角色 --%>
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3>更新角色</h3>
                    <div class="box-tools">
                        <a href="/manage/role" class="btn btn-primary btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <%-- 不写action 就是从哪里来,回到哪个路径中去 --%>
                    <form method="post" id="updateRolesForm">
                        <div class="form-group">
                            <label>角色名称</label>
                            <input type="text" name="rolesName" class="form-control" value="${roles.rolesName}">
                        </div>
                        <div class="form-group">
                            <label>角色代号</label>
                            <input type="text" name="rolesCode" class="form-control" value="${roles.rolesCode}">
                        </div>

                        <table class="table tree">
                            <thead>
                            <tr>
                                <th></th>
                                <th>权限名称</th>
                                <th>权限代号</th>
                                <th>资源URL</th>
                                <th>类型</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--展示从后台查出来的权限List--%>
                            <%-- <c:forEach items="${permissionList}" var="permission">
                                 <h3>${permission.id}</h3>
                             </c:forEach>--%>
                            <c:forEach items="${permissionMap}" var="entry">
                                <c:choose>
                                    <%--如果使顶级权限的话--%>
                                    <c:when test="${entry.key.parentId==0}">
                                        <tr class="treegrid-${permission.id} treegrid-expanded">
                                            <td>
                                                <input type="checkbox"
                                                       name="permissionId" ${entry.value ? 'checked' : ''}
                                                       value="${entry.key.id}">
                                            </td>
                                            <td>${entry.key.permissionName}</td>
                                            <td>${entry.key.permissionCode}</td>
                                            <td>${entry.key.permissionUrl}</td>
                                            <td>${entry.key.permissionType}</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="treegrid-${entry.key.id} treegrid-expanded treegrid-parent-${entry.key.parentId}">
                                            <td>
                                                <input type="checkbox"
                                                       name="permissionId"  ${entry.value ? 'checked' : ''}
                                                       value="${entry.key.id}">
                                            </td>
                                            <td>${entry.key.permissionName}</td>
                                            <td>${entry.key.permissionCode}</td>
                                            <td>${entry.key.permissionUrl}</td>
                                            <td>${entry.key.permissionType}</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                            </tbody>
                            <tfoot></tfoot>

                        </table>


                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>

        </section>

    </div>

    <%@include file="../../include/footer.jsp" %>
</div>


<%@include file="../../include/js.jsp" %>
<%-- 引入treegrid.js --%>
<script src="/static/bower_components/maxazan-jquery-treegrid-447d662/js/jquery.treegrid.js"></script>
<script src="/static/bower_components/maxazan-jquery-treegrid-447d662/js/jquery.treegrid.bootstrap3.js"></script>
<%-- 引入layer.js --%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#updateRolesForm").submit();

        });
    });
</script>
</body>
</html>

