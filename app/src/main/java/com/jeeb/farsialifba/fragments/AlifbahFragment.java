package com.jeeb.farsialifba.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.jeeb.farsialifba.MainActivity;
import com.jeeb.farsialifba.R;
import com.jeeb.farsialifba.RecordingActivity;
import com.jeeb.farsialifba.media.PlayAudioAlifBa;
import com.jeeb.farsialifba.media.UtilAndKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_AH;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ALIF;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_BAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_CHAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_DOL;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_FAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_FRAGMENT_TAG_ANSWER;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_GHAIN;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_GOF;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_HAIN;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_HAMZA;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_HAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_HIDOSHIMA;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_JIM;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_KHAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_KOF;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_LOM;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_MIM;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_NON;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_PAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_QOF;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_RAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_SAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_SHIN;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_SIN;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_SWAT;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_TAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_TOE;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_WOW;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_YAH;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ZAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ZGHAY;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ZOE;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ZOL;
import static com.jeeb.farsialifba.media.UtilAndKeys.KEY_ZOWAT;

import androidx.fragment.app.Fragment;

public class AlifbahFragment extends Fragment implements View.OnClickListener {

    private TextView mTxtAlifba;
    private TextView btnAlifBa;
    private PlayAudioAlifBa mAudioAlifBa;
    private Button mBtnPractise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAudioAlifBa = new PlayAudioAlifBa();
        View rootView = inflater.inflate(R.layout.fragment_alifbah, container, false);
        mBtnPractise = (Button)rootView.findViewById(R.id.btn_practise);
        mBtnPractise.setOnClickListener(this);
        mTxtAlifba =  (TextView)rootView.findViewById(R.id.ani_alif);
        GridView gridView = (GridView) rootView.findViewById(R.id.mylist);
        ArrayList<String> list = mAudioAlifBa.getAlifBa();
        List<String> listRes = new ArrayList<>();
        if (getActivity()!= null){
//            listRes =  Arrays.asList(getActivity().getResources().getStringArray(R.array.array_alifbahs));
            listRes.addAll(Arrays.asList(getActivity().getResources().getStringArray(R.array.array_alifbahs)));
        }
        AlifbaAdapter adapter = new AlifbaAdapter((ArrayList<String>) listRes, LayoutInflater.from(getActivity()));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setUpAlphabit(position,R.color.alif,R.color.background_alif,android.R.anim.slide_in_left,KEY_AH);
                        break;
                    case 1:
                        setUpAlphabit(position,R.color.alif,R.color.background_alif,android.R.anim.slide_in_left,KEY_ALIF);
                        break;
                    case 2:
                        setUpAlphabit(position,R.color.ba,R.color.background_ba,android.R.anim.slide_in_left,KEY_BAY);
                        break;
                    case 3:
                        setUpAlphabit(position,R.color.paye,R.color.background_paye,android.R.anim.slide_in_left,KEY_PAY);
                        break;
                    case 4:
                        setUpAlphabit(position,R.color.taa,R.color.background_taa,android.R.anim.slide_in_left,KEY_TAY);
                        break;
                    case 5:
                        setUpAlphabit(position,R.color.saye,R.color.background_saye,android.R.anim.slide_in_left,KEY_SAY);
                        break;
                    case 6:
                        setUpAlphabit(position,R.color.jim,R.color.background_jim,android.R.anim.slide_in_left,KEY_JIM);
                        break;
                    case 7:
                        setUpAlphabit(position,R.color.jim,R.color.background_jim,android.R.anim.slide_in_left,KEY_CHAY);
                        break;
                    case 8:
                        setUpAlphabit(position,R.color.heye,R.color.background_heye,android.R.anim.slide_in_left,KEY_HAY);
                        break;
                    case 9:
                        setUpAlphabit(position,R.color.khaye,R.color.background_khaye,android.R.anim.slide_in_left,KEY_KHAY);
                        break;
                    case 10:
                        setUpAlphabit(position,R.color.dol,R.color.background_dol,android.R.anim.slide_in_left,KEY_DOL);
                        break;
                    case 11:
                        setUpAlphabit(position,R.color.zol,R.color.background_zol,android.R.anim.slide_in_left,KEY_ZOL);
                        break;
                    case 12:
                        setUpAlphabit(position,R.color.ray,R.color.background_ray,android.R.anim.slide_in_left,KEY_RAY);

                        break;
                    case 13:
                        setUpAlphabit(position,R.color.zay,R.color.background_zay,android.R.anim.slide_in_left,KEY_ZAY);
                        break;
                    case 14:
                        setUpAlphabit(position,R.color.zghey,R.color.background_zghey,android.R.anim.slide_in_left,KEY_ZGHAY);
                        break;
                    case 15:
                        setUpAlphabit(position,R.color.sin,R.color.background_sin,android.R.anim.slide_in_left,KEY_SIN);
                        break;
                    case 16:
                        setUpAlphabit(position,R.color.shin,R.color.background_shin,android.R.anim.slide_in_left,KEY_SHIN);
                        break;
                    case 17:
                        setUpAlphabit(position,R.color.swat,R.color.background_swat ,android.R.anim.slide_in_left,KEY_SWAT);
                        break;
                    case 18:
                        setUpAlphabit(position,R.color.zowat,R.color.background_zowat,android.R.anim.slide_in_left,KEY_ZOWAT);
                        break;
                    case 19:
                        setUpAlphabit(position,R.color.toe,R.color.background_toe,android.R.anim.slide_in_left,KEY_TOE);
                        break;
                    case 20:
                        setUpAlphabit(position,R.color.alif,R.color.background_hain,android.R.anim.slide_in_left,KEY_ZOE);
                        break;
                    case 21:
                        setUpAlphabit(position,R.color.swat,R.color.background_ghain,android.R.anim.slide_in_left,KEY_HAIN);
                        break;
                    case 22:
                        setUpAlphabit(position,R.color.ba,R.color.background_faa,android.R.anim.slide_in_left,KEY_GHAIN);
                        break;
                    case 23:
                        setUpAlphabit(position,R.color.saye,R.color.background_qof,android.R.anim.slide_in_left,KEY_FAY);
                        break;
                    case 24:
                        setUpAlphabit(position,R.color.non,R.color.background_gof,android.R.anim.slide_out_right,KEY_QOF);
                        break;
                    case 25:
                        setUpAlphabit(position,R.color.hedoshima,R.color.background_kof,android.R.anim.slide_in_left,KEY_KOF);
                        break;
                    case 26:
                        setUpAlphabit(position,R.color.non,R.color.background_gof,android.R.anim.slide_out_right,KEY_GOF);
                        break;
                    case 27:
                        setUpAlphabit(position,R.color.mim,R.color.background_zol,android.R.anim.slide_in_left,KEY_LOM);
                        break;
                    case 28:
                        setUpAlphabit(position,R.color.ray,R.color.background_gof,android.R.anim.slide_out_right,KEY_MIM);
                        break;
                    case 29:
//
                        setUpAlphabit(position,R.color.dol,R.color.background_mim,android.R.anim.slide_in_left,KEY_NON);

                        break;
                    case 30:
                        setUpAlphabit(position,R.color. alif,R.color.background_saye,android.R.anim.slide_out_right,KEY_WOW);
                        break;
                    case 31:
                        setUpAlphabit(position,R.color.lom,R.color.background_saye,android.R.anim.slide_in_left,KEY_HIDOSHIMA);
                        break;
                    case 32:
                        setUpAlphabit(position,R.color.hamza,R.color.background_saye,android.R.anim.slide_out_right,KEY_HAMZA);
                        break;
                    case 33:
                        setUpAlphabit(position,R.color.yah,R.color.background_heye,android.R.anim.slide_in_left,KEY_YAH);
                        break;
                    default:
                }
            }
        });
        return rootView;
    }
    public void setUpAlphabit(int position,int letterColor, int bgColor,int animSide,String letter){
        mAudioAlifBa.managerOfSound(getActivity(),position);
        mTxtAlifba.setText(letter);
        mTxtAlifba.setTextColor(getResources().getColor(letterColor));
        mTxtAlifba.setBackgroundColor(getResources().getColor(bgColor));
        mTxtAlifba.startAnimation(AnimationUtils.loadAnimation(getActivity(), animSide));
    }

    @Override
    public void onClick(View view) {

        ViewDialog viewDialog = new ViewDialog();
        viewDialog.showDialog(getActivity(),getString(R.string.message_conformation));

    }


    public class AlifbaAdapter extends BaseAdapter {
        ArrayList mAlifbaLists;
        LayoutInflater mLayoutInflater;
        MediaPlayer mMediaPlayer;

        public AlifbaAdapter(ArrayList<String> alifBaaLists, LayoutInflater inflater) {
            mAlifbaLists = alifBaaLists;
            mLayoutInflater = inflater;
            mMediaPlayer = new MediaPlayer();
        }

        @Override
        public int getCount() {
            return mAlifbaLists.size();
        }

        @Override
        public Object getItem(int position) {
            return mAlifbaLists.get(position);
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
                mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rootView = mLayoutInflater.inflate(R.layout.items, parent, false);
                btnAlifBa = (TextView) rootView.findViewById(R.id.item_alifba);
                btnAlifBa.setText(mAlifbaLists.get(position).toString());
                setColorForAlibaGrid(position);
            }
            return rootView;
        }
    }
    public void setColorForAlibaGrid(int postion){
        String[] gridColor ={"#008B8B","#00FF00","#48D1CC","#556B2F","#696969","#6B8E23","#8FBC8F", "#AFEEEE","#B8860B","#BDB76B","#D8BFD8","#DEB887","#FFF0F5","#EE82EE"
        };
        switch (postion){
            case 0:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[0]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[13]));
                break;
            case 1:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[1]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[12]));
                break;
            case 2:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[2]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[11]));
                break;
            case 3:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[3]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[10]));
                break;
            case 4:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[4]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[9]));
                break;
            case 5:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[5]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[8]));
                break;
            case 6:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[6]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[7]));
                break;
            case 7:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[7]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[6]));
                break;
            case 8:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[8]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 9:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[9]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 10:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[10]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 11:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[11]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 12:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[12]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 13:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[13]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[0]));
                break;
            case 14:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[0]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[13]));
                break;
            case 15:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[1]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[12]));
                break;
            case 16:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[2]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[11]));
                break;
            case 17:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[3]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[10]));
                break;
            case 18:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[4]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[9]));
                break;
            case 19:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[5]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[8]));
                break;
            case 20:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[6]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[7]));
                break;
            case 21:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[7]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[6]));
                break;
            case 22:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[8]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 23:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[9]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 24:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[10]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 25:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[11]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 26:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[12]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 27:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[13]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[0]));
                break;
            case 28:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[0]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[5]));
                break;
            case 29:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[1]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[4]));
                break;
            case 30:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[2]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[3]));
                break;
            case 31:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[3]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[2]));
                break;
            case 32:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[4]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[1]));
                break;
            case 33:
                btnAlifBa.setBackgroundColor(Color.parseColor(gridColor[5]));
                btnAlifBa.setTextColor(Color.parseColor(gridColor[0]));
                break;

            default:
        }
    }

    public class ViewDialog {
        boolean shouldDoExcersize;
        public boolean showDialog(final Activity activity, String msg){

            final Dialog dialog = new Dialog(activity);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.custom_dialo_fragment);

            TextView text = dialog.findViewById(R.id.text_dialog);
            text.setText(msg);

            Button dialogButton = dialog.findViewById(R.id.btn_dialog_ok);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    ArrayList<String>fragmentTags = new ArrayList<>();
                    fragmentTags.add(UtilAndKeys.KEY_FRAGMENT_TAG_ANSWER);
                    fragmentTags.add(UtilAndKeys.KEY_FRAGMENT_TAG_QUESTION);

                    intent = MainActivity.newIntent(getActivity(), fragmentTags);

                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            Button dialogNo = dialog.findViewById(R.id.btn_dialog_no);
            dialogNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent(getContext(), RecordingActivity.class);
                    startActivity(intent);

                    shouldDoExcersize = false;
                }
            });
            dialog.show();
            return shouldDoExcersize;
        }
    }
}
