package com.example.test_nga.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test_nga.R;
import com.example.test_nga.data.NetFile;

import java.util.ArrayList;

public class NetFileListAdapter extends ArrayAdapter<NetFile> {
    private Context context;
    private ArrayList<NetFile> list;
    public NetFileListAdapter(Context context, ArrayList<NetFile> netFileList){
        super(context,android.R.layout.simple_list_item_1,netFileList);
        this.context=context;
        this.list=netFileList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view, null, false);
        TextView tv_filename = view.findViewById(R.id.row_view_tv_filename);
        TextView tv_filesize = view.findViewById(R.id.row_view_tv_filesize);
        TextView tv_filedate = view.findViewById(R.id.row_view_tv_filedate);
        ImageView iv_icon=view.findViewById(R.id.row_view_iv_icon);
        NetFile file=list.get(position);
        int fileType = file.getFileType();
        switch (fileType){
            case 0:
                iv_icon.setImageResource(R.drawable.doc);
                break;
            case 1:
                iv_icon.setImageResource(R.drawable.folder);
                break;
            case 2:
                iv_icon.setImageResource(R.drawable.disk);
                break;
        }

        tv_filename.setText(file.getFileName());
        tv_filesize.setText(file.getFileSizeStr());
        tv_filedate.setText(file.getFileModifiedDate());
        return view;
    }
}
