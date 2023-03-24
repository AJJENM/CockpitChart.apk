//package com.example.myapplication;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import com.example.myapplication.Modle.User;
//
//import java.util.List;
//
//public class UserViewModel extends AndroidViewModel {
//    private UserRepository userRepository;
//
////    List<Result> resultList = new ArrayList<>();
////    Result result = DatabaseUtil.selectList("dev-api/test/user","list");
////    List<User> user = (List<User>) result.getData();
////    LiveData<List<User>> getAllUserLive(){
////        return (LiveData<List<User>>) result.getData();
////    }
//    public UserViewModel(@NonNull Application application) {
//        super(application);
//        userRepository = new UserRepository(application);
//    }
//    LiveData<List<User>> listLiveData(){
//        return userRepository.getAllUsersLive();
//    }
//    //        Type type = new TypeToken<List<Result>>(){}.getType();
////        resultList = gson.fromJson(result,Data.class);
////        resultList = gson.fromJson(String.valueOf(result),new TypeToken<Result>(){}.getType());
////        Log.e("resultList", String.valueOf(resultList));
////        List<User> user = (List<User>) result.getData();
//
//
//}
