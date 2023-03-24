package com.example.myapplication;

import static android.text.Html.FROM_HTML_MODE_COMPACT;
import static android.text.Html.fromHtml;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.LineChart_view;
import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.Adapter.PartsAdapter;
import com.example.myapplication.Adapter.RepairHistoryAdapter;
import com.example.myapplication.Adapter.TopAdapter;
import com.example.myapplication.Post.DatabaseUtil;
import com.example.myapplication.Util.HtEChartView;
import com.example.myapplication.ViewModel.GetTitleViewModel;
import com.example.myapplication.ViewModel.OtherViewModel;
import com.example.myapplication.ViewModel.SpecialViewModel;
import com.example.myapplication.ViewModel.SummaryViewModel;
import com.example.myapplication.entity.Asset;
import com.example.myapplication.entity.History;
import com.example.myapplication.entity.Info;
import com.example.myapplication.entity.Other;
import com.example.myapplication.entity.Overview;
import com.example.myapplication.entity.Parts;
import com.example.myapplication.entity.Result;
import com.example.myapplication.entity.Special;
import com.example.myapplication.entity.Top;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    Gson gson = new Gson();
    RecyclerView recyclerView, recyclerView_parts, recyclerView_repairHistory, recyclerView_top;
    TextView text_time, todoRepairNum, underRepairNum, todoCheckNum, textView_hospital;
    TextView other_1, other_2, other_3, other_4, other_5;
    TextView special1, special2, special3, special4, special5, special6, special7, special8, special9, special10, special11, special12;
    TextView summary1, summary2, summary3, summary4, summary5, summary6, summary7, summary8, summary9;
    String code, hospital;

    LineChart lineChart, lineChart_2;
    PieChart pieChart_1, pieChart_2;

    Timer timer;
    TimerTask timerTask;
    int period = 1000*60*5;

//    HtEChartView eChart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

//        iniHttp();
        _getIntent();
        getOther();
        _getTitle();
        getSpecial();
        getSummary();
        getRecycleData();

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getOther();
                        _getTitle();
                        getSpecial();
                        getSummary();
                        getRecycleData();
                        setData();
                        setData_line2();
                        initUI();
                    }
                });
            }
        };
        timer.schedule(timerTask,period,period);

        //SettingButton点击
        imageButton = findViewById(R.id.imageButton6);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "退出医院", Toast.LENGTH_SHORT).show();
                toLogin();
            }
        });

        StartThread_time();
        setData();
        setData_line2();
        initUI();
//        reFlush();
//        text_time = findViewById(R.id.text_time);
//        todoRepairNum = findViewById(R.id.todoRepairNum);
//        underRepairNum = findViewById(R.id.underRepairNum);
//        todoCheckNum = findViewById(R.id.todoCheckNum);
//        textView_hospital = findViewById(R.id.textView_hospital);
//        textView_hospital.setText(fromHtml("<b>" + hospital + "</b>"));
//        Result overview = DatabaseUtil.selectList("api/cockpit/repair/overview", "?deptCode=" + code);
//        Overview overviews = DatabaseUtil.getEntity(overview, Overview.class);
//
//        final GetTitleViewModel getTitleViewModel = new ViewModelProvider(this).get(GetTitleViewModel.class);
//
//        getTitleViewModel.initOverView(overviews);
//        getTitleViewModel.mutableLiveData.observe(this, new Observer<Overview>() {
//            @Override
//            public void onChanged(Overview overview) {
//                todoRepairNum.setText(fromHtml("<small><small><small>" + " 报修中 " + "</small></small></small>" + overview.getTodoRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//                underRepairNum.setText(fromHtml("<small><small><small>" + " 维修中 " + "</small></small></small>" + overview.getUnderRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//                todoCheckNum.setText(fromHtml("<small><small><small>" + " 待验收 " + "</small></small></small>" + overview.getTodoCheckNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//            }
//        });
    }

    private void getRecycleData() {
        //科室报修情况
        recyclerView = findViewById(R.id.recyclerview);
        Result result = DatabaseUtil.selectList("api/cockpit/repair/dept/info", "?deptCode=" + code);
        List<Info> info = DatabaseUtil.getList(result);
        List<String> usernameList = info.stream().map(Info::getDept).collect(Collectors.toList());
        List<Integer> mobileList = info.stream().map(Info::getRepairNum).collect(Collectors.toList());

        //备件使用情况
        recyclerView_parts = findViewById(R.id.recyclerview_parts);
        Result result_parts = DatabaseUtil.selectList("api/cockpit/repair/parts", "?deptCode=" + code);
        String toJson_parts = gson.toJson(result_parts.getResponse());
        List<Parts> parts = gson.fromJson((String.valueOf(toJson_parts)), new TypeToken<List<Parts>>() {
        }.getType());
        List<String> partsList = parts.stream().map(Parts::getParts).collect(Collectors.toList());
        List<String> model = parts.stream().map(Parts::getModel).collect(Collectors.toList());
        List<String> sn = parts.stream().map(Parts::getSn).collect(Collectors.toList());
        Collections.replaceAll(sn, null, "无");
        List<Integer> usedNum = parts.stream().map(Parts::getUsedNum).collect(Collectors.toList());
        List<String> repairCode = parts.stream().map(Parts::getRepairCode).collect(Collectors.toList());

        //历史报修量
        recyclerView_repairHistory = findViewById(R.id.RepairHistory);
        Result result_repairHistory = DatabaseUtil.selectList("api/cockpit/repair/history", "?deptCode=" + code);
        String toJson_repairHistory = gson.toJson(result_repairHistory.getResponse());
        List<History> repairHistory = gson.fromJson((String.valueOf(toJson_repairHistory)), new TypeToken<List<History>>() {
        }.getType());
        List<Integer> _year = repairHistory.stream().map(History::getYear).collect(Collectors.toList());
        List<Integer> _repairNum = repairHistory.stream().map(History::getRepairNum).collect(Collectors.toList());

        //进行中的维修记录
        recyclerView_top = findViewById(R.id.top);
        Result result_top = DatabaseUtil.selectList("api/cockpit/repair/top", "?deptCode=" + code);
        String toJson_top = gson.toJson(result_top.getResponse());
        List<Top> top = gson.fromJson((String.valueOf(toJson_top)), new TypeToken<List<Top>>() {
        }.getType());
        List<String> deviceName = top.stream().map(Top::getDeviceName).collect(Collectors.toList());
        List<String> rRPerson = top.stream().map(Top::getrRPerson).collect(Collectors.toList());
        List<String> status = top.stream().map(Top::getStatus).collect(Collectors.toList());


        //适配器
        MyAdapter myAdapter = new MyAdapter(this, usernameList, mobileList);
        PartsAdapter partsAdapter = new PartsAdapter(this, partsList, model, sn, usedNum, repairCode);
        RepairHistoryAdapter repairHistoryAdapter = new RepairHistoryAdapter(this, _year, _repairNum);
        TopAdapter topAdapter = new TopAdapter(this, deviceName, rRPerson, status);

        //布局
        LinearLayoutManager manager = new LinearLayoutManager(this);
        LinearLayoutManager manager_parts = new LinearLayoutManager(this);
        LinearLayoutManager manager_repairHistory = new LinearLayoutManager(this);
        LinearLayoutManager manager_top = new LinearLayoutManager(this);

        //设置布局
        recyclerView.setLayoutManager(manager);
        recyclerView_parts.setLayoutManager(manager_parts);
        recyclerView_repairHistory.setLayoutManager(manager_repairHistory);
        recyclerView_top.setLayoutManager(manager_top);

        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView_parts.setItemAnimator(new DefaultItemAnimator());
        recyclerView_repairHistory.setItemAnimator(new DefaultItemAnimator());
        recyclerView_top.setItemAnimator(new DefaultItemAnimator());

        //设置适配器
        recyclerView.setAdapter(myAdapter);
        recyclerView_parts.setAdapter(partsAdapter);
        recyclerView_repairHistory.setAdapter(repairHistoryAdapter);
        recyclerView_top.setAdapter(topAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timerTask.cancel();
    }

    //接收数据
    public void _getIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bun");
        Bundle bundle2 = intent.getBundleExtra("bun2");
        code = bundle.getString("code");
        hospital = bundle2.getString("hospital");
    }

//Result result,result_parts,result_repairHistory,result_top,overview,result_special,result_summary,result_other,
//        result_normalRate,result_analyse,result_UI;
//    private void iniHttp(){
//        AppExecutors.getInstance().networkIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                result = DatabaseUtil.selectList("api/cockpit/repair/dept/info", "?deptCode=" + code);
//                result_parts = DatabaseUtil.selectList("api/cockpit/repair/parts", "?deptCode=" + code);
//                result_repairHistory = DatabaseUtil.selectList("api/cockpit/repair/history", "?deptCode=" + code);
//                result_top = DatabaseUtil.selectList("api/cockpit/repair/top", "?deptCode=" + code);
//                overview = DatabaseUtil.selectList("api/cockpit/repair/overview", "?deptCode=" + code);
//                result_special = DatabaseUtil.selectList("api/cockpit/device/special", "?deptCode=" + code);
//                result_summary = DatabaseUtil.selectList("api/cockpit/asset", "?deptCode=" + code);
//                result_other = DatabaseUtil.selectList("api/cockpit/other", "?deptCode=" + code);
//                result_normalRate = DatabaseUtil.selectList("api/cockpit/device/normal/rate", "?deptCode=" + code);
//                result_analyse = DatabaseUtil.selectList("api/cockpit/repair/analyse", "?deptCode=" + code);
//                result_UI = DatabaseUtil.selectList("api/cockpit/asset/tree", "?deptCode=" + code);
//
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("overviews", result);
//                Message msg = new Message();
//                msg.what = 1;
//                msg.setData(bundle);
//                handler1.sendMessage(msg);
//            }
//        });
//}
//
//
//        @SuppressLint("HandlerLeak")
//    Handler handler1 = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            super.handleMessage(msg);
//            if(msg.what == 1){
//                result = (Result) msg.getData().getSerializable("overviews");
//
//
//            }
//        }
//    };
    //Title设置
    public void _getTitle() {

        text_time = findViewById(R.id.text_time);
        todoRepairNum = findViewById(R.id.todoRepairNum);
        underRepairNum = findViewById(R.id.underRepairNum);
        todoCheckNum = findViewById(R.id.todoCheckNum);
        textView_hospital = findViewById(R.id.textView_hospital);
        textView_hospital.setText(fromHtml("<b>" + hospital + "</b>"));
        Result overview = DatabaseUtil.selectList("api/cockpit/repair/overview", "?deptCode=" + code);
        Overview overviews = DatabaseUtil.getEntity(overview, Overview.class);

        final GetTitleViewModel getTitleViewModel = new ViewModelProvider(this).get(GetTitleViewModel.class);

        getTitleViewModel.initOverView(overviews);
        getTitleViewModel.mutableLiveData.observe(this, new Observer<Overview>() {
            @Override
            public void onChanged(Overview overview) {
                todoRepairNum.setText(fromHtml("<small><small><small>" + " 报修中 " + "</small></small></small>" + overview.getTodoRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
                underRepairNum.setText(fromHtml("<small><small><small>" + " 维修中 " + "</small></small></small>" + overview.getUnderRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
                todoCheckNum.setText(fromHtml("<small><small><small>" + " 待验收 " + "</small></small></small>" + overview.getTodoCheckNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
            }
        });

//        todoRepairNum.setText(fromHtml("<small><small><small>" + " 报修中 " + "</small></small></small>" + overviews.getTodoRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//        underRepairNum.setText(fromHtml("<small><small><small>" + " 维修中 " + "</small></small></small>" + overviews.getUnderRepairNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//        todoCheckNum.setText(fromHtml("<small><small><small>" + " 待验收 " + "</small></small></small>" + overviews.getTodoCheckNum() + "<small><small><small>" + " 项 " + "</small></small></small>"));
//
//        text_time.invalidate();
//        todoRepairNum.invalidate();
//        underRepairNum.invalidate();
//        todoCheckNum.invalidate();
//        textView_hospital.invalidate();
    }

    //大型&特殊设备监控
    public void getSpecial() {
        special1 = findViewById(R.id.special_1);
        special2 = findViewById(R.id.special_2);
        special3 = findViewById(R.id.special_3);
        special4 = findViewById(R.id.special_4);
        special5 = findViewById(R.id.special_5);
        special6 = findViewById(R.id.special_6);
        special7 = findViewById(R.id.special_7);
        special8 = findViewById(R.id.special_8);
        special9 = findViewById(R.id.special_9);
        special10 = findViewById(R.id.special_10);
        special11 = findViewById(R.id.special_11);
        special12 = findViewById(R.id.special_12);

        pieChart_1 = findViewById(R.id.pieChart_1);
        pieChart_2 = findViewById(R.id.pieChart_2);
        List<PieEntry> pieEntries = new ArrayList<>();
        List<PieEntry> pieEntries2 = new ArrayList<>();

        final SpecialViewModel specialViewModel = new ViewModelProvider(this).get(SpecialViewModel.class);

        Result result_special = DatabaseUtil.selectList("api/cockpit/device/special", "?deptCode=" + code);
        Special special = DatabaseUtil.getEntity(result_special, Special.class);

        specialViewModel.initSpecial(special);

        specialViewModel.specialMutableLiveData.observe(this, new Observer<Special>() {
            @Override
            public void onChanged(Special special) {
                int a = (int) (special.getMeteringOriginalValue());
                special1.setText(fromHtml("计量设备" + "<br>" + a + "元"));
                special2.setText(fromHtml(special.getMeteringNum() + "台"));
                int A = (int) (special.getMeteringNormalRate() * 100);
                special3.setText(fromHtml("完好率" + A + "%"));
                int b = (int) (special.getSpecialOriginalValue());
                special4.setText(fromHtml("特种设备" + "<br>" + b + "元"));
                special5.setText(fromHtml(special.getSpecialNum() + "台"));
                int B = (int) (special.getSpecialNormalRate() * 100);
                special6.setText(fromHtml("完好率" + B + "%"));
                int c = (int) (special.getEmergencyOriginalValue());
                special7.setText(fromHtml("应急设备" + "<br>" + c + "元"));
                special8.setText(fromHtml(special.getEmergencyNum() + "台"));
                int C = (int) (special.getEmergencyNormalRate() * 100);
                special9.setText(fromHtml("完好率" + C + "%"));
                int d = (int) (special.getRadiationOriginalValue());
                special10.setText(fromHtml("辐射设备" + "<br>" + d + "元"));
                special11.setText(fromHtml(special.getRadiationNum() + "台"));
                int D = (int) (special.getRadiationNormalRate() * 100);
                special12.setText(fromHtml("完好率" + D + "%", FROM_HTML_MODE_COMPACT));

                pieEntries.add(new PieEntry((float) special.getLifeSupportNormalRate(), "正常"));
                pieEntries.add(new PieEntry((float) special.getLifeSupportFaultRate(), "异常"));
                pieEntries2.add(new PieEntry((float) special.getLargeNormalRate(), "正常"));
                pieEntries2.add(new PieEntry((float) special.getLargeFaultRate(), "异常"));
            }
        });
//        int a = (int) (special.getMeteringOriginalValue());
//        special1.setText(fromHtml("计量设备" + "<br>" + a + "元"));
//        special2.setText(fromHtml(special.getMeteringNum() + "台"));
//        int A = (int) (special.getMeteringNormalRate() * 100);
//        special3.setText(fromHtml("完好率" + A + "%"));
//        int b = (int) (special.getSpecialOriginalValue());
//        special4.setText(fromHtml("特种设备" + "<br>" + b + "元"));
//        special5.setText(fromHtml(special.getSpecialNum() + "台"));
//        int B = (int) (special.getSpecialNormalRate() * 100);
//        special6.setText(fromHtml("完好率" + B + "%"));
//        int c = (int) (special.getEmergencyOriginalValue());
//        special7.setText(fromHtml("应急设备" + "<br>" + c + "元"));
//        special8.setText(fromHtml(special.getEmergencyNum() + "台"));
//        int C = (int) (special.getEmergencyNormalRate() * 100);
//        special9.setText(fromHtml("完好率" + C + "%"));
//        int d = (int) (special.getRadiationOriginalValue());
//        special10.setText(fromHtml("辐射设备" + "<br>" + d + "元"));
//        special11.setText(fromHtml(special.getRadiationNum() + "台"));
//        int D = (int) (special.getRadiationNormalRate() * 100);
//        special12.setText(fromHtml("完好率" + D + "%", FROM_HTML_MODE_COMPACT));

//        pieChart_1 = findViewById(R.id.pieChart_1);
//        pieChart_2 = findViewById(R.id.pieChart_2);
//        List<PieEntry> pieEntries = new ArrayList<>();
//        List<PieEntry> pieEntries2 = new ArrayList<>();
//        pieEntries.add(new PieEntry((float) special.getLifeSupportNormalRate(),"正常"));
//        pieEntries.add(new PieEntry((float) special.getLifeSupportFaultRate(),"异常"));
//        pieEntries2.add(new PieEntry((float) special.getLargeNormalRate(),"正常"));
//        pieEntries2.add(new PieEntry((float) special.getLargeFaultRate(),"异常"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        PieDataSet pieDataSet2 = new PieDataSet(pieEntries2, "");
        //标签字体 为0不显示
        pieDataSet.setValueTextSize(0);
        pieDataSet2.setValueTextSize(0);

        PieData pieData = new PieData(pieDataSet);
        PieData pieData2 = new PieData(pieDataSet2);
        pieChart_1.setData(pieData);
        pieChart_2.setData(pieData2);

        pieChart_1.setExtraOffsets(-10, -5, -10, -10);
        pieChart_2.setExtraOffsets(-10, -5, -10, -10);
        //显示标签
        pieChart_1.setDrawEntryLabels(false);
        pieChart_2.setDrawEntryLabels(false);
        //是否可转动
        pieChart_1.setRotationEnabled(false);
        pieChart_2.setRotationEnabled(false);

        pieDataSet.setColors(Color.rgb(66, 187, 255), Color.rgb(39, 125, 249));//设置各个数据的颜色
        pieDataSet2.setColors(Color.rgb(66, 187, 255), Color.rgb(39, 125, 249));//设置各个数据的颜色
        //实体扇形的空心圆的半径   设置成0时就是一个圆 而不是一个环
        pieChart_1.setHoleRadius(2000);
        //中间半透明白色圆的半径    设置成0时就是隐藏
        pieChart_1.setTransparentCircleRadius(0);
        //设置中心圆的颜色
        pieChart_1.setHoleColor(Color.rgb(18, 27, 40));
        pieChart_2.setHoleColor(Color.rgb(18, 27, 40));
        //设置中心部分的字  （一般中间白色圆不隐藏的情况下才设置）
            pieChart_1.setCenterText(fromHtml("完好率<br>" + (int) (special.getLifeSupportNormalRate() * 100) + "%"));
            pieChart_2.setCenterText(fromHtml("完好率<br>" + (int) (special.getLargeNormalRate() * 100) + "%"));
        //设置中心字的字体颜色
        pieChart_1.setCenterTextColor(Color.rgb(39, 125, 249));
        pieChart_2.setCenterTextColor(Color.rgb(39, 125, 249));
        //设置中心字的字体大小
        pieChart_1.setCenterTextSize(10);
        pieChart_2.setCenterTextSize(10);
        //对于右下角一串字母的操作
        pieChart_1.getDescription().setEnabled(false);
        pieChart_2.getDescription().setEnabled(false);
        pieChart_1.setUsePercentValues(false);    // 表内数据用百分比替代，而不是原先的值
        pieChart_2.setUsePercentValues(false);
        pieChart_1.setHoleRadius(82);  // 设置中心圆半径占整个饼形图圆半径（图表半径）的百分比。默认50%
        pieChart_2.setHoleRadius(82);
        pieChart_1.setDragDecelerationEnabled(false);   // 拖动惯性
        pieChart_2.setDragDecelerationEnabled(false);

        //图例
        Legend legend = pieChart_1.getLegend();
        Legend legend2 = pieChart_2.getLegend();
        legend.setTextColor(Color.WHITE);
        legend2.setTextColor(Color.WHITE);
        //是否显示图例
        legend.setEnabled(true);
        legend2.setEnabled(true);
        // 图例图形尺寸，dp，默认8dp
        legend.setFormSize(6);
        legend.setTextSize(8);
        legend2.setFormSize(6);
        legend2.setTextSize(8);

        // 设置水平图例间间距，默认6dp
        legend.setXEntrySpace(10);
        legend2.setXEntrySpace(10);
        //显示位置
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        pieChart_1.animateXY(1000, 1000);
        pieChart_2.animateXY(1000, 1000);
        pieChart_1.invalidate();
        pieChart_2.invalidate();

        special1.invalidate();
        special2.invalidate();
        special3.invalidate();
        special4.invalidate();
        special5.invalidate();
        special6.invalidate();
        special7.invalidate();
        special8.invalidate();
        special9.invalidate();
        special10.invalidate();
        special11.invalidate();
        special12.invalidate();
    }

    //统计
    public void getSummary() {
        summary1 = findViewById(R.id.summary1);
        summary2 = findViewById(R.id.summary2);
        summary3 = findViewById(R.id.summary3);
        summary4 = findViewById(R.id.summary4);
        summary5 = findViewById(R.id.summary5);
        summary6 = findViewById(R.id.summary6);
        summary7 = findViewById(R.id.summary7);
        summary8 = findViewById(R.id.summary8);
        summary9 = findViewById(R.id.summary9);
        Result result_summary = DatabaseUtil.selectList("api/cockpit/asset", "?deptCode=" + code);
        Asset asset = DatabaseUtil.getEntity(result_summary, Asset.class);

        final SummaryViewModel summaryViewModel = new ViewModelProvider(this).get(SummaryViewModel.class);
        summaryViewModel.initAsset(asset);
        summaryViewModel.assetMutableLiveData.observe(this, new Observer<Asset>() {
            @Override
            public void onChanged(Asset asset) {
                summary1.setText(Html.fromHtml("<small><small>" + "设备总数量<br>" + "</small></small>" + "<b>" + asset.getDeviceNum() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
                summary2.setText(Html.fromHtml("<small><small>" + "设备总价值<br>" + "</small></small>" + "<b>" + asset.getDeviceOriginalValue() + "</b>" + "<small><small>" + "<br>万" + "</small></small>"));
                summary3.setText(Html.fromHtml("<small><small>" + "本月新增<br>" + "</small></small>" + "<b>" + asset.getNewThisMonth() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
                summary4.setText(Html.fromHtml("<small><small>" + "本月报废<br>" + "</small></small>" + "<b>" + asset.getScrapThisMonth() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
                summary5.setText(Html.fromHtml("<small><small>" + "日常保养<br>" + "</small></small>" + "<b>" + asset.getDailyMaintain() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
                summary6.setText(Html.fromHtml("<small><small>" + "二级保养<br>" + "</small></small>" + "<b>" + asset.getSecondMaintain() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
                summary7.setText(Html.fromHtml("<small><small>" + "性能检测<br>" + "</small></small>" + "<b>" + asset.getPerformanceTest() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
                summary8.setText(Html.fromHtml("<small><small>" + "计量提醒<br>" + "</small></small>" + "<b>" + asset.getMeteringRemind() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
                summary9.setText(Html.fromHtml("<small><small>" + "保修提醒<br>" + "</small></small>" + "<b>" + asset.getInsuranceRemind() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
            }
        });
//        int a = Integer.parseInt(String.valueOf(asset.getDeviceOriginalValue()));
//        summary1.setText(Html.fromHtml("<small><small>" + "设备总数量<br>" + "</small></small>" + "<b>" + asset.getDeviceNum() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
//        summary2.setText(Html.fromHtml("<small><small>" + "设备总价值<br>" + "</small></small>" + "<b>" + asset.getDeviceOriginalValue() + "</b>" + "<small><small>" + "<br>万" + "</small></small>"));
//        summary3.setText(Html.fromHtml("<small><small>" + "本月新增<br>" + "</small></small>" + "<b>" + asset.getNewThisMonth() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
//        summary4.setText(Html.fromHtml("<small><small>" + "本月报废<br>" + "</small></small>" + "<b>" + asset.getScrapThisMonth() + "</b>" + "<small><small>" + "<br>台" + "</small></small>"));
//        summary5.setText(Html.fromHtml("<small><small>" + "日常保养<br>" + "</small></small>" + "<b>" + asset.getDailyMaintain() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
//        summary6.setText(Html.fromHtml("<small><small>" + "二级保养<br>" + "</small></small>" + "<b>" + asset.getSecondMaintain() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
//        summary7.setText(Html.fromHtml("<small><small>" + "性能检测<br>" + "</small></small>" + "<b>" + asset.getPerformanceTest() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
//        summary8.setText(Html.fromHtml("<small><small>" + "计量提醒<br>" + "</small></small>" + "<b>" + asset.getMeteringRemind() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
//        summary9.setText(Html.fromHtml("<small><small>" + "保修提醒<br>" + "</small></small>" + "<b>" + asset.getInsuranceRemind() + "</b>" + "<small><small>" + "<br>未处理" + "</small></small>"));
//
//        summary1.invalidate();
//        summary2.invalidate();
//        summary3.invalidate();
//        summary4.invalidate();
//        summary5.invalidate();
//        summary6.invalidate();
//        summary7.invalidate();
//        summary8.invalidate();
//        summary9.invalidate();
    }

    //other设置
    public void getOther() {
        other_1 = findViewById(R.id.other_1);
        other_2 = findViewById(R.id.other_2);
        other_3 = findViewById(R.id.other_3);
        other_4 = findViewById(R.id.other_4);
        other_5 = findViewById(R.id.other_5);
        Result result_other = DatabaseUtil.selectList("api/cockpit/other", "?deptCode=" + code);
        Other other = DatabaseUtil.getEntity(result_other, Other.class);

        final OtherViewModel otherViewModel = new ViewModelProvider(this).get(OtherViewModel.class);
        otherViewModel.initOther(other);
        otherViewModel.otherMutableLiveData.observe(this, new Observer<Other>() {
            @Override
            public void onChanged(Other other) {
                other_1.setText(Html.fromHtml("供应商" + "<br>" + other.getSupplierNum() + "个"));
                other_2.setText(Html.fromHtml("厂商" + "<br>" + other.getProducerNum() + "个"));
                other_3.setText(Html.fromHtml("维修商" + "<br>" + other.getMaintainerNum() + "个"));
                other_4.setText(Html.fromHtml("科室" + "<br>" + other.getDeptNum() + "个"));
                other_5.setText(Html.fromHtml("医工" + "<br>" + other.getDeptNum() + "位"));
            }
        });
//        other_1.setText(Html.fromHtml("供应商" + "<br>" + other.getSupplierNum() + "个"));
//        other_2.setText(Html.fromHtml("厂商" + "<br>" + other.getProducerNum() + "个"));
//        other_3.setText(Html.fromHtml("维修商" + "<br>" + other.getMaintainerNum() + "个"));
//        other_4.setText(Html.fromHtml("科室" + "<br>" + other.getDeptNum() + "个"));
//        other_5.setText(Html.fromHtml("医工" + "<br>" + other.getDeptNum() + "位"));
//
//        other_1.invalidate();
//        other_2.invalidate();
//        other_3.invalidate();
//        other_4.invalidate();
//        other_5.invalidate();
    }

    //返回标题页面
    private void toLogin() {
        Intent intent2 = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent2);
        //设置跳转动画
        overridePendingTransition(R.anim.alpha, 0);
    }

    //获取时间
    private void StartThread_time() {
        new Thread() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }.start();
    }
    //在主线程中进行数据处理
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    long time = System.currentTimeMillis();
                    CharSequence format = DateFormat.format("hh:mm:ss", time);
                    CharSequence format2 = DateFormat.format("yyyy-MM-dd", time);
                    text_time.setText(fromHtml("<b>" + format + "</b>" + "<small><br><small>" + format2 + "</small></small>"));
                    break;

            }

        }
    };
//    private void reFlush(){
//        new Thread(){
//            @Override
//            public void run(){
//                do {
//                    try {
//                        Thread.sleep(100000);
//                        Message message = new Message();
//                        message.what = 1;
//                        handler2.sendMessage(message);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }while (true);
//            }
//        }.start();
//    }
//    //在主线程中进行数据处理
//    private Handler handler2 = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    finish();
//                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
//                    startActivity(intent);
//            }
//
//        }
//    };

    //LineChart Setting
    public void setData() {
        lineChart = findViewById(R.id.line_1);
        Result result_normalRate = DatabaseUtil.selectList("api/cockpit/device/normal/rate", "?deptCode=" + code);
        String toJson_normalRate = gson.toJson(result_normalRate.getResponse());
        List<Float> normalRate = gson.fromJson((String.valueOf(toJson_normalRate)), new TypeToken<List<Float>>() {
        }.getType());
        List<Entry> line = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            line.add(new Entry(i + 1, normalRate.get(i)));
        }
        LineDataSet lineDataSet = new LineDataSet(line, "");
        LineData lineData = new LineData(lineDataSet);
        //
        lineChart.setTouchEnabled(false);
        lineChart.getDescription().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        YAxis yAxis = lineChart.getAxisLeft();
        //x y轴颜色
//        xAxis.setAxisLineColor(Color.RED);
//        yAxis.setAxisLineColor(Color.RED);
        //折线的颜色
        lineDataSet.setColor(Color.rgb(28, 42, 113));
        //是否隐藏右边的Y轴
        lineChart.getAxisRight().setEnabled(false);
        //空心圆的圆心半径
        lineDataSet.setCircleHoleRadius(1);

        //圆点的半径
        lineDataSet.setCircleRadius(3);
        //圆点的颜色
        lineDataSet.setCircleColor(Color.rgb(28, 42, 113));
        //X轴所在位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Y轴坐标的个数
//        yAxis.setLabelCount(6,false);
        //Y轴自定义坐标
//        // 不显示数据描述
//        Description description = lineChart.getDescription();
//        description.setEnabled(false);
//        lineChart.setDescription(description);
        // 不显示图例
        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);
        //设置竖线的显示样式为虚线  lineLength控制虚线段的长度  spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        yAxis.enableGridDashedLine(5f, 5f, 0f);
        // 设置X轴的刻度数量，第二个参数表示是否平均分配
//        xAxis.setLabelCount(1, true);
//        yAxis.setLabelCount(1,true);
        // 是否显示坐标点的数据，折线上是否绘制数据
        lineDataSet.setDrawValues(false);

        xAxis.setTextColor(Color.GRAY);
        yAxis.setTextColor(Color.GRAY);
        lineData.setValueTextColor(Color.GRAY);
        xAxis.setTextSize(10);
        yAxis.setTextSize(8);
        yAxis.setAxisMinimum(0);    // 此轴显示的最小值

        yAxis.setAxisMaximum((float) 1.1);
        xAxis.setLabelCount(12, false);
        yAxis.setLabelCount(5, false); // 纵轴上标签显示的数量
        xAxis.setGranularity(1); // 设置X轴值之间最小距离
        xAxis.setAvoidFirstLastClipping(false);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        lineDataSet.setLineWidth(2f);//折线的宽度
        //设置曲线展示为圆滑曲线（如果不设置则默认折线)
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        // 不显示定位线，是否禁用点击高亮线
        lineDataSet.setHighlightEnabled(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
//                return super.getAxisLabel(value, axis);
                return (int) value + "月";
            }
        });
        lineChart.setData(lineData);
        lineChart.animateXY(1000, 1000);
        lineChart.invalidate(); // 刷新
    }

    public void setData_line2() {
        lineChart_2 = findViewById(R.id.line_2);
        Result result_analyse = DatabaseUtil.selectList("api/cockpit/repair/analyse", "?deptCode=" + code);
        String toJson_analyse = gson.toJson(result_analyse.getResponse());
        List<List<Float>> Diallel = gson.fromJson(String.valueOf(toJson_analyse), new TypeToken<List<List<Float>>>() {
        }.getType());
        List<Float> oneList = new ArrayList<>();
        List<Float> twoList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            oneList.add(Diallel.get(i).get(0));
            twoList.add(Diallel.get(i).get(1));
        }
        List<Entry> line_one = new ArrayList<>();
        List<Entry> line_two = new ArrayList<>();
        ArrayList<Integer> xvalue = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            line_one.add(new Entry(i + 1, oneList.get(i)));
            line_two.add(new Entry(i + 1, twoList.get(i)));
            xvalue.add(i + 1);
//            line_two.add(new Entry(i + 1, twoList.get(i)));
        }
        LineDataSet lineDataSet_one = new LineDataSet(line_one, "");
        LineDataSet lineDataSet_two = new LineDataSet(line_two, "");

        List<ILineDataSet> sets = new ArrayList<>();
        sets.add(lineDataSet_one);
        sets.add(lineDataSet_two);
        LineData lineData = new LineData(sets);
//        LineData lineData_one = new LineData(lineDataSet_one);
//        LineData lineData_two = new LineData(lineDataSet_two);
        //折线的颜色
        lineDataSet_one.setColor(Color.GRAY);
        lineDataSet_two.setColor(Color.rgb(28, 42, 113));
        //是否可以用手指移动图表
        lineChart_2.setDragEnabled(false);
        //设置为false以禁止通过在其上双击缩放图表。是否可以缩放图表，当屏幕一屏显示不下，希望能通过缩放或滑动图表，仅设置这个和上面那个是不行的，还需要配合Matrix来实现
        lineChart_2.setScaleEnabled(false);
//        lineChart_2.setTouchEnabled(false);
//        lineChart_2.getDescription().setEnabled(true);
        XAxis xAxis = lineChart_2.getXAxis();
        YAxis yAxis = lineChart_2.getAxisLeft();

        //是否隐藏右边的Y轴
        lineChart_2.getAxisRight().setEnabled(false);
        //空心圆的圆心半径
        lineDataSet_one.setCircleHoleRadius(1);
        lineDataSet_two.setCircleHoleRadius(1);

        //圆点的半径
        lineDataSet_one.setCircleRadius(3);
        lineDataSet_two.setCircleRadius(3);

        //圆点的颜色
        lineDataSet_one.setCircleColor(Color.GRAY);
        lineDataSet_two.setCircleColor(Color.rgb(28, 42, 113));

        //X轴所在位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 不显示图例
        Legend legend = lineChart_2.getLegend();
        legend.setEnabled(false);
        //设置竖线的显示样式为虚线  lineLength控制虚线段的长度  spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        yAxis.enableGridDashedLine(5f, 5f, 0f);
        // 设置X轴的刻度数量，第二个参数表示是否平均分配
//        xAxis.setLabelCount(1, true);
//        yAxis.setLabelCount(1,true);
        // 是否显示坐标点的数据，折线上是否绘制数据
        lineDataSet_one.setDrawValues(false);
        lineDataSet_two.setDrawValues(false);

        xAxis.setTextColor(Color.GRAY);
        yAxis.setTextColor(Color.GRAY);
        lineData.setValueTextColor(Color.GRAY);
        xAxis.setTextSize(10);
        yAxis.setTextSize(8);
//        yAxis.setAxisMinimum(0);    // 此轴显示的最小值

//        yAxis.setAxisMaximum((float) 1.1);
        xAxis.setLabelCount(12, false);
//        yAxis.setLabelCount(5, false); // 纵轴上标签显示的数量
        xAxis.setGranularity(1); // 设置X轴值之间最小距离
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        lineDataSet_one.setLineWidth(2f);//折线的宽度
        lineDataSet_two.setLineWidth(2f);//折线的宽度

        //设置曲线展示为圆滑曲线（如果不设置则默认折线)
        lineDataSet_one.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet_two.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        // 不显示定位线，是否禁用点击高亮线
        lineDataSet_one.setHighlightEnabled(true);
        lineDataSet_two.setHighlightEnabled(true);

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
//                return super.getAxisLabel(value, axis);
                return (int) value + "月";
            }
        });
//        lineChart_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lineDataSet_one.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
//
//            }
//        });
        // 不显示数据描述
        Description description = lineChart_2.getDescription();
        description.setEnabled(false);
        lineChart_2.setDescription(description);
        //点击后的高亮线的显示样式
        lineDataSet_one.enableDashedHighlightLine(10f, 5f, 0f);
        // 画水平高亮指示器，默认true
        lineDataSet_one.setDrawHorizontalHighlightIndicator(false);
        lineDataSet_two.setDrawHorizontalHighlightIndicator(false);
        //点击后的高亮线的显示样式
        lineDataSet_one.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet_two.enableDashedHighlightLine(10f, 5f, 0f);
        //设置点击交点后显示高亮线宽
        lineDataSet_one.setHighlightLineWidth(1.5f);
        lineDataSet_two.setHighlightLineWidth(1.5f);
        //设置点击交点后显示交高亮线的颜色
        lineDataSet_one.setHighLightColor(Color.WHITE);
        lineDataSet_two.setHighLightColor(Color.WHITE);
        //点击view
        MarkerView line_view = new LineChart_view(this, R.layout.linechart_view, lineChart_2, xvalue);
        line_view.setChartView(lineChart_2);
        lineChart_2.setMarker(line_view);

        //
        lineChart_2.setData(lineData);

        lineChart_2.animateXY(1000, 1000);
        lineChart_2.invalidate(); // 刷新
    }

    //treemap
    private void initUI() {

        Result result_UI = DatabaseUtil.selectList("api/cockpit/asset/tree", "?deptCode=" + code);
        String toJson = gson.toJson(result_UI.getResponse());

        //数据格式为json
        //格式说明可以查看百度EChart文档
//        String data1 = "{\"title\":{\"show\":false,\"text\":\"订单状态百分比\",\"subtext\":\"\"},\"tooltip\":{\"trigger\":\"item\",\"formatter\":\"{a} <br/>{b} : {c} ({d}%)\"},\"series\":[{\"center\":[\"50%\",\"60%\"],\"radius\":[\"50%\",\"80%\"],\"type\":\"pie\",\"name\":\"订单笔数\",\"data\":[{\"name\":\"待付款\",\"value\":\"1\"},{\"name\":\"待发货\",\"value\":\"1\"},{\"name\":\"待收货\",\"value\":\"16\"},{\"name\":\"已完成\",\"value\":\"27\"}]}],\"legend\":{\"show\":false,\"data\":[\"待付款\",\"待发货\",\"待收货\",\"已完成\"]}}";
//        String data2 = "{\"color\":[\"#3398DB\",\"#e43c59\"],\"title\":{\"show\":false,\"text\":\"订单销售走势统计图\",\"subtext\":\"订单销售走势统计图\"},\"tooltip\":{\"trigger\":\"axis\",\"axisPointer\":{\"type\":\"shadow\"}},\"grid\":{\"zlevel\":0,\"z\":0,\"borderWidth\":0,\"containLable\":true},\"xAxis\":[{\"type\":\"category\",\"axisTick\":{\"show\":true,\"splitNumber\":0},\"data\":[\"2018-09-22\",\"2018-09-23\",\"2018-09-24\",\"2018-09-25\",\"2018-09-26\",\"2018-09-27\",\"2018-09-28\"]}],\"yAxis\":[{\"type\":\"value\",\"axisLabel\":{\"formatter\":\"{value}\"}}],\"series\":[{\"smooth\":true,\"type\":\"line\",\"name\":\"待发货订单\",\"data\":[\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"0\"]},{\"smooth\":true,\"type\":\"line\",\"name\":\"已完成订单\",\"data\":[\"0\",\"0\",\"0\",\"0\",\"0\",\"0\",\"1\"]}],\"legend\":{\"show\":false,\"data\":[\"待发货订单\",\"已完成订单\"]}}";
//        String data3 = "{\"series\":[{\"type\":\"treemap\",\"data\": [{\"name\":\"小车河二院区\",\"value\":\"265\"},{\"name\":\"疫苗接种室\",\"value\":\"2\"},{\"name\":\"外二科\",\"value\":\"116\"},{\"name\":\"眼科\",\"value\":\"0\"},{\"name\":\"精康院区\",\"value\":\"649\"},{\"name\":\"精神康复科\",\"value\":\"0\"},{\"name\":\"精托病房\",\"value\":\"0\"},{\"name\":\"设备科\",\"value\":\"1\"},{\"name\":\"泌尿外科\",\"value\":\"10\"},{\"name\":\"中医科\",\"value\":\"45\"},{\"name\":\"社区\",\"value\":\"0\"},{\"name\":\"内二科\",\"value\":\"289\"},{\"name\":\"供应室\",\"value\":\"36\"},{\"name\":\"ICU\",\"value\":\"125\"},{\"name\":\"儿童保健科\",\"value\":\"0\"},{\"name\":\"全科医学科\",\"value\":\"0\"},{\"name\":\"中曹院区\",\"value\":\"14\"},{\"name\":\"五官科\",\"value\":\"21\"},{\"name\":\"妇产科\",\"value\":\"140\"},{\"name\":\"放射科/介入室\",\"value\":\"40\"},{\"name\":\"急诊科\",\"value\":\"39\"},{\"name\":\"病理科\",\"value\":\"5\"},{\"name\":\"康复医学科\",\"value\":\"0\"},{\"name\":\"心脏康复科\",\"value\":\"14\"},{\"name\":\"外一科\",\"value\":\"154\"},{\"name\":\"手术室\",\"value\":\"196\"},{\"name\":\"PICU\",\"value\":\"0\"},{\"name\":\"输液大厅 门诊儿保\",\"value\":\"3\"},{\"name\":\"病案室\",\"value\":\"0\"},{\"name\":\"胃镜室\",\"value\":\"21\"},{\"name\":\"内一科\",\"value\":\"183\"},{\"name\":\"内三科\",\"value\":\"311\"},{\"name\":\"药房\",\"value\":\"0\"},{\"name\":\"妇科\",\"value\":\"0\"},{\"name\":\"资产科\",\"value\":\"0\"},{\"name\":\"检验科\",\"value\":\"68\"},{\"name\":\"系统管理\",\"value\":\"3\"},{\"name\":\"老年病房\",\"value\":\"0\"},{\"name\":\"B超室 \",\"value\":\"13\"},{\"name\":\"口腔科\",\"value\":\"7\"},{\"name\":\"护士站\",\"value\":\"0\"},{\"name\":\"门急诊\",\"value\":\"0\"},{\"name\":\"老年体检科\",\"value\":\"0\"},{\"name\":\"儿科门诊\",\"value\":\"0\"},{\"name\":\"心电图室\",\"value\":\"6\"},{\"name\":\"发热门诊\",\"value\":\"3\"},{\"name\":\"预防接种科\",\"value\":\"0\"},{\"name\":\"麻醉科\",\"value\":\"3\"},{\"name\":\"综合病房\",\"value\":\"1\"},{\"name\":\"神经外科\",\"value\":\"109\"}] }]}";
        String data4 = "{\"color\":[\"#23292E\",\"#080D23\",\"#091825\",\"#091621\"],\"series\":[{\"type\":\"treemap\",\"roam\":false,\"nodeClick\":false,\"width\":\"100%\",\"height\":\"100%\",\"breadcrumb\":{\"show\": false, },\"label\":{\"fontSize\": 8, },\"data\": " + toJson + " }]}";
        //实例化EChart
        HtEChartView eChart1 = findViewById(R.id.HtChartView);

        //绑定数据
        eChart1.setData(data4);
        eChart1.invalidate();
    }

//
//    private Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            while (true){
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mRunnable.sendMessage()
//            }
//        }
//    };

}
