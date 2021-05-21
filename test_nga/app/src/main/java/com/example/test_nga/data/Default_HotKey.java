package com.example.test_nga.data;

import java.util.ArrayList;


public class Default_HotKey {
    public ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> hotKeyDataArrayList=new ArrayList<>();
        HotKeyData Switchingprogram=new HotKeyData("切换程序","VK_ALT+VK_TAB+VK_TAB+VK_TAB");
        HotKeyData MaxSize=new HotKeyData("全屏","VK_CONTROL+VK_ENTER");
        HotKeyData MinSize=new HotKeyData("最小化","VK_CONTROL+VK_ENTER");
        HotKeyData Exit=new HotKeyData("退出","VK_CONTROL+VK_W");
        hotKeyDataArrayList.add(Switchingprogram);
        hotKeyDataArrayList.add(MaxSize);
        hotKeyDataArrayList.add(MinSize);
        hotKeyDataArrayList.add(Exit);
        return hotKeyDataArrayList;
    }
}
