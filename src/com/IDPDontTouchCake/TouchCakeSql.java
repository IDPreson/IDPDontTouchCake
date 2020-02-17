package com.IDPDontTouchCake;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TouchCakeSql {
    public boolean checkServerExist(String description) throws SQLException {
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select * from donttouchcakeserver where `description`=?");
        statement.setString(1,description);
        resultSet=statement.executeQuery();
        if(resultSet.next()){
            connectSql.release(connection,statement,resultSet);
            return true;
        }
        return false;
    }
    public void addServerInfo(String description) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("insert into donttouchcakeserver (`description`,`status`) values (?,?)");
        statement.setString(1,description);
        statement.setString(2,"等待");
        statement.executeUpdate();
    }
    public void setServerStatus(String description,String status) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("update donttouchcakeserver set `status`=? where `description`=?");
        statement.setString(1,status);
        statement.setString(2,description);
        statement.executeUpdate();
    }
}
