package com.cicidi.home.domain.linkedin;

import java.util.List;

/**
 * Created by wchen00 on 3/4/17.
 */
public class BasicProfile {
    private String id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private String formattedName;
    private String phoneticFirstName;
    private String formattedPhoneticName;
    private String headline;
    private Location location;
    private Industry industry;
    private String currentShare;
    private String numConnections;
    private String numConnectionsCapped;
    private String summary;
    private String specialties;
    private List<Position> positions;
    private String pictureUrl;
    private String pictureUrlOrginal;
    private String siteStandardProfileRequest;
    private String apiStandardProfileRequest;
    private String publicProfileUrl;
    private String emailAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getPhoneticFirstName() {
        return phoneticFirstName;
    }

    public void setPhoneticFirstName(String phoneticFirstName) {
        this.phoneticFirstName = phoneticFirstName;
    }

    public String getFormattedPhoneticName() {
        return formattedPhoneticName;
    }

    public void setFormattedPhoneticName(String formattedPhoneticName) {
        this.formattedPhoneticName = formattedPhoneticName;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getCurrentShare() {
        return currentShare;
    }

    public void setCurrentShare(String currentShare) {
        this.currentShare = currentShare;
    }

    public String getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(String numConnections) {
        this.numConnections = numConnections;
    }

    public String getNumConnectionsCapped() {
        return numConnectionsCapped;
    }

    public void setNumConnectionsCapped(String numConnectionsCapped) {
        this.numConnectionsCapped = numConnectionsCapped;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrlOrginal() {
        return pictureUrlOrginal;
    }

    public void setPictureUrlOrginal(String pictureUrlOrginal) {
        this.pictureUrlOrginal = pictureUrlOrginal;
    }

    public String getSiteStandardProfileRequest() {
        return siteStandardProfileRequest;
    }

    public void setSiteStandardProfileRequest(String siteStandardProfileRequest) {
        this.siteStandardProfileRequest = siteStandardProfileRequest;
    }

    public String getApiStandardProfileRequest() {
        return apiStandardProfileRequest;
    }

    public void setApiStandardProfileRequest(String apiStandardProfileRequest) {
        this.apiStandardProfileRequest = apiStandardProfileRequest;
    }

    public String getPublicProfileUrl() {
        return publicProfileUrl;
    }

    public void setPublicProfileUrl(String publicProfileUrl) {
        this.publicProfileUrl = publicProfileUrl;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
