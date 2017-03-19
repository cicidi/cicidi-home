package com.cicidi.home.service;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.domain.repository.AccountRepository;
import com.cicidi.home.domain.repository.ProfileRepository;
import com.cicidi.home.domain.resume.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cicidi on 3/17/2017.
 */
@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CrawlerService crawlerService;

    public Profile getProfile(Account account) {
        return profileRepository.findByEntityId(account.getProfileId());
    }

    public Profile getProfile(String username) {
        Account account = accountRepository.findAccountByUsername(username);
        return profileRepository.findByEntityId(account.getProfileId());
    }

//    public Profile getProfile(String lastName, String firstName) {
//        profileRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
//    }

    public Profile createProfile(Connection<LinkedIn> connection) {

        if (connection == null) {
            return null;
        }
        String url = connection.getApi().profileOperations().getUserProfile().getPublicProfileUrl();
        LinkedInProfileFull linkedInProfileFull = connection.getApi().profileOperations().getProfileFullByPublicUrl(url);
        Profile profile = new Profile(linkedInProfileFull);
        List<Position> positionList = crawlerService.fetchByUrl(linkedInProfileFull.getPublicProfileUrl());
        if (positionList != null && positionList.size() > 0)
            profile.addWorkExperience(positionList);
        return profileRepository.save(profile);
    }
}
