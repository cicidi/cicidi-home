package com.cicidi.home.domain.resume;

/**
 * Created by cicidi on 2/18/17.
 */
public class Education extends Organization {
    private String major;
    private String degree;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
