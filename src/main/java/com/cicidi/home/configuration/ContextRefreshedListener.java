package com.cicidi.home.configuration;

import com.cicidi.home.repository.AccountRepository;
import com.cicidi.home.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
        logger.info("load/fresh resume on startup");
//        try {
//            Profile profile = entityService.loadAndUpdate("walter_chen");
//        } catch (JAXBException e) {
//            logger.error("JAXBException while loadAndUpdate resume");
//        }
        eventHolderBean.setEventFired(true);
    }
}