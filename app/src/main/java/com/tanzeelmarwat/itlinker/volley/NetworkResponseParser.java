package com.tanzeelmarwat.itlinker.volley;

import android.util.Log;

import com.google.gson.Gson;
import com.tanzeelmarwat.itlinker.models.Feed;
import com.tanzeelmarwat.itlinker.models.User;
import com.tanzeelmarwat.itlinker.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

public class NetworkResponseParser {

    private String TAG = "NetworkResponseParser";
    private static NetworkResponseParser networkResponseParser;

    public static NetworkResponseParser getInstance() {
        if(networkResponseParser != null) {
            return networkResponseParser;
        } else {
            networkResponseParser = new NetworkResponseParser();
            return networkResponseParser;
        }
    }

    public User loginParser(JSONArray dataArray) {
        Gson gson = new Gson();
        User user = null;
        try {
            if(dataArray.length() > 0) {
                user = gson.fromJson(dataArray.get(0).toString(), User.class);
            }
        } catch (JSONException e) {
            Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
        } catch (Exception e) {
            Log.e(Constants.TAG, TAG + " Exception : " + e.getMessage());
        }

        return user;
    }

    public HashMap<Integer, User> getListParser(JSONArray dataArray) {
        Gson gson = new Gson();
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        try {
            for (int i = 0; i < dataArray.length(); i++) {
                User user = gson.fromJson(dataArray.get(i).toString(), User.class);
                users.put(i, user);
            }
        } catch (JSONException e) {
            Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
        } catch (Exception e) {
            Log.e(Constants.TAG, TAG + " Exception : " + e.getMessage());
        }

        return users;
    }

    public HashMap<Integer, User> getUserProfileParser(JSONArray dataArray) {
        Gson gson = new Gson();
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        try {
            for (int i = 0; i < dataArray.length(); i++) {
                User user = gson.fromJson(dataArray.get(i).toString(), User.class);
                users.put(i, user);
            }
        } catch (JSONException e) {
            Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
        } catch (Exception e) {
            Log.e(Constants.TAG, TAG + " Exception : " + e.getMessage());
        }

        return users;
    }

    public HashMap<Integer, Feed> getFeedsParser(JSONArray dataArray) {
        Gson gson = new Gson();
        HashMap<Integer, Feed> feeds = new HashMap<Integer, Feed>();
        try {
            for (int i = 0; i < dataArray.length(); i++) {
                Feed feed = gson.fromJson(dataArray.get(i).toString(), Feed.class);
                feeds.put(i, feed);
            }
        } catch (JSONException e) {
            Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
        } catch (Exception e) {
            Log.e(Constants.TAG, TAG + " Exception : " + e.getMessage());
        }

        return feeds;
    }
}
