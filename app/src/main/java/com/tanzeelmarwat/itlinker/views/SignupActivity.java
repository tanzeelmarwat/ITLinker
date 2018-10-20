package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.utils.Constants;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvTitle;
    Spinner spUserType;
    EditText etName, etEmail, etPassword, etConfirmPassword;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mContext = this;
        spUserType = findViewById(R.id.sp_user_type);
        tvTitle = findViewById(R.id.tv_title);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        tvTitle.setText(getResources().getString(R.string.signup));

        if(Constants.IS_DEBUG_MODE) {
            spUserType.setSelection(1);
            etName.setText(Constants.TEST_NAME);
            etEmail.setText(Constants.TEST_EMAIL);
            etPassword.setText(Constants.TEST_PASSWORD);
            etConfirmPassword.setText(Constants.TEST_CONFIRM_PASSWORD);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                if(validateFields(name, email, password, confirmPassword)) {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    public boolean validateFields(String name, String email, String password, String confirmPassword) {
        boolean isValidated = true;
        String alertMessage = "";
        if(spUserType.getSelectedItemPosition() == 0) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.invalid_user_type);
        } else if(TextUtils.isEmpty(name)) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.empty_name);
        } else if(TextUtils.isEmpty(email)) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.empty_email);
        } else if(TextUtils.isEmpty(password)) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.empty_password);
        } else if(TextUtils.isEmpty(confirmPassword)) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.empty_confirm_password);
        } else if(!password.equals(confirmPassword)) {
            isValidated = false;
            alertMessage = getResources().getString(R.string.password_miss_match);
        }

        if(isValidated) {
            return isValidated;
        } else {
            Toast.makeText(mContext, alertMessage, Toast.LENGTH_LONG).show();
            return isValidated;
        }
    }
}
