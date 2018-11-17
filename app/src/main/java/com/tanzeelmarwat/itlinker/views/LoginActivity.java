package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.models.LoginResponse;
import com.tanzeelmarwat.itlinker.network.WebServiceRequest;
import com.tanzeelmarwat.itlinker.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvTitle;
    EditText etEmail;
    EditText etPassword;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        tvTitle = findViewById(R.id.tv_title);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvTitle.setText(getResources().getString(R.string.login));

        if(Constants.IS_DEBUG_MODE) {
            etEmail.setText(Constants.TEST_EMAIL);
            etPassword.setText(Constants.TEST_PASSWORD);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login(etEmail.getText().toString(), etPassword.getText().toString());
                break;
        }
    }

    private void login(String email, String password) {
        WebServiceRequest.getInstance().login(email, password, loginResponseListener);
    }

    private Callback<LoginResponse> loginResponseListener = new Callback<LoginResponse>() {

        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            Toast.makeText(mContext, "LoginResponse : " + response.body().toString(), Toast.LENGTH_SHORT);
            if (response.body().getStatus()==1) {
                // User has logged In Successfully
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(mContext, "LoginResponse : " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            Toast.makeText(mContext, "LoginResponse : " + t.getMessage(), Toast.LENGTH_SHORT);
        }
    };
}
