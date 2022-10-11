package com.yjy.dao;

import com.yjy.entity.Users;

import java.sql.*;

public class UserDao {
    public Users FindUser(String username) {
        Users users=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "select * from users where username=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String username1 = rs.getString("username");
                String password = rs.getString("password");
                String password_salt = rs.getString("password_salt");
                users=new Users(username1,password,password_salt);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public void add(Users users) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "root");
            String sql = "insert into users (username,password,password_salt)values(?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,users.getUsername());
            pst.setString(2,users.getPassword());
            pst.setString(3,users.getPassword_salt());
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
