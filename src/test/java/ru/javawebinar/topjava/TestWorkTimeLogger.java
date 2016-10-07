package ru.javawebinar.topjava;

import org.slf4j.Logger;

import static java.lang.System.nanoTime;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by nicolas on 07.10.2016.
 */
public class TestWorkTimeLogger {

    private static final Logger LOG = getLogger(TestWorkTimeLogger.class);

    public static long getStartTime() {
        return nanoTime();
    }

    public static void logFinalTime(String testClassName, long startTime) {
        long testTime = nanoTime() - startTime;
        LOG.info(testClassName + " work time: {}ms", Math.round(testTime / 1000000));
    }
}
