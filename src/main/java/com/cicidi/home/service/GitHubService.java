package com.cicidi.home.service;

import com.cicidi.home.domain.vo.WebLog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by cicidi on 2/19/2017.
 */
@Service
public class GitHubService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public List<RepositoryCommit> getGitCommits() throws IOException {

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

    public List<WebLog> createLog() {
        List<WebLog> webLogList = new ArrayList<>();
        List<RepositoryCommit> repositoryCommitList = null;
        try {
            repositoryCommitList = this.getGitCommits();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Comparator<RepositoryCommit> comparator = new Comparator<RepositoryCommit>() {
            public int compare(RepositoryCommit o1, RepositoryCommit o2) {
                return o2.getCommit().getAuthor().getDate().compareTo(o1.getCommit().getAuthor().getDate());
            }
        };
        Collections.sort(repositoryCommitList, comparator);

        int size = 0;
        for (RepositoryCommit repositoryCommit : repositoryCommitList) {
            if (size >= 2) break;
            WebLog log = new WebLog();
            log.setMessage(repositoryCommit.getCommit().getMessage());
            log.setLink(repositoryCommit.getSha());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(repositoryCommit.getCommit().getAuthor().getDate());
            log.setCalendar(calendar);
            webLogList.add(log);
            size++;
        }
        return webLogList;

    }


}
