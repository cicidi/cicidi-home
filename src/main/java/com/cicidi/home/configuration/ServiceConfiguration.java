package com.cicidi.home.configuration;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.service.GitHubService;
import com.cicidi.home.service.GoogleMapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import org.eclipse.egit.github.core.service.CommitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by cicidi on 2/19/2017.
 */
@Configuration
@ComponentScan("com.cicidi.home.*")
public class ServiceConfiguration {

    @Value("${google.api.key}")
    private String googleApiKey;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommitService commitService() {
        CommitService cservice = new CommitService();
        return cservice;
    }

    @Bean
    public GitHubService gitHubService() {
        return new GitHubService();
    }

    @Bean
    GoogleMapService googleMapService() {
        return new GoogleMapService();
    }

    @Bean
    public GeoApiContext getGeoApiContext() {
        return new GeoApiContext().setApiKey(googleApiKey);
    }

//    @Bean
//    ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }

    @PersistenceContext
    private EntityManager em;

    @Bean
    public SimpleJpaRepository<Profile, Long> simpleJpaRepository() {
        return new SimpleJpaRepository<Profile, Long>(Profile.class, em);
    }
}