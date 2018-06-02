package com.ksit.tms.shiro;


import com.ksit.tms.entity.Account;
import com.ksit.tms.entity.AccountLoginLog;
import com.ksit.tms.entity.Permission;
import com.ksit.tms.entity.Roles;
import com.ksit.tms.service.AccountService;
import com.ksit.tms.service.RolePermissionService;
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

    @Autowired
    AccountService accountService;
    @Autowired
    RolePermissionService rolePermissionService;


    private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    /**
     * 权限角色相关信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登陆对象,这个方法可以获得当前登陆对象
        Account account = (Account) principalCollection.getPrimaryPrincipal();

        //获取当前登陆对象所拥有的角色,因为存在账户和角色的关联关系,所以应该去rolesPermission这个表中去查对应关系
        List<Roles> rolesList = rolePermissionService.selectRoleByAccountId(account.getId());

        //获取当前登陆对象所拥有的权限: 因为一个账户可以拥有多个角色,一个角色可能拥有多个权限,所以将角色List 遍历,根据每一个角色去查询所拥有的权限
        List<Permission> permissionList = new ArrayList<>();

        for (Roles role :rolesList) {
            //根据每一个角色去查询权限信息,然后添加到permissionList中去,查询权限List 应该去permission 这个表中去查吧
            List<Permission> permissionList1 = rolePermissionService.selectPermissionByRolesId(role.getId());
            permissionList.addAll(permissionList1);
        }

        //验证权限信息需要两个 set 集合,一个是rolesName set,另一个是 permisisonName set
        Set<String> rolesNameSet = new HashSet<>();
            //循环遍历list 来添加进入set 集合
            for (Roles roles : rolesList) {
                rolesNameSet.add(roles.getRolesCode());
            }
        Set<String> permissionNameSet = new HashSet<>();
            for (Permission permission:permissionList) {
                permissionNameSet.add(permission.getPermissionCode());
            }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(rolesNameSet);
        simpleAuthorizationInfo.setStringPermissions(permissionNameSet);
        return simpleAuthorizationInfo;
    }


    /**
     * 身份验证令牌信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //usernamePasswordToken 是 AuthenticationToken 的实现类,接口指向实现类
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userMobile = usernamePasswordToken.getUsername();
        System.out.println("userMobile = " + userMobile);

        //判断登陆
        if (userMobile != null) {

            //使用Dubbo accountService为null 的问题
            Account account = accountService.selectByMobile(userMobile);

            //对账号的各种状态做捕获
            if (account != null) {

                if (Account.ACCOUNT_NORMAL.equals(account.getAccountState())) {
                    //记录登陆日志,记录登陆账号和 ip地址
                    logger.info("登陆综合管理系统{},ip地址为: ",account,usernamePasswordToken.getHost().toString());

                    //将登陆信息保存到数据库中去
                    AccountLoginLog accountLoginLog = new AccountLoginLog();
                    accountLoginLog.setAccountId(account.getId());
                    accountLoginLog.setLoginIp(usernamePasswordToken.getHost());
                    accountLoginLog.setLoginTime(new Date());
                    accountService.insertAccountLoginLog(accountLoginLog);

                    //会自动进行校验,这里的密码要注意是否在Controller 中进行加密了
                    //在页面使用shiro 标签库的时候,property 就是这个里面的参数
                    return new SimpleAuthenticationInfo(account,account.getAccountPassword(),getName());

                } else {
                    throw new LockedAccountException("账户状态异常");
                }
            } else {
                throw new UnknownAccountException("未发现账户");
            }
        }
        return null;
    }
}
