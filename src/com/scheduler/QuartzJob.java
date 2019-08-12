package com.scheduler;

import org.joda.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {

	//@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LocalDateTime localTime = LocalDateTime.now();
        System.out.println("Run QuartzJob at " + localTime.toString());

        MyTask mytask = new MyTask();
        mytask.perform();
    }
}
