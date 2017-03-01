package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
@Entity
@XmlRootElement(name = Constants.contact)
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.phone, Constants.address, Constants.linkList})
public class Contact extends DatabaseEntity {

    @XmlTransient
    private String phone;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = Constants.contact, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Link> linkList;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile profile;

    @XmlElement
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setContact(this);
        this.address = address;
    }

    @XmlElementWrapper(name = Constants.linkList)
    @XmlElement(name = Constants.link)
    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        for (Link link : linkList) {
            link.setContact(this);
        }
        this.linkList = linkList;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
