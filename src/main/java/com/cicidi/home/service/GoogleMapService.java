package com.cicidi.home.service;

import com.cicidi.home.domain.repository.GeoDataRepository;
import com.cicidi.home.domain.resume.Address;
import com.cicidi.home.domain.resume.GeoData;
import com.cicidi.home.domain.resume.Organization;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.Places;
import com.cicidi.home.domain.vo.ProfileVo;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.Geometry;
import com.google.maps.model.PlacesSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.slf4j.ext.XLogger;
//import org.slf4j.ext.XLoggerFactory;

/**
 * Created by cicidi on 2/26/2017.
 */
@Component
public class GoogleMapService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    GeoApiContext context;

    @Autowired
    GeoDataRepository geoDataRepository;

    public Map<String, Geometry> getGeoData(Profile profile) {

        Map<String, Geometry> geoMap = new HashMap<>();
        List<Organization> organizationList = new ArrayList<>();
        organizationList.addAll(profile.getEducationList());
        organizationList.addAll(profile.getWorkExperienceList());
        for (Organization organization : organizationList) {
            GeoData geoData = geoDataRepository.findByCompany(organization.getName(),
                    organization.getAddress().getCity(), organization.getAddress().getState(),
                    organization.getAddress().getCountry());
            if (geoData != null) {
                geoDataRepository.save(geoData);
                geoMap.put(organization.getName(), geoData.getGeometry());
                continue;
            } else {
                Address address = organization.getAddress();
                String query = organization.getName() + " " + address.getFullAddress();
                PlacesSearchResponse placesSearchResponse = null;
                try {
                    placesSearchResponse = googleMapTextSearch(query);
                } catch (Exception e) {
                    logger.error("error while google place : {}", query);
                }
                if (placesSearchResponse.results.length > 0) {
                    geoMap.put(organization.getName(), placesSearchResponse.results[0].geometry);
                }
            }
        }
//        geoMap = geoData();
        return geoMap;
    }

    private Map getGeoData(ProfileVo profileVo) {
        Map<String, Geometry> geoMap = new HashMap<>();
        for (Position position : profileVo.getAboutMe().getPositionList()) {
            Map extraData = position.getExtraData();
            Map location = (extraData != null) ? (Map) extraData.get("location") : null;
            String city = (location != null) ? (String) location.get("name") : null;
            String company = position.getCompany().getName();
            GeoData geoData = geoDataRepository.findByCompany(company, city,
                    null, null);
            if (geoData != null) {
                geoDataRepository.save(geoData);
                geoMap.put(position.getCompany().getName(), geoData.getGeometry());
                continue;
            } else {
                String query = company + " " + city;
                PlacesSearchResponse placesSearchResponse = null;
                try {
                    placesSearchResponse = googleMapTextSearch(query);
                } catch (Exception e) {
                    logger.error("error while google place : {}", query);
                }
                if (placesSearchResponse.results.length > 0) {
                    geoMap.put(company, placesSearchResponse.results[0].geometry);
                }
            }
        }
        return geoMap;
    }

    public PlacesSearchResponse googleMapTextSearch(String query) throws Exception {
        TextSearchRequest textSearchRequest = PlacesApi.textSearchQuery(context, query);
        PlacesSearchResponse response = textSearchRequest.await();
        if (response.results.length == 0) {
//            logger.debug("address not found {}", query);
        }
        return response;
    }


    public Map geoData() {
        Map<String, double[]> geoMap = new HashMap<>();
        double[] geoData = new double[2];
        geoData[0] = 37.69547879999999;
        geoData[1] = -121.9218157;
        geoMap.put("Blackhawk network", geoData);

        geoData = new double[2];
        geoData[0] = 37.3785469;
        geoData[1] = -122.0003521;
        geoMap.put("Apple", geoData);

        geoData = new double[2];
        geoData[0] = 29.6436325;
        geoData[1] = -82.3549302;
        geoMap.put("University of Florida", geoData);

        geoData = new double[2];
        geoData[0] = 31.0543678;
        geoData[1] = -97.4898863;
        geoMap.put("CGI", geoData);

        geoData = new double[2];
        geoData[0] = 38.870041;
        geoData[1] = 121.534141;
        geoMap.put("Dalian Maritime University", geoData);

        geoData = new double[2];
        geoData[0] = 37.373444;
        geoData[1] = -121.9648727;
        geoMap.put("Huawei", geoData);
        return geoMap;

    }

    public Places getPlaces(Profile profile) {
        Places places = null;
        try {
            places = new Places(this.getGeoData(profile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    public Places getPlaces(ProfileVo profileVo) {

        Places places = null;
        try {
            places = new Places(this.getGeoData(profileVo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }


}
