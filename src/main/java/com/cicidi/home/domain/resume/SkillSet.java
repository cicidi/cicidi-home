package com.cicidi.home.domain.resume;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillSet {
    @XmlElement(name = "name", required = true)
    private String techName;
    @XmlElementWrapper(name = "technologyList")
    @XmlElement(name = "technology")
    private List<String> technologyList;

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }
}
