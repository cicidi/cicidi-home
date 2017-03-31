package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Education;
import com.cicidi.home.domain.resume.Link;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.resume.WorkExperience;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wchen00 on 3/8/17.
 */
public class AboutMe {

    private String header;
    private String subtitle;
    private String paragraph;
    private String img;
    private List<Link> linkList;
    private List<Item> itemList;


    private List<Position> positionList;

    public AboutMe(Profile profile) {
        if (profile.getObjective() != null) {
            this.header = profile.getObjective().getPersonalEstimate();
            this.subtitle = profile.getObjective().getInterests();
            this.paragraph = profile.getObjective().getWhyCreateThisPage();
            this.img = profile.getObjective().getContentImg();
        }
        if (profile.getContact() != null && profile.getContact().getLinkList() != null) {
            this.linkList = new ArrayList<>();
            linkList.addAll(profile.getContact().getLinkList().stream().collect(Collectors.toList()));
        }
        List<WorkExperience> workExperienceList = profile.getWorkExperienceList();

        if (workExperienceList != null) {
            this.itemList = this.itemList == null ? new ArrayList<>() : this.itemList;
            this.itemList.addAll(workExperienceList.stream().map(Item::new).collect(Collectors.toList()));
        }

        List<Education> educationList = profile.getEducationList();
        if (educationList != null) {
            this.itemList = this.itemList == null ? new ArrayList<>() : this.itemList;
            this.itemList.addAll(educationList.stream().map(Item::new).collect(Collectors.toList()));
        }
    }


    public AboutMe(LinkedInProfileFull linkedInProfileFull) {
        this.positionList = this.positionList == null ? new ArrayList<>() : this.positionList;
        if (linkedInProfileFull.getPositions() != null) {
            this.itemList = new ArrayList<>();
            for (org.springframework.social.linkedin.api.Position position : linkedInProfileFull.getPositions()) {
                Item item = new Item(position);
                itemList.add(item);
                this.positionList.add(position);
            }
        }
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void addPositions(List<Position> positions) {
        this.itemList = this.itemList == null ? new ArrayList<>() : this.itemList;
        this.positionList = this.positionList == null ? new ArrayList<>() : this.positionList;
        itemList.addAll(positions.stream().map(Item::new).collect(Collectors.toList()));
        this.positionList.addAll(positions);
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

}
