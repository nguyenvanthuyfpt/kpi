package com.util;

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
        System.out.println(" QuartzSchedulerApp main thread: " +
                           Thread.currentThread().getName());

        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();

            // Trigger trigger = buildSimpleSchedulerTrigger();
            Trigger trigger =  buildCronSchedulerTrigger(); // for cron job trigger
            scheduleJob(trigger);
        } catch (SchedulerException e) {

        } catch (Exception e) {

        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private static void scheduleJob(Trigger trigger) throws Exception {

        JobDetail someJobDetail =
            JobBuilder.newJob(QuartzJob.class).withIdentity(JOB_NAME,
                                                            GROUP).build();

        scheduler.scheduleJob(someJobDetail, trigger);

    }

    private static Trigger buildCronSchedulerTrigger() {
        String CRON_EXPRESSION = "0 0/3 * 1/1 * ? *";
        Trigger trigger =
            TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME,
                                                     GROUP).withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION)).build();

        return trigger;
    }
}
