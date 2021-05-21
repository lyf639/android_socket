package com.example.test_nga.data;


import java.text.DecimalFormat;

public class NetFile {
    private long fileSize = 0;// 文件长度应该long型数据，否则大于2GB的文件大小无法表达
    private String fileName = "$error";// 文件名称，不含目录信息,默认值用于表示文件出错
    private String filePath = ".\\";// 该文件对象所处的目录，默认值为当前相对目录
    private String fileSizeStr = "0";// 文件的大小，用字符串表示，能智能地选择B、KB、MB、GB来表达
    private int fileType = 0;// fileType=0为文件，fileType=1为普通文件夹，fileType=2为盘符
    private String fileModifiedDate = "1970-01-01 00:00:00";// 文件最近修改日期，默认值为1970年

    public NetFile(String fileInfo, String filePath){
        if(fileInfo!=null) {
            String[] split = fileInfo.split(">");
            if (split != null && split.length > 0) {
                this.fileName = split[0];
                this.filePath = filePath;
                this.fileModifiedDate = split[1];
                this.fileSize = Long.parseLong(split[2]);
                this.fileType = Integer.parseInt(split[3]);
//            设置大小 字符串类型
                this.fileSizeStr = autoSizeTranslation();

            }
        }
    }

    private String autoSizeTranslation(){
        float filesizeStr;
        filesizeStr=this.fileSize;
        DecimalFormat df =new DecimalFormat("#0.00");
        if(filesizeStr*1.0/1024>=1){
            filesizeStr=filesizeStr/1024;
            if(filesizeStr*1.0/1024>=1){
                filesizeStr=filesizeStr/1024;
                if(filesizeStr*1.0/1024>=1){
                    filesizeStr=filesizeStr/1024;
                    String format = df.format(filesizeStr);
                    return format+"GB";
                }
                else{
                    String format = df.format(filesizeStr);
                    return format+"MB";
                }
            }
            else {
                String format = df.format(filesizeStr);
                return format+"KB";
            }
        }
        else
        {
            String format = df.format(filesizeStr);
            return format+"B";
        }
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSizeStr() {
        return fileSizeStr;
    }

    public void setFileSizeStr(String fileSizeStr) {
        this.fileSizeStr = fileSizeStr;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getFileModifiedDate() {
        return fileModifiedDate;
    }

    public void setFileModifiedDate(String fileModifiedDate) {
        this.fileModifiedDate = fileModifiedDate;
    }
}
