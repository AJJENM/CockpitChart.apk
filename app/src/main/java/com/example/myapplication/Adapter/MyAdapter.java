package com.example.myapplication.Adapter;

import static android.text.Html.fromHtml;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Util.Marquee;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private View view;
    private Context context;
    private List<String> usernameList;
    private List<Integer> mobileList;

    public MyAdapter(Context context, List<String> usernameList, List<Integer> mobileList){
        this.context = context;
        this.usernameList = usernameList;
        this.mobileList = mobileList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ceil_normal,parent,false);
//        View view = layoutInflater.inflate(R.layout.ceil_normal, parent, false);
        return new MyViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(fromHtml(usernameList.get(position)) + " # " + mobileList.get(position).toString() + "台");
        holder.textView2.setText(String.valueOf(position + 1));
        Integer max = Collections.max(mobileList);
        holder.progressBar.setMax(max);
        holder.progressBar.setProgress(mobileList.get(position));
        //滚动设置
        String a = (String) holder.textView.getText();
        holder.textView.initScrollTextView(a, 1.5F);
        holder.textView.starScroll();
    }
    @Override
    public int getItemCount() {
        return usernameList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView2;
        Marquee textView;
        ProgressBar progressBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView.setMaxLines(1);

            textView2 = itemView.findViewById(R.id.textview2);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }
}


