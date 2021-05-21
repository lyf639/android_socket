package com.example.test_nga.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.test_nga.R;
import com.example.test_nga.data.HotKeyData;
import com.example.test_nga.utility.SocketClient;

import java.util.ArrayList;

public class HotKeyGridAdapter extends ArrayAdapter<HotKeyData> {
    private Context context;
    private ArrayList<HotKeyData> keys;
    private SocketClient socketClient;
    //private DelayOperator delayOperator;
    public HotKeyGridAdapter(@NonNull Context context, ArrayList<HotKeyData> keys,SocketClient socketClient) {
        super(context, android.R.layout.simple_list_item_1,keys);
        this.keys=keys;
        this.context=context;
        this.socketClient=socketClient;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        if(convertView==null){
            v= LayoutInflater.from(context).inflate(R.layout.hotkey_grid_view,null,false);
        }else {
            v=convertView;
        }
        final Button button=(Button)v.findViewById(R.id.button2);
        button.setText(keys.get(position).getHotkeyName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(button.getText().equals("切换程序")){
//                     button.setText("切换");
//                     socketClient.work(keys.get(position).getHotkeyCmd());
//                     delayOperator=new DelayOperator(button,socketClient);
//                     delayOperator.start();
//                }
//                else if(button.getText().equals("切换")){
//                    socketClient.work("key:vk_tab");
//                    delayOperator.setnum();
//                }else
                socketClient.work(keys.get(position).getHotkeycmd());
            }
        });
        return v;
    }
}
