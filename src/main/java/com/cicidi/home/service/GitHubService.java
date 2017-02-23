package com.cicidi.home.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cicidi on 2/19/2017.
 */
@Service
public class GitHubService {

    @Autowired
    RestTemplate restTemplate;

    public List<RepositoryCommit> getGitCommits() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<RepositoryCommit> commitList = new ArrayList<>();
        try {
            String resp = restTemplate.getForObject("https://api.github.com/repos/cicidi/cicidi-home/commits", String.class);
            commitList = objectMapper.readValue(resp, new TypeReference<List<RepositoryCommit>>() {
            });
        } catch (Exception e) {
        }
        return commitList;
    }


}
