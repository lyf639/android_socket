package com.example.test_nga.operator;


import com.example.test_nga.data.Base_HotKey;
import com.example.test_nga.data.HotKeyData;
import com.example.test_nga.data.Movie_HotKey;
import com.example.test_nga.data.Music_HotKey;
import com.example.test_nga.data.PPT_HotKey;
import com.example.test_nga.data.Pic_HotKey;

import java.util.ArrayList;

public class HotKeyGenerator {
    public static ArrayList<HotKeyData> getHotkeyList(String filename){
        ArrayList<HotKeyData> hotKeyDataArrayList=new ArrayList<>();
        String[] s=filename.split("\\.");
        switch (s[1]){
            case "mp3":
            case "MP3":
            case "wav":
            case "WAV":
                hotKeyDataArrayList= Music_HotKey.getHotkeyList();
                break;
            case "mp4":
            case "MP4":
            case "avi":
            case "AVI":
            case "wmv":
            case "WMV":
            case "mov":
            case "MOV":
            case "fla":
            case "FLA":
                hotKeyDataArrayList= Movie_HotKey.getHotkeyList();
                break;
            case "pptx":
            case "PPTX":
            case "PPT":
            case "ppt":
                hotKeyDataArrayList= PPT_HotKey.getHotkeyList();
                break;
            case "jpg":
            case "jpeg":
            case "JPEG":
            case "JPG":
            case "png":
            case "PNG":
            case "gif":
            case "GIF":
            case "bmp":
            case "BMP":
                hotKeyDataArrayList= Pic_HotKey.getHotkeyList();
                break;
            default:
                break;
        }
        hotKeyDataArrayList.addAll(Base_HotKey.getHotkeyList());
        return hotKeyDataArrayList;
    }
}
