package com.n.meallog;

/**
 * Created by N on 2016-01-26.
 */
public class StatisticsData {
    private String image;
    private String foodName;
    private int num;

    public StatisticsData(String name, int n) {
        foodName = name;
        num = n;
    }

    public String getImage() {
        return image;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getNum() {
        return num;
    }
}
