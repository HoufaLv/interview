package com.iw.tms.shiro;

import com.iw.tms.entity.Permission;
import com.iw.tms.service.RolesPermissionService;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义ShiroRealm
 */
public class CustomerFilterChainDefinition implements FactoryBean<Ini.Section> {

    @Autowired
    private RolesPermissionService rolePermissionService;

    private String filterChainDefinitions;

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    @Override
    public Ini.Section getObject() throws Exception {
        Ini ini = new Ini();
        ini.load(filterChainDefinitions);

        //从数据库中查找所有的权限对象
        List<Permission> permissionList = rolePermissionService.listPermission();
        Ini.Section section = ini.get(Ini.DEFAULT_SECTION_NAME);

        for(Permission permission : permissionList) {
            section.put(permission.getPermissionUrl(),"perms["+permission.getPermissionCode()+"]");
        }
        section.put("/**","user");
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return Ini.Section.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
