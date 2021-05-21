package com.example.test_nga.data;

import java.util.ArrayList;

public class Music_HotKey {
    public static ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> list=new ArrayList<>();
        list.add(new HotKeyData("暂停/播放","key:vk_space"));
        list.add(new HotKeyData("上一首","key:vk_control+vk_alt+vk_left"));
        list.add(new HotKeyData("下一首","key:vk_control+vk_alt+vk_right"));
        list.add(new HotKeyData("音量+","key:vk_control+vk_alt+vk_up"));
        list.add(new HotKeyData("音量-","key:vk_control+vk_alt+vk_down"));
        list.add(new HotKeyData("喜欢","key:vk_control+vk_alt+vk_v"));
        list.add(new HotKeyData("隐藏/显示音乐","key:vk_control+vk_alt+vk_q"));
        list.add(new HotKeyData("隐藏/显示歌词","key:vk_control+vk_alt+vk_w"));
        list.add(new HotKeyData("快进","key:vk_control+vk_right"));
        list.add(new HotKeyData("快退","key:vk_control+vk_left"));
        return list;
    }
}
