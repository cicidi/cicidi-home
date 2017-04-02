package com.cicidi.home.repository;

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

    @Query(value = "SELECT g from GeoData g inner join g.address a where g.companyName=:companyName and a.city=:city and a.state=:state and a.country=:country")
    GeoData findByCompanyAddress(@Param("companyName") String companyName, @Param("city") String city, @Param("state") String state, @Param("country") String country);

    @Query(value = "SELECT g from GeoData g inner join g.address a where g.companyName=:companyName and a.city=:city ")
    GeoData findByCompanyAndCity(@Param("companyName") String companyName, @Param("city") String city);

}
