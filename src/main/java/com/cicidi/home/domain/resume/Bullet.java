package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.io.StringAdapter;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @Lob
    @Column(length = 100000)
    private String content;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.bullet, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bullet> bulletList;

    @XmlTransient
    private String[] bulletListvalue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bullet_id")
    @XmlTransient
    @JsonBackReference
    private Bullet bullet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workExperience_id")
    @XmlTransient
    @JsonBackReference
    private WorkExperience workExperience;


    public Bullet() {
        super();
    }

    public Bullet(String summary) {
        super();
        this.content = summary;
    }

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
