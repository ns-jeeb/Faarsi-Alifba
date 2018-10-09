package com.example.alifbahmodelviewviewmodel.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.alifbahmodelviewviewmodel.adapters.AlifbahAdapter;
import com.example.alifbahmodelviewviewmodel.models.AlifbaModel;
import com.example.alifbahmodelviewviewmodel.models.Person;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends BaseObservable {

    private static String TAG = "AlifBahDataViewModel";
    private AlifbahAdapter adapter;
    private List<AlifbaModel> data;

    public DataViewModel(){
        this.adapter = new AlifbahAdapter();
        data = new ArrayList<>();
    }


    public void populateAlifBah(){
        AlifbaModel alifbaModel ;

        for (int i=0; i<20; i++){
            alifbaModel = new AlifbaModel();
            alifbaModel.setLetter("Item"+String.valueOf(i));
            this.data.add(alifbaModel);
        }
    }

    @Bindable
    public List<AlifbaModel> getData(){
        return data;
    }

    @Bindable
    public AlifbahAdapter getAdapter(){
        return adapter;
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, adapter population, etc.
        populateAlifBah();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

}
