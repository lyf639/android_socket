package com.example.test_nga.data;

import java.util.ArrayList;

public class Base_HotKey {
    public static ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> list=new ArrayList<>();
        list.add(new HotKeyData("切换程序","key:vk_alt+vk_tab,vk_tab"));;
        list.add(new HotKeyData("最大化","key:vk_alt+vk_space+vk_x"));
        list.add(new HotKeyData("最小化","key:vk_alt+vk_space+vk_n"));
        list.add(new HotKeyData("还原","key:vk_alt+vk_space+vk_r"));
        list.add(new HotKeyData("确定","key:vk_enter"));
        list.add(new HotKeyData("R","key:vk_r"));
        list.add(new HotKeyData("C","key:vk_c"));
        list.add(new HotKeyData("N","key:vk_n"));
        list.add(new HotKeyData("Q","key:vk_q"));
        list.add(new HotKeyData("W","key:vk_w"));
        list.add(new HotKeyData("E","key:vk_e"));
        list.add(new HotKeyData("R","key:vk_r"));
        return list;
    }
}
