package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName"})})
@XmlRootElement(name = Constants.profile)
//@XmlType(propOrder = {Constants.firstName, Constants.lastName, Constants.faceImg, Constants.objective, Constants.contact,
//        Constants.educationList, Constants.workExperienceList, Constants.skillSets})
@XmlType(propOrder = {Constants.firstName, Constants.lastName, Constants.faceImg, Constants.objective, Constants.contact,
        Constants.educationList, Constants.workExperienceList, Constants.skillSets, Constants.projectList})
public class Profile extends DatabaseEntity {

    @XmlTransient
    private String username;
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

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.profile, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Project> projectList;

    @XmlTransient
    @Transient
    private LinkedInProfileFull linkedInProfileFull;

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

    @XmlElementWrapper(name = Constants.projectList)
    @XmlElement(name = Constants.project)
    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        for (Project project : projectList) {
            project.setProfile(this);
        }
        this.projectList = projectList;
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
        this.setProjectList(this.projectList);
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


    public Profile() {
        super();
    }

    public Profile(LinkedInProfileFull linkedInProfileFull) {
        super();
        this.setLinkedInProfileFull(linkedInProfileFull);
        this.setFirstName(linkedInProfileFull.getFirstName());
        this.setLastName(linkedInProfileFull.getLastName());
        this.setFaceImg(linkedInProfileFull.getProfilePictureUrl());
        this.setContact(new Contact(linkedInProfileFull));
        List<Education> educationList = new ArrayList<>();
        List<org.springframework.social.linkedin.api.Education> educations = linkedInProfileFull.getEducations();
        if (educations != null)
            for (org.springframework.social.linkedin.api.Education education : educations) {
                Education edu = new Education(education);
                educationList.add(edu);
            }
        this.setEducationList(educationList);

        List<WorkExperience> workExperienceList = new ArrayList<>();
        List<Position> positions = linkedInProfileFull.getPositions();
        if (positions != null)
            for (Position position : positions) {
                WorkExperience workExperience = new WorkExperience(position);
                workExperienceList.add(workExperience);
            }
        this.setWorkExperienceList(workExperienceList);

    }

    public LinkedInProfileFull getLinkedInProfileFull() {
        return linkedInProfileFull;
    }

    public void setLinkedInProfileFull(LinkedInProfileFull linkedInProfileFull) {
        this.linkedInProfileFull = linkedInProfileFull;
    }

    public void addWorkExperience(List<Position> positions) {
        this.workExperienceList.addAll(positions.stream().map(WorkExperience::new).collect(Collectors.toList()));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
