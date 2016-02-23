package com.n.model;

<<<<<<< HEAD
/**
 * Created by N on 2016-02-04.
 */
public class User {
    private int index;
    private String nicname;
    private String email;
    private String date;

    public User() {
    }

    public int getIndex() { return this.index; }
    public String getNicname() { return this.nicname; }
    public String getEmail() { return this.email; }
    public String getDate() { return this.date; }
=======
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jnag on 2016-02-05.
 */
public class User {
    @SerializedName("userID")
    String id;

    @SerializedName("userPW")
    String pw;
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
}
