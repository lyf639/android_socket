package com.example.test_nga.frag;

import android.content.Context;
import android.os.Bundle;

import android.os.Handler;
import android.provider.Browser;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_nga.MainActivity;
import com.example.test_nga.R;
import com.example.test_nga.data.CmdServerIpSetting;
import com.example.test_nga.data.NetFile;
import com.example.test_nga.operator.HotKeyGenerator;
import com.example.test_nga.operator.ShowRemoteFileHandler;
import com.example.test_nga.socket.CmdClientSocket;
import com.example.test_nga.utility.SocketClient;
import com.example.test_nga.view.HotKeyDialog;
import com.example.test_nga.view.MyNetFileListAdpater;
import com.example.test_nga.view.NetFileListAdapter;

import java.io.File;
import java.util.ArrayList;



public class Fragment1 extends Fragment {

    public static String finame="1.txt";

    String realfilepath = "";
    String nowpath="";
    public Fragment1(Context context){
        this.context=context;
    }
    ShowRemoteFileHandler.Workfinish workfinish;
    MyNetFileListAdpater myNetFileListAdpater;
    CmdClientSocket socket;
    RecyclerView recyclerView;
    Context context;
    ListView listView;

    public Fragment1() {


    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        Button bt = (Button) view.findViewById(R.id.button2);
        final                                                                                                Button bt_fan = (Button) view.findViewById(R.id.button3);
        final ShowRemoteFileHandler handler;

        listView=(ListView)view.findViewById(R.id.listview);
        final EditText et_ip=(EditText)view.findViewById(R.id.edit_ip);
        final EditText et_port=(EditText)view.findViewById(R.id.edit_port);

        final TextView tv_currentPath =(TextView)view.findViewById(R.id.tv_currentpath);




        this.setaffair();
        handler=new ShowRemoteFileHandler(workfinish);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = et_ip.getText().toString();
                int port = Integer.parseInt(et_port.getText().toString());
                CmdServerIpSetting.ip=ip;
                CmdServerIpSetting.port=port;
                socket =new CmdClientSocket(CmdServerIpSetting.ip,CmdServerIpSetting.port,handler);
                socket.work("dir:...");
                tv_currentPath.setText("...");
            }
        });


        bt_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRemoteFileHandler showRemoteFileHandler = new ShowRemoteFileHandler(workfinish);
                socket = new CmdClientSocket(CmdServerIpSetting.ip, CmdServerIpSetting.port, showRemoteFileHandler);
                String[] s=nowpath.split("/");
                String s1="";
                for(int i=0;i<s.length-1;i++){
                    s1+=s[i]+"/";
                }
                if (s.length==1){
                    String[] se=nowpath.split("\\\\");
                    String se2="";
                    for(int i=0;i<se.length-1;i++){
                        se2+=se[i]+"\\";
                    }
                    s1=se2;
                }
                socket.work("dir:"+s1);
                nowpath=s1;
                System.out.println(nowpath);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NetFile file = (NetFile) listView.getAdapter().getItem(position);
                String filePath = file.getFilePath();
                realfilepath = "";
                if (filePath.endsWith("/") | filePath.endsWith("\\")) {

                    realfilepath = filePath + file.getFileName();
                } else {

                    realfilepath = filePath + File.separator + file.getFileName();
                }

                if (file.getFileType() >= 1) {
                    if (file.getFileName().equals("...")) {
                        realfilepath = "...";
                    }
                    if(file.getFileType()==2){

                        realfilepath=file.getFileName();
                    }

                    ShowRemoteFileHandler showRemoteFileHandler = new ShowRemoteFileHandler(workfinish);
                    socket = new CmdClientSocket(CmdServerIpSetting.ip, CmdServerIpSetting.port, showRemoteFileHandler);
                    socket.work("dir:" + realfilepath);
                    nowpath=realfilepath;
                    System.out.println("!!!!!!!!!!!!!!!!!!"+nowpath);
                } else {
                    //Toast.makeText(MainActivity.this,realfilepath,Toast.LENGTH_LONG).show();
                    socket.work("opn:"+realfilepath);
//                    todo ShowOpenfile
                }
            }
        });
        registerForContextMenu(listView);
        return view;
    }

    public void setContext(Context context){
        this.context=context;
    }


    private void setaffair(){
        workfinish=new ShowRemoteFileHandler.Workfinish() {
            @Override
            public void updatelistview(ArrayList<NetFile> files) {
                NetFileListAdapter adapter=new NetFileListAdapter(context,files);
                listView.setAdapter(adapter);
            }

            @Override
            public void showError(String msg) {

            }
        };
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.ctx,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final int pos=menuInfo.position;
        SocketClient socketClient=new SocketClient(CmdServerIpSetting.ip,CmdServerIpSetting.port,new Handler());
        switch (item.getItemId()){
            case R.id.hotkey:
                NetFile file = (NetFile) listView.getAdapter().getItem(pos);
                if(file.getFileType()==0){
                    new HotKeyDialog(getContext(), HotKeyGenerator.getHotkeyList(file.getFileName()),"热键操作表",socketClient).dialog().show();
                    finame=file.getFileName();
                }
                else
                    new HotKeyDialog(getContext(), null,"",null).errorDiadlog().show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void findAllView(){

    }

}
