package com.cicidi.home.domain.resume;

import com.cicidi.home.domain.DatabaseEntity;
import com.cicidi.home.io.DateAdapter;
import com.cicidi.home.util.Constants;
import com.cicidi.home.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cicidi on 2/18/17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(
        name = "orgType",
        discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("organization")
@XmlTransient
//@XmlAccessorType(XmlAccessType.FIELD)
public class Organization extends DatabaseEntity {

    @XmlTransient
    protected String name;

    @XmlTransient
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "organization", cascade = CascadeType.ALL)
    @JsonManagedReference
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
    @XmlTransient
    protected String photo;
    @XmlTransient
    protected String icon;

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        address.setOrganization(this);
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
        this.startName = DateUtil.convertToString(start);
        if (this.end != null) {
            DateUtil.calLength(this.start, this.end);
        }
    }

    @XmlElement(name = Constants.end, required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.endName = DateUtil.convertToString(end);
        if (this.start != null) {
            DateUtil.calLength(this.start, this.end);
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

    @XmlElement
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @XmlElement
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


    public void setLength(String length) {
        //do nothing here
    }


}
