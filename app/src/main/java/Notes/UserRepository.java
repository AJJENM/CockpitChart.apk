//package com.example.myapplication;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//
//import com.example.myapplication.Modle.Result;
//import com.example.myapplication.Modle.User;
//import com.example.myapplication.Post.DatabaseUtil;
//
//import java.util.List;
//
//public class UserRepository {
//    private LiveData<List<User>> listLiveData;
//    public UserRepository(@NonNull Application application) {
//        Result result = DatabaseUtil.selectList("dev-api/test/user", "list");
//        listLiveData = (LiveData<List<User>>) result.getData();
//    }
//    LiveData<List<User>> getAllUsersLive() {
//        return listLiveData;
//    }
//}
