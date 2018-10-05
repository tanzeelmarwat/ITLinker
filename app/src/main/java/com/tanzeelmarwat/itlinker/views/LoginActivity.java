package com.tanzeelmarwat.itlinker.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tanzeelmarwat.itlinker.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(getResources().getString(R.string.login));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
        }
    }
}
