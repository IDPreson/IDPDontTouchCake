package com.IDPDontTouchCake;

import cn.nukkit.event.Listener;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;

public class cake extends PluginBase implements Listener {
    public static Config config;
    public static FormWindowSimple formWindowSimple;
    ShopWindow shopWindow=new ShopWindow();
    @Override
    public void onLoad() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕装载中");
    }
    @Override
    public void onEnable() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕启动中");
        this.getServer().getPluginManager().registerEvents(this,this);
        String[] str=new String[0];
        main(str);
        initConfig();
    }
    @Override
    public void onDisable() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕卸载中");
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void initConfig(){
        File file=new File(this.getDataFolder()+"//config.yml");
        if(!file.exists()){
            config=new Config(file,2);
            config.set("唯一服务器标识","");
            config.set("最低游戏人数",0);
            config.set("最大游戏人数",0);
            config.set("等待大厅X",0);
            config.set("等待大厅Y",0);
            config.set("等待大厅Z",0);
            config.set("等待时间",0);
            config.set("红队出生点X",0);
            config.set("红队出生点Y",0);
            config.set("红队出生点Z",0);
            config.set("蓝队出生点X",0);
            config.set("蓝队出生点Y",0);
            config.set("蓝队出生点Z",0);
            config.set("绿队出生点X",0);
            config.set("绿队出生点Y",0);
            config.set("绿队出生点Z",0);
            config.set("黄队出生点X",0);
            config.set("黄队出生点Y",0);
            config.set("黄队出生点Z",0);
            config.set("红队蛋糕X",0);
            config.set("红队蛋糕Y",0);
            config.set("红队蛋糕Z",0);
            config.set("蓝队蛋糕X",0);
            config.set("蓝队蛋糕Y",0);
            config.set("蓝队蛋糕Z",0);
            config.set("绿队蛋糕X",0);
            config.set("绿队蛋糕Y",0);
            config.set("绿队蛋糕Z",0);
            config.set("黄队蛋糕X",0);
            config.set("黄队蛋糕Y",0);
            config.set("黄队蛋糕Z",0);
            config.set("是否设置完毕",-1);
            config.save();
        }
    }
}