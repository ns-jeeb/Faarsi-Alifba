package com.jeeb.farsialifba.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MyView extends TextView {

    private boolean touchOn;
    private boolean mDownTouch = false;
    private OnToggledListener toggledListener;
    private int idX = 0;
    private int idY = 0;

    public interface OnToggledListener{
        void onToggled(MyView myView, boolean touchOn);
    }

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, int idX, int idY) {
        super(context);
        this.idX = idX;
        this.idY = idY;
        init();

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init() {
        touchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        if (touchOn){
            canvas.drawColor(Color.RED);
            paint.setTextSize(20);
            paint.setColor(Color.BLACK);
            canvas.drawText("No Text 2", 20, 25, paint);
        }else {

            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(20);
            canvas.drawText("Some Text 2", 20, 25, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                touchOn = !touchOn;
                invalidate();

                if(toggledListener != null){
                    toggledListener.onToggled(this, touchOn);
                }

                mDownTouch = true;
                return true;

            case MotionEvent.ACTION_UP:
                if (mDownTouch) {
                    mDownTouch = false;
                    performClick();
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void setOnToggledListener(OnToggledListener listener){
        toggledListener = listener;
    }

    public int getIdX(){
        return idX;
    }

    public int getIdY(){
        return idY;
    }
}
