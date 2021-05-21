package cn.wzu.edu.lyf.operator;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DIR {



    public static ArrayList<String> DealDir(String cmdBody) throws Exception {
        ArrayList<String> backList=new ArrayList<String>();
        switch (cmdBody){
            case "..":
                break;
            case "...":
                ArrayList<String> strings = DirRootpath();
                backList=strings;
                break;
            default:
                ArrayList<String> dirfilepath = Dirfilepath(cmdBody);
                backList=dirfilepath;
                break;
        }
        return backList;
        // TODO Auto-generated method stub
    }


    private static ArrayList<String> DirRootpath() throws IOException {
        ArrayList<String> rootList=new ArrayList<>();
        File[] files = File.listRoots();
        rootList.add("ok");
        rootList.add("dir");
        rootList.add(".../");
        for(File file:files){
            String fileName = file.getPath();
            String path = file.getPath();
            System.out.println(path);

            long lastModified = file.lastModified();//获取文件修改时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//给时间格式，例如：2018-03-16 09:50:23
            String fileDate = dateFormat.format(new Date(lastModified));//取得文件最后修改时间，并按格式转为字符串
            String fileSize="0";
            String fileType="1";

            switch (fileName){
                case "C:\\":
                    fileName="c://";
                    fileType="2";
                    break;


            }
            if(!file.isDirectory()){//判断是否为目录
                fileType="0";
                fileSize=""+file.length();
            }
            rootList.add(fileName+">"+fileDate+">"+fileSize+">"+fileType+">");
        }
        return rootList;

    }




    //    列出指定目录下的所有文件
    private static ArrayList<String> Dirfilepath(String cmdBody) throws IOException {
        ArrayList<String> backList=new ArrayList<String>();
        File file = new File(cmdBody);
        File[] listFiles = file.listFiles();
        backList.add("ok");
        backList.add("dir");
        backList.add(file.getCanonicalPath());
        assert listFiles != null;
        for(File mfile:listFiles){
            String fileName = mfile.getName();
            long lastModified = mfile.lastModified();//获取文件修改时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//给时间格式，例如：2018-03-16 09:50:23
            String fileDate = dateFormat.format(new Date(lastModified));//取得文件最后修改时间，并按格式转为字符串
            String fileSize="0";
            String fileType="1";
            if(fileName.equals("c:\\")||fileName.equals("D:\\")||fileName.equals("E:\\"))
            {
                fileType="2";
            }
            if(!mfile.isDirectory()){//判断是否为目录
                fileType="0";
                fileSize=""+mfile.length();
            }
            backList.add(fileName+">"+fileDate+">"+fileSize+">"+fileType+">");
        }
        return backList;
    }


//    todo 列出盘符

//    todo 列出上级目录的文件

}
