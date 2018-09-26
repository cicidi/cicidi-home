package com.cicidi.home.domain.resume;

import com.cicidi.home.util.Constants;
import com.cicidi.home.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;

/**
 * Created by cicidi on 2/18/17.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(Constants.education)
@XmlRootElement(name = Constants.education)
@XmlType(propOrder = {Constants.name, Constants.address, Constants.start, Constants.end, Constants.present, Constants.startName, Constants.endName,
        Constants.length, Constants.photo, Constants.icon, Constants.major, Constants.degree})

public class Education extends Organization {

    @XmlTransient
    private String major;

    @XmlTransient
    private String degree;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    @JsonBackReference(value = "profile_education")
    private Profile profile;

    public Education() {
        super();
    }

    public Education(org.springframework.social.linkedin.api.Education education) {
        super();
        this.setName(education.getSchoolName());
        this.setDegree(education.getDegree());
        Calendar calendar = Calendar.getInstance();
        if (education.getStartDate() != null) {
            this.setStart(DateUtil.convert(education.getStartDate()));
        }
        if (education.getEndDate() != null) {
            this.setStart(DateUtil.convert(education.getEndDate()));
        }


    }

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

    //    @XmlTransient
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @XmlTransient
    public boolean isTop50() {
        return true;
    }
}
