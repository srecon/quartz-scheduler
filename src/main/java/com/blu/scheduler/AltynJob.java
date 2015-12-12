package com.blu.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shamim on 07/12/15.
 */
public class AltynJob implements Job {
    private Logger logger = LoggerFactory.getLogger(AltynJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Do something useful!!", jobExecutionContext);
    }
}
