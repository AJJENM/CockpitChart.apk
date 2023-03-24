//package com.example.myapplication;
//
//import androidx.lifecycle.ViewModel;
//
//import com.example.myapplication.Modle.Result;
//import com.example.myapplication.Modle.User;
//import com.example.myapplication.Post.DatabaseUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class List01 extends ViewModel {
////    recyclerView = findViewById(R.id.recyclerview);
//    List<String> usernameList = new ArrayList<>();
//    List<String> mobileList = new ArrayList<>();
//    Result result = DatabaseUtil.selectList("dev-api/test/user", "list");
//    List<User> user = DatabaseUtil.getList(result);
//
//    usernameList =user.stream().map(User::getUsername).collect(Collectors.toList());
//    mobileList =user.stream().map(User::getMobile).collect(Collectors.toList());
//
////    //适配器
////    MyAdapter myAdapter = new MyAdapter(this, usernameList, mobileList);
////    //布局
////    LinearLayoutManager manager = new LinearLayoutManager(this);
////    //设置布局
////    try{
////        recyclerView.setLayoutManager(manager);
////    }
////    catch(
////    Exception e){
////        e.printStackTrace();
////    }
////    //设置动画
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////    //设置适配器
////        recyclerView.setAdapter(myAdapter);
//}