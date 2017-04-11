package com.cicidi.home.repository;

import com.cicidi.home.domain.resume.Profile;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by cicidi on 2/26/2017.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "profile", path = "/api/resume")
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);

    Profile findByUsername(String username);

    Profile findByEntityId(long entityId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT p from Profile p where p.contact.email=:email")
    Profile findByEmail(@Param("email") String email);

    long deleteByUsername(String username);
}
