//package com.cicidi.home;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.math.BigInteger;
//
//import org.apache.poi.xwpf.usermodel.TextAlignment;
//import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFNumbering;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
//
//public class GeneratorBullet {
//
//    private static String FILE_DOC = "generator-bullet.doc";
//
//    public static void main(String[] args) throws Exception {
//
//        XWPFDocument doc = new XWPFDocument();
//
//        XWPFParagraph para = doc.createParagraph();
//        para.setVerticalAlignment(TextAlignment.CENTER);
//        para.setNumID(addListStyle(doc));
//        XWPFRun run = para.createRun();
//        run.setText("Ciao come stai? sdfsdkjf sdjfklsdjf klsdjfkljsd fsjkld fjklsd jfklsd fkls jdfkljsdk fjsdkljfklsdj fklsd fklsjdfkljsdkl fjsdkl jfklsd fklsd fl?");
//        run.setFontFamily("Verdana");
//        run.setFontSize(10);
//
//        try (FileOutputStream out = new FileOutputStream(FILE_DOC)) {
//            doc.write(out);
//        }
//
//    }
//
//    private static BigInteger addListStyle(XWPFDocument doc) {
//        try {
//
//            XWPFNumbering numbering = doc.createNumbering();
//            // generate numbering style from XML
//            InputStream in = new FileInputStream("numbering.xml");
//            CTAbstractNum abstractNum = CTAbstractNum.Factory.parse(in);
//            XWPFAbstractNum abs = new XWPFAbstractNum(abstractNum, numbering);
//            // find available id in document
//            BigInteger id = BigInteger.valueOf(1);
//            boolean found = false;
//            while (!found) {
//                Object o = numbering.getAbstractNum(id);
//                found = (o == null);
//                if (!found)
//                    id = id.add(BigInteger.ONE);
//            }
//            // assign id
//            abs.getAbstractNum().setAbstractNumId(id);
//            // add to numbering, should get back same id
//            id = numbering.addAbstractNum(abs);
//            // add to num list, result is numid
//            return doc.getNumbering().addNum(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//}