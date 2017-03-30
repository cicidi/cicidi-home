package com.cicidi.home.service;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.domain.repository.AccountRepository;
import com.cicidi.home.domain.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by cicidi on 3/29/2017.
 */
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Transactional
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
        profileRepository.deleteByUsername(account.getUsername());
    }

    @Transactional
    public void deleteAccount(String username) {
        accountRepository.deleteByUsername(username);
        profileRepository.deleteByUsername(username);
    }
}
