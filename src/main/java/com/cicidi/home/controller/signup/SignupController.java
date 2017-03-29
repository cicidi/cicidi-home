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
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.service.CrawlerService;
import com.cicidi.home.service.ProfileService;
import com.cicidi.home.util.UsernameAlreadyInUseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AccountRepository accountRepository;

    private final ProviderSignInUtils providerSignInUtils;

    @Autowired
    ConnectionRepository connectionRepository;

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
        UserProfile userProfile = connection != null ? connection.fetchUserProfile() : null;
        Object errorMessage = request.getAttribute("message", request.SCOPE_SESSION);
        if (errorMessage != null) {
            logger.debug("message signupForm.Method.Get {}", errorMessage);
            request.setAttribute("message", errorMessage, WebRequest.SCOPE_REQUEST);

            if (userProfile != null) {
                SignupForm signupForm = new SignupForm(userProfile);
                signupForm.setIsExist(true);
                return signupForm;
            } else {
                SignupForm signupForm = new SignupForm();
                signupForm.setIsExist(true);
                return signupForm;
            }
        }
        if (userProfile != null) {
            String email = userProfile.getEmail();
            Account account = email != null ? accountRepository.findAccountByEmail(email) : null;
            SignupForm signupForm = new SignupForm(userProfile);
            if (account != null) {
                request.setAttribute("message", new Message(MessageType.INFO, "Your " + email + " account already exist. Please sign in"), WebRequest.SCOPE_REQUEST);
                signupForm.setIsExist(true);
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
        Account account;
        try {
            account = createAccount(form, formBinding, request);
        } catch (UsernameAlreadyInUseException e) {
            request.setAttribute("message", new Message(MessageType.INFO, "user name " + form.getUsername() + " already exist"), WebRequest.SCOPE_SESSION);
            return "redirect:/signup";
        }
        if (account != null) {
            SignInUtils.signin(account.getUsername());
            providerSignInUtils.doPostSignUp(account.getUsername(), request);
            return "redirect:/home";
        }
        return null;
    }

    // internal helpers

    private Account createAccount(SignupForm form, BindingResult formBinding, WebRequest request) throws UsernameAlreadyInUseException {
        Connection<LinkedIn> connection = (Connection<LinkedIn>) providerSignInUtils.getConnectionFromSession(request);
        String username = form.getUsername();
        if (accountRepository.findAccountByUsername(username) != null) {
            logger.info("username: {} already exist", username);
            throw new UsernameAlreadyInUseException(username);
        }
        Profile profile = profileService.createProfile(connection, form.getUsername());
        Account account = new Account(username, form.getPassword(), form.getFirstName(), form.getLastName(), form.getEmail(), ROLE.USER.name());
        accountRepository.save(account);
        return account;
    }

}
