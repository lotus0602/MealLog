package com.n.net;

import com.n.model.MealInfo;
import com.n.model.MealList;
import com.n.model.RequestResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by N on 2016-02-18.
 */
public interface MealInfoService {
    @POST("meal/userMealList.mobile")
    Call<MealList> getMyMealList();

    @POST("meal/shareMealList.mobile")
    Call<MealList> getSharedMealList();

    @FormUrlEncoded
    @POST("meal/userOneMealList.mobile")
    Call<MealInfo> getMealDetails(@Field("IDX") int i);

//    @Multipart
    @POST("meal/mealUploadList.mobile")
    Call<String> uploadMeal(@Body MultipartBody requestBody);

//    Call<String> uploadMeal(@Part("image") RequestBody image,
//                            @Part("meal") MealInfo info);
    @FormUrlEncoded
    @POST("meal/deleteUserMeal.mobile")
    Call<RequestResult> deleteMeal(@Field("IDX") int index);
}
