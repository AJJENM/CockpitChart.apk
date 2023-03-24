package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.entity.Overview;

public class GetTitleViewModel extends ViewModel{

    public final MutableLiveData<Overview> mutableLiveData = new MutableLiveData<>();

    public void initOverView(Overview overview){

            mutableLiveData.setValue(overview);

    }
    public void initi(){
        Overview overview = mutableLiveData.getValue();
        overview.setTodoCheckNum(100);
        overview.setUnderRepairNum(110);
        overview.setTodoRepairNum(90);
        mutableLiveData.setValue(overview);
    }
}
