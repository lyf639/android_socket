package cn.wzu.edu.lyf;


import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

public class ServerSocket01 {
    int port = 8029;// 自定义一个端口，端口号尽可能挑选一些不被其他服务占用的端口，祥见http://blog.csdn.net/hsj521li/article/details/7678880
    static int connect_count = 0;// 连接次数统计
    ArrayList<String>  msgBackList;

    public ServerSocket01() {
        // TODO Auto-generated constructor stub
    }

    public ServerSocket01(int port) {
        super();
        this.port = port;
    }

    private void printLocalIp(ServerSocket serverSocket) {// 枚举打印服务端的IP
        try {
            System.out.println("服务端命令端口prot=" + serverSocket.getLocalPort());
            Enumeration<NetworkInterface> interfaces = null;
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresss = ni.getInetAddresses();
                while (addresss.hasMoreElements()) {
                    InetAddress nextElement = addresss.nextElement();
                    String hostAddress = nextElement.getHostAddress();
                    System.out.println("本机IP地址为：" + hostAddress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void work() throws Exception {
        // 注意：由于Socket的工作是阻塞式，Android端Socket的工作必须在新的线程中实现，若在UI主线程中工作会报错

        ServerSocket serverSocket = new ServerSocket(port);
        printLocalIp(serverSocket);
        while (true) {// 无限循环，使之能结束当前socket服务后，准备下一次socket服务

            System.out.println("Waiting client to connect.....");
            Socket socket = serverSocket.accept();// 阻塞式，直到有客户端连接进来，才会继续往下执行，否则一直停留在此代码
            System.out.println("Client connected from: "
                    + socket.getRemoteSocketAddress().toString());

            // TODO: As follows:
            // 实现读客户端发送过来的命令，例如实现private ArrayList<String> readSocketMsg(Socket socket) throws IOException函数
            // 调用 ArrayList<String> cmdList=readSocketMsg(socket);
            // 定义一个全局变量 ArrayList<String>  msgBackList,供服务端处理命令，并将返回结果赋值给msgBackList
            // msgBackList=dealCmd(cmdList);//处理命令，例如dir命令，并将处理结果给msgBackList
            // 实现服务端写回数据函数 private void writebackMsg(Socket socket) throws IOException
            // 将msgBackList按规定的格式写回给客户端
            // 实现 private void close(Socket socket) throws IOException，关闭socket
            // 调用 close(socket);
            ArrayList<String> cmdList = readSocketMsg(socket);

            msgBackList= dealCmd(cmdList);
            try {
                writebackMsg(socket);
            }catch (Exception e){

            }

            close(socket);
            System.out.println("当前Socket服务结束");
        }
    }


    //将结果返回客户端
    private void writebackMsg(Socket socket)throws IOException {
        BufferedOutputStream os = new BufferedOutputStream(socket.getOutputStream());//socket.getOutputStream()是输出流，BufferedOutputStream则将其封装为带缓冲的输出流

        //			OutputStreamWriter writer=new OutputStreamWriter(os);//默认的字符编码，有可能是GB2312也有可能是UTF-8，取决于系统
        //			//建议不要用默认字符编码，而是指定UTF-8，以保证发送接收字符编码一致，不至于出乱码
        //输出流是字节传输的，还不具备字符串直接写入功能，因此再将其封装入OutputStreamWriter，使其支持字符串直接写入

        OutputStreamWriter writer=new OutputStreamWriter(os, StandardCharsets.UTF_8);//尝试将字符编码改成"GB2312"

        writer.write(""+msgBackList.size()+"\n");//未真正写入的输出流，仅仅在内存中
        writer.flush();//写入输出流，真正将数据传输出去
        for(int i=0;i<msgBackList.size();i++){
            writer.write(msgBackList.get(i)+"\n");
            writer.flush();
        }

    }


//    读取命令

    private ArrayList<String> readSocketMsg(Socket socket) throws IOException{
        ArrayList<String> msgList=new ArrayList<String>();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader=new BufferedReader(reader);
        String lineNumStr = bufferedReader.readLine();
        int lineNum=Integer.parseInt(lineNumStr);
        for(int i=0;i<lineNum;i++){
            String str = bufferedReader.readLine();
            msgList.add(str);
        }
        //读取结束后，输入流不能关闭，此时关闭，会将socket关闭，从而导致后续对socket写操作无法实现
        return msgList;
    }


    //    处理命令
    private ArrayList<String> dealCmd(ArrayList<String> cmdList) throws Exception {
        for (int i = 0; i < cmdList.size(); i++) {
            String cmd = cmdList.get(i);
            int index = cmd.indexOf(":");
            String cmdHead=cmd.substring(0,index+1);
            String cmdBody=cmd.substring(index+1);
            System.out.println(cmdBody);
            System.out.println(cmdHead);
            switch (cmdHead){
                case "dir:":
                    msgBackList= exeDir(cmdBody);
                    break;
                default:
                    msgBackList=new ArrayList<>();
                    msgBackList.add("NOT been Defined");
                    break;
            }
        }
        return msgBackList;
    }






    //    返回指定目录下的文件以及子目录
    private ArrayList<String> exeDir(String cmdBody) throws Exception {
        // TODO Auto-generated method stub
        ArrayList<String> backList=new ArrayList<String>();
        File file = new File(cmdBody);
        File[] listFiles = file.listFiles();
        backList.add("ok");
        backList.add(file.getCanonicalPath());
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


//错误返回





    private void close(Socket socket) throws IOException {
        if(socket!=null && socket.isConnected()){
            socket.close();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new ServerSocket01().work();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}


