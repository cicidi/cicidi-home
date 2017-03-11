package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Item {
    private String imgSrc;
    private String title;
    private String subTitle;
    private List<Bullet> bulletList;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public Item(Organization organization) {
        this.imgSrc = organization.getPhoto();
        this.title = organization.getName();
        StringBuffer sb = new StringBuffer();
        sb.append(organization.getStartName() + " - ");
        sb.append(organization.getEndName() + " - ");
        sb.append(organization.getLength() + " - ");
        if (organization.getAddress().getCity() != null)
            sb.append(organization.getAddress().getCity() + " - ");
        sb.append(organization.getAddress().getState() + " - ");
        this.subTitle = sb.toString();
        this.bulletList = new ArrayList<>();
        if (organization instanceof WorkExperience) {
            bulletList.addAll(((WorkExperience) organization).getBulletList());
        }
    }

    public Item() {
//        this.imgSrc =
//        this.title =skillSet.getTechName();
//        this.subTitle=skillSet.get
    }
}
