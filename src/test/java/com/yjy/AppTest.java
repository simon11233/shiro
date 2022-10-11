package com.yjy;

import static org.junit.Assert.assertTrue;

import com.yjy.dao.UserDao;
import com.yjy.entity.Users;
import com.yjy.realm.myRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test() {
            //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
            Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro2.ini");
            //2、得到SecurityManager实例 并绑定给SecurityUtils
            SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zs", "1111");
            subject.login(token);
                //5、身份验证失败
            //是否认证通过
//            System.out.println(subject.isAuthenticated());
//            //6、退出
//            subject.logout();
//            System.out.println(subject.isAuthenticated());

                boolean hasRole = subject.hasRole("role1");
                System.out.println(hasRole);
                boolean[] hasRoles = subject.hasRoles(Arrays.asList("role1", "role2"));
                for (boolean hasRole1 : hasRoles) {
                    System.out.println(hasRole1);
            }
                boolean permitted = subject.isPermitted("user:add");
                System.out.println(permitted);
                boolean permittedAll = subject.isPermittedAll("user:add","user:update","user:delete");
                System.out.println(permittedAll);
                if (subject.hasRole("role1")){
                    System.out.println("可以操作：增加，删除，修改");
                }else {
                    System.out.println("不可以操作：增加，删除，修改");
                }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        UserDao userDao = new UserDao();
        Users users = new Users();
        System.out.println("请输入密码");
        String password = scanner.next();
        users.setUsername(username);
        String password2 = new Md5Hash(password,"dddd",1).toString();
        users.setPassword_salt("dddd");
        users.setPassword(password2);
        userDao.add(users);
    }
    @Test
    public void newuser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        UserDao userDao = new UserDao();
        Users users = new Users();
        System.out.println("请输入密码");
        String password = scanner.next();
        users.setUsername(username);
        users.setPassword(password);
        userDao.add(users);
    }
@Test
    public void login(){
      Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        UserDao userDao = new UserDao();
        Users users = userDao.FindUser(username);
         System.out.println("请输入密码");
        String password = scanner.next();
        myRealm myRealm = new myRealm();

}

}
