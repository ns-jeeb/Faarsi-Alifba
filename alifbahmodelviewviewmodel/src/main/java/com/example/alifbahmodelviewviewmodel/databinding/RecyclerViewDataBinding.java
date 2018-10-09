package com.example.alifbahmodelviewviewmodel.databinding;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.support.v7.widget.RecyclerView;

import com.example.alifbahmodelviewviewmodel.adapters.AlifbahAdapter;
import com.example.alifbahmodelviewviewmodel.models.AlifbaModel;
import com.example.alifbahmodelviewviewmodel.viewmodels.PersonViewModel;

import java.util.List;

public class RecyclerViewDataBinding {
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(RecyclerView recyclerView, AlifbahAdapter adapter, List<AlifbaModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }

}
