package com.IDPDontTouchCake;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;

public class ShopWindow {
    public FormWindowSimple getShopWindow(){
        FormWindowSimple formWindowSimple=new FormWindowSimple("商店","选择一个道具");
        formWindowSimple.addButton(new ElementButton("缓慢陷阱-3杀敌数"));
        formWindowSimple.addButton(new ElementButton("反隐身陷阱-3杀敌数"));
        formWindowSimple.addButton(new ElementButton("失明陷阱-3杀敌数"));
        formWindowSimple.addButton(new ElementButton("隐身药水-6杀敌数"));
        formWindowSimple.addButton(new ElementButton("剧毒药水-5杀敌数"));
        formWindowSimple.addButton(new ElementButton("跳跃药水-3杀敌数"));
        return formWindowSimple;
    }
}
