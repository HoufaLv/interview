<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">


        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/home"><i class="fa fa-home"></i> <span>首页</span></a></li>
            <li class="header">销售管理</li>
            <li class="${param.menu == 'ticket_sales_new' ? 'active' : ''}"><a href="/ticket/sales/new"><i class="fa fa-circle-o"></i> <span>年票办理</span></a></li>
            <li class="${param.menu == 'ticket_out' ? 'active' : ''}"><a href="/ticket/out"><i class="fa fa-circle-o"></i> <span>年票续费</span></a></li>
            <li class="${param.menu == ''?'active':''}"><a href="#"><i class="fa fa-circle-o"></i> <span>年票挂失</span></a></li>
            <li class="${param.menu == 'ticket_chart' ? 'active' : ''}"><a href="/ticket/chart"><i class="fa fa-circle-o"></i> <span>年票解挂</span></a></li>
            <li class="${param.menu == '' ? 'active' : ''}"><a href="#"><i class="fa fa-circle-o"></i> <span>年票补办</span></a></li>
            <li class="${param.menu == '' ? 'active' : ''}"><a href="#"><i class="fa fa-circle-o"></i> <span>销售统计</span></a></li>

            <li class="treeview ${fn:startsWith(param.menu, 'search') ? 'active' : ''}">
                <a href="javascript:;">
                    <i class="fa fa-search"></i>
                    <span>查询</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'search_ticket' ? 'active' : ''}"><a href="/search/ticket"><i class="fa fa-circle-o"></i> 查询年票</a></li>
                    <li><a href="/search/customer"><i class="fa fa-circle-o"></i> 查询客户</a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
