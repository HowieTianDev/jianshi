package com.howietian.jianshi.entities;

/**
 * Created by 83624 on 2017/5/11.
 */

public class User {
  private String name;
  private String idNum;
  private String phoneNum;
  private String pwd;
  private String email;
  private String wordplace;

  public User(String name, String idNum, String phoneNum, String pwd, String email,
      String wordplace) {
    this.name = name;
    this.idNum = idNum;
    this.phoneNum = phoneNum;
    this.pwd = pwd;
    this.email = email;
    this.wordplace = wordplace;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdNum() {
    return idNum;
  }

  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWordplace() {
    return wordplace;
  }

  public void setWordplace(String wordplace) {
    this.wordplace = wordplace;
  }

  @Override public String toString() {
    return "User{"
        + "name='"
        + name
        + '\''
        + ", idNum='"
        + idNum
        + '\''
        + ", phoneNum='"
        + phoneNum
        + '\''
        + ", pwd='"
        + pwd
        + '\''
        + ", email='"
        + email
        + '\''
        + ", wordplace='"
        + wordplace
        + '\''
        + '}';
  }
}
