/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cicidi.home.controller.signup;

import com.cicidi.home.controller.signin.SignInUtils;
import com.cicidi.home.domain.account.Account;
import com.cicidi.home.domain.account.ROLE;
import com.cicidi.home.domain.message.Message;
import com.cicidi.home.domain.message.MessageType;
import com.cicidi.home.domain.repository.AccountRepository;
import com.cicidi.home.domain.repository.ProfileRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.service.CrawlerService;
import com.cicidi.home.service.ProfileService;
import com.cicidi.home.util.UsernameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class SignupController {

    private final AccountRepository accountRepository;
    private final ProviderSignInUtils providerSignInUtils;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CrawlerService crawlerService;

    @Autowired
    ProfileService profileService;


    @Inject
    public SignupController(AccountRepository accountRepository,
                            ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {
        this.accountRepository = accountRepository;
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public SignupForm signupForm(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            UserProfile userProfile = connection.fetchUserProfile();
            String email = userProfile.getEmail();
            Account account = email == null ? accountRepository.findAccountByEmail(userProfile.getEmail()) : null;
            SignupForm signupForm = new SignupForm(userProfile);
            if (account != null) {
                request.setAttribute("message", new Message(MessageType.INFO, "Your " + email + " account already exist. Please sign in"), WebRequest.SCOPE_REQUEST);
                signupForm.setIsExist(true);
            } else {
            }
            return signupForm;
        } else {
            return new SignupForm();
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            return null;
        }
        Account account = accountRepository.findAccountByUsername(form.getUsername());
        if (account != null) {
            request.setAttribute("message", new Message(MessageType.INFO, "Your user name" + form.getUsername() + " already exist. Please the other one"), WebRequest.SCOPE_REQUEST);
            return "redirect:/signup";
        }
        account = createAccount(form, formBinding, request);
        if (account != null) {
            SignInUtils.signin(account.getUsername());
            providerSignInUtils.doPostSignUp(account.getUsername(), request);
            return "redirect:/home";
        }
        return null;
    }

    // internal helpers

    private Account createAccount(SignupForm form, BindingResult formBinding, WebRequest request) {
        try {
            Connection<LinkedIn> connection = (Connection<LinkedIn>) providerSignInUtils.getConnectionFromSession(request);
            Profile profile = profileService.createProfile(connection);
            Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName(), form.getEmail(), profile.getEntityId(), ROLE.USER.name());
            accountRepository.createAccount(account);
            return account;
        } catch (UsernameAlreadyInUseException e) {
            formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            return null;
        }
    }

}
