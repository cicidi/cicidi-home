package com.cicidi.home.domain.resume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by cicidi on 2/18/17.
 */
@XmlType(name = "categoryName")
@XmlEnum
@XmlAccessorType(XmlAccessType.FIELD)
public enum TechCategory {
    DATABASE, SPRING, BIG_DATA, CORE_JAVA, AWS, UI, SECURITY;

    public String value() {
        return name();
    }

    public static TechCategory fromValue(String v) {
        return valueOf(v);
    }
}
