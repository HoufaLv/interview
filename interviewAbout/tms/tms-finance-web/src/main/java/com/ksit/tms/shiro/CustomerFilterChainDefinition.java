package com.ksit.tms.shiro;

import com.ksit.tms.entity.Permission;
import com.ksit.tms.service.RolePermissionService;
import com.ksit.tms.service.RolePermissionService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


public class CustomerFilterChainDefinition {

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    @Autowired
    private RolePermissionService rolePermissionService;

    private String filterChainDefinitions;
    private AbstractShiroFilter shiroFilter;

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    @PostConstruct
    public synchronized void init() {
        logger.info("********初始化URL权限************");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("*********初始化URL权限结束*********");
    }

    public synchronized void updateUrlPermission() {
        logger.info("***********刷新URL权限*************");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("***********刷新URL权限结束***********");
    }

    public synchronized void load() {
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);

        //从数据库中查找所有的权限对象
        List<Permission> permissionList = rolePermissionService.selectAll();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for(Permission permission : permissionList) {
            section.put(permission.getPermissionUrl(),"perms["+permission.getPermissionCode()+"]");
        }
        section.put("/**","user");

        //URL和权限的关系设置到shiroFilter中
        DefaultFilterChainManager defaultFilterChainManager = getFilterChainManager();
        for(Map.Entry<String,String> entry : section.entrySet()) {
            defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }
    }


    private DefaultFilterChainManager getFilterChainManager() {
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
        return defaultFilterChainManager;
    }

}
