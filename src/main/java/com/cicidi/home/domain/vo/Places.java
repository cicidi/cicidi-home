package com.cicidi.home.domain.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wchen00 on 3/8/17.
 */
public class Places {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, String> geoData;

    ObjectMapper objectMapper = new ObjectMapper();

    public Places(Map<String, String> geoData) {
        this.geoData = geoData;
    }

    public Map<String, String> getGeoData() {
        return geoData;
    }

    public void setGeoData(Map<String, String> geoData) {
        this.geoData = geoData;
    }

    public String getGeoDataJson() {
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(this.geoData);
        } catch (JsonProcessingException e) {
            logger.error("error while write geodata {} ", e.getMessage());
        } catch (IOException e) {
            logger.error("error while read geodata {} ", e.getMessage());
        }
        return json;
    }
}
