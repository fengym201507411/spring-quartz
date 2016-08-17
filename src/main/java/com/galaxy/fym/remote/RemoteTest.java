package com.galaxy.fym.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class RemoteTest {

    private static Logger logger = LoggerFactory.getLogger(RemoteTest.class);

    public static void out(){
        logger.debug("RemoteTest debug");
        logger.info("RemoteTest info");
        logger.warn("RemoteTest warm");
        logger.error("RemoteTest error");
    }
}
