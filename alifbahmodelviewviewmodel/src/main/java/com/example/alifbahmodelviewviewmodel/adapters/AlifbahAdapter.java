package com.example.alifbahmodelviewviewmodel.adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.alifbahmodelviewviewmodel.MyAbstrackClass;
import com.example.alifbahmodelviewviewmodel.R;
import com.example.alifbahmodelviewviewmodel.databinding.LetterItemBinding;
import com.example.alifbahmodelviewviewmodel.models.AlifbaModel;
import com.example.alifbahmodelviewviewmodel.viewmodels.DataItemViewModel;

import java.util.ArrayList;
import java.util.List;


public class AlifbahAdapter extends RecyclerView.Adapter<AlifbahAdapter.AlifBahViewHolder> {

    private List<AlifbaModel> data;

    public  AlifbahAdapter(){
        data = new ArrayList<>();
    }
    @NonNull
    @Override
    public AlifBahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_item,new FrameLayout(parent.getContext()),false);
        return new AlifBahViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlifBahViewHolder holder, int position) {
        AlifbaModel model = data.get(position);
        holder.setViewModel(new DataItemViewModel(model));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(@Nullable List<AlifbaModel> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public class AlifBahViewHolder extends RecyclerView.ViewHolder {

        LetterItemBinding mBinding;

        public AlifBahViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        private void bind() {
            mBinding = DataBindingUtil.bind(itemView);
        }
        public void unbind() {
            if (mBinding != null) {
                mBinding.unbind();
            }
        }
        public void setViewModel(DataItemViewModel itemViewModel){
            mBinding.setViewModel(itemViewModel);
        }
    }
}
