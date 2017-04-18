package com.cicidi.home.io;

import com.cicidi.home.domain.resume.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cicidi on 2/21/2017.
 */
@Component
public class XMLReader {
    private final Logger logger = LoggerFactory.getLogger(XMLReader.class);
    //    public static void main(String[] args) throws Exception {
//        JAXBContext jc = JAXBContext.newInstance(Profile.class);
//
//        Unmarshaller unmarshaller = jc.createUnmarshaller();
//        Profile profile = (Profile) unmarshaller.unmarshal(new File("src/main/resources/resume_config/resume.xml"));
//        File out = new File("src/main/resources/resume_config/resume_copy.xml");
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(profile, out);
//        marshaller.marshal(profile, System.out);
//    }
    @Value(value = "classpath:resume_config/resume.xml")
    private Resource resumeXml;

    public Profile parseFile() throws JAXBException {
        logger.info("parseFile");
        JAXBContext jc = JAXBContext.newInstance(Profile.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        InputStream dbAsStream = null;
        File file = null;
        try {
            logger.info("loading a resume.xml {} ", resumeXml.getURI().toString());
            dbAsStream = resumeXml.getInputStream();
            logger.info("loaded resume.xml {} ", resumeXml.getURI().toString());
        } catch (IOException e) {
            logger.error("can not get resume xml file");
        }
//        Profile profile = (Profile) unmarshaller.unmarshal(new File("classpath:/resume_config/resume.xml"));
        Profile profile = (Profile) unmarshaller.unmarshal(dbAsStream);
        logger.info("unmarshal resume.xml ");
        profile.dualDirecction();
        File out = new File("/tmp/resume_copy.xml");
        logger.info("create  resume_copy.xml ");
//        File out = new File("src/main/resources/resume_config/resume_copy.xml");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(profile, out);
        logger.info("marshaller resume_copy.xml ");
        marshaller.marshal(profile, System.out);
        return profile;
    }

    public Profile parseFileLocal() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Profile.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Profile profile = (Profile) unmarshaller.unmarshal(new File("src/main/resources/resume_config/resume.xml"));
        profile.dualDirecction();
        File out = new File("/tmp/resume_copy.xml");
//        File out = new File("src/main/resources/resume_config/resume_copy.xml");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(profile, out);
        marshaller.marshal(profile, System.out);
        return profile;
    }
}
