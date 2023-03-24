package com.example.myapplication.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.PartsViewHolder> {

    private View view;
    private Context context;
    private List<String> parts, repairCode,model,sn;
    private List<Integer> usedNum;
    public PartsAdapter(Context context,List<String> parts, List<String> model,List<String> sn, List<Integer> usedNum,List<String> repairCode){
        this.context = context;
        this.parts = parts;
        this.model = model;
        this.sn = sn;
        this.usedNum = usedNum;
        this.repairCode = repairCode;
    }

    @NonNull
    @Override
    public PartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part,parent,false);
        return new PartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsViewHolder holder, int position) {
        holder.textView_parts.setText(Html.fromHtml(parts.get(position) + " " + model.get(position) + " SN:" + sn.get(position)));

        holder.textView_usedNum.setText(Html.fromHtml( usedNum.get(position).toString() + "     "));

        holder.textView_repairCode.setText(Html.fromHtml( repairCode.get(position)));
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }
    public class PartsViewHolder extends RecyclerView.ViewHolder {
        TextView textView_parts, textView_usedNum, textView_repairCode;
        public PartsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_parts = itemView.findViewById(R.id.textView_parts);
            textView_usedNum = itemView.findViewById(R.id.textView_usedNum);
            textView_repairCode = itemView.findViewById(R.id.textView_repairCode);

        }
    }
}
