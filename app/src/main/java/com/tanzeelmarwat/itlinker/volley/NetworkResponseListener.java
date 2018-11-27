package com.tanzeelmarwat.itlinker.volley;

import com.android.volley.VolleyError;

public interface NetworkResponseListener {
    public void onSuccess(String response);
    public void onVolleyError(VolleyError error);
    public void onServerError(String error);
}
