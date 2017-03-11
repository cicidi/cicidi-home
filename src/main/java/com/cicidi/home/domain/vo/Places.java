package com.cicidi.home.domain.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by wchen00 on 3/8/17.
 */
public class Places {
    private Map geoData;

    public Places(Map geoData) {
        this.geoData = geoData;
    }

    public Map getGeoData() {
        return geoData;
    }

    public void setGeoData(Map geoData) {
        this.geoData = geoData;
    }

    public String getGeoDataJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(this.geoData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
