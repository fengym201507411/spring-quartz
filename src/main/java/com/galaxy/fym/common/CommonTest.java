package com.galaxy.fym.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class CommonTest {

    private static Logger logger = LoggerFactory.getLogger(CommonTest.class);

    public static void out(){
        logger.debug("CommonTest debug");
        logger.info("CommonTest info");
        logger.warn("CommonTest warm");
        logger.error("CommonTest error");
    }
}
