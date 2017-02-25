package com.cicidi.home.domain.resume;

import com.cicidi.home.io.DateAdapter;
import com.cicidi.home.io.StringAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"content", "bulletList"})
public class Bullet {
    @XmlTransient
    private String content;

    @XmlElementWrapper(name = "bulletList")
    @XmlElement(name = "bullet")
    private List<Bullet> bulletList;
    @XmlTransient
    private String[] bulletListvalue;

    @XmlElement(name = "content", required = false)
    @XmlJavaTypeAdapter(StringAdapter.class)
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
