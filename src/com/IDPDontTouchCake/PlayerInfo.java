package com.IDPDontTouchCake;

import cn.nukkit.Player;

public class PlayerInfo {
    public Player player;
    public String TeamName;
    public int Kills,Death,reSpawnTime=3;
    public boolean isDead=false;
    public PlayerInfo(Player player,String TeamName){
        this.player=player;
        this.TeamName=TeamName;
    }
}
