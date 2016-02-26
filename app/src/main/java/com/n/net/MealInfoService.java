package com.n.net;

import com.n.model.MealInfo;
import com.n.model.MealList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by N on 2016-02-18.
 */
public interface MealInfoService {
    @POST("meal/userMealList.mobile")
    Call<MealList> getMyMealList();

    @POST("meal/shareMealList.mobile")
    Call<MealList> getSharedMealList();

//    @Multipart
    @POST("meal/mealUploadList.mobile")
    Call<String> uploadMeal( @Body MultipartBody requestBody);

//    Call<String> uploadMeal(@Part("image") RequestBody image,
//                            @Part("meal") MealInfo info);
}
