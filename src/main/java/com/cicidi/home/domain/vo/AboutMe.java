package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Link;
import com.cicidi.home.domain.resume.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wchen00 on 3/8/17.
 */
public class AboutMe {

    private String header;
    private String summary;
    private String img;
    private List<Link> linkList;

    public AboutMe(Profile profile) {
        this.header = profile.getObjective().getPersonalEstimate();
        this.summary = profile.getObjective().getWhyCreateThisPage();
        this.img = profile.getObjective().getContentImg();
        this.linkList = new ArrayList<>();

        linkList.addAll(profile.getContact().getLinkList().stream().collect(Collectors.toList()));
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
