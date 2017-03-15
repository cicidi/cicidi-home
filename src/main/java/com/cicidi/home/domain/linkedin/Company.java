package com.cicidi.home.domain.linkedin;

/**
 * Created by wchen00 on 3/4/17.
 */
public class Company {
    String id;
    String name;
    String type;
    //use String for now. ref https://developer.linkedin.com/docs/reference/industry-codes
    String industry;
    String ticker;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
