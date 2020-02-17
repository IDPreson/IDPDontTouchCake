package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.level.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamInfo {
    public String TeamName;
    public ArrayList<PlayerInfo> TeamPlayers=new ArrayList<>();
    public HashMap<String,Boolean> PropMap=new HashMap<>();
    public Location SpawnLocation,CakeLocation,TeamScale_1,TeamScale_2;
    public boolean isDead=false,isCakebreak=false;
    public TeamInfo(String TeamName){
        this.TeamName=TeamName;
        init();
    }
    private void init(){
        PropMap.put("缓慢陷阱",false);
        PropMap.put("失明陷阱",false);
        PropMap.put("反隐身陷阱",false);
        switch (TeamName){
            case "红队":
                SpawnLocation=new Location(cake.config.getDouble("红队出生点X"),cake.config.getDouble("红队出生点Y"),cake.config.getDouble("红队出生点Z"));
                CakeLocation=new Location(cake.config.getDouble("红队蛋糕X"),cake.config.getDouble("红队蛋糕Y"),cake.config.getDouble("红队蛋糕Z"));
                TeamScale_1=new Location(cake.config.getDouble("红队范围X1"),cake.config.getDouble("红队范围Y1"),cake.config.getDouble("红队范围Z1"));
                TeamScale_2=new Location(cake.config.getDouble("红队范围X2"),cake.config.getDouble("红队范围Y2"),cake.config.getDouble("红队范围Z2"));
                break;
            case "蓝队":
                SpawnLocation=new Location(cake.config.getDouble("蓝队出生点X"),cake.config.getDouble("蓝队出生点Y"),cake.config.getDouble("蓝队出生点Z"));
                CakeLocation=new Location(cake.config.getDouble("蓝队蛋糕X"),cake.config.getDouble("蓝队蛋糕Y"),cake.config.getDouble("蓝队蛋糕Z"));
                TeamScale_1=new Location(cake.config.getDouble("蓝队范围X1"),cake.config.getDouble("蓝队范围Y1"),cake.config.getDouble("蓝队范围Z1"));
                TeamScale_2=new Location(cake.config.getDouble("蓝队范围X2"),cake.config.getDouble("蓝队范围Y2"),cake.config.getDouble("蓝队范围Z2"));
                break;
            case "黄队":
                SpawnLocation=new Location(cake.config.getDouble("黄队出生点X"),cake.config.getDouble("黄队出生点Y"),cake.config.getDouble("黄队出生点Z"));
                CakeLocation=new Location(cake.config.getDouble("黄队蛋糕X"),cake.config.getDouble("黄队蛋糕Y"),cake.config.getDouble("黄队蛋糕Z"));
                TeamScale_1=new Location(cake.config.getDouble("黄队范围X1"),cake.config.getDouble("黄队范围Y1"),cake.config.getDouble("黄队范围Z1"));
                TeamScale_2=new Location(cake.config.getDouble("黄队范围X2"),cake.config.getDouble("黄队范围Y2"),cake.config.getDouble("黄队范围Z2"));
                break;
            case "绿队":
                SpawnLocation=new Location(cake.config.getDouble("绿队出生点X"),cake.config.getDouble("绿队出生点Y"),cake.config.getDouble("绿队出生点Z"));
                CakeLocation=new Location(cake.config.getDouble("绿队蛋糕X"),cake.config.getDouble("绿队蛋糕Y"),cake.config.getDouble("绿队蛋糕Z"));
                TeamScale_1=new Location(cake.config.getDouble("绿队范围X1"),cake.config.getDouble("绿队范围Y1"),cake.config.getDouble("绿队范围Z1"));
                TeamScale_2=new Location(cake.config.getDouble("绿队范围X2"),cake.config.getDouble("绿队范围Y2"),cake.config.getDouble("绿队范围Z2"));
                break;
        }
    }
    public void addPlayer(Player player){
        TeamPlayers.add(new PlayerInfo(player,TeamName));
    }
    public void removePlayer(Player player){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                TeamPlayers.remove(i);
                break;
            }
        }
    }
    public void addPlayerKills(Player player){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                TeamPlayers.get(i).Kills=TeamPlayers.get(i).Kills+1;
                break;
            }
        }
    }
    public void addPlayerDeath(Player player){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                TeamPlayers.get(i).Deaths=TeamPlayers.get(i).Deaths+1;
                break;
            }
        }
    }
    public int getPlayerKills(Player player){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                return TeamPlayers.get(i).Kills;
            }
        }
        return 0;
    }
    public boolean getPlayerIsDead(Player player){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                return TeamPlayers.get(i).isDead;
            }
        }
        return true;
    }
    public void setPlayerKills(Player player,int num){
        for(int i=0;i<TeamPlayers.size();i++){
            if(TeamPlayers.get(i).player.equals(player)){
                TeamPlayers.get(i).Kills=num;
                break;
            }
        }
    }
    public void sendMessageToTeam(String message){
        for(int i=0;i<TeamPlayers.size();i++){
            TeamPlayers.get(i).player.sendMessage(message);
        }
    }
}
