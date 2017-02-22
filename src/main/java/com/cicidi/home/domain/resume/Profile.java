package com.cicidi.home.domain.resume;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName", "lastName", "faceImg", "objective", "contact", "educationList",
        "workExperienceList", "skillSets"})
public class Profile {

    private String firstName;
    private String lastName;
    private String faceImg;
    private Contact contact;

    @XmlElementWrapper(name = "educationList")
    @XmlElement(name = "education")
    private List<Education> educationList;
    @XmlElementWrapper(name = "workExperienceList")
    @XmlElement(name = "workExperience")
    private List<WorkExperience> workExperienceList;
    private Objective objective;
    @XmlElementWrapper(name = "skillSets")
    @XmlElement(name = "category")
    private List<SkillSet> skillSets;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
        this.workExperienceList = workExperienceList;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public List<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<SkillSet> skillSets) {
        this.skillSets = skillSets;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
}
