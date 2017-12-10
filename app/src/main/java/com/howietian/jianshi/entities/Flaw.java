package com.howietian.jianshi.entities;

/**
 * Created by HowieTian on 2017/11/11 0011.
 */

public class Flaw {
    private String title;
    private String rank;
    private String desc;
    private String influence;
    private Integer tag;

    public Flaw() {
    }

    public Flaw(String title, String rank, String desc, String influence, Integer tag) {
        this.title = title;
        this.rank = rank;
        this.desc = desc;
        this.influence = influence;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        this.influence = influence;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Flaw{" +
                "title='" + title + '\'' +
                ", rank='" + rank + '\'' +
                ", desc='" + desc + '\'' +
                ", influence='" + influence + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
