package com.cicidi.home.domain.vo;

import com.cicidi.home.util.Constants;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/2017.
 */
@XmlType(propOrder = {Constants.name, "url", "pic", Constants.icon})
public class Link {
    private String name;
    private String url;
    private String pic;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
