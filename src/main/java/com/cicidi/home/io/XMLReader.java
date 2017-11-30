package com.cicidi.home.io;

import com.cicidi.home.domain.resume.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by cicidi on 2/21/2017.
 */
@Component
public class XMLReader {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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


    public Profile parseFile(String input, String output) throws JAXBException {
        try {
            logger.info("parse file {}", input);
            logger.info("to file {}", output);
            JAXBContext jc = null;

            jc = JAXBContext.newInstance(Profile.class);

//            "src/main/resources/resume_config/resume.xml"
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream in = getClass().getResourceAsStream(input);
            Profile profile = (Profile) unmarshaller.unmarshal(in);
            profile.dualDirecction();
            File out = new File(output);
//        File out = new File("src/main/resources/resume_config/resume_copy.xml");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(profile, out);
            marshaller.marshal(profile, System.out);
            return profile;
        } catch (JAXBException e) {
            logger.info("JAXBException file");
            e.printStackTrace();
            throw e;
        }
    }
}
