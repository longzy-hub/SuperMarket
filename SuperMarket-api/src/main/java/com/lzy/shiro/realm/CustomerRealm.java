package com.lzy.shiro.realm;


import com.lzy.pojo.SysPerm;
import com.lzy.pojo.SysRole;
import com.lzy.pojo.SysUser;
import com.lzy.service.PersonalService;
import com.lzy.service.SysUserService;
import com.lzy.shiro.salt.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;



import javax.annotation.Resource;
import java.util.List;


//自定义realm
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private PersonalService personalService;

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        //根据主身份信息获取角色 和 权限信息
        SysRole role = sysUserService.findRolesByUserName(primaryPrincipal);
        //授权角色信息
        	//拿到授权器
        	SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(role.getName());
            //权限信息
            List<SysPerm> perms = sysUserService.findPermsByRoleId(role.getId());
            if(!CollectionUtils.isEmpty(perms)){
             perms.forEach(perm->{
                 System.out.println(perm.getName());
                   simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            return simpleAuthorizationInfo;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据身份信息
        String principal = (String) token.getPrincipal();
        SysUser user = sysUserService.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            personalService.insertPersonal(user.getId());
            return new SimpleAuthenticationInfo(principal,user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName());
        }
        return null;
    }

}
