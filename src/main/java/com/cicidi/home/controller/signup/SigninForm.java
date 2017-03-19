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

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.social.connect.UserProfile;

public class SigninForm extends Form{

    @NotEmpty
    private String username;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static SigninForm fromProviderUser(UserProfile providerUser) {
        SigninForm form = new SigninForm();
        form.setUsername(providerUser.getUsername());
        return form;
    }
}
