package com.example.test_nga.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;


import com.example.test_nga.R;
import com.example.test_nga.data.HotKeyData;
import com.example.test_nga.utility.SocketClient;

import java.util.ArrayList;

public class HotKeyDialog {
    private Context context;
    private ArrayList<HotKeyData> hotKeyList;
    private String title;
    private SocketClient socketClient;
    public HotKeyDialog(Context context, ArrayList<HotKeyData> hotKeyList, String title, SocketClient socketClient){
        this.context=context;
        this.hotKeyList=hotKeyList;
        this.title=title;
        this.socketClient=socketClient;
    }
    public AlertDialog dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        View v= LayoutInflater.from(context).inflate(R.layout.hotkey_view,null,false);
        GridView gv=(GridView)v.findViewById(R.id.gridview);
        HotKeyGridAdapter hotKeyGridAdapter=new HotKeyGridAdapter(context,hotKeyList,socketClient);
        gv.setAdapter(hotKeyGridAdapter);
        builder.setView(v);
        builder.setNegativeButton("返回",null);
        AlertDialog alertDialog=builder.create();
        return alertDialog;
    }
    public AlertDialog errorDiadlog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("错误操作！");
        builder.setMessage("这不是一个有效的文件！");
        builder.setPositiveButton("确定",null);
        AlertDialog alertDialog=builder.create();
        return alertDialog;
    }
//    public AlertDialog mouseDialog(){
//        AlertDialog.Builder builder=new AlertDialog.Builder(context);
//        final GestureDetector gestureDetector=new GestureDetector(context,new MousePadOnGestureListener(socketClient));
//        final GestureDetector gestureDetector1=new GestureDetector(context,new MouseRolOnGesLisntener(socketClient));
//        builder.setTitle(title);
//        View v=LayoutInflater.from(context).inflate(R.layout.mouse_view,null,false);
//        TextView chu=v.findViewById(R.id.chu);
//        TextView hua=v.findViewById(R.id.hua);
//        Button zuo=v.findViewById(R.id.zuo);
//        Button you=v.findViewById(R.id.you);
//        Button quickBtn=v.findViewById(R.id.quickBtn);
//        Button del=v.findViewById(R.id.delKeyboard);
//        final EditText editText=v.findViewById(R.id.quickKeyboard);
//        del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                socketClient.work("key:vk_back_space");
//            }
//        });
//        quickBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                socketClient.work("cps:"+editText.getText().toString());
//            }
//        });
//        zuo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                socketClient.work("clk:left_press");
//            }
//        });
//        you.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                socketClient.work("clk:right");
//            }
//        });
//        chu.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                gestureDetector.onTouchEvent(motionEvent);
//                return true;
//            }
//        });
//        hua.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                gestureDetector1.onTouchEvent(motionEvent);
//                return true;
//            }
//        });
//        builder.setView(v);
//        builder.setNegativeButton("返回",null);
//        AlertDialog alertDialog=builder.create();
//        return alertDialog;
//    }
}
