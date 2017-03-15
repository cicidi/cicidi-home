package com.cicidi.home.service;

import com.cicidi.home.domain.resume.Organization;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.Places;
import com.cicidi.home.domain.vo.ProfileVo;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    //    private static XLogger logger = XLoggerFactory.getXLogger(GoogleMapService.class);
    @Autowired
    GeoApiContext context;

    public Map<String, double[]> getGeoData(Profile profile) throws Exception {
        Map<String, double[]> geoMap = new HashMap<>();
        List<Organization> organizationList = new ArrayList<>();
//        organizationList.addAll(profile.getEducationList());
//        organizationList.addAll(profile.getWorkExperienceList());
//        for (Organization organization : organizationList) {
//            Address address = organization.getAddress();
//            String query = organization.getName() + " " + address.getFullAddress();
//            PlacesSearchResponse placesSearchResponse = googleMapTextSearch(query);
//            if (placesSearchResponse.results.length > 0) {
//                double[] geo = new double[2];
//                geo[0] = placesSearchResponse.results[0].geometry.location.lat;
//                geo[1] = placesSearchResponse.results[0].geometry.location.lng;
//                geoMap.put(organization.getName(), geo);
//            }
//        }
        geoMap = geoData();
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
            places = new Places(this.getGeoData(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }
}
