package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.Location;
import org.springframework.social.linkedin.api.Position;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@XmlType(propOrder = {Constants.number, Constants.street, Constants.city, Constants.state, Constants.country, Constants.zipCode})
public class Address extends DatabaseEntity {
    private String number;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @XmlTransient
    private String fullAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    @XmlTransient
    @JsonBackReference
    private Organization organization;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    @XmlTransient
    @JsonBackReference
    private Contact contact;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "geo_data_id")
    @XmlTransient
    @JsonBackReference
    private GeoData geoData;

    public Address() {
        super();
    }

    public Address(Location location) {
        super();
        this.setCity(location.getName());
        this.setCountry(location.getCountry());
    }

    public Address(Company company) {
        super();
        if (company.getLocations() != null && company.getLocations().size() > 0) {
            this.setCity(company.getLocations().get(0).getAddress().getCity());
            this.setStreet(company.getLocations().get(0).getAddress().getStreet1());
            this.setZipCode(company.getLocations().get(0).getAddress().getPostalCode());
        }
    }

    public Address(Position position) {
        super();
        this.setCity(ProfileUtil.getCity(position));
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (number != null) {
            sb.append(number);
            sb.append(" ");
        }
        if (street != null) {
            sb.append(street);
            sb.append(" ");
        }
        if (city != null) {
            sb.append(city);
            sb.append(" ");
        }
        if (state != null) {
            sb.append(state);
            sb.append(" ");
        }
//        if (country != null) {
//            sb.append(country);
//            sb.append(" ");
//        }
//        if (zipCode != null) {
//            sb.append(zipCode);
//            sb.append(" ");
//        }
        return sb.toString();
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public GeoData getGeoData() {
        return geoData;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
    }
}


