package com.jeeb.farsialifba.model;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Jeeb on 11/15/2016.
 */

public class AlifBah extends ViewModel{
    private String mLetters;

    public String getLetters() {
        return mLetters;
    }

    public void setLetters(String letters) {
        mLetters = letters;
    }
}
