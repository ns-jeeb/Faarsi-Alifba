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

    private RecyclerView recyclerViewRecordings;
    private ArrayList<Recording> recordingArraylist;
    private RecordingAdapter recordingAdapter;
    private TextView textViewNoRecordings;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_list);
        initViews();

        fetchRecordings();//905-566-0555
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initViews() {
        recordingArraylist = new ArrayList<>();


        /** enabling back button ***/
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Recording List");
        }

        /** setting up recyclerView **/

        recyclerViewRecordings = findViewById(R.id.recording_recycler);
        recyclerViewRecordings.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerViewRecordings.setHasFixedSize(true);
//        final LottieAnimationView lottieView = (LottieAnimationView)findViewById(R.id.emoji_talk);
//        lottieView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lottieView.playAnimation();
////                lottieView.cancelAnimation();
//
//            }});

        textViewNoRecordings = findViewById(R.id.textViewNoRecordings);

        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void fetchRecordings() {

        File root = android.os.Environment.getExternalStorageDirectory();
        if (root != null){
            String path = root.getAbsolutePath() + "/AlifBah_Records/Audios";
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            Log.d("Files", "Size: "+ files.length);
            Intent intent = new Intent();

            if(files.length > 0){
                for (File file : files) {

                    Log.d("Files", "FileName:" + file.getName());
                    String fileName = file.getName();
                    String recordingUri = root.getAbsolutePath() + "/AlifBah_Records/Audios/" + fileName;

                    Recording recording = new Recording(recordingUri, fileName, false);
                    recordingArraylist.add(recording);
                }

                textViewNoRecordings.setVisibility(View.GONE);
                recyclerViewRecordings.setVisibility(View.VISIBLE);
                setAdaptertoRecyclerView();
                setResult(0,intent);

            }else{
                textViewNoRecordings.setVisibility(View.VISIBLE);
                recyclerViewRecordings.setVisibility(View.GONE);
                intent.putExtra("no_data",true);
                setResult(1,intent);
                this.finish();
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fetchRecordings();
    }

    private void setAdaptertoRecyclerView() {
        recordingAdapter = new RecordingAdapter(this,recordingArraylist);
        recordingAdapter.setRecordItemClickListener(this);
        recyclerViewRecordings.setAdapter(recordingAdapter);
    }

    @Override
    public void onRecordedItemListener(View view, int position, Recording recording) {
        Toast.makeText(this,""+position+recording.getFileName(),Toast.LENGTH_LONG).show();
    }

}
