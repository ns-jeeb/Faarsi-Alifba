package com.example.alifbahmodelviewviewmodel.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.example.alifbahmodelviewviewmodel.models.AlifbaModel;

public class DataItemViewModel extends BaseObservable {
    private AlifbaModel mModel;

    public DataItemViewModel(AlifbaModel model){
        mModel = model;
    }

    public void setUP(){
        //adding listeners
    }
    public void tearDown(){
        // removing listeners
    }

    @Bindable
    public String getLetter(){
        return !TextUtils.isEmpty(mModel.getLetter()) ? mModel.getLetter(): "";
    }
}
