package com.cicidi.home.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.apache.commons.lang3.StringUtils.replaceEach;

public class BackGroundGenerator {
    static final String imagePath = "static/img/walter.jpg";
    static final String resumePath = "resume_config/resume.xml";

    public static void main(String args[]) throws IOException {
        BackGroundGenerator generator = new BackGroundGenerator();
        generator.fillImageWithKeyWords(imagePath, resumePath);
    }

    public int[][] metric = new int[108][192];

    public void fillImageWithKeyWords(String imagePath, String resumePath) throws IOException {
        ClassLoader classLoader = new BackGroundGenerator().getClass().getClassLoader();
        File file = new File(classLoader.getResource(imagePath).getFile());
        BufferedImage image = ImageIO.read(file);
        int c = 0;
        String content = this.getKeywordsFromResumeConfig(resumePath);
        int l = content.length();

        for (int i = 0; i < image.getHeight(); i += 10) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < image.getWidth(); j += 10) {
                int color = image.getRGB(j, i);
                if (color == -1) {
                    this.metric[i / 10][j / 10] = 0;
                    System.out.print(" ");
                } else {
                    this.metric[i / 10][j / 10] = 1;
                    System.out.print(content.charAt(c % l));
                    c++;
                }
            }
            System.out.println();
        }
    }

    public String getKeywordsFromResumeConfig(String resumePath) throws IOException {
        ClassLoader classLoader = new BackGroundGenerator().getClass().getClassLoader();
        String contents = new String(Files.readAllBytes(Paths.get(classLoader.getResource(resumePath).getFile())));
        int start = contents.indexOf("<skillSets>");
        int end = contents.indexOf("</skillSets>");
        return this.cleanUpKeywords(contents.substring(start, end));

    }

    public String cleanUpKeywords(String input) {
        String[] searchList = {
                "<skillSets>", //1
                "</skillSets>", //2
                "<category>",//3
                "</category>",//4
                "<name>",//5
                "</name>",//6
                "<list>",//7
                "</list>",//8
                "\n",//9
                "( )+"
        };//9
        String[] replacementList = {
                "SkillSets: ",//1
                " ",//2
                " ",//3
                " ",//4
                " ",//5
                " ",//6
                " ",//7
                " ",//8
                " ",
                " "
        };//9

        String output = replaceEach(input, searchList, replacementList).replaceAll("( )+", " ").replaceAll(",,", ",");
        return output;
    }
}




