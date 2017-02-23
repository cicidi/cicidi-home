package com.cicidi.home.domain.resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/17.
 */


@XmlRootElement(name = "education")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "address", "start", "end", "startName", "endName", "length", "photo", "icon", "major", "degree"})

public class Education extends Organization {
    private String major;
    private String degree;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
