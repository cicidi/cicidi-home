package com.cicidi.home.service;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.repository.AccountRepository;
import com.cicidi.home.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return this.getProfile(account.getUsername());
    }

    public Profile getProfile(String username) {
        Profile profile = profileRepository.findByUsername(username);
        return profile;
    }

    //    public Profile getProfile(String lastName, String firstName) {
//        profileRepository.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
//    }
    @Transactional
    public Profile createProfile(Connection<LinkedIn> connection, String username) {

        if (connection == null) {
            return null;
        }

        String url = connection.getApi().profileOperations().getUserProfile().getPublicProfileUrl();
        LinkedInProfileFull linkedInProfileFull = connection.getApi().profileOperations().getProfileFullByPublicUrl(url);
        Profile profile = profileRepository.findByEmail(linkedInProfileFull.getEmailAddress());
        if (profile != null) {
            profileRepository.delete(profile);
        }
        profile = new Profile(linkedInProfileFull);
        List<Position> positionList = crawlerService.fetchByUrl(linkedInProfileFull.getPublicProfileUrl());
        if (positionList != null && positionList.size() > 0)
            profile.addWorkExperience(positionList);
        profile.setUsername(username);
        return profileRepository.save(profile);
    }

    @Transactional
    public void delete(String username) {
        profileRepository.deleteByUsername(username);
    }
}
