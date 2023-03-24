package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.entity.Special;

public class SpecialViewModel extends ViewModel {
    public final MutableLiveData<Special> specialMutableLiveData = new MutableLiveData<>();
    public void initSpecial(Special special){
        specialMutableLiveData.setValue(special);
    }
}
