//package com.example.myapplication;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.util.AttributeSet;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
//public class TreeView extends View {
//
//
//    private float x=200;
//    private float y=200;
//    String a="Treeeeeeee";
//
//    Paint paint=new Paint();
//
//    public TreeView(Context context) {
//        super(context);
//    }
//
//    public TreeView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//
//    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        paint.setAntiAlias(true);
//        paint.setColor(Color.BLUE);
//        paint.setTextSize(100);
//        canvas.drawCircle(x,y,150, paint);
//        canvas.drawText( a,x+100,y+400,paint);
//        paint.setColor(Color.RED);
//        canvas.drawRect(new Rect(15,15,150,70),paint);
//
//
//    }
//
//}