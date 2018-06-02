package com.ksit.tms.shiro;

import com.ksit.tms.service.RolePermissionService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Lvhoufa
 */
public class CustomerFilterChainDefinition{


    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    /**
     * 使用rolePermissionService 去查出所有权限
     */
    @Autowired
    private RolePermissionService rolePermissionService;

    private String filterChainDefinitions;
    private AbstractShiroFilter shiroFilter;


    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    /**
     * 获取 getFilterChainManager
     * @return
     */
    private DefaultFilterChainManager getFilterChainManager(){
        //获取路径匹配过滤器链解析器
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();

        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();

        return defaultFilterChainManager;
    }


    /**
     * 初始化权限
     */
    @PostConstruct
    public synchronized void init() {
        logger.info("------------初始化权限-----------");
        getFilterChainManager().getFilterChains().clear();

    }


    /**
     * 加载url 和权限的 对应关系
     * @throws Exception
     */
    public void load() throws Exception {

    }

}
