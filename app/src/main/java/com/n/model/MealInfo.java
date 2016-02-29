package com.n.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by N on 2016-02-17.
 */
public class MealInfo implements Serializable{
    @SerializedName("idx")
    private int index;
    @SerializedName("username")
    private String id;
    @SerializedName("name")
    private String title;
    @SerializedName("category")
    private String foodCategory;
    @SerializedName("content")
    private String contents;
    @SerializedName("eatdate")
    private String date;
    @SerializedName("wheneat")
    private String mealTime;
    @SerializedName("picpath")
    private String imagePath;
    @SerializedName("share")
    private boolean isShare;

    public MealInfo() {}

    public MealInfo(String name, String eatdate, String wheneat,
                    String category, String content, boolean isShare) {
        this.title = name;
        this.date = eatdate;
        this.mealTime = wheneat;
        this.foodCategory = category;
        this.contents = content;
        this.isShare = isShare;
    }

    public int getIndex() { return this.index; }
    public String getId() { return this.id; }
    public String getTitle() { return this.title; }
    public String getFoodCategory() { return this.foodCategory; }
    public String getContents() { return this.contents; }
    public String getDate() { return this.date; }
    public String getMealTime() { return this.mealTime; }
    public String getImagePath() { return this.imagePath; }
    public boolean isShare() { return this.isShare; }
}
