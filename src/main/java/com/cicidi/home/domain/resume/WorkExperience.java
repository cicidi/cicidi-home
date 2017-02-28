package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@XmlRootElement(name = Constants.workExperience)
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.startName,
        Constants.endName, Constants.length, Constants.photo, Constants.icon, Constants.summary, Constants.role,
        Constants.bulletList})
public class WorkExperience extends Organization {
    @XmlTransient
    private String summary;

    @XmlTransient
    private String role;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.workExperience, cascade = CascadeType.ALL)
    private List<Bullet> bulletList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    private Profile profile;

    @XmlElement
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @XmlElement
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlElementWrapper(name = Constants.bulletList)
    @XmlElement(name = Constants.bullet)
    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        for (Bullet bullet : bulletList) {
            bullet.setWorkExperience(this);
        }
        this.bulletList = bulletList;
    }

    @XmlTransient
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
