package com.cicidi.home.domain.account;

/**
 * Created by cicidi on 3/18/2017.
 */
public enum ROLE {
    USER, ADMIN, LINKEDINUSER;

    public UserAuthority asAuthorityFor(Account account) {
        return new UserAuthority(account, this.name());
    }
}
