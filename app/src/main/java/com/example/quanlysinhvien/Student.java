package com.example.quanlysinhvien;

import java.util.Date;

public class Student {
    private int mssv;
    private String name;
    private String email;
    private String birthday;

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Student(int mssv, String name, String email, String birthday) {
        this.mssv = mssv;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    public Student(){

    }
}
