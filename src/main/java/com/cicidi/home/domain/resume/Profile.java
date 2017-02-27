package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlRootElement(name = Constants.profile)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.firstName, Constants.lastName, Constants.faceImg, Constants.objective, Constants.contact,
        Constants.educationList, Constants.workExperienceList, Constants.skillSets})
public class Profile extends DatabaseEntity {

    private String firstName;
    private String lastName;
    private String faceImg;
    private Contact contact;

    @XmlElementWrapper(name = Constants.educationList)
    @XmlElement(name = Constants.education)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.education)
    private List<Education> educationList;

    @XmlElementWrapper(name = Constants.workExperienceList)
    @XmlElement(name = Constants.workExperience)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.workExperience)
    private List<WorkExperience> workExperienceList;

    private Objective objective;

    @XmlElementWrapper(name = Constants.skillSets)
    @XmlElement(name = Constants.category)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.skillSets)
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
