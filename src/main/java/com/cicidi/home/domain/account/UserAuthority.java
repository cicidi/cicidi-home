package com.cicidi.home.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Id
    private Account account;

    @NotNull
    @Id
    private String authority;

    public UserAuthority() {
    }

    public UserAuthority(Account account, String authority) {
        this.account = account;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    //overrides, getters, setters
}