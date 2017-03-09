package com.cicidi.home.service;

import com.cicidi.home.domain.resume.*;
import com.cicidi.home.domain.resume.Link;
import com.cicidi.home.domain.vo.WebLog;
import com.cicidi.home.io.ApacheXML2PDF;
import com.cicidi.home.io.XMLReader;
import com.cicidi.home.util.Constants;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cicidi on 2/18/17.
 */
//@Service
//public class Test {
//    public static void main(String[] args) throws JAXBException {
//        new XMLReader().parseFile();
//        new ApacheXML2PDF().createPdf();
//    }
//}
