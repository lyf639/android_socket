package cn.wzu.edu.lyf.app;

import cn.wzu.edu.lyf.socket.CmdServerSocket;

import java.net.ServerSocket;

public class ServerSocketApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            CmdServerSocket socket= new CmdServerSocket();
            socket.work();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
