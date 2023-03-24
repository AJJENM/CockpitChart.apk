//package com.example.myapplication;
//
//import android.content.Context;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//
//public class MarqueeTextView extends androidx.appcompat.widget.AppCompatTextView {
//
//    public MarqueeTextView(Context context) {
//        super(context);
//        initView(context);
//    }
//
//    public MarqueeTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initView(context);
//    }
//
//    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initView(context);
//    }
//
//    private void initView(Context context) {
//        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        this.setSingleLine(true);
//        this.setMarqueeRepeatLimit(-1);
//
//
//    }
//
//    //最关键的部分
//    public boolean isFocused() {
//        return true;
//    }
//
////    @Override
////    public void onDraw(Canvas canvas){
////        super.onDraw(canvas);
////        if (isFirstDraw) {
////            Log.d(TAG , "isFirstDraw=="+isFirstDraw);
////            getTextWidth();
////            firstScrollX = getScrollX(); // 获取第一次滑动的X轴距离
////            System.out.println("firstScrollX======"+firstScrollX);
////            currentScrollX = firstScrollX;
////            mWidth = this.getWidth();  // 获取文本宽度，如果文本宽度大于屏幕宽度，则为屏幕宽度，否则为文本宽度
////            Log.d(TAG , "mWidth======"+mWidth);
////            endX = firstScrollX + textWidth - mWidth/2;  // 滚动的最大距离，可根据需要来定
////            Log.d(TAG , "endX========"+endX);
////            isFirstDraw = false;
////        }
////    }
//}
//
