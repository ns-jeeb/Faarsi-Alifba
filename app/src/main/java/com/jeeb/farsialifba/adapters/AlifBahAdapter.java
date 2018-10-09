package com.jeeb.farsialifba.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jeeb.farsialifba.R;

import java.util.ArrayList;

/**
 * Created by Jeeb on 11/9/2016.
 */

public class AlifBahAdapter extends BaseAdapter {

    private ArrayList mNumbers;
    private LayoutInflater mLayoutInflater;
    private MediaPlayer mMediaPlayer;
    private TextView mBtnNumber;

    public AlifBahAdapter(ArrayList<String> numbers, LayoutInflater inflater) {
        mNumbers = numbers;
        mLayoutInflater = inflater;
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return mNumbers.size();
    }

    @Override
    public Object getItem(int position) {
        return mNumbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View rootView = convertView;
        if (rootView != null) {
            return rootView;
        } else {
            mLayoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = mLayoutInflater.inflate(R.layout.items, parent, false);
            mBtnNumber = (TextView) rootView.findViewById(R.id.item_alifba);
            mBtnNumber.setText(mNumbers.get(position).toString());
            setColorForAlibaGrid(position);
        }
        return rootView;
    }

    public void setColorForAlibaGrid(int postion) {
        String[] gridColor = {"#008B8B", "#00FF00", "#48D1CC", "#556B2F", "#696969", "#6B8E23", "#8FBC8F", "#AFEEEE", "#B8860B", "#BDB76B", "#D8BFD8", "#DEB887", "#FFF0F5", "#EE82EE"
        };
        switch (postion) {
            case 0:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[0]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[13]));
                break;
            case 1:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[1]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[12]));
                break;
            case 2:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[2]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[11]));
                break;
            case 3:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[3]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[10]));
                break;
            case 4:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[4]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[9]));
                break;
            case 5:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[5]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[8]));
                break;
            case 6:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[6]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[7]));
                break;
            case 7:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[7]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[6]));
                break;
            case 8:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[8]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 9:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[9]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 10:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[10]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 11:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[11]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 12:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[12]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 13:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[13]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[0]));
                break;
            case 14:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[0]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[13]));
                break;
            case 15:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[1]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[12]));
                break;
            case 16:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[2]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[11]));
                break;
            case 17:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[3]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[10]));
                break;
            case 18:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[4]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[9]));
                break;
            case 19:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[5]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[8]));
                break;
            case 20:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[6]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[7]));
                break;
            case 21:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[7]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[6]));
                break;
            case 22:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[8]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 23:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[9]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 24:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[10]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 25:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[11]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 26:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[12]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 27:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[13]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[0]));
                break;
            case 28:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[0]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 29:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[1]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 30:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[2]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 31:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[3]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 32:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[4]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 33:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[5]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[0]));
                break;
            case 34:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[6]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 35:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[7]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 36:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[8]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 37:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[9]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 38:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[10]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 39:
                mBtnNumber.setBackgroundColor(Color.parseColor(gridColor[11]));
                mBtnNumber.setTextColor(Color.parseColor(gridColor[1]));
                break;

            default:
        }
    }
}
