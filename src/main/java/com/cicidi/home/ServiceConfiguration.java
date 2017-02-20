package com.cicidi.home;

import com.cicidi.home.service.GitHubService;
import org.eclipse.egit.github.core.service.CommitService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cicidi on 2/19/2017.
 */
@Configuration
@ComponentScan("com.cicidi.home.*")
public class ServiceConfiguration {

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

}