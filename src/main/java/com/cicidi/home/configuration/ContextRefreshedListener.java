package com.cicidi.home.configuration;

import com.cicidi.home.domain.account.Account;
import com.cicidi.home.domain.repository.AccountRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.service.EntityService;
import com.cicidi.home.util.UsernameAlreadyInUseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EventHolderBean eventHolderBean;

    @Autowired
    EntityService entityService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    public void setEventHolderBean(EventHolderBean eventHolderBean) {
        this.eventHolderBean = eventHolderBean;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("load resume on startup");
        try {
            Profile profile = entityService.loadAndUpdate();
            Account account = accountRepository.findAccountByUsername("walter_chen");
            if (account == null) {
                account = new Account("walter_chen", "password", profile.getFirstName(), profile.getLastName(), profile.getContact().getEmail(), profile.getEntityId(), "ADMIN");
                accountRepository.createAccount(account);
            } else {
                accountRepository.update(account.getEntityId(), profile.getEntityId());
            }
        } catch (JAXBException e) {
            logger.error("JAXBException while loadAndUpdate resume");
        } catch (UsernameAlreadyInUseException e) {
        }
        eventHolderBean.setEventFired(true);
    }
}