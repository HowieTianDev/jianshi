package com.howietian.jianshi.entities;

/**
 * Created by HowieTian on 2017/10/28 0028.
 */

public class Unit {
    private Integer id;
    private String name;
    private String group;
    private String brief;
    private String pName;
    private String position;
    private String title;
    private String education;
    private String phoneNum;

    public Unit(){

    }

    public Unit(String name, String group, String brief, String pName, String position, String title, String education, String phoneNum) {
        this.name = name;
        this.group = group;
        this.brief = brief;
        this.pName = pName;
        this.position = position;
        this.title = title;
        this.education = education;
        this.phoneNum = phoneNum;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", brief='" + brief + '\'' +
                ", pName='" + pName + '\'' +
                ", position='" + position + '\'' +
                ", title='" + title + '\'' +
                ", education='" + education + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
