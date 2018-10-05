package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constants.DELAY_INTERVAL);
    }
}
