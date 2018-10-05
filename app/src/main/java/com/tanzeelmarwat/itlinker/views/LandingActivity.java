package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tanzeelmarwat.itlinker.R;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mContext = this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.btn_signup: {
                Intent intent = new Intent(mContext, SignupActivity.class);
                startActivity(intent);
            }
                break;
        }
    }
}
