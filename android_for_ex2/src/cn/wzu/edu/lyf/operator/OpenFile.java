package cn.wzu.edu.lyf.operator;

import java.awt.*;
import java.io.File;

public class OpenFile {
    public static void  openFile(String filepath){
        Desktop desk=Desktop.getDesktop();
        try
        {
            File file=new File(filepath);//创建一个java文件系统
            desk.open(file); //调用open（File f）方法打开文件
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }


}
