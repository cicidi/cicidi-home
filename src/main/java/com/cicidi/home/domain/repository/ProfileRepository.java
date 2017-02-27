package com.cicidi.home.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cicidi on 2/26/2017.
 */
@Repository
public interface ProfileRepository<Profile> extends CrudRepository {
}
