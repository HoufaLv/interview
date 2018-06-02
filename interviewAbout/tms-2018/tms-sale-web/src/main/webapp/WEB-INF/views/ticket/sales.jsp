<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 年票办理</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <style>
        .form-control {
            width: 400px;
        }
        .photo {
            width: 100%;
            height: 250px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 250px;
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
                    <c:if test="${not empty message}">
                        <div class="alert alert-info">${message}</div>
                    </c:if>
                    <form method="post" id="saveForm">
                        <input type="hidden" name="customerIdCardPhoto" id="customerIdCardPhoto">
                        <input type="hidden" name="customerIdCardPhotoBack" id="customerIdCardPhotoBack">
                        <input type="hidden" name="customerPhoto" id="customerPhoto">
                        <div class="form-group">
                            <label>年票卡号</label>
                            <input type="text" name="ticketNum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" name="price" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" name="customerName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>年龄</label>
                            <input type="text" name="customerAge" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <select name="customerGender" class="form-control">
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>联系电话</label>
                            <input type="text" name="customerTel" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>住址</label>
                            <input type="text" name="customerAddress" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <input type="text" name="customerIdCard" class="form-control">
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div id="picker1">选择身份证正面照片</div>
                                <div class="photo" id="userCardPhoto"></div>
                            </div>
                            <div class="col-md-4">
                                <div id="picker2">选择身份证反面照片</div>
                                <div class="photo" id="userCardPhotoBack"></div>
                            </div>
                            <div class="col-md-4">
                                <div id="picker3">选择免冠照片</div>
                                <div class="photo" id="userPhoto"></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
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
            layer.msg(123);
            $("#saveForm").submit();
        });

        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '/static/plugins/uploader/Uploader.swf',
            // 文件接收服务端。
            server: '/file/upload',
            fileVal:'file',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker1',
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
            $("#userCardPhoto").html("");
            if(response.status == 'success') {
                var fileName = response.data;
                var $img = $("<img>").attr("src","/file/download?fileId="+fileName).attr("width","200");
                $img.appendTo($("#userCardPhoto"));

                //将key存放到隐藏域中
                $("#customerIdCardPhoto").val(fileName);
                layer.msg("上传成功");
            } else {
                layer.msg(response.message);
            }

        });

        uploader.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });

        uploader.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        // 初始化Web Uploader
        var uploader2 = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '/static/plugins/uploader/Uploader.swf',
            // 文件接收服务端。
            server: '/file/upload',
            fileVal:'file',
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
        index = -1;

        uploader2.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });

        uploader2.on( 'uploadSuccess', function( file,response ) {
            $("#userCardPhotoBack").html("");
            if(response.status == 'success') {
                var fileName = response.data;
                var $img = $("<img>").attr("src","/file/download?fileId="+fileName).attr("width","200");
                $img.appendTo($("#userCardPhotoBack"));

                //将key存放到隐藏域中
                $("#customerIdCardPhotoBack").val(fileName);
                layer.msg("上传成功");
            } else {
                layer.msg(response.message);
            }

        });

        uploader2.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });

        uploader2.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

        // 初始化Web Uploader
        var uploader3 = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '/static/plugins/uploader/Uploader.swf',
            // 文件接收服务端。
            server: '/file/upload',
            fileVal:'file',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker3',
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        index = -1;

        uploader3.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });

        uploader3.on( 'uploadSuccess', function( file,response ) {
            $("#userPhoto").html("");
            if(response.status == 'success') {
                var fileName = response.data;
                var $img = $("<img>").attr("src","/file/download?fileId="+fileName).attr("height","180");
                $img.appendTo($("#userPhoto"));

                //将key存放到隐藏域中
                $("#customerPhoto").val(fileName);
                layer.msg("上传成功");
            } else {
                layer.msg(response.message);
            }

        });

        uploader3.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });

        uploader3.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });

    })
</script>
</body>
</html>
