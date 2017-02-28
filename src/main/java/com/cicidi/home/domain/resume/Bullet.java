package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.io.StringAdapter;
import com.cicidi.home.util.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.content, Constants.bulletList})

public class Bullet extends DatabaseEntity {
    @XmlTransient
    private String content;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.bullet, cascade = CascadeType.ALL)
    private List<Bullet> bulletList;

    @XmlTransient
    private String[] bulletListvalue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bullet_id")
    @XmlTransient
    private Bullet bullet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workExperience_id")
    @XmlTransient
    private WorkExperience workExperience;

    @XmlElement(name = Constants.content, required = false)
    @XmlJavaTypeAdapter(StringAdapter.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElementWrapper(name = Constants.bulletList)
    @XmlElement(name = Constants.bullet)
    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        if (bulletList != null)
            for (Bullet bullet : bulletList) {
                bullet.setBullet(this);
            }
        this.bulletList = bulletList;
    }

    public String getBulletListvalue() {
        String result = "";
        if (bulletList == null || bulletList.size() == 0) {
            return null;
        }
        for (Bullet bullet : bulletList) {
            result += bullet.content + Constants.spliter;
        }
        return result.substring(0, result.length() - 4);
    }


    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public WorkExperience getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }
}
