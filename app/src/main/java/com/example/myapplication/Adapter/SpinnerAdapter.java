package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> hospitalList;
    public SpinnerAdapter(List<String> hospitalList,Context context){
        this.hospitalList = hospitalList;
        this.context = context;
    }
    TextView textView;
    @Override
    public int getCount() {
        return hospitalList.size();
    }

    @Override
    public Object getItem(int position) {
        return hospitalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.spinner,null);
        if (convertView!=null){
            holder.textView = convertView.findViewById(R.id.Spinner);
            holder.textView.setText(hospitalList.get(position));
            convertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    v.setScaleX(hasFocus ? 1.15f : 1.0f);
                    v.setScaleY(hasFocus ? 1.15f : 1.0f);
                    holder.textView.setTextColor(hasFocus ? Color.RED : Color.WHITE);
                }
            });

        }
        return convertView;

    }
    class ViewHolder{
        TextView textView;
    }

}
