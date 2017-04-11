package com.cicidi.home.domain.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wchen00 on 3/31/17.
 */

//TBD
public class PlaceNote {
    private Map<String, String> note;

    public PlaceNote() {
        this.note = new HashMap<>();
    }

    public void add(String k, String v) {
        this.note.put(k, v);
    }

    public String getNote() {
//        String sb=new StringBuffer();
//        for()
        return null;
    }
}
