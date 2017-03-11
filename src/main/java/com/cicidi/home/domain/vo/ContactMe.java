package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Link;
import com.cicidi.home.domain.resume.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wchen00 on 3/8/17.
 */
public class ContactMe {
    private String profileImg;
    private String fullAddress;
    private String country;
    private List<Link> linkList;

    public ContactMe(Profile profile) {
        this.profileImg = profile.getFaceImg();
        this.fullAddress = profile.getContact().getAddress().getFullAddress();
        this.country = profile.getContact().getAddress().getCountry();
        linkList = new ArrayList<>();
        linkList.addAll(profile.getContact().getLinkList().stream().collect(Collectors.toList()));
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }


    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
}
