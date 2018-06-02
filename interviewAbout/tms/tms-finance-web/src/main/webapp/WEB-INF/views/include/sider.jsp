<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 左侧菜单栏 -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">


        <!-- 菜单 -->
        <ul class="sidebar-menu">
            <li class="${param.menu == 'home' ? 'active' : ''}"><a href="/home"><i class="fa fa-home"></i> <span>首页</span></a></li>

            <%-- 暂时不提供库存权限 --%>
            <%--<shiro:hasRole name="store">
            </shiro:hasRole>--%>
                <li class="header">库存管理</li>
                <li class="${param.menu == 'ticket_storage' ? 'active' : ''}"><a href="/ticket/storage"><i class="fa fa-circle-o"></i> <span>年票入库</span></a></li>
                <li class="${param.menu == 'ticket_out' ? 'active' : ''}"><a href="/ticket/out"><i class="fa fa-circle-o"></i> <span>年票下发</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o"></i> <span>年票作废</span></a></li>
                <li class="${param.menu == 'ticket_chart' ? 'active' : ''}"><a href="/ticket/chart"><i class="fa fa-circle-o"></i> <span>盘点统计</span></a></li>


            <%-- 暂时不提供库存权限 --%>
            <%--<shiro:hasRole name="finance">
            </shiro:hasRole>--%>
                <li class="header">财务管理</li>
                <li><a href="/finance/ticket"><i class="fa fa-circle-o"></i> <span>售票点缴费</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o"></i> <span>景区结算</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o"></i> <span>销售统计</span></a></li>
                <li><a href="#"><i class="fa fa-circle-o"></i> <span>验票统计</span></a></li>


            <%-- 备用 --%>
            <%--<!-- 普通菜单 -->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="../../index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
                    <li><a href="../../index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
                </ul>
            </li>
            <!-- 带数字提示的菜单 -->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-files-o"></i>
                    <span>Layout Options</span>
                    <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="../layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
                    <li><a href="../layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
                    <li><a href="../layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
                    <li><a href="../layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
                </ul>
            </li>


            <!-- 多级菜单 -->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-share"></i> <span>Multilevel</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> Level One
                            <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                            <li>
                                <a href="#"><i class="fa fa-circle-o"></i> Level Two
                                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
                </ul>
            </li>
            <li><a href="../../documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
            <li class="header">LABELS</li>
            <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
            <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>--%>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>