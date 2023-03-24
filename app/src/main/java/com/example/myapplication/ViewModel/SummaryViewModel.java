package com.example.myapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.entity.Asset;

public class SummaryViewModel extends ViewModel {
    public final MutableLiveData<Asset> assetMutableLiveData = new MutableLiveData<>();
    public void initAsset(Asset asset){
        assetMutableLiveData.setValue(asset);
    }

}
