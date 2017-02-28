package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@XmlRootElement(name = Constants.category)
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillSet extends DatabaseEntity {
    @XmlElement(name = Constants.name, required = true)
    private String techName;

    private String list;

    @XmlElementWrapper(name = Constants.technologyList)
    @XmlElement(name = Constants.technology)
    @ElementCollection
    @CollectionTable(name = "technology")
    private List<String> technologyList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    private Profile profile;

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
