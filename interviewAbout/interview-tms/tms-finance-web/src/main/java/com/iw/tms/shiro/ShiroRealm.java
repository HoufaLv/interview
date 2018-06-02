package com.iw.tms.shiro;

import com.iw.tms.entity.Account;
import com.iw.tms.entity.AccountLoginLog;
import com.iw.tms.entity.Permission;
import com.iw.tms.entity.Roles;
import com.iw.tms.service.AccountService;
import com.iw.tms.service.RolesPermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm {

    //记录日志
    private Logger logger = LoggerFactory.getLogger(CustomerFilterChainDefinition.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RolesPermissionService rolesPermissionService;

    /**
     * 判断角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登陆的对象
        Account account = (Account) principalCollection.getPrimaryPrincipal();

        //获取当前登陆对象所对应的角色
        List<Roles> rolesList = rolesPermissionService.selectRolesByAccountId(account.getId());

        //获取当前登陆对象拥有的权限
        List<Permission> permissionList = new ArrayList<>();
        for (Roles r : rolesList) {
            List<Permission> rolesPermissionList = rolesPermissionService.findAllPermissionByRolesId(r.getId());
            permissionList.addAll(rolesPermissionList);
        }

        //搞一个set
        Set<String> rolesNameSet = new HashSet<>();
        for (Roles r : rolesList) {
            rolesNameSet.add(r.getRolesCode());
        }

        Set<String> permissionNameSet = new HashSet<>();
        for (Permission permission: permissionList) {
            permissionNameSet.add(permission.getPermissionCode());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //当前用户拥有的角色(code)
        simpleAuthorizationInfo.setRoles(rolesNameSet);
        //当前用户拥有的权限(code)
        simpleAuthorizationInfo.setStringPermissions(permissionNameSet);

        return simpleAuthorizationInfo;

    }

    /**
     * 根据提供的消息,进行登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //先转换成UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

        //获取用户名,也就是前端登陆的手机号
        String userMobile = usernamePasswordToken.getUsername();

        //进行校验
        if (userMobile!=null) {
            Account account = accountService.selectByMobile(userMobile);
            if (account!=null){
                //登陆在service 中去做,在这个里面判断是否可以登陆
                if (Account.STATE_NORMAL.equals(account.getAccountState())){

                    logger.info("登陆系统",account);

                    //保存登陆日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountService.saveAccountLoginLog(accountLoginLog);

                    //这一步就是登陆方法,shiro在内部实现了登陆方案,根据从数据库中查出来的那行数据和控制器中提供的password 来进行比对
                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());
                }
            }else{
                throw new LockedAccountException("账户不存在");
            }
        }else{
            throw new LockedAccountException("账户不存在");
        }
        return null;
    }
}
