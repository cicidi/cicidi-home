package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.domain.vo.Link;
import com.cicidi.home.util.Constants;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by cicidi on 2/17/2017.
 */
@XmlRootElement(name = Constants.contact)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {Constants.phone, Constants.address, Constants.linkList})
public class Contact extends DatabaseEntity {

    private String phone;
    private Address address;
    @XmlElementWrapper(name = Constants.linkList)
    @XmlElement(name = Constants.link)
    private List<Link> linkList;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }
}
