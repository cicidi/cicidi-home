package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/2017.
 */
@XmlType(propOrder = {Constants.title, Constants.personalEstimate, Constants.interests, Constants.whyCreateThisPage, Constants.contentImg})
public class Objective extends DatabaseEntity {
    private String title;
    private String personalEstimate;
    private String interests;
    private String contentImg;
    private String whyCreateThisPage;


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
}
