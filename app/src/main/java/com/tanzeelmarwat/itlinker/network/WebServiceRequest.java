package com.tanzeelmarwat.itlinker.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tanzeelmarwat.itlinker.models.LoginResponse;
import com.tanzeelmarwat.itlinker.models.SignupResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceRequest {

    private static WebServiceRequest instance;
    private API api;

    private WebServiceRequest() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(httpClient.build())
                .build();

        api = retrofit.create(API.class);
    }

    public static WebServiceRequest getInstance() {
        if(instance == null) {
            instance = new WebServiceRequest();
        }

        return instance;
    }

    public void login(String email, String password, Callback<LoginResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);
        Call<LoginResponse> call = api.login(parameters);
        call.enqueue(callback);

    }

    public void signup(String userType, String name, String email, String password, Callback<SignupResponse> callback) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userType", userType);
        parameters.put("name", name);
        parameters.put("email", email);
        parameters.put("password", password);
        Call<SignupResponse> call = api.signup(parameters);
        call.enqueue(callback);

    }
}
