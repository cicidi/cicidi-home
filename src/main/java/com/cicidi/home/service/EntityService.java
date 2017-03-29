package com.cicidi.home.service;

import com.cicidi.home.domain.repository.ProfileRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.io.XMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

/**
 * Created by wchen00 on 3/2/17.
 */


@Service
public class EntityService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    XMLReader xmlReader;

    public Profile getProfileFromDB(String username) {
        Profile profile = profileRepository.findByUsername(username);
        return profile;
    }

    public Profile loadProfileFromUploadFIle(String path) throws JAXBException {
        Profile profile = xmlReader.parseFile();
        return profile;
    }

    public Profile loadAndUpdate(String username) throws JAXBException {
        Profile profile = this.loadProfileFromUploadFIle("somePath");
        if (profile == null) {
            //has to be some default one;
            return null;
        }
        // file name by profile and lastname
        Profile original = this.getProfileFromDB(username);
        if (original != null) {
            profileRepository.delete(original.getEntityId());
        }
        profile.setUsername(username);
        profileRepository.save(profile);
        return profile;
    }
}
