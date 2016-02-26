package com.n.net;

import com.n.model.RequestResult;

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
    Call<RequestResult> basicLogin(@FieldMap Map<String, String> user);

    @FormUrlEncoded
    @POST("joinMember.mobile")
    Call<RequestResult> basicJoin(@FieldMap Map<String, String> userJoin);
}
