package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.*;

/**
 * Created by cicidi on 2/18/17.
 */


@XmlRootElement(name = Constants.education)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.startName, Constants.endName,
        Constants.length, Constants.photo, Constants.icon, Constants.major, Constants.degree})

public class Education extends Organization {

    private String major;

    private String degree;

    @XmlTransient
    private Profile profile;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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
