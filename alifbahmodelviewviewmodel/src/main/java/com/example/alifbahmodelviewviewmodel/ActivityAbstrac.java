package com.example.alifbahmodelviewviewmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityAbstrac extends AppCompatActivity {
    MyAbstrackClass mClass;
    MyAbstrackClass.MyInterface showName;

    public void setShowName(MyAbstrackClass.MyInterface showName) {
        this.showName = showName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstrac);

        final TextView txtView = findViewById(R.id.name);


        mClass = new MyAbstrackClass() {
            @Override
            public void getName(String name) {
                txtView.setText(name + mClass.getLastName());
            }
        };
        mClass.getName("name");

    }
}
