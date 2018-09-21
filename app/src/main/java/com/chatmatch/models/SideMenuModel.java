package com.chatmatch.models;

import android.graphics.drawable.Drawable;

public class SideMenuModel {
    public int GroupId;
    public int ItemId;
    public int Order;
    public String Title;
    public Drawable Icon;

    public SideMenuModel(){
        //Empty Constructor
    }

    public SideMenuModel(String title, Drawable icon, int groupid, int itemid, int order) {
        this.Title = title;
        this.Icon = icon;
        this.GroupId = groupid;
        this.ItemId = itemid;
    }
}
