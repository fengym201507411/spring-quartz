package com.galaxy.fym.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fengyiming on 2016/6/24.
 */
@Service
public class Job2Test {

    private static Logger logger = LoggerFactory.getLogger(JobTest.class);

    public static void out(){
        logger.debug("Job2Test debug");
        logger.info("Job2Test info");
        logger.warn("Job2Test warm");
        logger.error("Job2Test error");
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void jobPrint() throws Exception{
        System.out.println("--------Job2222Test jobPrint11111 thread time：" + new Date().getTime() + "-------------");
        Thread.sleep(2000l);
        System.out.println("--------Job2222Test jobPrint11111 thread time：" + new Date().getTime() + "-------------");
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void jobPrint2(){
        System.out.println("--------Job2222Test jobPrint22222 time：" + new Date().getTime()+"-------------");
    }
}
