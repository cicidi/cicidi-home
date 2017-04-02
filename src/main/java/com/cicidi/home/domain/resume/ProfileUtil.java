package com.cicidi.home.domain.resume;

import org.springframework.social.linkedin.api.Position;

import java.util.Map;

/**
 * Created by cicidi on 3/31/2017.
 */
public class ProfileUtil {
    public static String getCity(Position position) {
        Map extraData = position.getExtraData();
        Map location = (extraData != null) ? (Map) extraData.get("location") : null;
        String name = location != null ? (String) location.get("name") : null;
        return name;
    }

}
