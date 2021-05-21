package cn.wzu.edu.lyf.socket;


import cn.wzu.edu.lyf.operator.DIR;
import cn.wzu.edu.lyf.operator.OpenFile;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;

public class CmdServerSocket {
    int port = 8029;// 自定义一个端口，端口号尽可能挑选一些不被其他服务占用的端口，祥见http://blog.csdn.net/hsj521li/article/details/7678880
    static int connect_count = 0;// 连接次数统计
    ArrayList<String> msgBackList;

    //    无参构造函数
    public CmdServerSocket(){

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
            new Thread(new Task(socket)).start();

        }
    }


    static class Task implements Runnable{
        private Socket socket;
        private ArrayList<String> msgBackList;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                handleSocket();

            } catch (Exception e) {
                cmdException(e.toString());

            }
            finally {
                try {
                    writebackMsg(socket);
                    close(socket);
                    System.out.println("当前Socket服务结束");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }

        public void cmdException(String message){

            msgBackList.clear();
            msgBackList.add(message);

        }

        public void handleSocket() throws Exception {
            ArrayList<String> cmdList = readSocketMsg(socket);
            msgBackList= dealCmd(cmdList);

        }


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
                System.out.println(msgBackList.get(i));
                writer.flush();
            }

        }

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
                        msgBackList= DIR.DealDir(cmdBody);
                        break;
                    case "opn:":
                        OpenFile.openFile(cmdBody);
                        msgBackList=new ArrayList<>();
                        msgBackList.add("1");
                        msgBackList.add("ok");
                        msgBackList.add("opn");
                        break;
                    default:
                        msgBackList=new ArrayList<>();
                        msgBackList.add("error");
                        msgBackList.add("NOT been Defined");
                        break;
                }
            }
            return msgBackList;
        }



        //关闭
        private void close(Socket socket) throws IOException {
            if(socket!=null && socket.isConnected()){
                socket.close();
            }
        }
    }

}
