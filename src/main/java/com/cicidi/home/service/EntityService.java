package com.cicidi.home.service;

import com.cicidi.home.domain.repository.ProfileRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.io.XMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Created by wchen00 on 3/2/17.
 */


@Service
public class EntityService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    SimpleJpaRepository<Profile, Long> simpleJpaRepository;

    @Autowired
    XMLReader xmlReader;

    public Profile getProfileFromDB(String lastName, String firstName) {
        List<Profile> list = profileRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Profile loadProfileFromUploadFIle(String path) throws JAXBException {
        Profile profile = xmlReader.parseFile();
        return profile;
    }

    public Profile loadAndUpdate() throws JAXBException {
        Profile profile = this.loadProfileFromUploadFIle("somePath");
        Profile target = null;
        if (profile == null) {
            //has to be some default one;
            return null;
        }
        target = this.getProfileFromDB(profile.getLastName(), profile.getFirstName());
        if (target == null) {
            profileRepository.save(profile);
            return profile;
        } else {
            target.setContact(profile.getContact());
            target.setEducationList(profile.getEducationList());
            target.setFaceImg(profile.getFaceImg());
            target.setObjective(profile.getObjective());
            target.setSkillSets(profile.getSkillSets());
            target.setWorkExperienceList(profile.getWorkExperienceList());
            profileRepository.save(target);
            return target;
        }
    }
}
