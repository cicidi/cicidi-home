package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.maps.model.Geometry;

import javax.persistence.*;

/**
 * Created by cicidi on 3/30/2017.
 */
@Entity
public class GeoData extends DatabaseEntity {
    private String companyName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "geometry_id")
    @JsonBackReference
    private Geometry geometry;

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
        this.address = address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
