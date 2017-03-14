package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Bullet;
import com.cicidi.home.domain.resume.Education;
import com.cicidi.home.domain.resume.Organization;
import com.cicidi.home.domain.resume.WorkExperience;
import com.cicidi.home.util.DateUtil;
import org.springframework.social.linkedin.api.LinkedInDate;
import org.springframework.social.linkedin.api.Position;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
            sb.append(organization.getEndName() + " . ");
            sb.append(organization.getLength() + " . ");
            if (organization.getAddress().getCity() != null)
                sb.append(organization.getAddress().getCity() + " , ");
            sb.append(organization.getAddress().getState() + " , ");
            sb.append(organization.getAddress().getCountry());
            this.subTitle_2 = sb.toString();
            this.bulletList = new ArrayList<>();
            if (((WorkExperience) organization).getBulletList() != null)
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

    public Item(Position position) {
        this.title = position.getTitle();
        this.subTitle = position.getCompany().getName();
        StringBuffer sb = new StringBuffer();
        sb.append(DateUtil.convertToString(position.getStartDate()) + " - ");
        if (position.getStartDate() != null && position.getEndDate() == null) {
            sb.append("Present" + " . ");
            Calendar c = Calendar.getInstance();
            sb.append(DateUtil.calLength(position.getStartDate(), new LinkedInDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))) + " . ");
        } else {
            sb.append(DateUtil.convertToString(position.getEndDate()) + " . ");
            sb.append(DateUtil.calLength(position.getStartDate(), position.getEndDate()) + " . ");
        }
        if (position.getCompany().getLocations() != null && position.getCompany().getLocations().size() > 0) {
            sb.append(position.getCompany().getLocations().get(0).getAddress().getCity());
        } else {
            sb.append(((Map) position.getExtraData().get("location")).get("name"));
        }
        this.subTitle_2 = sb.toString();
        this.bulletList = new ArrayList<>();
        bulletList.add(new Bullet(position.getSummary()));
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
