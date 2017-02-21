package com.cicidi.home.domain.resume;

import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"firstName", "lastName", "contact", "educationList",
        "workExperienceList", "objective", "skillSets"})
public class Profile {

    private String firstName;
    private String lastName;
    private Contact contact;
    @XmlElementWrapper(name = "educationList")
    @XmlElement(name = "Education")
    private List<Education> educationList;
    @XmlElementWrapper(name = "workExperienceList")
    @XmlElement(name = "WorkExperience")
    private List<WorkExperience> workExperienceList;
    private Objective objective;
    @XmlElementWrapper(name = "skillSets")
    @XmlElement(name = "SkillSet")
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
}
