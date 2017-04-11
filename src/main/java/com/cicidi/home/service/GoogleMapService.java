package com.cicidi.home.service;

import com.cicidi.home.domain.resume.*;
import com.cicidi.home.domain.vo.Places;
import com.cicidi.home.domain.vo.ProfileVo;
import com.cicidi.home.repository.GeoDataRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    @Autowired
    ObjectMapper objectMapper;

    public Map<String, String> getGeoData(Profile profile) {

        LinkedHashMap<String, String> geoMap = new LinkedHashMap<>();
        List<Organization> organizationList = new ArrayList<>();
        organizationList.addAll(profile.getWorkExperienceList());
        organizationList.addAll(profile.getEducationList());
        for (Organization organization : organizationList) {
//            if (true) break;
            GeoData geoData = null;
            String city = organization.getAddress().getCity();
            String state = organization.getAddress().getState();
            String country = organization.getAddress().getCountry();

            if (country != null && state != null) {
                List<GeoData> geoDataList = geoDataRepository.findByCompanyAddress(organization.getName(),
                        organization.getAddress().getCity(), organization.getAddress().getState(),
                        organization.getAddress().getCountry());
                if (!CollectionUtils.isEmpty(geoDataList)) {
                    geoData = geoDataList.get(0);
                }
            } else {
                List<GeoData> geoDataList = geoDataRepository.findByCompanyAndCity(organization.getName(),
                        organization.getAddress().getCity());
                if (!CollectionUtils.isEmpty(geoDataList)) {
                    geoData = geoDataList.get(0);
                }
            }
            if (geoData != null) {

                geoMap.put(organization.getName(), geoData.getGeometryJson());
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
                    Geometry geometry = placesSearchResponse.results[0].geometry;
                    try {
                        geoData = new GeoData(organization.getName(), address, objectMapper.writeValueAsString(geometry));
                        geoMap.put(organization.getName(), geoData.getGeometryJson());
                        geoDataRepository.save(geoData);
                        //temp break save google map request limit;
//                        break;
                    } catch (JsonProcessingException e) {
                        logger.error("error while convert geometry");
                    }
                }
            }
        }
//        geoMap = geoData();
        return geoMap;
    }

    private Map<String, String> getGeoData(ProfileVo profileVo) {
        Map<String, String> geoMap = new HashMap<>();
        for (Position position : profileVo.getAboutMe().getPositionList()) {
            if (true) break;
            String city = ProfileUtil.getCity(position);
            String company = position.getCompany().getName();
            List<GeoData> geoDataList = geoDataRepository.findByCompanyAddress(company, city,
                    null, null);
            GeoData geoData = null;
            if (!CollectionUtils.isEmpty(geoDataList)) {
                geoData = geoDataList.get(0);
            }
            if (geoData != null) {
                geoMap.put(company, geoData.getGeometryJson());
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
                    Geometry geometry = placesSearchResponse.results[0].geometry;
                    try {
                        Address address = new Address();
                        address.setCity(city);
                        geoData = new GeoData(company, address, objectMapper.writeValueAsString(geometry));
                        geoMap.put(company, geoData.getGeometryJson());
                        geoDataRepository.save(geoData);
                    } catch (JsonProcessingException e) {
                        logger.error("error while convert geometry");
                    }
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
