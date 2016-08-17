package com.galaxy.fym;

import com.galaxy.fym.common.CommonTest;
import com.galaxy.fym.common.SpringMVCAnnotationUtils;
import com.galaxy.fym.front.FrontTest;
import com.galaxy.fym.job.JobTest;
import com.galaxy.fym.model.ClassDocument;
import com.galaxy.fym.pay.Pay;
import com.galaxy.fym.remote.RemoteTest;
import com.galaxy.fym.service.ServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by fengyiming on 2016/6/24.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void out(){
        logger.debug("Main debug");
        logger.info("Main info");
        logger.warn("Main warm");
        logger.error("Main error");
    }

    public static void main(String[] args) throws IOException {
        List<ClassDocument> list = SpringMVCAnnotationUtils.getAllController("com.galaxy.fym");
    }
}
