package com.cicidi.home.domain.resume;

/**
 * Created by cicidi on 2/17/2017.
 */
public class Contact {

    private String phone;
    private Address address;
    private String email;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
