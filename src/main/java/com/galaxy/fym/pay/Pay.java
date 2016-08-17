package com.galaxy.fym.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class Pay {
    private static Logger logger = LoggerFactory.getLogger(Pay.class);

    public static void out(){
        logger.debug("JobTest debug");
        logger.info("JobTest info");
        logger.warn("JobTest warm");
        logger.error("JobTest error");
    }
}
