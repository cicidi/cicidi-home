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

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

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
public class ApacheXML2PDF {

    /**
     * Main method.
     *
     * @param args         command-line arguments
     * @param resumeConfig
     */
    public void createPdf(String resumeConfig) {
        try {
            System.out.println("FOP xml to pdf\n");
            System.out.println("Preparing...");

            // Setup directories
            File baseDir = new File("src/main/resources");
            File outDir = new File("/tmp");
            outDir.mkdirs();


            // Setup input and output files
            File xmlfile = new File(outDir, "/resume_copy.xml");
//            File xsltfile = new File(baseDir, "resume_config/resume-xsl-fo.xsl");
            File pdffile = new File(outDir, "/walter_chen_resume.pdf");
            if (!pdffile.exists()) {
                pdffile.createNewFile();
            }

//            System.out.println("Input: XML (" + xmlfile + ")");
//            System.out.println("Stylesheet: " + xsltfile);
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
                InputStream xsltfile = getClass().getResourceAsStream(resumeConfig + "/resume-xsl-fo.xsl");
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

                // Set the value of a <param> in the stylesheet
                transformer.setParameter("versionParam", "2.0");

                // Setup input for XSLT transformation
//                InputStream xsltfile = getClass().getResourceAsStream(resumeConfig + "resume-xsl-fo.xsl");
                Source src = new StreamSource(xmlfile);

                // Resulting SAX events (the generated FO) must be piped through to FOP
                Result res = new SAXResult(fop.getDefaultHandler());

                // Start XSLT transformation and FOP processing
                transformer.transform(src, res);
            } finally {
                out.close();
            }

            System.out.println("Success!");
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}