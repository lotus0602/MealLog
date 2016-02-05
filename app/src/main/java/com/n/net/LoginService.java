package com.n.net;

import com.n.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Jnag on 2016-02-05.
 */
public interface LoginService {
    @POST("meallog.do")
    Call<User> basicLogin();
}
