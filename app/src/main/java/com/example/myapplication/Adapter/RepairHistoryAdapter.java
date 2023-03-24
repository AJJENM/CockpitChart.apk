package com.example.myapplication.Adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Util.HtmlTagHandler;
import com.example.myapplication.R;

import java.util.List;

public class RepairHistoryAdapter extends RecyclerView.Adapter<RepairHistoryAdapter.RepairHistoryViewHolder> {

    private View view;
    private Context context;
    private List<Integer> year, repairNum;
    public RepairHistoryAdapter(Context context, List<Integer> year, List<Integer> repairNum){
        this.context = context;
        this.year = year;
        this.repairNum = repairNum;
    }

    @NonNull
    @Override
    public RepairHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repairhistory,parent,false);
        return new RepairHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairHistoryViewHolder holder, int position) {
    String string = year.get(position).toString() + "-" + "<myfont size='24px'>" +"<b>" + repairNum.get(position).toString()+ "</b>" + "</myfont>" + "次";
    Spanned spanned = Html.fromHtml(string,null,new HtmlTagHandler("myfont"));
    holder._year.setText(spanned);
//    holder._repairNum.setText(Html.fromHtml( "<b>" + repairNum.get(position).toString() + "</b>"));
//        Html.fromHtml(  year.get(position).toString() + "-" + "<font color='red' size='1000px'>" +"<b>" + repairNum.get(position).toString()+ "</b>" + "</font>" + "次")
    }

    @Override
    public int getItemCount() {
        return repairNum.size();
    }

    public class RepairHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView _year, _repairNum;
        public RepairHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            _year = itemView.findViewById(R.id._year);
//            _year.setCompoundDrawableTintList(R.color.seaShell);
//            _repairNum = itemView.findViewById(R.id._repairNum);
        }
    }
}
