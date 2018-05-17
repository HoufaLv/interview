package com.iw.tms.shiro;

import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态定义权限和url 的关系
 */
public class CustomerFilterChainDefinition {

    // TODO: 2018/5/15 0015 查看Shiro 源码,动态定义权限和url 的关系

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    private String filterChainDefinitions;
    private AbstractShiroFilter shiroFilter;

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

}
