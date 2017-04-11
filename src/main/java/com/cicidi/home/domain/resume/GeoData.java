package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by cicidi on 3/30/2017.
 */
@Entity
public class GeoData extends DatabaseEntity {

    private String companyName;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "geoData", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Address address;

    private String geometryJson;


    public GeoData() {
        super();
    }

    public GeoData(String name, Address address, String geometryJson) {
        super();
        this.companyName = name;
        this.setAddress(address);
        this.geometryJson = geometryJson;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setGeoData(this);
        this.address = address;
    }

    public String getGeometryJson() {
        return geometryJson;
    }

    public void setGeometryJson(String geometryJson) {
        this.geometryJson = geometryJson;
    }
}
