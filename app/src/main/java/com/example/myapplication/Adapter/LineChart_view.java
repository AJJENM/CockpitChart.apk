package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class LineChart_view extends MarkerView {

    private TextView textView,textView2,textView3;
    LineChart lineChart;
    ArrayList<Integer> xvalue;//X轴数据源
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public LineChart_view(Context context, int layoutResource, LineChart lineChart, ArrayList<Integer> xvalue) {
        super(context, layoutResource);
        textView = findViewById(R.id.analyse_1);
        textView2 = findViewById(R.id.analyse_2);
        textView3 = findViewById(R.id.mouth);
        this.lineChart=lineChart;
        this.xvalue=xvalue;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight){
        LineData lineData=lineChart.getLineData();//得到已经绘制成型的折线图的数据
        LineDataSet set=(LineDataSet)lineData.getDataSetByIndex(0);//获取第一条折线图Y轴数据
        LineDataSet set1=(LineDataSet)lineData.getDataSetByIndex(1);//获取第二条折线图Y轴数据
        int DataSetIndex=highlight.getDataSetIndex();//获取点击的是哪条折线上的交叉点，0就是第一条，以此类推
        int index;
        if (DataSetIndex==0){
            index= set.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
        }else {
            index= set1.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
        }
        //根据index值，分别获取当前X轴上对应的两条折线的Y轴的值
        Entry entry=set.getEntryForIndex(index);//
        Entry entry1=set1.getEntryForIndex(index);
//        Log.i("x,y轴","/"+index+"/"+DataSetIndex);
        textView.setText(Html.fromHtml("月平均相应工时（小时） "+ "<b>" + (int)entry.getY() + "</b>")); // set the entry-value as the display text
        textView2.setText(Html.fromHtml("月平均维修工时（小时） " + "<b>" + (int)entry1.getY() + "</b>"));
        textView3.setText(Html.fromHtml(  xvalue.get(index)+ "月"));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());

    }

}

