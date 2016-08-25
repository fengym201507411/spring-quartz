package com.galaxy.fym.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyiming on 2016/8/18.
 */
@Service
public class TaskService {
    private final Logger logger = LoggerFactory.getLogger("task service");

    @Autowired
    private ContextLifecycleScheduledTaskRegistrar contextLifecycleScheduledTaskRegistrar;

    public Object print(){
        try {
            List<CronTask> cronTasks = contextLifecycleScheduledTaskRegistrar.getCronTaskList();
            return cronTasks;
        }catch (Exception e){
            logger.error("------------------");
            return null;
        }
    }
}
