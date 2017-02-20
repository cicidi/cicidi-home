package com.cicidi.home.domain.resume;

import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
public class Bullet {
    private String content;
    private List<Bullet> bulletList;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}
