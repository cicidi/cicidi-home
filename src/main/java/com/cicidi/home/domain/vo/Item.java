package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Bullet;
import com.cicidi.home.domain.resume.Education;
import com.cicidi.home.domain.resume.Organization;
import com.cicidi.home.domain.resume.WorkExperience;
import com.cicidi.home.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Item {
    private String imgSrc;
    private String title;
    private String subTitle;
    private String subTitle_2;
    private List<Bullet> bulletList;

    public Item(Organization organization) {
        this.imgSrc = organization.getPhoto();


        if (organization instanceof WorkExperience) {
            this.title = ((WorkExperience) organization).getRole();
            this.subTitle = organization.getName();
            StringBuffer sb = new StringBuffer();
            sb.append(organization.getStartName() + " - ");
            sb.append(organization.getEndName() + " &#8226 ");
            sb.append(organization.getLength() + " &#8226 ");
            if (organization.getAddress().getCity() != null)
                sb.append(organization.getAddress().getCity() + " , ");
            sb.append(organization.getAddress().getState() + " , ");
            sb.append(organization.getAddress().getCountry());
            this.subTitle_2 = sb.toString();
            this.bulletList = new ArrayList<>();
            bulletList.addAll(((WorkExperience) organization).getBulletList());
        } else {
            this.title = organization.getName();
            StringBuffer sb = new StringBuffer();
            sb.append(((Education) organization).getDegree());
            if (((Education) organization).getMajor() != null) {
                sb.append(",");
                sb.append(((Education) organization).getMajor());
            }
            this.subTitle = sb.toString();
            if (organization.getStart() != null && organization.getEnd() != null) {
                sb = new StringBuffer();
                sb.append(DateUtil.getYear(organization.getStart()) + " - ");
                sb.append(DateUtil.getYear(organization.getEnd()));
                this.subTitle_2 = sb.toString();
            }

        }
    }

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


    public String getSubTitle_2() {
        return subTitle_2;
    }

    public void setSubTitle_2(String subTitle_2) {
        this.subTitle_2 = subTitle_2;
    }
}
