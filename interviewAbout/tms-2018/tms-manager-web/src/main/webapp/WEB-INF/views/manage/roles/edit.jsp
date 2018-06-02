<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS-综合管理系统 | 角色编辑</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/navhead.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_roles"/>
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

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">修改角色</h3>
                    <div class="box-tools">
                        <a href="/manage/roles" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>角色名称</label>
                            <input type="hidden" name="rolesId" class="form-control">
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
                            <c:forEach items="${permissionMap}" var="entry">
                                <c:choose>
                                    <c:when test="${entry.key.parentId == 0}">
                                        <tr class="treegrid-${entry.key.id} treegrid-expanded">
                                            <th>
                                                <input type="checkbox" name="permissionId" ${entry.value?'checked':''} value="${entry.key.id}">
                                            </th>
                                            <td>${entry.key.permissionName}</td>
                                            <td>${entry.key.permissionCode}</td>
                                            <td>${entry.key.url}</td>
                                            <td>${entry.key.permissionType}</td>

                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="treegrid-${entry.key.id} treegrid-expanded treegrid-parent-${entry.key.parentId}">
                                            <th>
                                                <input type="checkbox" name="permissionId"  ${entry.value?'checked':''} value="${entry.key.id}">
                                            </th>
                                            <td>${entry.key.permissionName}</td>
                                            <td>${entry.key.permissionCode}</td>
                                            <td>${entry.key.url}</td>
                                            <td>${entry.key.permissionType}</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<!-- iCheck -->
<script src="/static/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function(){
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
        $(".tree").treegrid();
        $('input[type=checkbox]').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
    })
</script>
</body>
</html>
