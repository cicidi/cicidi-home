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
    private String[] bulletListvalue;


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

    public String getBulletListvalue() {
        String result = "";
        if (bulletList == null || bulletList.size() == 0) {
            return null;
        }
        for (Bullet bullet : bulletList) {
            result += bullet.content + "####";
        }
        return result.substring(0, result.length() - 4);
    }

}
