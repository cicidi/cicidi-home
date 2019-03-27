/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package com.cicidi.home.io;

//Java

import com.cicidi.home.service.CrawlerService;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class demonstrates the conversion of an XML file to PDF using
 * JAXP (XSLT) and FOP (XSL-FO).
 */
@Component
public class ApacheXML2PDF {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Main method.
     *
     * @param args         command-line arguments
     * @param resumeConfig
     */

    @Value("${env}")
    private String env;

    @Value("${tmpFolder}")
    private String tmpFolder;

    public void createPdf() {
        try {
            System.out.println("FOP xml to pdf\n");
            System.out.println("Preparing...");

            // Setup directories
            File outDir = new File(tmpFolder);
            outDir.mkdirs();


            // Setup input and output files
            File xmlfile = new File(outDir, "/resume_copy.xml");
            File pdffile = new File(outDir, "/walter_chen_resume.pdf");
            if (!pdffile.exists()) {
                pdffile.createNewFile();
            }

            System.out.println("Output: PDF (" + pdffile + ")");
            System.out.println();
            System.out.println("Transforming...");

            // configure fopFactory as desired
            final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            // configure foUserAgent as desired

            // Setup output
            OutputStream out = new java.io.FileOutputStream(pdffile);
            out = new java.io.BufferedOutputStream(out);
            try {
                // Construct fop with desired output format
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

                // Setup XSLT
                TransformerFactory factory = TransformerFactory.newInstance();
//                InputStream xsltfile = getClass().getResourceAsStream(resumeConfig + "/resume-xsl-fo.xsl");

                ClassLoader classLoader = new ApacheXML2PDF().getClass().getClassLoader();
                String path = null;
                if (env.equals("dev")) {
                    path = classLoader.getResource("resume_config/resume-xsl-fo.xsl").getFile();
                } else if (env.equals("prod")) {
                    path = tmpFolder+"/resume-xsl-fo.xsl";
                }
                File xsltfile = new File(path);
                logger.info("xls-fo.xsl path :{}", xsltfile.getAbsoluteFile());
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

                // Set the value of a <param> in the stylesheet
                transformer.setParameter("versionParam", "2.0");

                // Setup input for XSLT transformation
                Source src = new StreamSource(xmlfile);

                // Resulting SAX events (the generated FO) must be piped through to FOP
                Result res = new SAXResult(fop.getDefaultHandler());

                // Start XSLT transformation and FOP processing
                transformer.transform(src, res);
            } finally {
                out.close();
            }

            System.out.println("Success!");
        } catch (
                Exception e)

        {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}