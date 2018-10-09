package com.jeeb.farsialifba.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeeb.farsialifba.MainActivity;
import com.jeeb.farsialifba.R;
import com.jeeb.farsialifba.adapters.AnswerItemRecyclerViewAdapter;
import com.jeeb.farsialifba.databinding.FragmentAnswerListBinding;
import com.jeeb.farsialifba.media.PlayAudioAlifBa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnswerItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 6;
    private OnAnswerListClickListener mListener;
    private PlayAudioAlifBa mAudioAlifBa;
    private FragmentAnswerListBinding mBinding;

    public AnswerItemFragment() {
    }

    public static AnswerItemFragment newInstance(int columnCount) {
        AnswerItemFragment fragment = new AnswerItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer_list, container, false);
        mBinding = DataBindingUtil.bind(view);
        mAudioAlifBa = new PlayAudioAlifBa();
        List<String> answerList = new ArrayList<>();
        if (getActivity() != null){
            answerList =  Arrays.asList(getActivity().getResources().getStringArray(R.array.array_alifbahs));
        }
        Collections.shuffle(answerList);

        AnswerItemRecyclerViewAdapter adapter = new AnswerItemRecyclerViewAdapter(answerList, mListener);
        mBinding.answerList.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        mBinding.answerList.setHasFixedSize(true);
        mBinding.answerList.setAdapter(adapter);
        return mBinding.getRoot();
    }

    ArrayList<String> newLetterList;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAnswerListClickListener) {
            mListener = (OnAnswerListClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAnswerListClickListener {

        void onItemAnswerClickLister(String item, ViewGroup context);
    }
}
