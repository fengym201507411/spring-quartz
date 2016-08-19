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
public class JobTest {

    private static Logger logger = LoggerFactory.getLogger(JobTest.class);

    public static void out(){
        logger.debug("JobTest debug");
        logger.info("JobTest info");
        logger.warn("JobTest warm");
        logger.error("JobTest error");
    }

    public void jobPrint() throws Exception{
        System.out.println("--------Job1111Test jobPrint1 thread time：" + new Date().getTime() + "-------------");
        Thread.sleep(1000);
        System.out.println("-------- 1000   end-----------");
    }

    public void jobPrint2() throws Exception{
        System.out.println("--------Job1111Test jobPrint2 thread time：" + new Date().getTime()+"-------------");
        Thread.sleep(2000);
        System.out.println("-------- 2000   end-----------");
        /*while(true){
            System.out.println("----- all ---");
        }*/
    }
}
