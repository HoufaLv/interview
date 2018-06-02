package com.kaishengit.tms.shiro;

import com.kaishengit.tms.entity.Account;
import com.kaishengit.tms.entity.AccountLoginLog;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    //自动注入用户工具类AccountService  角色权限工具类RolePermissionService
    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 判断角色与权限
     * @date 2018/4/17
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取当前登录对象
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        //2.获取当前登录对象角色
        List<Roles> rolesList = rolePermissionService.findRolesByAccountId(account.getId());
        //3.获取当前登录对象权限(有重复)
        List<Permission> permissionList = rolePermissionService.findPermissionsByAccountId(account.getId());
        //4.将对象拥有的角色代号和权限代号放入对应的set集合以去重复
        Set<String> rolesCodeSet = new HashSet<>();
        if (rolesList!=null && !rolesList.isEmpty()){
            for (Roles roles:rolesList){
                rolesCodeSet.add(roles.getRolesCode());
            }
        }

        Set<String> permissionCodeSet = new HashSet<>();
        if (permissionList!=null && !permissionList.isEmpty()){
            for (Permission permission:permissionList){
                permissionCodeSet.add(permission.getPermissionCode());
            }
        }

        //5.为SimpleAuthorizationInfo对象设置角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(rolesCodeSet);
        simpleAuthorizationInfo.setStringPermissions(permissionCodeSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 判断登录
     * @date 2018/4/17
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        if (userMobile!=null){
            Account account = accountService.findAccountByMobile(userMobile);
            if (account == null){
                throw new UnknownAccountException("找不到该账号:"+userMobile);
            } else {
                if (Account.STATE_NORMAL.equals(account.getAccountState())){

                    logger.info("{}登录成功{}",account,usernamePasswordToken.getHost());
                    //保存登录日志
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setLoginTime(new Date());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setAccountId(account.getId());

                    accountService.saveAccountLoginLog(accountLoginLog);

                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());

                } else {
                    throw new LockedAccountException("账号被禁用或锁定:"+account.getAccountState());
                }
            }
        }


        return null;
    }
}
