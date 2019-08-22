package com.scheduler;

import com.exp.EException;

import com.util.DaoUtil;

import org.apache.log4j.Logger;

import org.joda.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {
    
    final static Logger logger  = Logger.getLogger(QuartzJob.class);
    
    private String execJob;
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        MyTask mytask = new MyTask();
        String temp = context.getJobDetail().getDescription();
        logger.info("execJob "+temp);
        try {
            mytask.perform(execJob);
        } catch (EException ex) {
            logger.error(ex.toString());
        }
    }

    public void setExecJob(String execJob) {
        this.execJob = execJob;
    }

    public String getExecJob() {
        return execJob;
    }
}
