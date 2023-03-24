package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Util.Marquee;
import com.example.myapplication.R;

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private View view;
    private Context context;
    private List<String> deviceName,rRPerson,status;
    public TopAdapter(Context context, List<String> deviceName, List<String> rRPerson, List<String> status){
        this.context = context;
        this.deviceName = deviceName;
        this.rRPerson = rRPerson;
        this.status = status;
    }

    @NonNull
    @Override
    public TopAdapter.TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        i = i + 1;
        view = LayoutInflater.from(parent.getContext()).inflate(com.example.myapplication.R.layout.top,parent,false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAdapter.TopViewHolder holder, int position) {
        holder._deviceName.setText(deviceName.get(position));
        holder._rRPerson.setText(rRPerson.get(position));
        holder._status.setText(status.get(position));
        holder.Num.setText(String.valueOf(position + 1));
        //滚动设置
        String a = (String) holder._deviceName.getText();
        holder._deviceName.initScrollTextView(a, 1.5F);
        holder._deviceName.starScroll();
        String b = (String) holder._rRPerson.getText();
        holder._rRPerson.initScrollTextView(b, 1.5F);
        holder._rRPerson.starScroll();
    }

    @Override
    public int getItemCount() {
        return deviceName.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder{
        TextView _status, Num;
        Marquee _deviceName, _rRPerson;
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            _deviceName = itemView.findViewById(R.id.deviceName);
            _rRPerson = itemView.findViewById(R.id.rRPerson);
            _status = itemView.findViewById(R.id.status);
            Num = itemView.findViewById(R.id.Num);
        }
    }
}
