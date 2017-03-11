package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName"})})
@XmlRootElement(name = Constants.profile)
//@XmlType(propOrder = {Constants.firstName, Constants.lastName, Constants.faceImg, Constants.objective, Constants.contact,
//        Constants.educationList, Constants.workExperienceList, Constants.skillSets})
@XmlType(propOrder = {Constants.firstName, Constants.lastName, Constants.faceImg, Constants.objective, Constants.contact,
        Constants.educationList, Constants.workExperienceList, Constants.skillSets})
public class Profile extends DatabaseEntity {
    @XmlTransient
    private String firstName;

    @XmlTransient
    private String lastName;

    @XmlTransient
    private String faceImg;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Contact contact;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "profile_education")
    private List<Education> educationList;
    //
    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "profile_workExperience")
    private List<WorkExperience> workExperienceList;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Objective objective;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SkillSet> skillSets;

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        contact.setProfile(this);
        this.contact = contact;
    }

    @XmlElementWrapper(name = Constants.educationList)
    @XmlElement(name = Constants.education)
    public List<Education> getEducationList() {
        return educationList;
    }


    public void setEducationList(List<Education> educationList) {
        for (Education education : educationList) {
            education.setProfile(this);
        }
        this.educationList = educationList;
    }

    @XmlElementWrapper(name = Constants.workExperienceList)
    @XmlElement(name = Constants.workExperience)
    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
        for (WorkExperience workExperience : workExperienceList) {
            workExperience.setProfile(this);
        }
        this.workExperienceList = workExperienceList;
    }

    @XmlElement
    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        objective.setProfile(this);
        this.objective = objective;
    }

    @XmlElementWrapper(name = Constants.skillSets)
    @XmlElement(name = Constants.category)
    public List<SkillSet> getSkillSets() {
        return skillSets;
    }

    public void setSkillSets(List<SkillSet> skillSets) {
        for (SkillSet skillSet : skillSets) {
            skillSet.setProfile(this);
        }
        this.skillSets = skillSets;
    }

    @XmlElement
    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public Profile dualDirecction() {
        // because JAXB setter give List as empty:
        this.setSkillSets(this.skillSets);
        this.setEducationList(this.educationList);
        this.setWorkExperienceList(this.workExperienceList);
        this.contact.setLinkList(this.contact.getLinkList());
        for (Education education : this.educationList) {
            education.getAddress().setOrganization(education);
        }
        for (WorkExperience workExperience : this.workExperienceList) {
            workExperience.getAddress().setOrganization(workExperience);
            workExperience.setBulletList(workExperience.getBulletList());
            for (Bullet bullet : workExperience.getBulletList()) {
                bullet.setBulletList(bullet.getBulletList());
            }
        }
        return this;
    }
}
