package com.example.test_nga.data;

import java.util.ArrayList;




public class Movie_HotKey {
    public static ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> list=new ArrayList<>();
        list.add(new HotKeyData("暂停/播放","key:vk_space"));
        list.add(new HotKeyData("退出全屏","key:vk_escape"));
        list.add(new HotKeyData("全屏放映","key:vk_alt+vk_enter"));
        list.add(new HotKeyData("音量+","key:vk_up"));
        list.add(new HotKeyData("音量-","key:vk_down"));
        list.add(new HotKeyData("静音","key:vk_control+vk_m"));
        list.add(new HotKeyData("快进","key:vk_right"));
        list.add(new HotKeyData("快退","key:vk_left"));
        list.add(new HotKeyData("退出程序","key:vk_alt+vk_f4"));
        return list;
    }
}
