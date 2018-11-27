package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.models.LoginResponse;
import com.tanzeelmarwat.itlinker.models.User;
import com.tanzeelmarwat.itlinker.network.API;
import com.tanzeelmarwat.itlinker.network.WebServiceRequest;
import com.tanzeelmarwat.itlinker.utils.Constants;
import com.tanzeelmarwat.itlinker.utils.Utility;
import com.tanzeelmarwat.itlinker.volley.NetworkRequest;
import com.tanzeelmarwat.itlinker.volley.NetworkResponseListener;
import com.tanzeelmarwat.itlinker.volley.NetworkResponseParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "LoginActivity";

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
                loginUsingVolley();
                break;
        }
    }

    private void sendEmail() {
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, "tanzeelmarwat@gmail.com");
        email.putExtra(Intent.EXTRA_SUBJECT, "Test Email");
        email.putExtra(Intent.EXTRA_TEXT, "Email Body");
        startActivity(Intent.createChooser(email, "Choose an email client from..."));
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

    private void loginUsingVolley() {
        if (Utility.isConnectedToInternet(mContext)) {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            Utility.showProgress(mContext);
            NetworkRequest.getInstance().login(loginListener, email, Utility.encodePassword(password));
        } else {
            Toast.makeText(mContext, getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }

    public NetworkResponseListener loginListener = new NetworkResponseListener() {
        @Override
        public void onSuccess(String response) {
            Utility.dismissProgress();
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray userdata = obj.getJSONArray(API.DATA_RESULT);
                User user = NetworkResponseParser.getInstance().loginParser(userdata);
                if(user != null) {
                    User.setCurrentUser(user);
                    SharedPreferences sharedpreferences = getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("user_id", user.getId());
                    editor.putString("user_name", user.getName());
                    editor.commit();

                    SharedPreferences sharedPref = getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE);
                    String userId = sharedPref.getString("user_id", "");
                    String userName = sharedPref.getString("user_name", "");

                    Toast.makeText(mContext, "User ID : " + userId + " User Name : " + userName, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(mContext, "Email/Password invalid", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
            }
        }

        @Override
        public void onVolleyError(VolleyError error) {
            Utility.dismissProgress();
            Log.e(Constants.TAG, TAG + " Volley Error : " + error.getMessage());
        }

        @Override
        public void onServerError(String error) {
            Utility.dismissProgress();
            Log.e(Constants.TAG, TAG + " Server Error : " + error);
        }
    };
}
