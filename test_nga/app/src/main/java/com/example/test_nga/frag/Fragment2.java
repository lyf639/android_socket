package com.example.test_nga.frag;

import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_nga.R;
import com.example.test_nga.data.CmdServerIpSetting;
import com.example.test_nga.operator.HotKeyGenerator;
import com.example.test_nga.utility.SocketClient;
import com.example.test_nga.view.HotKeyDialog;


public class Fragment2 extends Fragment {

    SocketClient socketClient=new SocketClient(CmdServerIpSetting.ip,CmdServerIpSetting.port,new Handler());
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2, container, false);
        Button bt1=(Button)view.findViewById(R.id.buttonmian);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment1 f1=new Fragment1();
                new HotKeyDialog(getContext(), HotKeyGenerator.getHotkeyList(f1.finame),"热键操作表",socketClient).dialog().show();
            }
        });

        return view;
    }
}
