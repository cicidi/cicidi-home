package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.io.DateAdapter;
import com.cicidi.home.util.Constants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cicidi on 2/18/17.
 */

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends DatabaseEntity {
    @XmlTransient
    private String monthNames[] = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    protected String name;
    protected Address address;
    @XmlTransient
    protected Date start;
    @XmlTransient
    protected Date end;
    @XmlTransient
    protected String startName;
    @XmlTransient
    protected String endName;
    @XmlTransient
    protected String length;
    protected String photo;
    protected String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElement(name = Constants.start, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        this.startName = monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
        if (this.end != null) {
            this.calLength();
        }
    }

    @XmlElement(name = Constants.end, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        this.endName = monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
        if (this.start != null) {
            this.calLength();
        }
    }

    @XmlElement(name = Constants.startName, required = true)
    public String getStartName() {
        return startName;
    }

    @XmlElement(name = Constants.endName, required = true)
    public String getEndName() {
        return endName;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @XmlElement(name = Constants.length, required = true)
    public String getLength() {
        return length;
    }

    private void calLength() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.start);
        int year_start = calendar.get(Calendar.YEAR);
        int month_start = calendar.get(Calendar.MONTH);
        calendar.setTime(this.end);
        int year_end = calendar.get(Calendar.YEAR);
        int month_end = calendar.get(Calendar.MONTH);
        int i = (year_end - year_start) * 12 + month_end - month_start;
        int y = i / 12;
        int m = i % 12;
        StringBuilder sb = new StringBuilder();
        if (y > 0) {
            String yrs = "yrs ";
            if (y == 1) {
                yrs = "yr ";
            }
            sb.append(y + yrs);
        }
        if (m > 0) {
            String mos = "mos ";
            if (m == 1) {
                mos = "mo ";
            }
            sb.append(m + mos);
        }
        this.length = sb.toString().trim();
    }


    public void setLength(String length) {
        //do nothing here
    }
}
