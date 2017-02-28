package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/17.
 */

@Entity
@XmlRootElement(name = Constants.education)
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.startName, Constants.endName,
        Constants.length, Constants.photo, Constants.icon, Constants.major, Constants.degree})

public class Education extends Organization {

    @XmlTransient
    private String major;

    @XmlTransient
    private String degree;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    private Profile profile;

    @XmlElement
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @XmlElement
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @XmlTransient
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
