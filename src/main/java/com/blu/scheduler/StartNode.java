package com.blu.scheduler;

import org.quartz.*;

import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by shamim on 09/12/15.
 */
public class StartNode {
    private static Logger LOGGER = LoggerFactory.getLogger(StartNode.class);

    public static void main(String[] args) {
        LOGGER.info("Start node!");
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            LOGGER.info("Scheduler Instance ID:" + scheduler.getSchedulerInstanceId());
            // get the JOb
            List<String> jobGroupNames =  scheduler.getJobGroupNames();
            for(String jobGroup : jobGroupNames){
                GroupMatcher<JobKey> jobKeyGroupMatcher = GroupMatcher.groupEquals(jobGroup);
                Set<JobKey> jobKeys =  scheduler.getJobKeys(jobKeyGroupMatcher);
                for(JobKey jobKey : jobKeys){
                    //LOGGER.info("Job Key:" + jobKey);
                    if(jobKey.getName().equalsIgnoreCase("jobMedian")){
                        LOGGER.info("Found Job:" + jobKey.getName());
                        scheduler.start();
                    }
                }

            }

        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
