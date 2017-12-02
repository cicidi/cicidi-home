package com.cicidi.home.controller.signup;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.util.UsernameAlreadyInUseException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface ISignupService {


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    SignupForm signupForm(WebRequest request);

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request, HttpServletRequest httpServletRequest);

}
