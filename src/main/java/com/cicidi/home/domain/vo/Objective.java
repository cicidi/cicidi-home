package com.cicidi.home.domain.vo;

import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Objective {

    private String title_1;
    private String title_2;
    private String paragraph_1;
    private String paragraph_2;
    private String content;
    private String imgSrc;
    private List<Link> links;

    public String getTitle_1() {
        return title_1;
    }

    public void setTitle_1(String title_1) {
        this.title_1 = title_1;
    }

    public String getTitle_2() {
        return title_2;
    }

    public void setTitle_2(String title_2) {
        this.title_2 = title_2;
    }

    public String getParagraph_1() {
        return paragraph_1;
    }

    public void setParagraph_1(String paragraph_1) {
        this.paragraph_1 = paragraph_1;
    }

    public String getParagraph_2() {
        return paragraph_2;
    }

    public void setParagraph_2(String paragraph_2) {
        this.paragraph_2 = paragraph_2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
