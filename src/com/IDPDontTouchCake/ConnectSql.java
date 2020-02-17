package com.IDPDontTouchCake;

import java.sql.*;

public class ConnectSql {
    private String SqlName;
    private String UserName;
    private String PassWord;

       public ConnectSql(String SqlName, String UserName, String PassWord) {
           this.SqlName = "jdbc:mysql://localhost:3306/" + SqlName + "?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8";
           this.UserName = UserName;
           this.PassWord = PassWord;
       }

       public Connection Connect() throws SQLException {
           return DriverManager.getConnection(this.SqlName, this.UserName, this.PassWord);
       }

       public void release(Connection connection, Statement statement, ResultSet resultSet) {
           if (resultSet != null) {
               try {
                   resultSet.close();
               } catch (Exception var7) {
                   var7.printStackTrace();
               }
           }

           if (statement != null) {
               try {
                   statement.close();
               } catch (Exception var6) {
                   var6.printStackTrace();
               }
           }
           if (connection != null) {
               try {
                   connection.close();
               } catch (Exception var5) {
                   var5.printStackTrace();
               }
            }
    }
}
