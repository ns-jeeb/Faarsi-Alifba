package com.jeeb.farsialifba;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.jeeb.farsialifba.databinding.ActivityHostingBinding;
import com.jeeb.farsialifba.fragments.AnswerItemFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_FRAGMENT_TAG_LIST;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements AnswerItemFragment.OnAnswerListClickListener {

    private ArrayList<String> mEmptyList;
    private String letter;
    private ActivityHostingBinding mBinding;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    List<String> alifBa;
    QueLayoutAdpter mAdapter;


    public static Intent newIntent(Context context, ArrayList<String> fragmentTags){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putStringArrayListExtra(KEY_FRAGMENT_TAG_LIST,fragmentTags);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_hosting);
        fragmentManager = getSupportFragmentManager();

        AnswerItemFragment answerFrag = (AnswerItemFragment) fragmentManager.findFragmentById(R.id.answer_layout);

        if (answerFrag == null) {
            answerFrag = new AnswerItemFragment();
        }
        if (answerFrag.isAdded()){
            return;
        }
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.answer_layout,answerFrag);
        transaction.commit();

        mEmptyList = new ArrayList<>();
        mAdapter = new QueLayoutAdpter(this,mEmptyList,LayoutInflater.from(this));
        mBinding.gridView.setAdapter(mAdapter);


    }

    @Override
    public void onItemAnswerClickLister(String item, final ViewGroup context) {
        launchTheTest(context.getContext(),item);
    }



    public void launchTheTest(final Context context, String item){
        mBinding.txtMessage.setVisibility(View.GONE);
        String rightLetter=null;
        alifBa = Arrays.asList(getResources().getStringArray(R.array.array_alifbahs));

        for (int i=0; i<alifBa.size();i++){
            if (alifBa.get(i).equalsIgnoreCase(item)&& mEmptyList.size() == i){
                rightLetter = item;
                break;
            }
        }

        if (rightLetter != null && !rightLetter.isEmpty()){
            mEmptyList.add(rightLetter);
            mAdapter.notifyDataSetChanged();
            if (mEmptyList.size()== 34){
                Toast.makeText(this,"Good Job you successfully completed the test",Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(context).setTitle(String.valueOf(getString(R.string.title_dialog)))
                        .setMessage(String.valueOf(getString(R.string.test_completed)))
                        .setPositiveButton("بلی", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //go to next Activity
                                Intent intent = new Intent(context, RecordingActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("نه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //go back to main activity
                    }
                }).show();

            }
        }else {
            Toast.makeText(this,"not correct",Toast.LENGTH_LONG).show();

        }
    }
    public class QueLayoutAdpter extends ArrayAdapter{

        LayoutInflater mInflater;
        ArrayList<String>mAlifBahs;

        public QueLayoutAdpter(Context context, ArrayList<String> alifBahLetters,LayoutInflater inflater){
            super(context, R.layout.answer_letters_item);
            mAlifBahs = alifBahLetters;
            mInflater = inflater;
        }
        @Override
        public int getCount() {
            return mAlifBahs.size();
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                mInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = mInflater.inflate(R.layout.answer_letters_item,parent,false);
            }
            Log.i("TAG", "Postion in adapter: " + position);
            Log.i("TAG", "Name in adapter: " + mAlifBahs);
            TextView txtItem = view.findViewById(R.id.textView);
            txtItem.setTextColor(Color.RED);
            txtItem.setText(mAlifBahs.get(position));
            return view;
        }

    }
}
