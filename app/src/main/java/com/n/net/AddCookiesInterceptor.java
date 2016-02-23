package com.n.net;

import android.util.Log;

import com.n.meallog.SharedPreferencesManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by N on 2016-02-23.
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) SharedPreferencesManager.getPreferences()
                .getStringSet(SharedPreferencesManager.PREF_COOKIES, new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header : " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
