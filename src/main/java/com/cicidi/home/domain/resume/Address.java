package com.cicidi.home.domain.resume;

/**
 * Created by cicidi on 2/18/17.
 */
public class Address {
    private String number;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String fullAddress;

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
        return sb.toString();
    }
}


