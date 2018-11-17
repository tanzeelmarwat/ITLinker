package com.tanzeelmarwat.itlinker.network;

public interface WebServiceResponseListener {
    void onSuccess(String call, Object response);
    void onFailure(String call, Throwable t);
}
