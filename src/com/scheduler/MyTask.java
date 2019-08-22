package com.scheduler;

import com.bo.disability.report.BReportKpi;

import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;

import com.form.disability.jobs.FJobScheduler;

import com.util.DaoUtil;
import com.util.Utilities;


import java.sql.Connection;

import java.util.Date;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MyTask {
    /**
     * It should handle any runtime exception if the application should continue
     * when encounters a exception, otherwise the application will stop
     */
    final static Logger logger  = Logger.getLogger(MyTask.class);
    
    public void perform(String execJob) throws EException{
        try {
            logger.info("BEGIN:perform " + execJob);
            Connection cnn = DBConnector.getConnection();
            String job_exec = "{call report_object(1,0,'8',2019,0)}";
            DaoUtil.execSchedulerJobs(cnn, job_exec);
            logger.info("END:perform");
        } catch (EException ex) {
            logger.error(ex.toString());
        }
    }
}
