package com.jeeb.farsialifba;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.jeeb.farsialifba.adapters.RecordingAdapter;
import com.jeeb.farsialifba.model.Recording;

import java.io.File;
import java.util.ArrayList;

public class RecordingListActivity extends AppCompatActivity implements RecordingAdapter.OnRecordItemClickListener{
    @Override
    public void onRecordedItemListener(View view, int position, Recording recording) {

    }

}
