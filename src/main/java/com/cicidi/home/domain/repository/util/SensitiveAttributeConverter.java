package com.cicidi.home.domain.repository.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by cicidi on 3/27/2017.
 */
@Converter
public class SensitiveAttributeConverter implements AttributeConverter<String, String> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    PasswordEncoder passwordEncoder;

    public SensitiveAttributeConverter() {
        logger.debug("init sensitive converter");
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String convertToDatabaseColumn(String s) {
        logger.info("decrypting");
        return passwordEncoder.encode(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        logger.info("decrypting");
        return s;
    }
}
