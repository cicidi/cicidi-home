package com.cicidi.home.service;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.io.ApacheXML2PDF;
import com.cicidi.home.io.XMLReader;
import com.cicidi.home.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

/**
 * Created by wchen00 on 3/2/17.
 */


@Service
public class EntityService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    XMLReader xmlReader;

    @Value("${resumeConfig}")
    private String resumeConfig;

    @Value("${tmpFolder}")
    private String tmpFolder;

    @Autowired
    ApacheXML2PDF apacheXML2PDF;


    private static final String resumePath = "/resume.xml";
    private static final String resume_Copy_Path = "/resume_copy.xml";

    public Profile getProfileFromDB(String username) {
        Profile profile = profileRepository.findByUsername(username);
        return profile;
    }

    public Profile loadProfileFromUploadFIle() throws JAXBException {
        Profile profile = xmlReader.parseFile(resumeConfig + resumePath, tmpFolder + resume_Copy_Path);
        apacheXML2PDF.createPdf();
        return profile;
    }

    public Profile loadAndUpdate(String username) throws JAXBException {
        logger.info("load and update profile");
        Profile profile = this.loadProfileFromUploadFIle();
        if (profile == null) {
            //has to be some default one;
            return null;
        }
        // file name by profile and lastname
        Profile original = this.getProfileFromDB(username);
        if (original != null) {
            logger.info("delete existing wc profile");
            profileRepository.delete(original.getEntityId());
        }
        logger.info("save profile :{}", username);
        profile.setUsername(username);
        logger.info("saved done :{}", username);
        profileRepository.save(profile);
        return profile;
    }
}
