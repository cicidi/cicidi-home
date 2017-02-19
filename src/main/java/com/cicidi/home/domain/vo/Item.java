package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Bullet;

import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Item {
    private String src;
    private String header;
    private String paragraph;
    private List<Bullet> bulletList;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}
