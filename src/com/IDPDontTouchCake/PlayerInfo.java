package com.IDPDontTouchCake;

import cn.nukkit.Player;

import java.sql.SQLException;

public class PlayerInfo {
    public Player player;
    public String TeamName,KilledName="",vipstatement="非VIP";
    public Player KilledPlayer=null;
    public int Kills=0,Deaths=0,reSpawnTime=3,breakcake=0;
    public boolean canMove=true;
    public PlayerInfo(Player player,String TeamName){
        this.player=player;
        this.TeamName=TeamName;
        getVIPinfo();
    }
    private void getVIPinfo(){
        try {
            vipstatement=cake.touchCakeSql.getPlayerVIP(player.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double getVipMultiple(){
        switch (vipstatement){
            case "非VIP":
                return 1;
            case "VIP-1":
                return 1.2;
            case "VIP-2":
                return 1.4;
            case "VIP-3":
                return 1.6;
            case "VIP-4":
                return 1.8;
            case "VIP-5":
                return 2.0;
        }
        return 1;
    }
}
