package com.example.test_nga.socket;


import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class CmdClientSocket {

    private String ip;
    private int port;
    private ArrayList<String> cmd;
    private static final int Conect_time_out=10000;
    private static final int Transfer_time_out=1000;
    private Handler handler;
    private Socket socket;
    public static final int SERVER_MSG_ERROR=0;
    public static final int SERVER_MSG_OK=1;
    public int msgType;
    public static final String KEY_SERVER_ACK_MSG = "KEY_SERVER_ACK_MSG";
    private OutputStreamWriter writer;
    private BufferedReader bufferedReader;
    public CmdClientSocket(String ip, int port, Handler handler){
        this.port=port;
        this.ip=ip;
        this.handler=handler;
    }

    private void connect() throws IOException {
        InetSocketAddress address=new InetSocketAddress(ip,port);
        socket=new Socket();
        socket.connect(address,Conect_time_out);
        if(!Debug.isDebuggerConnected()){
            socket.setSoTimeout(Transfer_time_out);
        }
    }
    private void writeCmd(String cmd) throws IOException {
        BufferedOutputStream os=new BufferedOutputStream(socket.getOutputStream());
        writer=new OutputStreamWriter(os,"UTF-8");
//        存入命令的行数
        writer.write("1\n");
        writer.write(cmd+"\n");
        writer.flush();
    }
    private ArrayList<String> readSocketMsg() throws IOException {
        ArrayList<String> msgList=new ArrayList<>();
        InputStreamReader isr=new InputStreamReader(socket.getInputStream(),"UTF-8");
        bufferedReader=new BufferedReader(isr);
        String numStr = bufferedReader.readLine();
        int linNum = Integer.parseInt(numStr);
        if(linNum<1){
            msgList.add("Receive empty message");
            return msgList;
        }
        String status=bufferedReader.readLine();
        if(status.equalsIgnoreCase("ok")){
            msgType=SERVER_MSG_OK;
        }else {
            msgList.add(status);
        }

        for (int i = 0; i <linNum ; i++) {
            String s = bufferedReader.readLine();
            msgList.add(s);
        }
        return msgList;
    }
    private void close() throws IOException {
        bufferedReader.close();
        writer.close();
        socket.close();
    }
    private void doCmdTask(String cmd){
        ArrayList<String> msgList=new ArrayList<>();
        try {
            connect();
            writeCmd(cmd);
            msgList = readSocketMsg();
            close();
        } catch (IOException e) {
            msgType=SERVER_MSG_ERROR;
            msgList.add(e.toString());
            e.printStackTrace();
        }
        Message message = handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY_SERVER_ACK_MSG,msgList);
        message.arg2=msgType;
        message.setData(bundle);
        handler.sendMessage(message);
    }
    public void work(final String cmd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                doCmdTask(cmd);
            }
        }).start();
    }

}
