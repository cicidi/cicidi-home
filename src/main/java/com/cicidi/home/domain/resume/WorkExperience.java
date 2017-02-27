package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = Constants.workExperience)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.startName,
        Constants.endName, Constants.length, Constants.photo, Constants.icon, Constants.summary, Constants.role,
        Constants.bulletList})
public class WorkExperience extends Organization {
    private String summary;

    private String role;

    @XmlElementWrapper(name = Constants.bulletList)
    @XmlElement(name = Constants.bullet)

    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.bullet)
    private List<Bullet> bulletList;

    @XmlTransient
    private Profile profile;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entityId", nullable = false)
    @XmlTransient
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
