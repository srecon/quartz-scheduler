package com.blu.scheduler;

import org.quartz.*;

import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by shamim on 11/12/15.
 */
public class CreateJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateJob.class);
    private static final String JOB_NAME="jobMedian";
    private static final String GROUP = "jobMedianGroup";
    private static final String TRIGGER_NAME= "trgMedian";
    private static final boolean isRecoverable = false;
    private static final Integer INTERVAL = 40; // in seconds



    private void create() throws SchedulerException {
        final Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // create JOb
        JobDetail jobMedian = newJob(AltynJob.class).withIdentity(JOB_NAME, GROUP)
                                                .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went down
                                                .build();
        // trigger
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        scheduleBuilder.withIntervalInSeconds(INTERVAL).repeatForever();

        Trigger trgMedian = newTrigger().withIdentity(TRIGGER_NAME, GROUP)
                                        .startNow().withSchedule(scheduleBuilder).build();
        LOGGER.info("Start the scheduler!!");
        scheduler.start();
        // Schedule the job
        scheduler.scheduleJob(jobMedian, trgMedian);

    }

    public static void main(String[] args) {
        LOGGER.info("Create and Start the scheduler!!");
        try {
            new CreateJob().create();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
