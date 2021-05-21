package com.example.test_nga.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_nga.R;
import com.example.test_nga.data.NetFile;

import java.util.ArrayList;

public class MyNetFileListAdpater extends RecyclerView.Adapter<MyNetFileListAdpater.ViewHolder> {

    private ArrayList<NetFile> netFiles;
    private Context context;
    public MyNetFileListAdpater(ArrayList<NetFile> files, Context context){
        this.netFiles=files;
        this.context=context;

    }

    @NonNull
    @Override
    public MyNetFileListAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyNetFileListAdpater.ViewHolder holder, int position) {
        holder.tv_filename.setText(netFiles.get(position).getFileName());
        holder.tv_date.setText(netFiles.get(position).getFileModifiedDate());

    }

    @Override
    public int getItemCount() {
        return netFiles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_filename;
        public TextView tv_date;
        public ViewHolder(View view){
            super(view);
            tv_filename=view.findViewById(R.id.row_view_tv_filename);
            tv_date=view.findViewById(R.id.row_view_tv_filedate);
        }
    }
}
