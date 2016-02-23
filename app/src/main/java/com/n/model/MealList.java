package com.n.model;

import java.util.ArrayList;

/**
 * Created by N on 2016-02-23.
 */
public class MealList {
    private ArrayList<MealInfo> result;

    public MealList() {
        result = new ArrayList<>();
    }

    public ArrayList<MealInfo> getInfos() { return this.result; }
}
