package com.example.test_nga.operator;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.test_nga.data.NetFile;
import com.example.test_nga.frag.Fragment1;
import com.example.test_nga.socket.CmdClientSocket;
import com.example.test_nga.view.NetFileListAdapter;

import java.util.ArrayList;

public class ShowRemoteFileHandler extends Handler {

    private Context context;
    private ListView listView;
    Workfinish workfinish;
    public static final String KEY_SERVER_ACK_MSG = "KEY_SERVER_ACK_MSG";
    public ShowRemoteFileHandler(Workfinish workfinish) {
        super();
        this.workfinish=workfinish;
       // this.context = context;

    }


    public interface Workfinish{
        public void updatelistview(ArrayList<NetFile> files);
        public void showError(String msg);
    }




    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        Bundle bundle = msg.getData();
        ArrayList<NetFile> files=new ArrayList<>();
        int msgType=msg.arg2;
        ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_SERVER_ACK_MSG);
        if(msgType== CmdClientSocket.SERVER_MSG_OK) {
            String returnType = stringArrayList.get(0);
            if (returnType.equals("dir")) {

                String parentDir = stringArrayList.get(1);

                for (int i = 2; i < stringArrayList.size(); i++) {
                    String s = stringArrayList.get(i);
                    if (s != null && s != "") {
                        NetFile file = new NetFile(s, parentDir);
                        files.add(file);
                    }
                }
                workfinish.updatelistview(files);

            }
            else if(returnType.equals("opn")){

            }
        }

        else {
            workfinish.showError(stringArrayList.toString());

        }
    }

}