package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.block.BlockBedrock;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.HashMap;

public class cake extends PluginBase implements Listener {
    public static Config config;
    public static HashMap<String,TeamInfo> Teams=new HashMap<>();
    ShopWindow shopWindow=new ShopWindow();
    PlayerBugProp playerBugProp=new PlayerBugProp();
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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player=this.getServer().getPlayer(sender.getName());
        if(command.getName().equalsIgnoreCase("lobbyLocation")){
            config.set("等待大厅X",player.x);
            config.set("等待大厅Y",player.y);
            config.set("等待大厅Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("redTeamLocation")){
            config.set("红队出生点X",player.x);
            config.set("红队出生点Y",player.y);
            config.set("红队出生点Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("blueTeamLocation")){
            config.set("蓝队出生点X",player.x);
            config.set("蓝队出生点Y",player.y);
            config.set("蓝队出生点Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("greenTeamLocation")){
            config.set("绿队出生点X",player.x);
            config.set("绿队出生点Y",player.y);
            config.set("绿队出生点Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("yellowTeamLocation")){
            config.set("黄队出生点X",player.x);
            config.set("黄队出生点Y",player.y);
            config.set("黄队出生点Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setRedCake")){
            config.set("红队蛋糕X",player.x);
            config.set("红队蛋糕Y",player.y);
            config.set("红队蛋糕Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setBlueCake")){
            config.set("蓝队蛋糕X",player.x);
            config.set("蓝队蛋糕Y",player.y);
            config.set("蓝队蛋糕Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setGreenCake")){
            config.set("绿队蛋糕X",player.x);
            config.set("绿队蛋糕Y",player.y);
            config.set("绿队蛋糕Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setYellowCake")){
            config.set("黄队蛋糕X",player.x);
            config.set("黄队蛋糕Y",player.y);
            config.set("黄队蛋糕Z",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setRedScale1")){
            config.set("红队范围X1",player.x);
            config.set("红队范围Y1",player.y);
            config.set("红队范围Z1",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setBlueScale1")){
            config.set("蓝队范围X1",player.x);
            config.set("蓝队范围Y1",player.y);
            config.set("蓝队范围Z1",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setGreenScale1")){
            config.set("绿队范围X1",player.x);
            config.set("绿队范围Y1",player.y);
            config.set("绿队范围Z1",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setYellowScale1")){
            config.set("黄队范围X1",player.x);
            config.set("黄队范围Y1",player.y);
            config.set("黄队范围Z1",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setRedScale2")){
            config.set("红队范围X2",player.x);
            config.set("红队范围Y2",player.y);
            config.set("红队范围Z2",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setBlueScale2")){
            config.set("蓝队范围X2",player.x);
            config.set("蓝队范围Y2",player.y);
            config.set("蓝队范围Z2",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setGreenScale2")){
            config.set("绿队范围X2",player.x);
            config.set("绿队范围Y2",player.y);
            config.set("绿队范围Z2",player.z);
            config.save();
            return true;
        }
        if(command.getName().equalsIgnoreCase("setYellowScale2")){
            config.set("黄队范围X2",player.x);
            config.set("黄队范围Y2",player.y);
            config.set("黄队范围Z2",player.z);
            config.save();
            return true;
        }
        return false;
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent p){

    }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent p){
        if(p.getBlock() instanceof BlockBedrock){
            p.getPlayer().showFormWindow(shopWindow.getShopWindow());
        }
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent b){
        b.setCancelled(true);
    }
    @EventHandler
    public void onPlayerFormRespondedEvent(PlayerFormRespondedEvent p){
        if(p.wasClosed()){
            return;
        }
        if(p.getWindow() instanceof FormWindowSimple){
            FormResponseSimple formResponseSimple=(FormResponseSimple)p.getResponse();
            if(p.getPlayer().getNameTag().contains("红队")){
                playerBugProp.buyProp(Teams.get("红队").getPlayerKills(p.getPlayer()),p.getPlayer(),formResponseSimple.getClickedButton().getText(),"红队");
            }
            if(p.getPlayer().getNameTag().contains("蓝队")){
                playerBugProp.buyProp(Teams.get("蓝队").getPlayerKills(p.getPlayer()),p.getPlayer(),formResponseSimple.getClickedButton().getText(),"蓝队");
            }
            if(p.getPlayer().getNameTag().contains("绿队")){
                playerBugProp.buyProp(Teams.get("绿队").getPlayerKills(p.getPlayer()),p.getPlayer(),formResponseSimple.getClickedButton().getText(),"绿队");
            }
            if(p.getPlayer().getNameTag().contains("黄队")){
                playerBugProp.buyProp(Teams.get("黄队").getPlayerKills(p.getPlayer()),p.getPlayer(),formResponseSimple.getClickedButton().getText(),"黄队");
            }
        }
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
            config.set("红队范围X1",0);
            config.set("红队范围Y1",0);
            config.set("红队范围Z1",0);
            config.set("蓝队范围X1",0);
            config.set("蓝队范围Y1",0);
            config.set("蓝队范围Z1",0);
            config.set("绿队范围X1",0);
            config.set("绿队范围Y1",0);
            config.set("绿队范围Z1",0);
            config.set("黄队范围X1",0);
            config.set("黄队范围Y1",0);
            config.set("黄队范围Z1",0);
            config.set("红队范围X2",0);
            config.set("红队范围Y2",0);
            config.set("红队范围Z2",0);
            config.set("蓝队范围X2",0);
            config.set("蓝队范围Y2",0);
            config.set("蓝队范围Z2",0);
            config.set("绿队范围X2",0);
            config.set("绿队范围Y2",0);
            config.set("绿队范围Z2",0);
            config.set("黄队范围X2",0);
            config.set("黄队范围Y2",0);
            config.set("黄队范围Z2",0);
            config.set("是否设置完毕",-1);
            config.save();
        }else{
            config=new Config(file,2);
            config.set("唯一服务器标识",this.getServer().getMotd());
            if(config.getInt("是否设置完毕")==0){
                TeamInfo teamInfo=new TeamInfo("红队");
                Teams.put("红队",teamInfo);
                teamInfo=new TeamInfo("蓝队");
                Teams.put("蓝队",teamInfo);
                teamInfo=new TeamInfo("绿队");
                Teams.put("黄队",teamInfo);
                teamInfo=new TeamInfo("黄队");
                Teams.put("黄队",teamInfo);
            }
        }
    }
}