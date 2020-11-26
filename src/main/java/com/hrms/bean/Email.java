package com.hrms.bean;


public class Email {
  private String account;
  private  String name;
  private  String groupEmail;

    public Email() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupEmail() {
        return groupEmail;
    }

    public void setGroupEmail(String groupEmail) {
        this.groupEmail = groupEmail;
    }

    public Email(String account) {
    this.account = account;
}

    @Override
    public String toString() {
        return "Email{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", groupEmail='" + groupEmail + '\'' +
                '}';
    }

    public Email(String account, String name, String groupEmail) {
        this.account = account;
        this.name = name;
        this.groupEmail = groupEmail;
    }
}
