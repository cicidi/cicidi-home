package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Link;

/**
 * Created by cicidi on 3/10/2017.
 */
public class LinkVo {
    private String name;
    private String url;
    private String icon;

    public LinkVo(Link link) {
        this.name = link.getName();
        this.url = link.getUrl();
        this.icon = Icon.findByName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.icon = Icon.findByName(name);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
