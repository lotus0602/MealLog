package com.n.net;

<<<<<<< HEAD
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by N on 2016-02-04.
=======
import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Jnag on 2016-02-05.
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://dacapolife87.iptime.org/meallog/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

<<<<<<< HEAD
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new AddCookiesInterceptor());
        httpClient.addInterceptor(new ReceivedCookiesInterceptor());

        Retrofit retrofit = builder.client(httpClient.build()).build();
=======
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                    .header("Accept", "applicaton/json")
                    .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
        return retrofit.create(serviceClass);
    }
}
