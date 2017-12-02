package com.cicidi.home.controller.signin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ISigninService {
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    void signin();
}
