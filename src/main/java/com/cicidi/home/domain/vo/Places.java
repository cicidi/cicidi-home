package com.cicidi.home.domain.vo;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.service.GoogleMapService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
