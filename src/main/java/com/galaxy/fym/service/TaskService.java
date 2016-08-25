package com.galaxy.fym.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.stereotype.Service;

/**
 * Created by fengyiming on 2016/8/18.
 */
@Service
public class TaskService {
    private final Logger logger = LoggerFactory.getLogger("task service");

    @Autowired
    private ScheduledExecutorFactoryBean scheduledExecutorFactoryBean;

    public Object print(){
        try {
            return null;
        }catch (Exception e){
            logger.error("------------------");
            return null;
        }
    }
}
