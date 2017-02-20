package com.cicidi.home.domain.resume;

import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
public class WorkExperience extends Organization {
    private String summary;
    private String role;
    private List<Bullet> bulletList;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}
