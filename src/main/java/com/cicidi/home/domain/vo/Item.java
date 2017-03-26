package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Bullet;
import com.cicidi.home.domain.resume.Education;
import com.cicidi.home.domain.resume.Organization;
import com.cicidi.home.domain.resume.WorkExperience;
import com.cicidi.home.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.linkedin.api.LinkedInDate;
import org.springframework.social.linkedin.api.Position;

import java.util.ArrayList;
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
            String endName = organization.getEndName();
            sb.append(endName + " . ");
            sb.append(organization.getLength() + " . ");
            // append location
            String city = organization.getAddress().getCity();
            String state = organization.getAddress().getState();
            String country = organization.getAddress().getCountry();
            if (city != null)
                sb.append(city);
            if (state != null)
                sb.append("," + state);
            if (country != null)
                sb.append("," + country);
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
        this.subTitle_2 = this.getSubtitle_2(position);
        this.bulletList = new ArrayList<>();
        bulletList.add(new Bullet(position.getSummary()));
    }

    public String getSubtitle_2(Position position) {
        StringBuffer sb = new StringBuffer();

        LinkedInDate start = position.getStartDate();

        // if check if end should be present.
        LinkedInDate end = position.getIsCurrent() ? DateUtil.getCurrent() : position.getEndDate();

        if (start != null && end != null) {
            sb.append(DateUtil.convertToString(start) + " - ");
            if (position.getIsCurrent()) {
                sb.append("Present" + " . ");
            } else {
                sb.append(DateUtil.convertToString(end) + " . ");
//            sb.append(DateUtil.calLength(position.getStartDate(), position.getEndDate()) + " . ");
            }
            sb.append(DateUtil.calLength(start, end) + " . ");
        }
        if (position.getCompany().getLocations() != null && position.getCompany().getLocations().size() > 0) {
            sb.append(position.getCompany().getLocations().get(0).getAddress().getCity());
        } else {
            Map extraData = position.getExtraData();
            Map location = (extraData != null) ? (Map) extraData.get("location") : null;
            String name = (location != null) ? (String) location.get("name") : null;
            if (!StringUtils.isEmpty(name)) {
                sb.append(name);
            }
        }
        return sb.toString();

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
