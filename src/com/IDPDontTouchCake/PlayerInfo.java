package com.IDPDontTouchCake;

import cn.nukkit.Player;

public class PlayerInfo {
    public Player player;
    public String TeamName,KilledName;
    public int Kills,Deaths,reSpawnTime=3,breakcake=0;
    public boolean isDead=false,canMove=true;
    public PlayerInfo(Player player,String TeamName){
        this.player=player;
        this.TeamName=TeamName;
    }
}
