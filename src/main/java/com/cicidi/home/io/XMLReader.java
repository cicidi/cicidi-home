package com.cicidi.home.io;

import com.cicidi.home.domain.resume.Profile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by cicidi on 2/21/2017.
 */
public class XMLReader {
    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Profile.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Profile profile = (Profile) unmarshaller.unmarshal(new File("src/main/resources/resume_config/resume_copy.xml"));

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(profile, System.out);
    }

}
