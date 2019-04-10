package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@XmlRootElement(name = Constants.project)
@XmlAccessorType(XmlAccessType.FIELD)
public class Project extends DatabaseEntity {
    @XmlElement(name = Constants.name, required = true)
    private String projectName;

    //    @XmlElement(name = Constants.description)
//    @ElementCollection
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @XmlTransient
    @JsonBackReference
    private Profile profile;

    public String url;
    @Length(max = 1000)
    public String descritpion;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}
