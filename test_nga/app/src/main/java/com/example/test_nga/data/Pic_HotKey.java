package com.example.test_nga.data;

import java.util.ArrayList;

public class Pic_HotKey {
    public static ArrayList<HotKeyData> getHotkeyList(){
        ArrayList<HotKeyData> list=new ArrayList<>();
        list.add(new HotKeyData("放大","key:vk_plus"));
        list.add(new HotKeyData("缩小","key:vk_minus"));
        list.add(new HotKeyData("实际尺寸","key:vk_slash"));
        list.add(new HotKeyData("关闭程序","key:vk_alt+vk_f4"));
        list.add(new HotKeyData("下一张","key:vk_right"));
        list.add(new HotKeyData("上一张","key:vk_left"));
        list.add(new HotKeyData("左旋转90度","key:vk_control+vk_l"));
        list.add(new HotKeyData("右旋转90度","key:vk_control+vk_r"));
        list.add(new HotKeyData("删除","key:vk_delete"));
        list.add(new HotKeyData("图片信息","key:vk_i"));
        return list;
    }
}
