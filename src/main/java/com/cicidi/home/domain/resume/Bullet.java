package com.cicidi.home.domain.resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Bullet {
    private String content;

    @XmlElementWrapper(name = "bulletList")
    @XmlElement(name = "bullet")
    private List<Bullet> bulletList;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}
