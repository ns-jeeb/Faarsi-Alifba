package com.jeeb.farsialifba.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jeeb.farsialifba.R;
import com.jeeb.farsialifba.model.Recording;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Recording>mRecordings;
    private MediaPlayer mPlayer;
    private boolean isPlaying = false;
    private int last_index = -1;
    private Handler mHandler = new Handler();
    private ViewHolder mHolder;
    private String mRecordingUri;
    private int mPosition;
    private int mLastProgress = 0;

    public void setRecordItemClickListener(OnRecordItemClickListener recordItemClickListener) {
        mRecordItemClickListener = recordItemClickListener;
    }

    private OnRecordItemClickListener mRecordItemClickListener;

    public RecordingAdapter(Context context, ArrayList<Recording> recordings){
        mContext = context;
        mRecordings = recordings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recording_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setUpData(viewHolder,i);
    }

    @Override
    public int getItemCount() {
        return mRecordings.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setUpData(ViewHolder holder, int position) {

        Recording recording = mRecordings.get(position);
        holder.mTxtRecoringName.setText(recording.getFileName());
        mPosition = position;
        if(recording.isPlaying()){
            holder.mImageViewPlay.setImageResource(R.drawable.ic_shortcut_stop);
            TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView);
            holder.mSeekBar.setVisibility(View.VISIBLE);
            seekUpdating(holder);
        }else{
            holder.mImageViewPlay.setImageResource(R.drawable.ic_shortcut_play_circle_outline);
            TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView);
            holder.mSeekBar.setVisibility(View.GONE);
        }
        mangeSeekBar(holder);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageViewPlay,mImageViewDelete;
        private TextView mTxtRecoringName;
        private SeekBar mSeekBar;

        Recording recording;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mImageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            mImageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            mTxtRecoringName = itemView.findViewById(R.id.textViewRecordingname);
            mSeekBar = itemView.findViewById(R.id.seekBar);

            mImageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    recording = mRecordings.get(position);
                    mRecordingUri = recording.getUri();
                    if( !isPlaying ){
                        stopPlaying();
                        if( position == last_index ){
                            recording.setPlaying(false);
                            stopPlaying();
                            notifyItemChanged(position);
                        }else{
                            markAllPause();
                            recording.setPlaying(true);
                            notifyItemChanged(position);
                            startPlaying(recording,position, ViewHolder.this);
                            last_index = position;
                        }

                    }else {
                        if( recording.isPlaying() ){
                            recording.setPlaying(false);
                            stopPlaying();
                            Log.d("isPlayin","True");
                        }else {
                            stopPlaying();
                            markAllPause();
                            notifyItemChanged(position);
                            startPlaying(recording,position,ViewHolder.this);
                            mSeekBar.setMax(mPlayer.getDuration());
                            recording.setPlaying(true);
                            Log.d("isPlayin","False");
                        }

                        notifyItemChanged(position);
                        last_index = position;
                    }
                }

            });
            mImageViewDelete.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() >=0){
                        recording = mRecordings.get(mPosition);
                        mRecordings.remove(mPosition);
                        deleteTruck(recording.getFileName(),mPosition);
                        notifyItemRemoved(getAdapterPosition());
                        mRecordItemClickListener.onRecordedItemListener(view,mRecordings.size(),recording);
                    }

                }
            });
        }


    }
    public void mangeSeekBar(final ViewHolder holder){
        holder.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer!= null && fromUser){
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mRunnable);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mRunnable);

                seekUpdating(holder);
            }
        });
    }
    private void markAllPause(){
        for (int i=0; i<mRecordings.size(); i++){
            mRecordings.get(i).setPlaying(false);
            mRecordings.set(i,mRecordings.get(i));
        }
        notifyDataSetChanged();
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            seekUpdating(mHolder);
        }
    };

    private void seekUpdating(ViewHolder holder) {
        mHolder = holder;
        if (mPlayer!= null){
            int currentPosition = mPlayer.getCurrentPosition();
            mHolder.mSeekBar.setMax(mPlayer.getDuration());
            mHolder.mSeekBar.setProgress(currentPosition);
            mLastProgress = currentPosition;
        }
        mHandler.postDelayed(mRunnable,100);
    }

    public void stopPlaying(){
        try{
            mPlayer.release();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mPlayer = null;
        isPlaying = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void deleteTruck(String nameFile,int position){
        File root = android.os.Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath() + "/AlifBah_Records/Audios";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (nameFile!= null){

            boolean isDeleted = false;
            try{
                if (files[position].exists()){
                    isDeleted =files[position].delete();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                if (isDeleted){
                    notifyDataSetChanged();
                }
            }
            if (isDeleted){
                notifyItemRemoved(position);
            }
        }
    }

    private void startPlaying(final Recording audio, final int position, ViewHolder holder) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mRecordingUri);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }
        //showing the pause button
        holder.mSeekBar.setMax(mPlayer.getDuration());
        isPlaying = true;

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                audio.setPlaying(false);
                notifyItemChanged(position);
                Log.e("onCompletion","mPlayer"+ mPlayer);
            }
        });
    }


    public interface OnRecordItemClickListener {
        void onRecordedItemListener(View view, int position, Recording recording);
    }
}
