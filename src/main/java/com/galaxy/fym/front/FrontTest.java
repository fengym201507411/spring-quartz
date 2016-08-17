package com.galaxy.fym.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class FrontTest {

    private static Logger logger = LoggerFactory.getLogger(FrontTest.class);

    public static void out(){
        logger.debug("FrontTest debug");
        logger.info("FrontTest info");
        logger.warn("FrontTest warm");
        logger.error("FrontTest error");
    }
}
