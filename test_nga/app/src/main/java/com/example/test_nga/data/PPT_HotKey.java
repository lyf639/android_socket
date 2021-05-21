package com.example.test_nga.data;

import java.util.ArrayList;


public class PPT_HotKey {
    public static ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> list=new ArrayList<>();
        list.add(new HotKeyData("退出放映","key:vk_escape"));
        list.add(new HotKeyData("当前放映","key:vk_shift+vk_f5"));
        list.add(new HotKeyData("从头放映","key:vk_f5"));
        list.add(new HotKeyData("关闭程序","key:vk_control+vk_q"));
        list.add(new HotKeyData("上一页","key:vk_up"));
        list.add(new HotKeyData("下一页","key:vk_down"));
        list.add(new HotKeyData("黑屏/正常","key:vk_b"));
        list.add(new HotKeyData("切换PPT","key:vk_control+vk_f6"));
        return list;
    }
}
