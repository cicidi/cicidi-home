package com.cicidi.home.domain.repository;

import com.cicidi.home.domain.resume.GeoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cicidi on 3/30/2017.
 */
@Repository
public interface GeoDataRepository extends JpaRepository<GeoData, Long> {

    @Query(value = "SELECT g from GeoData g where g.company=:company and g.address.city=:city and g.address.state=:state and g.address.country=:country")
    GeoData findByCompany(@Param("company") String company, @Param("city") String city, @Param("state") String state, @Param("country") String country);
}
