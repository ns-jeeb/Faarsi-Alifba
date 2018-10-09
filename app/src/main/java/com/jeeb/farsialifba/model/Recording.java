package com.jeeb.farsialifba.model;

public class Recording {

    private String mUri, mFileName;
    private boolean isPlaying;

    public Recording(String uri, String fileName, boolean isPlaying){
        this.mFileName = fileName;
        this.isPlaying = isPlaying;
        mUri = uri;
    }//telus billing 8003613311

    public void setUri(String uri) {
        mUri = uri;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getUri() {
        return mUri;
    }

    public String getFileName() {
        return mFileName;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
