package com.cicidi.home.configuration;

import com.cicidi.home.service.CrawlerService;
import com.cicidi.home.service.GitHubService;
import com.cicidi.home.service.GoogleMapService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.maps.GeoApiContext;
import org.eclipse.egit.github.core.service.CommitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Bean
    public LoadingCache<String, List<org.springframework.social.linkedin.api.Position>> getLinkInProfilePositionByUrl() {
        return CacheBuilder.newBuilder()
                .maximumSize(100) // maximum 100 records can be cached

                .expireAfterAccess(4, TimeUnit.HOURS)
                .build(new CacheLoader<String, List<org.springframework.social.linkedin.api.Position>>() {
                    @Override
                    public List<org.springframework.social.linkedin.api.Position> load(String url) throws Exception {
                        return crawlerService().fetchByUrl(url);
                    } // build the cacheloader
                });
    }

    @Bean
    public CrawlerService crawlerService() {
        return new CrawlerService();
    }

}