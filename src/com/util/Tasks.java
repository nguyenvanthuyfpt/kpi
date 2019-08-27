package com.util;

import com.bo.disability.jobs.BJobScheduler;

import com.form.FBeans;

import com.form.disability.jobs.FJobScheduler;

import com.scheduler.QuartzJob;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Tasks implements ServletContextListener {

    private Thread t = null;
    private ServletContext context;

    private static final String TRIGGER_NAME = "MyTriggerName";
    private static final String GROUP = "simple_Group";
    private static final String JOB_NAME = "someJob";
    private static Scheduler scheduler;
    
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(" QuartzSchedulerApp main thread: " + Thread.currentThread().getName());

        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            FJobScheduler job = new FJobScheduler();
            FBeans beans = new BJobScheduler().getSchedulerJobs(job);
            for (int i=0;i<beans.size();i++) {
                job = (FJobScheduler)beans.get(i);
                Trigger trigger =  buildCronSchedulerTrigger(job); // for cron job trigger
                scheduleJob(trigger, job.getId()+"#"+job.getJobCode()+"#"+job.getJobExec());
            }            
        } catch (SchedulerException e) {

        } catch (Exception e) {

        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private static void scheduleJob(Trigger trigger, String execJob) throws Exception {
        QuartzJob job = new QuartzJob();
        JobDetail someJobDetail = JobBuilder.newJob(QuartzJob.class)
            .withDescription(execJob)
            .withIdentity(JOB_NAME, GROUP).build();
        
        scheduler.scheduleJob(someJobDetail, trigger);
    }

    private static Trigger buildCronSchedulerTrigger(FJobScheduler job) {
        String CRON_EXPRESSION = job.getJobCron(); 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_NAME, GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION)).build();
        
        return trigger;
    }
}
