package com.cicidi.home.domain.resume;

/**
 * Created by cicidi on 2/18/2017.
 */
public class Objective {
    private String title;
    private String personalEstimate;
    private String interests;
    private String whyCreateThisPage;
    private String img;

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

    public String getWhyCreateThisPage() {
        return whyCreateThisPage;
    }

    public void setWhyCreateThisPage(String whyCreateThisPage) {
        this.whyCreateThisPage = whyCreateThisPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
