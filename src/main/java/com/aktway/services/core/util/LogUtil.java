package com.aktway.services.core.util;

/**
 * Created by rames on 26-02-2019.
 */

import org.apache.log4j.Logger;

public class LogUtil {
    public LogUtil() {
    }

    public static void logTotalTimeTaken(Logger logger, String component, long startTime) {
        long endTime = System.currentTimeMillis();
        logger.info("Total " + component + " Time " + (endTime - startTime) + " milli seconds");
    }
}

