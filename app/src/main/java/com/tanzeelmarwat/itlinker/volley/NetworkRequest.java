package com.tanzeelmarwat.itlinker.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tanzeelmarwat.itlinker.network.API;
import com.tanzeelmarwat.itlinker.utils.Constants;
import com.tanzeelmarwat.itlinker.utils.MyApplication;
import com.tanzeelmarwat.itlinker.utils.Utility;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class NetworkRequest {

    private String TAG = "NetworkRequest";
    private static NetworkRequest networkRequest;

    public static NetworkRequest getInstance() {
        if(networkRequest != null) {
            return networkRequest;
        } else {
            networkRequest = new NetworkRequest();
            return networkRequest;
        }
    }

    public void login(final NetworkResponseListener responseListener, final String email, final String password) {
        StringRequest strReq = new StringRequest(Request.Method.POST, API.BASE_URL + API.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(Constants.TAG, TAG + " API : " + API.BASE_URL + API.LOGIN + " : response: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    // Check for error
                    if (obj.optBoolean(API.CALL_FLAG)) {
                        responseListener.onSuccess(response);
                    } else {
                        responseListener.onServerError(response);
                    }
                } catch (Exception e) {
                    responseListener.onServerError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                Log.e(Constants.TAG, TAG + " : " + API.BASE_URL + API.LOGIN + " : params : " + params.toString());
                return params;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                API.VOLLEY_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding request to the request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }

    public void getFeeds(final NetworkResponseListener responseListener, final String type) {
        StringRequest strReq = new StringRequest(Request.Method.POST, API.BASE_URL + API.GET_FEEDS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(Constants.TAG, TAG + " API : " + API.BASE_URL + API.GET_FEEDS + " : response: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    // Check for error
                    if (obj.optBoolean(API.CALL_FLAG)) {
                        responseListener.onSuccess(response);
                    } else {
                        responseListener.onServerError(response);
                    }
                } catch (Exception e) {
                    responseListener.onServerError(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("type", type);
                Log.e(Constants.TAG, TAG + " : " + API.BASE_URL + API.GET_FEEDS + " : params : " + params.toString());
                return params;
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                API.VOLLEY_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding request to the request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }
}
