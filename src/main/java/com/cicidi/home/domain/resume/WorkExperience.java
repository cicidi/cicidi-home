package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;
import com.cicidi.home.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.social.linkedin.api.Position;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@DiscriminatorValue("workExperience")
@XmlRootElement(name = Constants.workExperience)
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.present, Constants.startName,
        Constants.endName, Constants.length, Constants.photo, Constants.icon, Constants.summary, Constants.role,
        Constants.bulletList})
public class WorkExperience extends Organization implements Comparable<WorkExperience> {
    @XmlTransient
    private String summary;

    @XmlTransient
    private String role;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.workExperience, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bullet> bulletList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    @JsonBackReference(value = "profile_workExperience")
    private Profile profile;

    public WorkExperience() {
        super();
    }

    public WorkExperience(Position position) {
        super();
        this.setSummary(position.getSummary());
        this.setRole(position.getTitle());
        this.setName(position.getCompany().getName());
        this.setIcon(position.getCompany().getLogoUrl());
//        this.setAddress(new Address(position.getCompany()));
        this.setAddress(new Address(position));
        this.present = position.getIsCurrent();
//        position.get
        if (position.getStartDate() != null) {
            this.setStart(DateUtil.convert(position.getStartDate()));
        }
        if (position.getEndDate() != null) {
            this.setStart(DateUtil.convert(position.getEndDate()));
        }
    }

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


    @Override
    public int compareTo(WorkExperience o) {
        return 0;
    }
    @XmlTransient
    public boolean isTop500() {
        return true;
    }
}
