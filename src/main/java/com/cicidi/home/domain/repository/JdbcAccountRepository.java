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
package com.cicidi.home.domain.repository;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.util.UsernameAlreadyInUseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAccountRepository implements AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createAccount(Account user) throws UsernameAlreadyInUseException {
        //use current long value as key with change this to uuid in future.
        long id = System.currentTimeMillis();
        try {
            jdbcTemplate.update(
                    "insert into Account (entity_id,first_name, last_name, username, password, enabled,role) values (?,?, ?, ?, ?,?,?)", id,
                    user.getFirstName(), user.getLastName(), user.getUsername(),
                    passwordEncoder.encode(user.getPassword()), true, user.getRole());
        } catch (DuplicateKeyException e) {
            throw new UsernameAlreadyInUseException(user.getUsername());
        }
    }

    public Account findAccountByUsername(String username) {
        List<Account> accountList = jdbcTemplate.query("select username, first_name, last_name ,email,role from Account where username = ?", new Object[]{username},
                new RowMapper<Account>() {
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getString("username"), null, rs.getString("first_name"), rs
                                .getString("last_name"), rs
                                .getString("email"), rs.getString("role"));
                    }
                });
        if (accountList.size() > 0) {
            return accountList.get(0);
        }
        return null;
    }

    @Override
    public Account findAccountByEmail(String email) {
        List<Account> accountList = jdbcTemplate.query("select username, first_name, last_name ,email,role from Account where email = ?", new Object[]{email},
                new RowMapper<Account>() {
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getString("username"), null, rs.getString("first_name"), rs
                                .getString("last_name"), rs
                                .getString("email"), rs.getString("role"));
                    }
                });
        if (accountList.size() > 0) {
            return accountList.get(0);
        }
        return null;
    }

//    @Override
//    public void update(long accountId, long profileId) {
//
//        Object[] params = {profileId, accountId};
//
//        int[] types = {Types.VARCHAR, Types.BIGINT};
//
//        int rows = jdbcTemplate.update("update account set profile_id=? where entity_id=?", params, types);
//        logger.info(rows + " row(s) updated");
//    }


}
