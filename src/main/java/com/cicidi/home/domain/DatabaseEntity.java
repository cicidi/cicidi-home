package com.cicidi.home.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by cicidi on 2/26/2017.
 */

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DatabaseEntity {

    @Id
    @XmlTransient
    @GeneratedValue
    private long entityId;

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
