package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/2017.
 */
@Entity
@XmlType(propOrder = {Constants.title, Constants.personalEstimate, Constants.interests, Constants.whyCreateThisPage, Constants.contentImg})
public class Objective extends DatabaseEntity {
    private String title;

    private String personalEstimate;

    private String interests;

    private String contentImg;

    @Lob
    @Column(length = 100000)
    private String whyCreateThisPage;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    @JsonBackReference
    private Profile profile;

    public String getPersonalEstimate() {
        return personalEstimate;
    }

    public void setPersonalEstimate(String personalEstimate) {
        this.personalEstimate = personalEstimate;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWhyCreateThisPage() {
        return whyCreateThisPage;
    }

    public void setWhyCreateThisPage(String whyCreateThisPage) {
        this.whyCreateThisPage = whyCreateThisPage;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
