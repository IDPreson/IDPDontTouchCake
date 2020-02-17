package com.IDPDontTouchCake;

import cn.nukkit.Player;

public class PlayerBugProp {
    public void buyProp(int kills, Player p, String itemName,String TeamName){
        switch (itemName){
            case "缓慢陷阱-3杀敌数":
                if(cake.Teams.get(TeamName).PropMap.get("缓慢陷阱")){
                    p.sendMessage("§l§e**[IDP]-您的队伍已购买|缓慢陷阱，无需重复购买！");
                }else{
                    if(kills-3>=0){
                        p.sendMessage("§l§e**[IDP]-购买缓慢陷阱成功！");
                        cake.Teams.get(TeamName).PropMap.put("缓慢陷阱",true);
                        cake.Teams.get(TeamName).setPlayerKills(p,kills-3);
                    }else{
                        p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                    }
                }
                break;
            case "失明陷阱-3杀敌数":
                if(cake.Teams.get(TeamName).PropMap.get("失明陷阱")){
                    p.sendMessage("§l§e**[IDP]-您的队伍已购买|失明陷阱，无需重复购买！");
                }else{
                    if(kills-3>=0){
                        p.sendMessage("§l§e**[IDP]-购买失明陷阱成功！");
                        cake.Teams.get(TeamName).PropMap.put("失明陷阱",true);
                        cake.Teams.get(TeamName).setPlayerKills(p,kills-3);
                    }else{
                        p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                    }
                }
                break;
            case "反隐身陷阱-3杀敌数":
                if(cake.Teams.get(TeamName).PropMap.get("反隐身陷阱")){
                    p.sendMessage("§l§e**[IDP]-您的队伍已购买|反隐身陷阱，无需重复购买！");
                }else{
                    if(kills-3>=0){
                        p.sendMessage("§l§e**[IDP]-购买反隐身陷阱成功！");
                        cake.Teams.get(TeamName).PropMap.put("反隐身陷阱",true);
                        cake.Teams.get(TeamName).setPlayerKills(p,kills-3);
                    }else{
                        p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                    }
                }
                break;
        }
    }
}
