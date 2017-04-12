package com.cicidi.home.service;

import com.cicidi.home.util.Constants;
import com.cicidi.home.util.DateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.LinkedInDate;
import org.springframework.social.linkedin.api.Position;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicidi on 3/14/2017.
 */
@Service
public class CrawlerService {

    //    public static void main(String[] args) {
//        CrawlerService c = new CrawlerService();
//        c.fetchByUrl("https://www.linkedin.com/in/walter-chen-0b7558122");
//        c.fetchByUrl("https://www.linkedin.com/in/walter-chen-0b7558122");
//    }
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    String profile;

    public Elements getPositionElements() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("tmp/input_2.html").getFile());
        Document doc = null;
        try {
            doc = Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.select("#experience ul li");
        return elements;
    }

    public Elements getPositionElements(String path) {
        Document doc = null;
        try {
            Connection.Response response = Jsoup.connect(path)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://walterchen.us")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();
            doc = response.parse();
        } catch (Exception e) {
            logger.info("not able to get profile from path :{}, messge :{} ", path, e.getMessage());
        }
        if (doc == null) return null;
        Elements elements = doc.select("#experience ul li");
        return elements;
    }

    public Element getDescriptElement(Element element) {
        Elements elements = element.select(".description");
        return elements.size() > 0 ? elements.get(0) : null;
    }


    public ArrayList<LinkedInDate> getDateElements(Element element) {
        Elements e = element.select(".date-range time");
        if (e.size() == 0) {
            return null;
        }

        String start = null;
        String end = null;
        ArrayList<LinkedInDate> list = new ArrayList<>();
        if (e.size() == 2) {
            start = e.get(0).childNodeSize() > 0 ? ((TextNode) e.get(0).childNode(0)).text() : null;
            end = e.get(1).childNodeSize() > 0 ? ((TextNode) e.get(1).childNode(0)).text() : null;
        }
        if (e.size() == 1) {
            start = e.get(0).childNodeSize() > 0 ? ((TextNode) e.get(0).childNode(0)).text() : null;
            end = Constants.present;
        }
        LinkedInDate startDate = DateUtil.getLinkedInDate(start);
        LinkedInDate endDate = DateUtil.getLinkedInDate(end);
        list.add(startDate);
        list.add(endDate);
        return list;
    }

    public String getTitle(Element element) {
        Elements e = element.select(".item-title a");
        TextNode node = (TextNode) e.get(0).childNode(0);
        String title = node.text();
        return title;
    }

    public String getCompany(Element element) {
        Elements e = element.select(".item-subtitle a");
        TextNode node = (TextNode) e.get(0).childNode(0);
        String company = node.text();
        return company;
    }

    public String getSummary(Element element) {
        String summary = element.childNodeSize() > 0 ? ((TextNode) element.childNode(0)).text() : null;
        return summary;
    }

    public boolean isCurrent(Element element) {
        boolean isCurrent = element.childNodeSize() > 0 ? ((TextNode) element.childNode(0)).hasAttr("currentPositions") : false;
        return isCurrent;
    }

    public List<Position> fetchByUrl(String url) {

        List<Position> positionList = new ArrayList<>();
        Elements elements;
        if (profile.equals("dev")) {
            elements = this.getPositionElements();
        } else {
            elements = this.getPositionElements(url);
        }
        // start from 1, does not include latest one.
        if (elements == null) return null;
        for (int i = 1; i < elements.size(); i++) {
            Element element = elements.get(i);
            Company company = new Company(0, this.getCompany(element));
            Element desciptionElement = this.getDescriptElement(element);
            Position position = new Position(company, "id", this.isCurrent(desciptionElement),
                    this.getDateElements(element).get(0), this.getDateElements(element).get(1),
                    this.getSummary(desciptionElement), this.getTitle(element));
            positionList.add(position);
        }
        return positionList;
    }

//    public static void main(String[] args) throws IOException {
//
//        Connection.Response response = Jsoup.connect("http://www.linkedin.com/in/walter-chen-0b7558122/")
//                .ignoreContentType(true)
//                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
//                .referrer("http://www.google.com")
//                .timeout(12000)
//                .followRedirects(true)
//                .execute();
//        Document doc = response.parse();
//        System.out.println(doc);
//    }
}
