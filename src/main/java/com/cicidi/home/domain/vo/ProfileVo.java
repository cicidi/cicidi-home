package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Profile;
import org.springframework.social.linkedin.api.LinkedInProfileFull;

import java.util.List;

/**
 * Created by wchen00 on 3/8/17.
 */
public class ProfileVo {
    private String firstName;
    private String lastName;
    private Album album;
    private AboutMe aboutMe;
    private Feature feature;
    private Objective objective;
    private List<WebLog> webLogList;
    private ContactMe contactMe;
    private Places places;


    public ProfileVo(Profile profile) throws Exception {
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.album = new Album(profile);
        this.aboutMe = new AboutMe(profile);
        this.contactMe = new ContactMe(profile);
    }

    public ProfileVo(LinkedInProfileFull linkedInProfileFull) {
//        Profile profile = new Profile(linkedInProfileFull);
        this.firstName = linkedInProfileFull.getFirstName();
        this.lastName = linkedInProfileFull.getLastName();
        this.album = new Album(linkedInProfileFull);
        this.aboutMe = new AboutMe(linkedInProfileFull);
        this.contactMe = new ContactMe(linkedInProfileFull);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public AboutMe getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(AboutMe aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public List<WebLog> getWebLogList() {
        return webLogList;
    }

    public void setWebLogList(List<WebLog> webLogList) {
        this.webLogList = webLogList;
    }

    public ContactMe getContactMe() {
        return contactMe;
    }

    public void setContactMe(ContactMe contactMe) {
        this.contactMe = contactMe;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }
}
