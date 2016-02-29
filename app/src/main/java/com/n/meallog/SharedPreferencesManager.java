package com.n.meallog;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by N on 2016-02-23.
 */
public class SharedPreferencesManager {
    private static final String APP_NAME = "Meal_Log";

    public static final String PREF_COOKIES = "Cookies";

    private static SharedPreferences preferences = null;

    private SharedPreferencesManager() {}

    public static void createSharedPreference(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        }
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }
}
