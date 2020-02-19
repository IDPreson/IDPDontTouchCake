package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.BlockCake;
import cn.nukkit.item.*;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Location;
import cn.nukkit.potion.Effect;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.DyeColor;

import java.net.InetSocketAddress;
import java.sql.SQLException;

public class TimeTask extends Task {
    @Override
    public void onRun(int i) {
        if(cake.config.getInt("是否设置完毕")==0){
            if(cake.config.getString("状态").equals("等待")){
                for(Player player: Server.getInstance().getOnlinePlayers().values()){
                    player.sendPopup("当前玩家数:"+cake.config.getInt("当前游戏人数")+"||"+"最低需要:"+cake.config.getInt("最低游戏人数"));
                }
            }
            if(cake.config.getString("状态").equals("倒计时")){
                cake.config.set("当前游戏人数",Server.getInstance().getOnlinePlayers().size());
                cake.config.save();
                if(cake.config.getString("状态").equals("倒计时")){
                    if(cake.config.getInt("当前游戏人数")<cake.config.getInt("最低游戏人数")){
                        cake.CountDown=45;
                        cake.config.set("状态","等待");
                        Server.getInstance().broadcastMessage("§l**[IDP]-玩家数小于最低游戏人数，停止倒计时！");
                    }
                }
                for(Player player: Server.getInstance().getOnlinePlayers().values()){
                    player.sendPopup("倒计时:"+cake.CountDown+"秒");
                }
                cake.CountDown=cake.CountDown-1;
                if(cake.CountDown<=0){
                    cake.config.set("状态","分队中");
                }
            }
            if(cake.config.getString("状态").equals("分队中")){
                int o=1;
                for(Player player:Server.getInstance().getOnlinePlayers().values()){
                    player.getInventory().clearAll();
                    Effect effect=new Effect(Effect.SPEED,"速度",0,0,255).setAmplifier(1);
                    effect.setDuration(999999);
                    player.addEffect(effect);
                    switch (o%4){
                        case 0:
                            player.setNameTag("§l§4红队"+"|"+player.getName());
                            player.setDisplayName("§l§4红队"+"|"+player.getName());
                            Item itemBootsLeather_1=new ItemBootsLeather().setColor(DyeColor.RED);
                            Item itemChestplateLeather_1=new ItemChestplateLeather().setColor(DyeColor.RED);
                            Item itemHelmetLeather_1=new ItemHelmetLeather().setColor(DyeColor.RED);
                            Item itemLeggingsLeather_1=new ItemLeggingsLeather().setColor(DyeColor.RED);
                            ItemStick itemStick_1=new ItemStick();
                            Enchantment enchantment=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment.setLevel(2);
                            itemStick_1.addEnchantment(enchantment);
                            ItemBow itemBow_1=new ItemBow();
                            enchantment=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment.setLevel(1);
                            itemBow_1.addEnchantment(enchantment);
                            enchantment=Enchantment.get(Enchantment.ID_BOW_INFINITY);
                            enchantment.setLevel(2);
                            itemBow_1.addEnchantment(enchantment);
                            ItemArrow itemArrow_1=new ItemArrow();
                            player.getInventory().addItem(itemStick_1);
                            player.getInventory().addItem(itemBow_1);
                            player.getInventory().addItem(itemArrow_1);
                            player.getInventory().setBoots(itemBootsLeather_1);
                            player.getInventory().setChestplate(itemChestplateLeather_1);
                            player.getInventory().setHelmet(itemHelmetLeather_1);
                            player.getInventory().setLeggings(itemLeggingsLeather_1);
                            cake.Teams.get("红队").TeamPlayers.add(new PlayerInfo(player,"红队"));
                            break;
                        case 1:
                            player.setNameTag("§l§3蓝队"+"|"+player.getName());
                            player.setDisplayName("§l§3蓝队"+"|"+player.getName());
                            Item itemBootsLeather_2=new ItemBootsLeather().setColor(DyeColor.BLUE);
                            Item itemChestplateLeather_2=new ItemChestplateLeather().setColor(DyeColor.BLUE);
                            Item itemHelmetLeather_2=new ItemHelmetLeather().setColor(DyeColor.BLUE);
                            Item itemLeggingsLeather_2=new ItemLeggingsLeather().setColor(DyeColor.BLUE);
                            ItemStick itemStick_2=new ItemStick();
                            Enchantment enchantment_2=Enchantment.get(Enchantment.ID_KNOCKBACK).setLevel(2,false);
                            itemStick_2.addEnchantment(enchantment_2);
                            ItemBow itemBow_2=new ItemBow();
                            enchantment=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment.setLevel(1);
                            itemBow_2.addEnchantment(enchantment);
                            enchantment=Enchantment.get(Enchantment.ID_BOW_INFINITY);
                            enchantment.setLevel(2);
                            itemBow_2.addEnchantment(enchantment);
                            ItemArrow itemArrow_2=new ItemArrow();
                            player.getInventory().addItem(itemStick_2);
                            player.getInventory().addItem(itemBow_2);
                            player.getInventory().addItem(itemArrow_2);
                            player.getInventory().setBoots(itemBootsLeather_2);
                            player.getInventory().setChestplate(itemChestplateLeather_2);
                            player.getInventory().setHelmet(itemHelmetLeather_2);
                            player.getInventory().setLeggings(itemLeggingsLeather_2);
                            cake.Teams.get("蓝队").TeamPlayers.add(new PlayerInfo(player,"蓝队"));
                            break;
                        case 2:
                            player.setNameTag("§l§a绿队"+"|"+player.getName());
                            player.setDisplayName("§l§a绿队"+"|"+player.getName());
                            Item itemBootsLeather_3=new ItemBootsLeather().setColor(DyeColor.GREEN);
                            Item itemChestplateLeather_3=new ItemChestplateLeather().setColor(DyeColor.GREEN);
                            Item itemHelmetLeather_3=new ItemHelmetLeather().setColor(DyeColor.GREEN);
                            Item itemLeggingsLeather_3=new ItemLeggingsLeather().setColor(DyeColor.GREEN);
                            ItemStick itemStick_3=new ItemStick();
                            Enchantment enchantment_3=Enchantment.get(Enchantment.ID_LURE).setLevel(2,true);
                            itemStick_3.addEnchantment(enchantment_3);
                            ItemBow itemBow_3=new ItemBow();
                            enchantment=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment.setLevel(1);
                            itemBow_3.addEnchantment(enchantment);
                            enchantment=Enchantment.get(Enchantment.ID_BOW_INFINITY);
                            enchantment.setLevel(2);
                            itemBow_3.addEnchantment(enchantment);
                            ItemArrow itemArrow_3=new ItemArrow();
                            player.getInventory().addItem(itemStick_3);
                            player.getInventory().addItem(itemBow_3);
                            player.getInventory().addItem(itemArrow_3);
                            player.getInventory().setBoots(itemBootsLeather_3);
                            player.getInventory().setChestplate(itemChestplateLeather_3);
                            player.getInventory().setHelmet(itemHelmetLeather_3);
                            player.getInventory().setLeggings(itemLeggingsLeather_3);
                            cake.Teams.get("绿队").TeamPlayers.add(new PlayerInfo(player,"绿队"));
                            break;
                        case 3:
                            player.setNameTag("§l§e黄队"+"|"+player.getName());
                            player.setDisplayName("§l§e黄队"+"|"+player.getName());
                            Item itemBootsLeather_4=new ItemBootsLeather().setColor(DyeColor.YELLOW);
                            Item itemChestplateLeather_4=new ItemChestplateLeather().setColor(DyeColor.YELLOW);
                            Item itemHelmetLeather_4=new ItemHelmetLeather().setColor(DyeColor.YELLOW);
                            Item itemLeggingsLeather_4=new ItemLeggingsLeather().setColor(DyeColor.YELLOW);
                            ItemStick itemStick_4=new ItemStick();
                            Enchantment enchantment_4=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment_4.setLevel(2);
                            itemStick_4.addEnchantment(enchantment_4);
                            ItemBow itemBow_4=new ItemBow();
                            enchantment=Enchantment.get(Enchantment.ID_KNOCKBACK);
                            enchantment.setLevel(1);
                            itemBow_4.addEnchantment(enchantment);
                            enchantment=Enchantment.get(Enchantment.ID_BOW_INFINITY);
                            enchantment.setLevel(2);
                            itemBow_4.addEnchantment(enchantment);
                            ItemArrow itemArrow_4=new ItemArrow();
                            player.getInventory().addItem(itemStick_4);
                            player.getInventory().addItem(itemBow_4);
                            player.getInventory().addItem(itemArrow_4);
                            player.getInventory().setBoots(itemBootsLeather_4);
                            player.getInventory().setChestplate(itemChestplateLeather_4);
                            player.getInventory().setHelmet(itemHelmetLeather_4);
                            player.getInventory().setLeggings(itemLeggingsLeather_4);
                            cake.Teams.get("黄队").TeamPlayers.add(new PlayerInfo(player,"黄队"));
                            break;
                    }
                    o++;
                }
                for(TeamInfo teamInfo:cake.Teams.values()){
                    for(PlayerInfo playerInfo:teamInfo.TeamPlayers){
                        playerInfo.player.teleport(teamInfo.SpawnLocation);
                        playerInfo.player.sendTitle("§l§a你是"+teamInfo.TeamName,"§e努力守住你的蛋糕！！",20,45,15);
                    }
                }
                cake.config.set("状态","开始");
                try {
                    cake.touchCakeSql.setServerStatus(cake.config.getString("唯一服务器标识"),"开始");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(cake.config.getString("状态").equals("开始")){
                int redTeamPlayers=0,blueTeamPlayers=0,greenTeamPlayers=0,yellowTeamPlayers=0;
                String redCake="",blueCake="",greenCake="",yellowCake="";
                cake.DeathFight=cake.DeathFight-1;
                for(TeamInfo teamInfo:cake.Teams.values()) {
                    if(teamInfo.isDead){
                        continue;
                    }
                    switch (teamInfo.TeamName){
                        case "红队":
                            redTeamPlayers=teamInfo.TeamPlayers.size();
                            if(!teamInfo.isCakebreak){
                                redCake="√";
                            }else{
                                redCake="×";
                            }
                            break;
                        case "蓝队":
                            blueTeamPlayers=teamInfo.TeamPlayers.size();
                            if(!teamInfo.isCakebreak){
                                blueCake="√";
                            }else{
                                blueCake="×";
                            }
                            break;
                        case "黄队":
                            yellowTeamPlayers=teamInfo.TeamPlayers.size();
                            if(!teamInfo.isCakebreak){
                                yellowCake="√";
                            }else{
                                yellowCake="×";
                            }
                            break;
                        case "绿队":
                            greenTeamPlayers=teamInfo.TeamPlayers.size();
                            if(!teamInfo.isCakebreak){
                                greenCake="√";
                            }else{
                                greenCake="×";
                            }
                            break;
                    }
                    if(!teamInfo.isDead){
                        if(teamInfo.TeamPlayers.size()==0){
                            teamInfo.isDead=true;
                            Server.getInstance().broadcastMessage("§l**[IDP]"+teamInfo.TeamName+"已经灭亡！");
                            teamInfo.isCakebreak=true;
                        }
                    }
                }
                for(TeamInfo teamInfo:cake.Teams.values()) {
                    for(PlayerInfo playerInfo:teamInfo.TeamPlayers){
                        playerInfo.player.sendPopup("击杀:"+playerInfo.Kills+"||"+"§l§4红队("+redTeamPlayers+"|"+redCake+")"+"§l§3蓝队("+blueTeamPlayers+"|"+blueCake+")"+"§l§a绿队("+greenTeamPlayers+"|"+greenCake+")"+"§l§e黄队("+yellowTeamPlayers+"|"+yellowCake+")"+"剩余时间:"+cake.DeathFight);
                        if(!playerInfo.canMove){
                            playerInfo.reSpawnTime=playerInfo.reSpawnTime-1;
                        }
                        if(playerInfo.reSpawnTime<=0){
                            playerInfo.reSpawnTime=3;
                            playerInfo.canMove=true;
                        }
                    }
                }
                if(cake.DeathFight<=0){
                    cake.config.set("状态","死斗");
                    Location lobbyLocation=new Location(cake.config.getDouble("等待大厅X"),cake.config.getDouble("等待大厅Y"),cake.config.getDouble("等待大厅Z"));
                    Server.getInstance().broadcastMessage("§l**[IDP]-开始死斗模式！！！！");
                    for(TeamInfo teamInfo:cake.Teams.values()) {
                        teamInfo.isCakebreak=true;
                        for(PlayerInfo playerinfo:teamInfo.TeamPlayers){
                            playerinfo.player.teleport(lobbyLocation);
                        }
                    }
                }
                if(redTeamPlayers!=0 && blueTeamPlayers==0 && greenTeamPlayers==0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜红队获得了胜利！");
                    cake.victoryTeamName="红队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers!=0 && greenTeamPlayers==0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜蓝队获得了胜利！");
                    cake.victoryTeamName="蓝队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers==0 && greenTeamPlayers!=0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜绿队获得了胜利！");
                    cake.victoryTeamName="绿队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers==0 && greenTeamPlayers==0 && yellowTeamPlayers!=0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜黄队获得了胜利！");
                    cake.victoryTeamName="黄队";
                    cake.config.set("状态","结束");
                }
            }
            if(cake.config.getString("状态").equals("死斗")){
                int redTeamPlayers=0,blueTeamPlayers=0,greenTeamPlayers=0,yellowTeamPlayers=0;
                for(TeamInfo teamInfo:cake.Teams.values()) {
                    switch (teamInfo.TeamName) {
                        case "红队":
                            redTeamPlayers = teamInfo.TeamPlayers.size();
                            break;
                        case "蓝队":
                            blueTeamPlayers = teamInfo.TeamPlayers.size();
                            break;
                        case "黄队":
                            yellowTeamPlayers = teamInfo.TeamPlayers.size();
                            break;
                        case "绿队":
                            greenTeamPlayers = teamInfo.TeamPlayers.size();
                            break;
                    }
                    if (!teamInfo.isDead) {
                        if (teamInfo.TeamPlayers.size() == 0) {
                            teamInfo.isDead = true;
                            Server.getInstance().broadcastMessage("§l**[IDP]" + teamInfo.TeamName + "已经灭亡！");
                            teamInfo.isCakebreak = true;
                        }
                    }
                }
                for(TeamInfo teamInfo:cake.Teams.values()) {
                    for(PlayerInfo playerInfo:teamInfo.TeamPlayers){
                        playerInfo.player.sendPopup("击杀:"+playerInfo.Kills+"||"+"§l§4红队("+redTeamPlayers+")"+"§l§3蓝队("+blueTeamPlayers+")"+"§l§a绿队("+greenTeamPlayers+")"+"§l§e黄队("+yellowTeamPlayers+")"+"§l§c！！死斗！！");
                    }
                }
                if(redTeamPlayers!=0 && blueTeamPlayers==0 && greenTeamPlayers==0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜§4红队§6获得了胜利！");
                    cake.victoryTeamName="红队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers!=0 && greenTeamPlayers==0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜§b蓝队§6获得了胜利！");
                    cake.victoryTeamName="蓝队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers==0 && greenTeamPlayers!=0 && yellowTeamPlayers==0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜§a绿队§6获得了胜利！");
                    cake.victoryTeamName="绿队";
                    cake.config.set("状态","结束");
                }
                if(redTeamPlayers==0 && blueTeamPlayers==0 && greenTeamPlayers==0 && yellowTeamPlayers!=0){
                    Server.getInstance().broadcastMessage("§l**[IDP]-恭喜§e黄队§6获得了胜利！");
                    cake.victoryTeamName="黄队";
                    cake.config.set("状态","结束");
                }
            }
            if(cake.config.getString("状态").equals("结束")){
                if(cake.setment.equals("")){
                    for(TeamInfo teamInfo:cake.Teams.values()) {
                        for (PlayerInfo playerInfo : teamInfo.TeamPlayers) {
                            try {
                                if(cake.touchCakeSql.isPlayerExist(playerInfo.player.getName()))
                                {
                                    if(teamInfo.TeamName.equals(cake.victoryTeamName)){
                                        cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,1,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                                    }else{
                                        cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,0,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                                    }
                                }else{
                                    cake.touchCakeSql.createPlayerInfo(playerInfo.player.getName());
                                    if(teamInfo.TeamName.equals(cake.victoryTeamName)){
                                        cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,1,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                                    }else{
                                        cake.touchCakeSql.Settlement(playerInfo.player.getName(),playerInfo.Kills,playerInfo.Deaths,0,1,playerInfo.breakcake,playerInfo.player,playerInfo.getVipMultiple());
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            playerInfo.player.getInventory().clearAll();
                            playerInfo.player.setGamemode(3);
                            ItemApple itemApple=new ItemApple();
                            itemApple.setCustomName("返回大厅");
                            playerInfo.player.getInventory().addItem(itemApple);
                        }
                    }
                    cake.setment="已结算";
                }else{
                    cake.reloadtime=cake.reloadtime-1;
                    Server.getInstance().broadcastMessage("服务器于"+cake.reloadtime+"秒后重启！");
                    if(cake.reloadtime<=0){
                        for (Player player:Server.getInstance().getOnlinePlayers().values()){
                            InetSocketAddress inetSocketAddress=new InetSocketAddress(cake.config.getString("大厅服IP"),cake.config.getInt("大厅服端口"));
                            player.transfer(inetSocketAddress);
                        }
                        Server.getInstance().getLevelByName("Game").setBlock(new Location(cake.config.getDouble("红队蛋糕X"),cake.config.getDouble("红队蛋糕Y"),cake.config.getDouble("红队蛋糕Z")),new BlockCake());
                        Server.getInstance().getLevelByName("Game").setBlock(new Location(cake.config.getDouble("蓝队蛋糕X"),cake.config.getDouble("蓝队蛋糕Y"),cake.config.getDouble("蓝队蛋糕Z")),new BlockCake());
                        Server.getInstance().getLevelByName("Game").setBlock(new Location(cake.config.getDouble("黄队蛋糕X"),cake.config.getDouble("黄队蛋糕Y"),cake.config.getDouble("黄队蛋糕Z")),new BlockCake());
                        Server.getInstance().getLevelByName("Game").setBlock(new Location(cake.config.getDouble("绿队蛋糕X"),cake.config.getDouble("绿队蛋糕Y"),cake.config.getDouble("绿队蛋糕Z")),new BlockCake());
                        Server.getInstance().reload();
                    }
                }
            }
        }
        cake.config.save();
    }
}
