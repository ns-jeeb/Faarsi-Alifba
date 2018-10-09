package com.jeeb.farsialifba.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeb.farsialifba.R;
import com.jeeb.farsialifba.fragments.AnswerItemFragment.OnAnswerListClickListener;
import com.jeeb.farsialifba.media.PlayAudioAlifBa;

import java.util.ArrayList;
import java.util.List;

public class AnswerItemRecyclerViewAdapter extends RecyclerView.Adapter<AnswerItemRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final OnAnswerListClickListener mListener;

    public AnswerItemRecyclerViewAdapter(List<String> items, OnAnswerListClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTxtItem.setText(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemAnswerClickLister(holder.mItem, (ViewGroup) v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTxtItem;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTxtItem = (TextView) view.findViewById(R.id.item_alifba);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTxtItem.getText() + "'";
        }
    }
}
