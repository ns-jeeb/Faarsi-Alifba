package com.jeeb.farsialifba.media;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.animation.AnimationUtils;
import android.widget.MediaController;
import android.widget.Toast;

import com.jeeb.farsialifba.R;

import static com.jeeb.farsialifba.media.UtilAndKeys.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Jeeb on 1/27/2016.
 */
public class PlayAudioAlifBa {

    public PlayAudioAlifBa() {

    }

    public ArrayList<String> getAlifBa() {
        ArrayList<String> list = new ArrayList<>();
        list.add(KEY_ALIF);
        list.add(KEY_BAY);
        list.add(KEY_PAY);
        list.add(KEY_TAY);
        list.add(KEY_SAY);
        list.add(KEY_JIM);
        list.add(KEY_CHAY);
        list.add(KEY_HAY);
        list.add(KEY_KHAY);
        list.add(KEY_DOL);
        list.add(KEY_ZOL);
        list.add(KEY_RAY);
        list.add(KEY_ZAY);
        list.add(KEY_ZGHAY);
        list.add(KEY_SIN);
        list.add(KEY_SHIN);
        list.add(KEY_SWAT);
        list.add(KEY_ZOWAT);
        list.add(KEY_TOE);
        list.add(KEY_ZOE);
        list.add(KEY_HAIN);
        list.add(KEY_GHAIN);
        list.add(KEY_FAY);
        list.add(KEY_QOF);
        list.add(KEY_KOF);
        list.add(KEY_GOF);
        list.add(KEY_LOM);
        list.add(KEY_MIM);
        list.add(KEY_NON);
        list.add(KEY_WOW);
        list.add(KEY_HIDOSHIMA);
        list.add(KEY_HAMZA);
        list.add(KEY_YAH);
        return list;

    }
 public ArrayList<String> getNumbers() {
        ArrayList<String> list = new ArrayList<>();
        list.add(KEY_YAK       );
        list.add(KEY_DO        );
        list.add(KEY_SAA       );
        list.add(KEY_CHAR      );
        list.add(KEY_PANJ      );
        list.add(KEY_SHASH     );
        list.add(KEY_HAFT      );
        list.add(KEY_HASHT     );
        list.add(KEY_NO        );
        list.add(KEY_DAA       );
        list.add(KEY_YAZDA     );
        list.add(KEY_DOVAZDA   );
        list.add(KEY_SAZDA     );
        list.add(KEY_CHARDA    );
        list.add(KEY_PONZDA    );
        list.add(KEY_SHONZDA   );
        list.add(KEY_HABDA     );
        list.add(KEY_HAGZDA    );
        list.add(KEY_NOZDA     );
        list.add(KEY_BIST      );
        list.add(KEY_BIST_YAK  );
        list.add(KEY_BIST_DO   );
        list.add(KEY_BIST_SAA  );
        list.add(KEY_BIST_CHAR );
        list.add(KEY_BIST_PANJ );
        list.add(KEY_BIST_SHASH);
        list.add(KEY_BIST_HAFT );
        list.add(KEY_BIST_HASHT);
        list.add(KEY_BIST_NO   );
        list.add(KEY_SEE       );
        list.add(KEY_SEE_YAK   );
        list.add(KEY_SEE_DO    );
        list.add(KEY_SEE_SAA   );
        list.add(KEY_SEE_CHAR  );
        list.add(KEY_SEE_PANJ  );
        list.add(KEY_SEE_SHASH );
        list.add(KEY_SEE_HAFT  );
        list.add(KEY_SEE_HASHT );
        list.add(KEY_SEE_NO    );
        list.add(KEY_CHIL      );
        return list;

    }

    MediaPlayer mp = null;
    String[] gridColor ={
            "#008B8B",
            "#00FF00",
            "#48D1CC",
            "#556B2F",
            "#696969",
            "#6B8E23",
            "#8FBC8F",
            "#AFEEEE",
            "#B8860B",
            "#BDB76B",
            "#D8BFD8",
            "#DEB887",
            "#FFFF00",
            "#FFF0F5",
            "#EE82EE",
            "#DC143C",
            "#C0C0C0"
    };

    public MediaPlayer getMediaPlay(Context context, int i) throws IOException {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, i);
        mediaPlayer.start();
        return mediaPlayer;
    }

    public void managerOfSound(Context context, int position) {

        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (position >= 0) {

            switch (position) {
                case 0:
                    mp = MediaPlayer.create(context, R.raw.m_hoh);
//                    mp.start();
                    break;
                case 1:
                    mp = MediaPlayer.create(context, R.raw.m_alif);
//                    mp.start();
                    break;
                case 2:
                    mp = MediaPlayer.create(context, R.raw.m_bay);
//                    mp.start();
                    break;
                case 3:
                    mp = MediaPlayer.create(context, R.raw.m_pay);
//                    mp.start();
                    break;
                case 4:
                    mp = MediaPlayer.create(context, R.raw.m_tay);
//                    mp.start();
                    break;
                case 5:
                    mp = MediaPlayer.create(context, R.raw.m_say);
//                    mp.start();
                    break;
                case 6:
                    mp = MediaPlayer.create(context, R.raw.m_jim);
//                    mp.start();
                    break;
                case 7:
                    mp = MediaPlayer.create(context, R.raw.chaye);
//                    mp.start();
                    break;
                case 8:
                    mp = MediaPlayer.create(context, R.raw.m_hay);
//                    mp.start();
                    break;
                case 9:
                    mp = MediaPlayer.create(context, R.raw.m_khay);
//                    mp.start();
                    break;
                case 10:
                    mp = MediaPlayer.create(context, R.raw.m_dal);
//                    mp.start();
                    break;
                case 11:
                    mp = MediaPlayer.create(context, R.raw.m_zal);
//                    mp.start();
                    break;
                case 12:
                    mp = MediaPlayer.create(context, R.raw.m_ray);
//                    mp.start();
                    break;
                case 13:
                    mp = MediaPlayer.create(context, R.raw.m_zay);
//                    mp.start();
                    break;
                case 14:
                    mp = MediaPlayer.create(context, R.raw.m_zhay);
//                    mp.start();
                    break;
                case 15:
                    mp = MediaPlayer.create(context, R.raw.m_sin);
//                    mp.start();
                    break;
                case 16:
                    mp = MediaPlayer.create(context, R.raw.m_shin);
//                    mp.start();
                    break;
                case 17:
                    mp = MediaPlayer.create(context, R.raw.m_swat);
//                    mp.start();
                    break;
                case 18:
                    mp = MediaPlayer.create(context, R.raw.m_zwat);
//                    mp.start();
                    break;
                case 19:
                    mp = MediaPlayer.create(context, R.raw.m_toe);
//                    mp.start();
                    break;
                case 20:
                    mp = MediaPlayer.create(context, R.raw.m_zoe);
//                    mp.start();
                    break;
                case 21:
                    mp = MediaPlayer.create(context, R.raw.m_hain);
//                    mp.start();
                    break;
                case 22:
                    mp = MediaPlayer.create(context, R.raw.m_ghain);
//                    mp.start();
                    break;
                case 23:
                    mp = MediaPlayer.create(context, R.raw.m_fay);
//                    mp.start();
                    break;
                case 24:
                    mp = MediaPlayer.create(context, R.raw.m_qof);
//                    mp.start();
                    break;
                case 25:
                    mp = MediaPlayer.create(context, R.raw.m_kof);
//                    mp.start();
                    break;
                case 26:
                    mp = MediaPlayer.create(context, R.raw.m_gaf);
//                    mp.start();
                    break;
                case 27:
                    mp = MediaPlayer.create(context, R.raw.m_lom);
//                    mp.start();
                    break;
                case 28:
                    mp = MediaPlayer.create(context, R.raw.m_mim);

//                    mp.start();
                    break;
                case 29:
                    mp = MediaPlayer.create(context, R.raw.m_non);
//                    mp.start();
                    break;
                case 30:
                    mp = MediaPlayer.create(context, R.raw.m_wow);
//                    mp.start();
                    break;
                case 31:
                    mp = MediaPlayer.create(context, R.raw.m_hadochishma);
//                    mp.start();
                    break;
                case 32:
                    mp = MediaPlayer.create(context, R.raw.m_hamza);
//                    mp.start();
                    break;
                case 33:
                    mp = MediaPlayer.create(context, R.raw.m_yah);
//                    mp.start();
                    break;
                default:
            }
            mp.start();
        }
    }

    public void managerOfNumberSound(Context context, int position) {

        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (position >= 0) {

            switch (position) {
                case 0:
                    mp = MediaPlayer.create(context, R.raw.a1_yak);
//                    mp.start();
                    break;
                case 1:
                    mp = MediaPlayer.create(context, R.raw.a2_do);
//                    mp.start();
                    break;
                case 2:
                    mp = MediaPlayer.create(context, R.raw.a3_sa);
//                    mp.start();
                    break;
                case 3:
                    mp = MediaPlayer.create(context, R.raw.a4_char);
//                    mp.start();
                    break;
                case 4:
                    mp = MediaPlayer.create(context, R.raw.a5_panj);
//                    mp.start();
                    break;
                case 5:
                    mp = MediaPlayer.create(context, R.raw.a6_shash);
//                    mp.start();
                    break;
                case 6:
                    mp = MediaPlayer.create(context, R.raw.a7_haft);
//                    mp.start();
                    break;
                case 7:
                    mp = MediaPlayer.create(context, R.raw.a8_hasht);
//                    mp.start();
                    break;
                case 8:
                    mp = MediaPlayer.create(context, R.raw.a9_no);
//                    mp.start();
                    break;
                case 9:
                    mp = MediaPlayer.create(context, R.raw.a10_daa);
//                    mp.start();
                    break;
                case 10:
                    mp = MediaPlayer.create(context, R.raw.a11_yazda);
//                    mp.start();
                    break;
                case 11:
                    mp = MediaPlayer.create(context, R.raw.a12_dowazda);
//                    mp.start();
                    break;
                case 12:
                    mp = MediaPlayer.create(context, R.raw.a13_sazdaa);
//                    mp.start();
                    break;
                case 13:
                    mp = MediaPlayer.create(context, R.raw.a14_chardaa);
//                    mp.start();
                    break;
                case 14:
                    mp = MediaPlayer.create(context, R.raw.a15_ponzda);
//                    mp.start();
                    break;
                case 15:
                    mp = MediaPlayer.create(context, R.raw.a16_shanzda);
//                    mp.start();
                    break;
                case 16:
                    mp = MediaPlayer.create(context, R.raw.a17_habdaa);
//                    mp.start();
                    break;
                case 17:
                    mp = MediaPlayer.create(context, R.raw.a18_hghzdaa);
//                    mp.start();
                    break;
                case 18:
                    mp = MediaPlayer.create(context, R.raw.a19_nozdaa);
//                    mp.start();
                    break;
                case 19:
                    mp = MediaPlayer.create(context, R.raw.a20_bist);
//                    mp.start();
                    break;
                case 20:
                    mp = MediaPlayer.create(context, R.raw.a21_bistyak);
//                    mp.start();
                    break;
                case 21:
                    mp = MediaPlayer.create(context, R.raw.a22_bistdo);
//                    mp.start();
                    break;
                case 22:
                    mp = MediaPlayer.create(context, R.raw.a23_bistsaa);
//                    mp.start();
                    break;
                case 23:
                    mp = MediaPlayer.create(context, R.raw.a24_bistchar);
//                    mp.start();
                    break;
                case 24:
                    mp = MediaPlayer.create(context, R.raw.a25_bistpanj);
//                    mp.start();
                    break;
                case 25:
                    mp = MediaPlayer.create(context, R.raw.a26_bistshash);
//                    mp.start();
                    break;
                case 26:
                    mp = MediaPlayer.create(context, R.raw.a27_bisthaft);
//                    mp.start();
                    break;
                case 27:
                    mp = MediaPlayer.create(context, R.raw.a28_bisthasht);

//                    mp.start();
                    break;
                case 28:
                    mp = MediaPlayer.create(context, R.raw.a29_bitono);
//                    mp.start();
                    break;
                case 29:
                    mp = MediaPlayer.create(context, R.raw.a30_see);
//                    mp.start();
                    break;
                case 30:
                    mp = MediaPlayer.create(context, R.raw.a31_seeyak);
//                    mp.start();
                    break;
                case 31:
                    mp = MediaPlayer.create(context, R.raw.a32_seedo);
//                    mp.start();
                    break;
                case 32:
                    mp = MediaPlayer.create(context, R.raw.a33_seesay);
//                    mp.start();
                    break;
                case 33:
                    mp = MediaPlayer.create(context, R.raw.a34_seechar);
//                    mp.start();
                    break;
                case 34:
                    mp = MediaPlayer.create(context, R.raw.a35_seepanj);
//                    mp.start();
                    break;
                case 35:
                    mp = MediaPlayer.create(context, R.raw.a36_seeshash);
//                    mp.start();
                    break;
                case 36:
                    mp = MediaPlayer.create(context, R.raw.a37_seehaft);
//                    mp.start();
                    break;
                case 37:
                    mp = MediaPlayer.create(context, R.raw.a38_seehasht);
//                    mp.start();
                    break;
                case 38:
                    mp = MediaPlayer.create(context, R.raw.a39_seeno);
//                    mp.start();
                    break;
                case 39:
                    mp = MediaPlayer.create(context, R.raw.a40_chell);
//                    mp.start();
                    break;

                default:
            }
            mp.start();
        }
    }

}
