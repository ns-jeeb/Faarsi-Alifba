package com.jeeb.farsialifba.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.jeeb.farsialifba.R;
import com.jeeb.farsialifba.adapters.AlifBahAdapter;
import com.jeeb.farsialifba.media.PlayAudioAlifBa;

import java.util.ArrayList;

import static com.jeeb.farsialifba.media.UtilAndKeys.*;

import androidx.fragment.app.Fragment;

public class NumberFragment extends Fragment implements View.OnClickListener{

    private TextView mTxtNumber;
    private PlayAudioAlifBa mAudioNumbers;
    public NumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAudioNumbers = new PlayAudioAlifBa();
        View rootView = inflater.inflate(R.layout.fragment_alifbah, container, false);

        mTxtNumber =  (TextView)rootView.findViewById(R.id.ani_alif);
        Button btnTamroin = rootView.findViewById(R.id.btn_practise);
        btnTamroin.setOnClickListener(this);
        GridView gridView = (GridView) rootView.findViewById(R.id.mylist);
        ArrayList<String> list = mAudioNumbers.getNumbers();
        AlifBahAdapter adapter = new AlifBahAdapter(list, LayoutInflater.from(getActivity()));
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mTxtNumber.setText(KEY_YAK);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.background_alif));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.alif));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        break;
                    case 1:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_DO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.ba));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_ba));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 2:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SAA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.background_paye));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.paye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right));
                        break;
                    case 3:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_CHAR);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.taa));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_taa));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 4:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_PANJ);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.background_shin));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.saye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 5:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SHASH);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 jim));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_kof));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 6:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_HAFT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.      background_yah));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.chaye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 7:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_HASHT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 heye));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 8:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_NO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.background_khaye));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.khaye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 9:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_DAA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 dol));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_dol));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 10:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_YAZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 zol));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zol));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 11:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_DOVAZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 ray));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_ray));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 12:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SAZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 zay));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zay));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 13:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_CHARDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 zghey));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zghey));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 14:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_PONZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 sin));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_sin));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 15:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SHONZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 shin));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_shin));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 16:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_HABDA);
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 17:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_HAGZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 swat));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_swat));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 18:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_NOZDA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 zowat));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zowat));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 19:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 toe));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_toe));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 20:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_YAK);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 zoe));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zoe));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 21:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_DO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 yah));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_hain));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 22:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_SAA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 hamza));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_ghain));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 23:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_CHAR);
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 24:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_PANJ);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 hedoshima));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_kof));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 25:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_SHASH);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 non));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_gof));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 26:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_HAFT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 mim));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_zol));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 27:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_HASHT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 ray));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_gof));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));

                        break;
                    case 28:

                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_BIST_NO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 dol));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_mim));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 29:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 alif));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_yah));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 30:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_YAK);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 lom));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_saye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 31:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_DO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 shin));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_shin));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 32:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_SAA);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 mim));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 33:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_CHAR);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 hain));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;

                    case 34:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_PANJ);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 swat));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 35:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_SHASH);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 paye));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 36:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_HAFT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 alif));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 37:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_HASHT);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 lom));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 38:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_SEE_NO);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 qof));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    case 39:
                        mAudioNumbers.managerOfNumberSound(getActivity(),position);
                        mTxtNumber.setText(KEY_CHIL);
                        mTxtNumber.setTextColor(getResources().getColor(R.color.                 faa));
                        mTxtNumber.setBackgroundColor(getResources().getColor(R.color.background_heye));
                        mTxtNumber.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
                        break;
                    default:
                }
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {
    }
}
