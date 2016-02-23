package com.n.net;

<<<<<<< HEAD
import com.n.model.JoinResult;
import com.n.model.LoginResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by N on 2016-02-04.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST("meallogin.mobile")
    Call<LoginResult> basicLogin(@FieldMap Map<String, String> user);

    @FormUrlEncoded
    @POST("joinMember.mobile")
    Call<JoinResult> basicJoin(@FieldMap Map<String, String> userJoin);
=======
import com.n.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Jnag on 2016-02-05.
 */
public interface LoginService {
    @POST("meallog.do")
    Call<User> basicLogin();
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
}
