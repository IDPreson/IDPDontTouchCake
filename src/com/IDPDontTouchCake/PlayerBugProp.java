package com.IDPDontTouchCake;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemPotion;

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
                        cake.Teams.get(TeamName).sendMessageToTeam("§l§e**[IDP]-您的队伍已购买缓慢陷阱！");
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
                        cake.Teams.get(TeamName).sendMessageToTeam("§l§e**[IDP]-您的队伍已购买失明陷阱！");
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
                        cake.Teams.get(TeamName).sendMessageToTeam("§l§e**[IDP]-您的队伍已购买反隐身陷阱！");
                    }else{
                        p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                    }
                }
                break;
            case "隐身药水-6杀敌数":
                if(kills-6>=0){
                    p.sendMessage("§l§e**[IDP]-购买隐身药水成功！");
                    Item invisible=ItemPotion.get(373,7);
                    cake.Teams.get(TeamName).setPlayerKills(p,kills-6);
                    p.getPlayer().getInventory().addItem(invisible);
                }else{
                    p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                }
                break;
            case "剧毒药水-5杀敌数":
                if(kills-5>=0){
                    p.sendMessage("§l§e**[IDP]-购买剧毒药水成功！");
                    Item itemPotion=ItemPotion.get(438,25);
                    cake.Teams.get(TeamName).setPlayerKills(p,kills-5);
                    p.getPlayer().getInventory().addItem(itemPotion);
                }else{
                    p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                }
                break;
            case "跳跃药水-3杀敌数":
                if(kills-3>=0){
                    p.sendMessage("§l§e**[IDP]-购买跳跃药水成功！");
                    Item itemPotion=ItemPotion.get(373,9);
                    cake.Teams.get(TeamName).setPlayerKills(p,kills-3);
                    p.getPlayer().getInventory().addItem(itemPotion);
                }else{
                    p.sendMessage("§l§e**[IDP]-您的杀敌数不足，购买失败！");
                }
                break;
        }
    }
}
