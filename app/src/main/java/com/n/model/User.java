package com.n.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jnag on 2016-02-05.
 */
public class User {
    @SerializedName("userID")
    String id;

    @SerializedName("userPW")
    String pw;
}
