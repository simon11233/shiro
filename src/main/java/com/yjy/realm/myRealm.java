package com.yjy.realm;

import com.yjy.dao.UserDao;
import com.yjy.entity.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class myRealm extends AuthorizingRealm {
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username =(String)token.getPrincipal();
        UserDao userDao = new UserDao();
        Users users = userDao.FindUser(username);
        String password = users.getPassword();
        String salt = users.getPassword_salt();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(salt),getName());
        return info;
    }
//授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
