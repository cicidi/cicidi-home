package com.cicidi.home.domain.vo;

/**
 * Created by wchen00 on 3/10/17.
 */
public enum Icon {


    FACEBOOK("fa fa-facebook"), LINKEDIN("fa fa-linkedin"), EMAIL("icon-mail"), GITHUB("fa fa-github"), WECHAT("fa fa-weixin"),

    TWITTER("fa-twitter"), HOMEPAGE("fa fa-home");

    private String s;

    Icon(String s) {
        this.s = s;
    }

    public String getIcon() {
        return this.s;
    }

    public static String findByName(String name) {
        for (Icon icon : values()) {
            if (icon.name().equalsIgnoreCase(name)) {
                return icon.getIcon();
            }
        }
        return "fa fa-external-link";
    }
}
