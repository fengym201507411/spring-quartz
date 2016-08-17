package com.galaxy.fym.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class OtherTest {

    private static Logger logger = LoggerFactory.getLogger(OtherTest.class);

    public static void out(){
        logger.debug("OtherTest debug");
        logger.info("OtherTest info");
        logger.warn("OtherTest warm");
        logger.error("OtherTest error");
    }
}
