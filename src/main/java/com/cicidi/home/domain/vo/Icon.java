package com.cicidi.home.domain.vo;

/**
 * Created by wchen00 on 3/10/17.
 */
public enum Icon {


    FACEBOOK("s"), LINKEDIN("ab"), EMAIL("email"), GITHUB("fa fa-github-alt");
    private String s;

    Icon(String s) {
        this.s = s;
    }

    public String getIcon() {
        return this.s;
    }
}
