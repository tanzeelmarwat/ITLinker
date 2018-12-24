package com.tanzeelmarwat.itlinker.network;


import com.tanzeelmarwat.itlinker.models.LoginResponse;
import com.tanzeelmarwat.itlinker.models.SignupResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    public  static String BASE_URL = "http://www.marwatitsolutions.com/linker/";
    public  static String IMAGE_URL = "http://www.marwatitsolutions.com/linker/images/feeds/";
    public static String CALL_FLAG = "callFlag";
    public static String CALL_Message = "callMessage";
    public static String DATA_RESULT = "dataResult";
    public static int VOLLEY_TIMEOUT = 15000; // 15 seconds (Unit is milli second)
    public static String LOGIN = "login.php";
    public static String GET_FEEDS = "get_feeds.php";
//    public  static String IMAGE_URL = "http://www.marwatitsolutions.com/linker/images/feeds/";
//    public  static String API_KEY = "FJ4z@artkO$aDGMhkTYz";
//    public static int VOLLEY_TIMEOUT = 15000; // 15 seconds (Unit is milli second)
//    public static String CALL_FLAG = "callFlag";
//    public static String CALL_Message = "callMessage";
//    public static String DATA_RESULT = "dataResult";
//
//    public static String GET_ORGANIZATIONS = "get_organizations.php";
//    public static String GET_USER_TYPES = "get_user_types.php";
//    public static String SIGNUP = "signup.php";
//    public static String SIGNUP_UPDATE = "signup_update.php";
//    public static String LOGIN = "login.php";
//    public static String FORGOT_PASSWORD = "forgot_password.php";
//    public static String GET_USERS = "get_users.php";
//    public static String GET_USER_PROFILE = "get_user_profile.php";
//    public static String POST_FEED = "post_feed.php";
//    public static String GET_FEEDS = "get_feeds.php";

    @FormUrlEncoded
    @POST("/linker/login")
    Call<LoginResponse> login(@FieldMap Map<String, Object> parameters);

    @GET("login.php")
    Call<LoginResponse> getLogin(@Query("email") String email, @Query("password") String password);

    @FormUrlEncoded
    @POST("/linker/signup.php")
    Call<SignupResponse> signup(@FieldMap Map<String, Object> parameters);

}
