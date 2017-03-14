package com.cicidi.home.domain.repository;

import com.cicidi.home.domain.resume.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cicidi on 2/26/2017.
 */
@Repository
@RepositoryRestResource(collectionResourceRel = "profile", path = "/api/resume")
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
}
