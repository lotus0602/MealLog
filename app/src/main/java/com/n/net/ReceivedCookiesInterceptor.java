package com.n.net;

import com.n.meallog.SharedPreferencesManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by N on 2016-02-23.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferencesManager.getPreferences().edit()
                    .putStringSet(SharedPreferencesManager.PREF_COOKIES, cookies)
                    .apply();
        }

        return originalResponse;
    }
}
