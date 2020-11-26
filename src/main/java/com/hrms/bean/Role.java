package com.hrms.bean;

public class Role {


    private String rolename;


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}