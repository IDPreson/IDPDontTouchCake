package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.Server;

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
    public boolean isPlayerExist(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select * from donttouchcakeinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        if(resultSet.next()){
            connectSql.release(connection,statement,resultSet);
            return true;
        }
        return false;
    }
    public void createPlayerInfo(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("insert into donttouchcakeinfo(`name`) values(?)");
        statement.setString(1,name);
        statement.executeUpdate();
    }
    public String getPlayerVIP(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select `vip` from basicinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            String vip=resultSet.getString("vip");
            connectSql.release(connection,statement,resultSet);
            return vip;
        }
        return "非VIP";
    }
    public void Settlement(String name, int Kills, int deaths, int victorycount, int totalcount, int breakcakecount,Player p,double times) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("update donttouchcakeinfo set `kills`=`kills`+?,`deaths`=`deaths`+?,`victorycount`=`victorycount`+?,`totalcount`=`totalcount`+?,`breakcakecount`=`breakcakecount`+? where `name`=?");
        statement.setInt(1,Kills);
        statement.setInt(2,deaths);
        statement.setInt(3,victorycount);
        statement.setInt(4,totalcount);
        statement.setInt(5,breakcakecount);
        statement.setString(6,name);
        statement.executeUpdate();
        int exp_1=getPlayerExp_1(name);
        int exp_2=getPlayerExp_2(name);
        int level=getPlayerLevel(name);
        int coins=0;
        if(victorycount==1){
            exp_1=exp_1+(int)((25+Kills)*times);
            coins=150+(int)(Kills*times);
            p.sendMessage("§l**[IDP]-你获得了金币:"+coins+"|经验:"+(int)((25+Kills)*times));
        }else{
            exp_1=exp_1+(int)((10+Kills)*times);
            coins=(int)(Kills*times);
            p.sendMessage("§l**[IDP]-你获得了金币:"+coins+"|经验:"+(int)((10+Kills)*times));
        }
        if(exp_1>=exp_2){
            exp_1=exp_1-exp_2;
            level=level+1;
            exp_2=exp_2*level;
            Server.getInstance().getLogger().info(exp_1+" "+exp_2+" "+level);
            p.sendMessage("§l**[IDP]-恭喜你升级了！！");
        }
        statement=connection.prepareStatement("update basicinfo set `nextupexp`=?,`needupexp`=?,`level`=?,`coin`=`coin`+? where `name`=?");
        statement.setInt(1,exp_1);
        statement.setInt(2,exp_2);
        statement.setInt(3,level);
        statement.setInt(4,coins);
        statement.setString(5,name);
        statement.executeUpdate();
        connectSql.release(connection,statement,null);
    }
    public int getPlayerExp_1(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select `nextupexp` from basicinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            int exp=resultSet.getInt("nextupexp");
            connectSql.release(connection,statement,resultSet);
            return exp;
        }
        return 0;
    }
    public int getPlayerExp_2(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select `needupexp` from basicinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            int exp=resultSet.getInt("needupexp");
            connectSql.release(connection,statement,resultSet);
            return exp;
        }
        return 0;
    }
    public int getPlayerLevel(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select `level` from basicinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            int level=resultSet.getInt("level");
            connectSql.release(connection,statement,resultSet);
            return level;
        }
        return 0;
    }
    public String getPLayerPrefix(String name) throws SQLException{
        ConnectSql connectSql;
        PreparedStatement statement;
        Connection connection;
        ResultSet resultSet;
        connectSql=new ConnectSql("playerinfo","root","1264629802");
        connection=connectSql.Connect();
        statement=connection.prepareStatement("select `currentprefix` from basicinfo where `name`=?");
        statement.setString(1,name);
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            String prefix=resultSet.getString("currentprefix");
            connectSql.release(connection,statement,resultSet);
            return prefix;
        }
        return "";
    }
}
