package com.hrms.bean;

import java.util.List;

public class User {
    private  int id;
    private  String job_number;
    private  String name;
    private String pwd;
    private String name_alias;
    private  int lock;


    public User(String job_number, String name, String pwd, String name_alias, int lock) {
        this.job_number = job_number;
        this.name = name;
        this.pwd = pwd;
        this.name_alias = name_alias;
        this.lock = lock;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }



    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName_alias() {
        return name_alias;
    }


    public void setName_alias(String name_alias) {
        this.name_alias = name_alias;
    }

    public User(String job_number, String name, String pwd, String name_alias) {
        this.job_number = job_number;
        this.name = name;
        this.pwd = pwd;
        this.name_alias = name_alias;
    }

    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", job_number='" + job_number + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name_alias='" + name_alias + '\'' +
                ", lock=" + lock +
                '}';
    }
}