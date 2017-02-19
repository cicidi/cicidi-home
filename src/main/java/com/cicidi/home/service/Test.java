package com.cicidi.home.service;

import com.cicidi.home.domain.resume.Address;
import com.cicidi.home.domain.resume.Contact;
import com.cicidi.home.domain.resume.Education;
import com.cicidi.home.domain.resume.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by cicidi on 2/18/17.
 */
public class Test {
    public Profile createProfile() {
        Profile profile = new Profile();
        profile.setFirstName("Walter");
        profile.setLastName("Chen");

        Contact contact = new Contact();
        contact.setEmail("cicidi@gmail.com");
        contact.setPhone("352-281-8555");
        Address contact_address = new Address();
        contact_address.setCity("Fremont");
        contact_address.setState("California");
        contact_address.setCountry(Locale.US.getDisplayCountry());
        contact.setAddress(contact_address);
        profile.setContact(contact);

        Education education_uf = new Education();
        education_uf.setName("University of Florida");
        Address uf_address = new Address();
        uf_address.setCity("Gainesville");
        uf_address.setState("Florida");
        uf_address.setCountry(Locale.US.getDisplayCountry());
        education_uf.setAddress(uf_address);
        education_uf.setDegree("Master");
        education_uf.setMajor("Information System");


        List<Education> educationList = new ArrayList<>();
        Education education_dmu = new Education();
        education_dmu.setName("University of Florida");
        Address dmu_address = new Address();
        dmu_address.setCity("Dalian");
        dmu_address.setState("LiaoNing");
        dmu_address.setCountry(Locale.CHINA.getDisplayCountry());
        education_dmu.setAddress(dmu_address);
        education_dmu.setDegree("Bachelor");
        educationList.add(education_uf);
        educationList.add(education_dmu);
        profile.setEducationList(educationList);

        return profile;
    }
}
