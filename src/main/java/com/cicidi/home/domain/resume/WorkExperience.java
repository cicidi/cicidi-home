package com.cicidi.home.domain.resume;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = "workExperience")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "address", "start", "end", "startName", "endName", "length", "photo", "icon", "summary", "role", "bulletList"})
public class WorkExperience extends Organization {
    private String summary;
    private String role;
    @XmlElementWrapper(name = "bulletList")
    @XmlElement(name = "bullet")
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
