//package com.cicidi.home.io;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.math.BigInteger;
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//
///**
// * Created by cicidi on 2/20/2017.
// */
//public class WordReader {
//
//
//    public static void main(String[] args) throws Exception {
//        //Blank Document
//        XWPFDocument document = new XWPFDocument();
//        //Write the Document in file system
//        FileOutputStream out = new FileOutputStream(new File("createparagraph.docx"));
//
//        //create Paragraph
//        XWPFParagraph paragraph = document.createParagraph();
//
//        paragraph.setStyle("ListParagraph");
//        paragraph.setNumID(BigInteger.valueOf(2));
//        paragraph.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(12));
//        XWPFRun run = paragraph.createRun();
//        run.setText("At tutorialspoint.com, we strive hard to " +
//                "provide quality tutorials for self-learning " +
//                "purpose in the domains of Academics, Information " +
//                "Technology, Management and Computer Programming Languages.");
//        document.write(out);
//        out.close();
//        System.out.println("createparagraph.docx written successfully");
//    }
//}
