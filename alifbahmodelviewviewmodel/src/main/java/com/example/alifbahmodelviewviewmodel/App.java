package com.example.alifbahmodelviewviewmodel;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.example.alifbahmodelviewviewmodel.databinding.AppDataBindingComponent;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}
