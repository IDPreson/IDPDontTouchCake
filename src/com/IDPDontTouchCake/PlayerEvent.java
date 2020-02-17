package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.potion.Effect;

import java.util.HashMap;
import java.util.Map;

public class PlayerEvent {
    public void PlayerQuit(Player p, HashMap<String, TeamInfo> Teams) {
        if (p.getPlayer().getNameTag().contains("红队")) {
            for (int i = 0; i < Teams.get("红队").TeamPlayers.size(); i++) {
                if (Teams.get("红队").TeamPlayers.get(i).player.equals(p.getPlayer())) {
                    Teams.get("红队").TeamPlayers.remove(i);
                    Teams.get("红队").sendMessageToTeam("§l**[IDP]-你的队友" + p.getPlayer().getName() + "退出了游戏");
                    break;
                }
            }
        }
        if (p.getPlayer().getNameTag().contains("蓝队")) {
            for (int i = 0; i < Teams.get("蓝队").TeamPlayers.size(); i++) {
                if (Teams.get("蓝队").TeamPlayers.get(i).player.equals(p.getPlayer())) {
                    Teams.get("蓝队").TeamPlayers.remove(i);
                    Teams.get("蓝队").sendMessageToTeam("§l**[IDP]-你的队友" + p.getPlayer().getName() + "退出了游戏");
                    break;
                }
            }
        }
        if (p.getPlayer().getNameTag().contains("绿队")) {
            for (int i = 0; i < Teams.get("绿队").TeamPlayers.size(); i++) {
                if (Teams.get("绿队").TeamPlayers.get(i).player.equals(p.getPlayer())) {
                    Teams.get("绿队").TeamPlayers.remove(i);
                    Teams.get("绿队").sendMessageToTeam("§l**[IDP]-你的队友" + p.getPlayer().getName() + "退出了游戏");
                    break;
                }
            }
        }
        if (p.getPlayer().getNameTag().contains("黄队")) {
            for (int i = 0; i < Teams.get("黄队").TeamPlayers.size(); i++) {
                if (Teams.get("黄队").TeamPlayers.get(i).player.equals(p.getPlayer())) {
                    Teams.get("黄队").TeamPlayers.remove(i);
                    Teams.get("黄队").sendMessageToTeam("§l**[IDP]-你的队友" + p.getPlayer().getName() + "退出了游戏");
                    break;
                }
            }
        }
    }
    public void PlayerDeath(Player p, HashMap<String, TeamInfo> Teams) {
        String TeamName_1 = "";
        if (p.getNameTag().contains("黄队")) {
            TeamName_1 = "黄队";
        }
        if (p.getNameTag().contains("红队")) {
            TeamName_1 = "红队";
        }
        if (p.getNameTag().contains("绿队")) {
            TeamName_1 = "绿队";
        }
        if (p.getNameTag().contains("蓝队")) {
            TeamName_1 = "蓝队";
        }
        for (int i = 0; i < Teams.get(TeamName_1).TeamPlayers.size(); i++) {
            if (Teams.get(TeamName_1).TeamPlayers.get(i).player.equals(p)) {
                if (!Teams.get(TeamName_1).isCakebreak) {
                    Teams.get(TeamName_1).TeamPlayers.get(i).Deaths = Teams.get(TeamName_1).TeamPlayers.get(i).Deaths + 1;
                    Teams.get(TeamName_1).TeamPlayers.get(i).canMove = false;
                    if (!Teams.get(TeamName_1).TeamPlayers.get(i).KilledName.equals("")) {
                        Server.getInstance().broadcastMessage(Teams.get(TeamName_1).TeamPlayers.get(i).player.getName() + "被" + Teams.get(TeamName_1).TeamPlayers.get(i).KilledName + "击杀！");
                        String TeamName = "";
                        if (Teams.get("黄队").TeamPlayers.get(i).KilledName.contains("黄队")) {
                            TeamName = "黄队";
                        }
                        if (Teams.get("黄队").TeamPlayers.get(i).KilledName.contains("红队")) {
                            TeamName = "红队";
                        }
                        if (Teams.get("黄队").TeamPlayers.get(i).KilledName.contains("绿队")) {
                            TeamName = "绿队";
                        }
                        if (Teams.get("黄队").TeamPlayers.get(i).KilledName.contains("蓝队")) {
                            TeamName = "蓝队";
                        }
                        for (int o = 0; o < Teams.get(TeamName).TeamPlayers.size(); o++) {
                            if (Teams.get(TeamName).TeamPlayers.get(i).player.equals(p)) {
                                Teams.get(TeamName).TeamPlayers.get(i).Kills=Teams.get(TeamName).TeamPlayers.get(i).Kills+1;
                                Teams.get(TeamName).TeamPlayers.get(i).player.sendMessage("击杀数+1");
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    public void PlayerReSpawn (Player p, HashMap < String, TeamInfo > Teams){
        String TeamName_1 = "";
        if (p.getNameTag().contains("黄队")) {
            TeamName_1 = "黄队";
        }
        if (p.getNameTag().contains("红队")) {
            TeamName_1 = "红队";
        }
        if (p.getNameTag().contains("绿队")) {
            TeamName_1 = "绿队";
        }
        if (p.getNameTag().contains("蓝队")) {
            TeamName_1 = "蓝队";
        }
        for (int i = 0; i < Teams.get(TeamName_1).TeamPlayers.size(); i++) {
            if (Teams.get(TeamName_1).TeamPlayers.get(i).player.equals(p.getPlayer())) {
                if (Teams.get(TeamName_1).isCakebreak) {
                    Teams.get(TeamName_1).TeamPlayers.remove(i);
                    p.getPlayer().sendActionBar("你的蛋糕已被破坏\n你无法重生了！");
                    p.getPlayer().setGamemode(3);
                    break;
                } else {
                    p.getPlayer().sendActionBar("3秒后可移动!");
                    Teams.get(TeamName_1).TeamPlayers.get(i).KilledName="";
                    break;
                }
            }
        }
    }
    public void checkPlayerMove(Player p,String TeamName,HashMap < String, TeamInfo > Teams){
        for(TeamInfo teamInfo:Teams.values()){
            boolean flag_1=teamInfo.PropMap.get("缓慢陷阱");
            boolean flag_2=teamInfo.PropMap.get("失明陷阱");
            boolean flag_3=teamInfo.PropMap.get("反隐身陷阱");
            if(!flag_1 && !flag_2 && !flag_3) {
                break;
            }
            else{
                double distance=Math.sqrt(Math.pow(p.x-teamInfo.CakeLocation.x,2)+Math.pow(p.y-teamInfo.CakeLocation.y,2.0)+Math.pow(p.z-teamInfo.CakeLocation.z,2.0));
                if(distance<=4){
                    if(flag_1){
                        Effect effect= new Effect(Effect.SLOWNESS,"缓慢",0,0,255);
                        effect.setVisible(true);
                        effect.setAmplifier(3);
                        effect.setDuration(120);
                        p.getPlayer().addEffect(effect);
                        teamInfo.PropMap.put("缓慢陷阱",false);
                        teamInfo.sendMessageToTeam("§l§c[警告]缓慢陷阱被触发！！");
                    }
                    if(flag_2){
                        Effect effect= new Effect(Effect.BLINDNESS,"失明",0,0,255);
                        effect.setVisible(true);
                        effect.setAmplifier(3);
                        effect.setDuration(120);
                        p.getPlayer().addEffect(effect);
                        teamInfo.PropMap.put("失明陷阱",false);
                        teamInfo.sendMessageToTeam("§l§c[警告]失明陷阱被触发！！");
                    }
                    if(flag_3){
                        for (Map.Entry<Integer, Effect> entry : p.getEffects().entrySet()) {
                            if(entry.getValue().getId()==Effect.INVISIBILITY){
                                p.removeEffect(entry.getKey());
                                teamInfo.PropMap.put("反隐身陷阱",false);
                                teamInfo.sendMessageToTeam("§l§c[警告]反隐身陷阱被触发！！");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
