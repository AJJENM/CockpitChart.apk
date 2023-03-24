//package com.example.myapplication;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toolbar;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
////抽取常用方法
//public class BaseActivity extends AppCompatActivity {
//    private ProgressDialog eLoadingDialog;
//    private Toolbar toolbar;
//    private UserBiz userBiz;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setStatusBarColor(0xff000000);
//        }
//        eLoadingDialog = new ProgressDialog(this);
//        eLoadingDialog.setMessage("加载中>>>>>>");
//    }
//    protected void stopLoadingProgress(){
//        if (eLoadingDialog != null && eLoadingDialog.isShowing()) eLoadingDialog.dismiss();
//    }
//    protected void startLoadingProgress(){
//        eLoadingDialog.show();
//    }
////    protected void setUpToolBar(){
////        toolbar = findViewById(R.id.id_toolbar);
////        setSupportActionBar(toolbar);
////        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                onBackPressed();
////            }
////        });
////    }
////    protected void setToolbar(int drawable, View.OnClickListener clickListener){
////    this.toolbar = findViewById()
////    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        stopLoadingProgress();
//        eLoadingDialog =null;
//    }
//    protected void  toMain(){
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//}
//    //查看宽高
////    @Override
////    public void onWindowFocusChanged(boolean hasFocus) {
////        super.onWindowFocusChanged(hasFocus);
////        if (hasFocus) {
////            Log.w("tv_width", "" + eChart1.getWidth());
////            Log.w("tv_height", "" + eChart1.getHeight());
////        }
////    }