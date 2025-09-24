package com.jeeb.farsialifba;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jeeb.farsialifba.adapters.RecordingAdapter;
import com.jeeb.farsialifba.databinding.ActivityRecordingBinding;
import com.jeeb.farsialifba.model.Recording;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecordingActivity extends AppCompatActivity implements View.OnClickListener, RecordingAdapter.OnRecordItemClickListener{

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int RECORD_AUDIO_REQUEST_CODE = 200;
    private static final int REQUEST_CODE_RECORDING = 1010;
    private static String mFileName;
    private MediaRecorder mRecorder;
    private MediaPlayer   mPlayer ;
    private int mLastProgress;
    private ActivityRecordingBinding mBinding;
    private Handler mHandler = new Handler();
    private String mRecordeName;
    private Recording recording;

    private ArrayList<Recording> recordingArraylist;
    private RecordingAdapter recordingAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionTorecordAudio(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RECORD_AUDIO_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RECORD_AUDIO_REQUEST_CODE){
            if (grantResults.length == 3 &&grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(this, "You must give permissions to use this app. App is exiting.",Toast.LENGTH_LONG).show();
                finishAffinity();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionTorecordAudio();
        }
        recordingArraylist = new ArrayList<>();


        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_recording);
        mBinding.chronometerTimer.setBase(SystemClock.elapsedRealtime());

//        mBinding.imageViewPlay.setOnClickListener(this);
        mBinding.imageViewStop.setOnClickListener(this);
        mBinding.imageViewRecord.setOnClickListener(this);

        fetchRecordings();
        setAdaptertoRecyclerView();
        if (recordingArraylist.size()> 0){
            mBinding.txtNoDataMessage.setVisibility(View.GONE);
        }else {
            mBinding.txtNoDataMessage.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    private void fetchRecordings() {

        File root = android.os.Environment.getExternalStorageDirectory();
        if (root != null){
            String path = root.getAbsolutePath() + "/AlifBah_Records/Audios";
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            if (files != null) {
                updateRecordingList(files, root);
            }

        }
    }

    public void updateRecordingList(File[]files,File root){
        for (File file : files) {
            Log.d("Files", "FileName:" + file.getName());
            String fileName = file.getName();
            String recordingUri = root.getAbsolutePath() + "/AlifBah_Records/Audios/" + fileName;

            recording = new Recording(recordingUri, fileName, false);
            recordingArraylist.add(recording);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fetchRecordings();
    }

    private void setAdaptertoRecyclerView() {
        recordingAdapter = new RecordingAdapter(this,recordingArraylist);
        mBinding.recordingRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        mBinding.recordingRecycler.setHasFixedSize(true);

        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        recordingAdapter.setRecordItemClickListener(this);
        mBinding.recordingRecycler.setAdapter(recordingAdapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {

        if (view == mBinding.imageViewRecord && isEditTextChecked()) {
            prepareForRecording();
            startRecording();
        }
        else if (view == mBinding.imageViewStop) {
            prepareStop();
            stopRecording();
            File root = android.os.Environment.getExternalStorageDirectory();
            if (root != null){
                String path = root.getAbsolutePath() + "/AlifBah_Records/Audios";
                Log.d("Files", "Path: " + path);
                File directory = new File(path);
                File[] files = directory.listFiles();

                for (File file: files){
                    if (file.getName().equalsIgnoreCase(mBinding.edTxtRecordedName.getText().toString())){
                        Recording tempRecording = new Recording(file.getAbsolutePath(),file.getName(),false);
                        recordingArraylist.add(tempRecording);
                        recordingAdapter.notifyDataSetChanged();
                    }
                }
            }
            mBinding.edTxtRecordedName.setText(null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareForRecording() {
        TransitionManager.beginDelayedTransition(mBinding.linearLayoutRecorder);
        mBinding.imageViewRecord.setVisibility(View.GONE);
        mBinding.imageViewStop.setVisibility(View.VISIBLE);
//        mBinding.linearLayoutPlay.setVisibility(View.GONE);
    }
    public boolean isEditTextChecked(){
        if (mBinding.edTxtRecordedName.getText().toString().isEmpty()) {
            mBinding.edTxtRecordedName.setError(String.valueOf(getString(R.string.mes_no_field)));
           return false;
        }else {
            mRecordeName = mBinding.edTxtRecordedName.getText().toString();
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void prepareStop() {
        TransitionManager.beginDelayedTransition(mBinding.linearLayoutRecorder);
        mBinding.imageViewRecord.setVisibility(View.VISIBLE);
//        mBinding.imageViewStop.setVisibility(View.GONE);
//        mBinding.linearLayoutPlay.setVisibility(View.VISIBLE);
    }

    private void startRecording() {
        //we use the MediaRecorder class to record
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        /**In the lines below, we create a directory AlifBah_Records/Audios in the phone storage
         * and the audios are being stored in the Audios folder **/
        File root = android.os.Environment.getExternalStorageDirectory();
        File file = new File(root.getAbsolutePath() + "/AlifBah_Records/Audios");
        if (!file.exists()) {
            file.mkdirs();
        }

        mFileName =  root.getAbsolutePath() + "/AlifBah_Records/Audios/" +mRecordeName;
        Log.d("filename",mFileName);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mLastProgress = 0;
//        mBinding.btnDisplayItems.setEnabled(true);
//        mBinding.seekBar.setProgress(0);
        stopPlaying();
        //starting the chronometer
        mBinding.chronometerTimer.setBase(SystemClock.elapsedRealtime());
        mBinding.chronometerTimer.start();
        mBinding.txtNoDataMessage.setVisibility(View.GONE);
    }

    private void stopRecording() {

        try{
            mRecorder.stop();
            mRecorder.release();
        }catch (Exception e){
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        mBinding.chronometerTimer.stop();
        mBinding.chronometerTimer.setBase(SystemClock.elapsedRealtime());
        //showing the play button
        Toast.makeText(this, String.valueOf(getString(R.string.mes_save_record)), Toast.LENGTH_SHORT).show();
        mBinding.imageViewStop.setVisibility(View.GONE);
    }

    private void stopPlaying() {
        try{
            mPlayer.release();
        }catch (Exception e){
            e.printStackTrace();
        }
        mPlayer = null;
        //showing the play button
//        mBinding.imageViewPlay.setImageResource(R.drawable.ic_shortcut_play_circle_outline);
        mBinding.chronometerTimer.stop();
    }

//    private void startPlaying() {
//        mPlayer = new MediaPlayer();
//        try {
//            mPlayer.setDataSource(mFileName);
//            mPlayer.prepare();
//            mPlayer.start();
//        } catch (IOException e) {
//            Log.e("LOG_TAG", "prepare() failed");
//        }
//        //making the imageview pause button
//        mBinding.imageViewPlay.setImageResource(R.drawable.ic_shortcut_stop);
//
//        mBinding.seekBar.setProgress(mLastProgress);
//        mPlayer.seekTo(mLastProgress);
//        mBinding.seekBar.setMax(mPlayer.getDuration());
//        seekUpdation();
//        mBinding.chronometerTimer.start();
//
//        /** once the audio is complete, timer is stopped here**/
//        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mBinding.imageViewPlay.setImageResource(R.drawable.ic_shortcut_play_circle_outline);
//                isPlaying = false;
//                mBinding.chronometerTimer.stop();
//            }
//        });
//
//        /** moving the track as per the seekBar's position**/
//        mBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if( mPlayer!=null && fromUser ){
//                    //here the track's progress is being changed as per the progress bar
//                    mPlayer.seekTo(progress);
//                    //timer is being updated as per the progress of the seekbar
//                    mBinding.chronometerTimer.setBase(SystemClock.elapsedRealtime() - mPlayer.getCurrentPosition());
//                    mLastProgress = progress;
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    private void seekUpdation() {
        if(mPlayer != null){
            int mCurrentPosition = mPlayer.getCurrentPosition() ;
//            mBinding.seekBar.setProgress(mCurrentPosition);
            mLastProgress = mCurrentPosition;
        }
        mHandler.postDelayed(runnable, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_main,menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {

//        switch (item.getItemId()){
//            case R.id.action_settings:
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }

//    }

    @Override
    public void onRecordedItemListener(View view, int position, Recording recording) {
        if (position<= 0){
            mBinding.txtNoDataMessage.setVisibility(View.VISIBLE);
        }else {
            mBinding.txtNoDataMessage.setVisibility(View.GONE);
        }
    }
}
