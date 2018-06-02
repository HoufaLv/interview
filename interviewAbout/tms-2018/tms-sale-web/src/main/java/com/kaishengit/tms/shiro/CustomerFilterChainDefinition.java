package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.service.RolePermissionService;
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

public class CustomerFilterChainDefinition  {

    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);
    /*@Autowired
    private RolePermissionService rolePermissionService;*/

    private String filterChainDefinition;
    private AbstractShiroFilter shiroFilter;

    public void setFilterChainDefinition(String filterChainDefinition) {
        this.filterChainDefinition = filterChainDefinition;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    /**
     * Spring容器启动时调用
     * @date 2018/4/18
     * @param
     * @return void
     */
    @PostConstruct
    public synchronized void init() {
        logger.info("--------初始化URL权限---------");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("----------初始化URL权限结束----------");
    }

    /**
     * 重新加载URL权限
     * @date 2018/4/18
     * @param
     * @return void
     */
    public synchronized void updateUrlPermission(){
        logger.info("----------刷新URL权限-----------");
        //清除原有的URL权限
        getFilterChainManager().getFilterChains().clear();
        //加载现有的URL权限
        load();
        logger.info("------------刷新URL权限结束--------------");
    }

    /**
     * 加载url和权限的对应关系
     * @date 2018/4/18
     * @param
     * @return void
     */
    public synchronized void load()  {
        Ini ini = new Ini();
        ini.load(filterChainDefinition);
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        //从数据库中查找所有权限对象
        /*List<Permission> permissionList = rolePermissionService.findAllPermission();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for (Permission permission:permissionList){

            section.put(permission.getUrl(),"perms["+permission.getPermissionCode()+"]");
        }*/

        section.put("/**","user");


        //url和权限的关系设置到shiroFilter中
        DefaultFilterChainManager defaultFilterChainManager = getFilterChainManager();
        for (Map.Entry<String,String> entry:section.entrySet()){
            defaultFilterChainManager.createChain(entry.getKey(),entry.getValue());
        }

    }

    private DefaultFilterChainManager getFilterChainManager(){
        PathMatchingFilterChainResolver pathMatchingFilterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) pathMatchingFilterChainResolver.getFilterChainManager();
        return defaultFilterChainManager;
    }


}
