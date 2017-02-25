package com.cicidi.home.io;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

// remove "\n" from string
public class StringAdapter extends XmlAdapter<String, String> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyy");


    @Override
    public String unmarshal(String v) throws Exception {
        v = v.replace("\n", "");
        return v.trim();
    }

    @Override
    public String marshal(String v) throws Exception {
        v = v.replace("\n", "");
        return v.trim();
    }

}