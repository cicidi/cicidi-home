package com.cicidi.home.io;

import com.cicidi.home.util.Constants;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyy");

    @Override
    public String marshal(Date v) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(v);
        }
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        if (v.equals(Constants.present))
            return new Date();
        synchronized (dateFormat) {
            return dateFormat.parse(v);
        }
    }

}