<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增销售点账户</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../../include/css.jsp" %>
    <%-- 引入文件上传的css --%>
    <link rel="stylesheet" href="/static/plugins/webuploader-0.1.5/webuploader.css">
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

<body class="hold-transition skin-blue sidebar-mini">

<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>

    <%--使用动态包含,可以传递不同参数--%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_roles"></jsp:param>
    </jsp:include>


    <%-- 右侧内容部分 --%>
    <div class="content-wrapper">
        <%-- 好像一个panel 一样 --%>
        <section class="content-header">
            <h3>新增销售点账户</h3>
        </section>

        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增销售点账户</h3>
                </div>

                <div class="box-body">
                    <%-- 将后台传过来的数据在这里进行显示,修改之后再重新提交,不写action 就是从哪来,回哪去 --%>
                    <form method="post" id="saveForm">
                        <input name="storeAccountId" type="hidden" value="${ticketStore.storeAccountId}">
                        <div class="form-group">
                            <label> 营业点名称 </label>
                            <input type="text" name="storeName" class="form-control" value="${ticketStore.storeName}">
                        </div>
                        <div class="form-group">
                            <label> 营业点地址 </label>
                            <input type="text" name="storeAddress" class="form-control" value="${ticketStore.storeAddress}">
                        </div>
                        <div class="form-group">
                            <label> 营业点电话 </label>
                            <input type="text" name="storeTel" class="form-control" value="${ticketStore.storeTel}">
                        </div>
                        <div class="form-group">
                            <label> 营业点法人 </label>
                            <input type="text" name="storeManager" class="form-control" value="${ticketStore.storeManager}">
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div id="picker">选择联系人身份证照片</div>
                                <div class="photo" id="userPhoto"></div>
                            </div>
                            <div class="col-md-6">
                                <div id="picker2">选择营业执照照片</div>
                                <div class="photo" id="storePhoto"></div>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <button id="newStoreBtn" class="btn btn-primary pull-right">提交</button>
                </div>
            </div>


        </section>

    </div>

    <%-- 底部部分 --%>
    <%@include file="../../include/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp" %>


<%-- 引入webupload.js --%>
<script src="/static/plugins/webuploader-0.1.5/webuploader.js"></script>

<%-- 引入layer.js --%>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {



        /*提交新增售票点表单*/
        $("#newStoreBtn").click(function () {
            $("#saveForm").submit();
        });

        var uploader = WebUploader.create({
            // swf文件路径
            swf: '/static/plugins/webuploader-0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: '/zxcvxzcv',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        var index = -1;

        uploader.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });

        uploader.on( 'uploadSuccess', function( file,response ) {
            $("#userPhoto").html("");

            layer.msg("上传成功");
        });

        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });

        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });


        /*第二个照片的上传*/
        var uploader2 = WebUploader.create({
            // swf文件路径
            swf: '/static/plugins/webuploader-0.1.5/Uploader.swf',

            // 文件接收服务端。
            server: 'http://webuploader.duapp.com/server/fileupload.php',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker2',

            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

    });
</script>

</body>
</html>

