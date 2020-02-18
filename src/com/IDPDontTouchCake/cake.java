package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.block.BlockBedrock;
import cn.nukkit.block.BlockCake;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.level.LevelLoadEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.event.potion.PotionApplyEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.ItemApple;
import cn.nukkit.level.GameRule;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.Config;

import java.io.File;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.HashMap;

public class cake extends PluginBase implements Listener {
    public static Config config;
    public static HashMap<String,TeamInfo> Teams=new HashMap<>();
    public static TouchCakeSql touchCakeSql=new TouchCakeSql();
    public static int CountDown=20,DeathFight=300,reloadtime=10;
    public static String victoryTeamName="",setment="";
    String cakeSet="";
    ShopWindow shopWindow=new ShopWindow();
    PlayerBugProp playerBugProp=new PlayerBugProp();
    PlayerEvent playerEvent=new PlayerEvent();
    @Override
    public void onLoad() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕装载中");
    }
    @Override
    public void onEnable() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕启动中");
        this.getServer().getPluginManager().registerEvents(this,this);
        this.getServer().getScheduler().scheduleRepeatingTask(new TimeTask(),20);
        String[] str=new String[0];
        main(str);
        initConfig();
    }
    @Override
    public void onDisable() {
        this.getServer().getLogger().info("IDP别碰我的蛋糕卸载中");
        try {
            touchCakeSql.setServerStatus(this.getServer().getMotd(),"关闭");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            cakeSet="红队蛋糕";
            return true;
        }
        if(command.getName().equalsIgnoreCase("setBlueCake")){
            cakeSet="蓝队蛋糕";
            return true;
        }
        if(command.getName().equalsIgnoreCase("setGreenCake")){
            cakeSet="绿队蛋糕";
            return true;
        }
        if(command.getName().equalsIgnoreCase("setYellowCake")){
            cakeSet="黄队蛋糕";
            return true;
        }
        return false;
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent p){
        p.getPlayer().setFoodEnabled(false);
        try {
            p.getPlayer().setNameTag("⭐"+touchCakeSql.getPlayerLevel(p.getPlayer().getName())+"|"+touchCakeSql.getPLayerPrefix(p.getPlayer().getName())+"|"+p.getPlayer().getName()+"§8");
            p.getPlayer().setDisplayName("⭐"+touchCakeSql.getPlayerLevel(p.getPlayer().getName())+"|"+p.getPlayer().getName()+"§8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ItemApple itemApple=new ItemApple();
        itemApple.setCustomName("返回大厅");
        p.getPlayer().getInventory().addItem(itemApple);
        if(config.getInt("是否设置完毕")==0){
            if(config.getString("状态").equals("等待")){
                if(config.getInt("当前游戏人数")+1<=config.getInt("最大游戏人数")){
                    config.set("当前游戏人数",this.getServer().getOnlinePlayers().size());
                }else{
                    p.getPlayer().sendMessage("§l**[IDP]-游戏玩家数达最大，您转为观战模式！");
                    p.getPlayer().setGamemode(3);
                }
                if(config.getInt("当前游戏人数")>=config.getInt("最低游戏人数")){
                    config.set("状态","倒计时");
                }
            }
        }
        config.save();
    }
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent p){
        p.setCancelled(true);
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent p){
        if(config.getInt("是否设置完毕")!=0){
            return;
        }
        config.set("当前游戏人数",this.getServer().getOnlinePlayers().size());
        config.save();
        if(config.getString("状态").equals("倒计时")){
            if(config.getInt("当前游戏人数")<config.getInt("最低游戏人数")){
                CountDown=20;
                config.set("状态","等待");
                this.getServer().broadcastMessage("§l**[IDP]-玩家数小于最低游戏人数，停止倒计时！");
            }
        }
        if(config.getString("状态").equals("开始")){
            playerEvent.PlayerQuit(p.getPlayer(),Teams);
        }
        config.save();
    }
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent p){
        p.setKeepInventory(true);
        if(config.getInt("是否设置完毕")!=0 || !config.getString("状态").equals("开始")){
            return;
        }
        playerEvent.PlayerDeath(p.getEntity(),Teams);
    }
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent p){
        if(config.getInt("是否设置完毕")!=0 || !config.getString("状态").equals("开始")){
            return;
        }
        String TeamName_1 = "";
        if (p.getPlayer().getNameTag().contains("黄队")) {
            TeamName_1 = "黄队";
        }
        if (p.getPlayer().getNameTag().contains("红队")) {
            TeamName_1 = "红队";
        }
        if (p.getPlayer().getNameTag().contains("绿队")) {
            TeamName_1 = "绿队";
        }
        if (p.getPlayer().getNameTag().contains("蓝队")) {
            TeamName_1 = "蓝队";
        }
        p.getPlayer().setGamemode(3);
        p.getPlayer().setGamemode(0);
        for (int i = 0; i < Teams.get(TeamName_1).TeamPlayers.size(); i++) {
            if (Teams.get(TeamName_1).TeamPlayers.get(i).player.equals(p.getPlayer())) {
                PlayerInfo playerInfo=Teams.get(TeamName_1).TeamPlayers.get(i);
                if (Teams.get(TeamName_1).isCakebreak) {
                    try {
                        if(cake.touchCakeSql.isPlayerExist(playerInfo.player.getName())){
                            cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,0,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                        }else{
                            cake.touchCakeSql.createPlayerInfo(playerInfo.player.getName());
                            cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,0,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Teams.get(TeamName_1).TeamPlayers.remove(i);
                    p.getPlayer().getInventory().clearAll();
                    p.getPlayer().sendTitle("你的蛋糕已被破坏\n你无法重生了！");
                    p.getPlayer().setGamemode(3);
                    ItemApple itemApple=new ItemApple();
                    itemApple.setCustomName("返回大厅");
                    p.getPlayer().getInventory().addItem(itemApple);
                    p.getPlayer().removeAllEffects();
                    break;
                } else {
                    p.getPlayer().sendTitle("3秒后可移动!");
                    Teams.get(TeamName_1).TeamPlayers.get(i).KilledName="";
                    Teams.get(TeamName_1).TeamPlayers.get(i).KilledPlayer=null;
                    p.setRespawnPosition(Teams.get(TeamName_1).SpawnLocation);
                    break;
                }
            }
        }
    }
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent p){
        if(config.getInt("是否设置完毕")!=0 || !config.getString("状态").equals("开始")){
            return;
        }
        String TeamName="";
        if(p.getPlayer().getNameTag().contains("黄队")){
            TeamName="黄队";
        }
        if(p.getPlayer().getNameTag().contains("红队")){
            TeamName="红队";
        }
        if(p.getPlayer().getNameTag().contains("绿队")){
            TeamName="绿队";
        }
        if(p.getPlayer().getNameTag().contains("蓝队")){
            TeamName="蓝队";
        }
        playerEvent.checkPlayerMove(p.getPlayer(),TeamName,Teams);
        if(!TeamName.equals("")){
            for(int i=0;i<Teams.get(TeamName).TeamPlayers.size();i++){
                if(Teams.get(TeamName).TeamPlayers.get(i).player.equals(p.getPlayer())){
                    if(!Teams.get(TeamName).TeamPlayers.get(i).canMove){
                        p.getPlayer().sendTitle("§l§c你还未重生结束！");
                        p.setCancelled(true);
                        Effect effect=new Effect(Effect.SPEED,"速度",0,0,255).setAmplifier(2);
                        effect.setDuration(999999);
                        p.getPlayer().addEffect(effect);
                        break;
                    }
                }
            }
        }
    }
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
        if(config.getInt("是否设置完毕")!=0 || !config.getString("状态").equals("开始")){
            e.setCancelled(true);
            return;
        }
        Player beAttackPlayer=(Player) e.getEntity();
        Player AttackPlayer=(Player) e.getDamager();
        String TeamName_1="";
        if(beAttackPlayer.getPlayer().getNameTag().contains("黄队")){
            TeamName_1="黄队";
        }
        if(beAttackPlayer.getPlayer().getNameTag().contains("红队")){
            TeamName_1="红队";
        }
        if(beAttackPlayer.getPlayer().getNameTag().contains("绿队")){
            TeamName_1="绿队";
        }
        if(beAttackPlayer.getPlayer().getNameTag().contains("蓝队")){
            TeamName_1="蓝队";
        }
        String TeamName_2="";
        if(AttackPlayer.getPlayer().getNameTag().contains("黄队")){
            TeamName_2="黄队";
        }
        if(AttackPlayer.getPlayer().getNameTag().contains("红队")){
            TeamName_2="红队";
        }
        if(AttackPlayer.getPlayer().getNameTag().contains("绿队")){
            TeamName_2="绿队";
        }
        if(AttackPlayer.getPlayer().getNameTag().contains("蓝队")){
            TeamName_2="蓝队";
        }
        if(TeamName_1.equals(TeamName_2)){
            e.setCancelled(true);
            return;
        }
        for(int i=0;i<Teams.get(TeamName_1).TeamPlayers.size();i++){
            if(Teams.get(TeamName_1).TeamPlayers.get(i).player.equals(beAttackPlayer)){
                Teams.get(TeamName_1).TeamPlayers.get(i).KilledName=AttackPlayer.getNameTag();
                Teams.get(TeamName_1).TeamPlayers.get(i).KilledPlayer=AttackPlayer;
                break;
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent p) {
        if (p.getBlock() instanceof BlockBedrock) {
            p.getPlayer().showFormWindow(shopWindow.getShopWindow());
        }
        if (p.getItem().getCustomName().equals("返回大厅")) {
            InetSocketAddress inetSocketAddress=new InetSocketAddress(config.getString("大厅服IP"),config.getInt("大厅服端口"));
            p.getPlayer().transfer(inetSocketAddress);
        }
        if(!cakeSet.equals("")){
            config.set(cakeSet+"X",p.getBlock().x);
            config.set(cakeSet+"Y",p.getBlock().y);
            config.set(cakeSet+"Z",p.getBlock().z);
            config.save();
            cakeSet="";
        }
    }
    @EventHandler
    public void onPotionApplyEvent(PotionApplyEvent p){
        if(p.getApplyEffect().getName().contains("%potion.invisibility")){
            p.setCancelled(true);
            Effect effect=new Effect(Effect.INVISIBILITY,"隐身",0,0,255);
            effect.setDuration(210);
            effect.setAmplifier(0);
            effect.setVisible(false);
            p.getEntity().addEffect(effect);
        }
    }
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent b){
        if(b.getBlock() instanceof BlockCake){
            String TeamName_1 = "";
            if (b.getPlayer().getNameTag().contains("黄队")) {
                TeamName_1 = "黄队";
            }
            if (b.getPlayer().getNameTag().contains("红队")) {
                TeamName_1 = "红队";
            }
            if (b.getPlayer().getNameTag().contains("绿队")) {
                TeamName_1 = "绿队";
            }
            if (b.getPlayer().getNameTag().contains("蓝队")) {
                TeamName_1 = "蓝队";
            }
            if(Teams.get(TeamName_1).CakeLocation.equals(b.getBlock().getLocation())){
                b.getPlayer().sendMessage("你不能破坏你自己的蛋糕");
                b.setCancelled(true);
                return;
            }
            for(TeamInfo teamInfo:Teams.values()){
                if(teamInfo.CakeLocation.equals(b.getBlock().getLocation())){
                    teamInfo.isCakebreak=true;
                    for(PlayerInfo playerInfo:teamInfo.TeamPlayers){
                        playerInfo.player.sendTitle("§l§c你的蛋糕被摧毁了\n你死亡后无法重生！");
                    }
                    this.getServer().broadcastMessage("§l**[IDP]-"+b.getPlayer().getName()+"摧毁了"+teamInfo.TeamName+"的蛋糕！");
                    break;
                }
            }
            for(TeamInfo teamInfo:Teams.values()){
                for(PlayerInfo playerInfo:teamInfo.TeamPlayers){
                    if(playerInfo.player.equals(b.getPlayer())){
                        playerInfo.breakcake=playerInfo.breakcake+1;
                        playerInfo.player.sendMessage("§l**[IDP]-摧毁蛋糕数+1");
                        break;
                    }
                }
            }
        }else{
            b.setCancelled(true);
        }
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
    @EventHandler
    public void onLevelLoadEvent(LevelLoadEvent l){
        l.getLevel().gameRules.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN,true);
        this.getServer().getLevelByName("Game").setBlock(Teams.get("红队").CakeLocation,new BlockCake());
        this.getServer().getLevelByName("Game").setBlock(Teams.get("蓝队").CakeLocation,new BlockCake());
        this.getServer().getLevelByName("Game").setBlock(Teams.get("绿队").CakeLocation,new BlockCake());
        this.getServer().getLevelByName("Game").setBlock(Teams.get("黄队").CakeLocation,new BlockCake());
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
            config.set("唯一服务器标识",this.getServer().getMotd());
            config.set("当前游戏人数",0);
            config.set("最低游戏人数",0);
            config.set("最大游戏人数",0);
            config.set("等待大厅X",0);
            config.set("等待大厅Y",0);
            config.set("等待大厅Z",0);
            config.set("状态","关闭");
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
            config.set("大厅服IP","111.229.24.142");
            config.set("大厅服端口",52113);
            config.save();
            try {
                if(!touchCakeSql.checkServerExist(this.getServer().getMotd())){
                    touchCakeSql.addServerInfo(this.getServer().getMotd());
                }else{
                    touchCakeSql.setServerStatus(this.getServer().getMotd(),"等待");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                touchCakeSql.setServerStatus(this.getServer().getMotd(),"等待");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            config=new Config(file,2);
            config.set("状态","等待");
            config.set("当前游戏人数",0);
            if(config.getInt("是否设置完毕")==0){
                TeamInfo teamInfo=new TeamInfo("红队");
                Teams.put("红队",teamInfo);
                teamInfo=new TeamInfo("蓝队");
                Teams.put("蓝队",teamInfo);
                teamInfo=new TeamInfo("绿队");
                Teams.put("绿队",teamInfo);
                teamInfo=new TeamInfo("黄队");
                Teams.put("黄队",teamInfo);
            }
            config.save();
        }
    }
}