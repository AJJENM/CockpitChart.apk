package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.entity.Other;

public class OtherViewModel extends ViewModel {
    public final MutableLiveData<Other> otherMutableLiveData = new MutableLiveData<>();
    public void initOther(Other other){
        otherMutableLiveData.setValue(other);
    }
}
