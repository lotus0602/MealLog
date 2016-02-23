package com.n.model;

/**
 * Created by N on 2016-02-17.
 */
public class MealInfo {
    private int idx;
    private String username;
    private String name;
    private String category;
    private String content;
    private String eatdate;
    private String wheneat;
    private String picpath;
    private boolean share;

    public MealInfo() {}

    public MealInfo(String name, String eatdate, String wheneat,
                    String category, String content, boolean isShare) {
        this.name = name;
        this.eatdate = eatdate;
        this.wheneat = wheneat;
        this.category = category;
        this.content = content;
        this.share = isShare;
    }

    public int getIdx() { return this.idx; }
    public String getUsername() { return this.username; }
    public String getName() { return this.name; }
    public String getCategory() { return this.category; }
    public String getContent() { return this.content; }
    public String getEatdate() { return this.eatdate; }
    public String getWheneat() { return this.wheneat; }
    public String getPicpath() { return this.picpath; }
    public boolean isShare() { return this.share; }
}
