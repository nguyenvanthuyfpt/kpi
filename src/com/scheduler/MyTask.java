package com.scheduler;

import com.bo.disability.report.BReportKpi;

import com.exp.EException;

import com.form.FBeans;

import com.util.Utilities;


import java.util.Date;
import java.sql.SQLException;

public class MyTask {
	/**
	 * It should handle any runtime exception if the application should continue
	 * when encounters a exception, otherwise the application will stop
	 */
	public void perform() {
      System.out.println("\tMyTask performed by thread: " + Thread.currentThread().getName());
      try {
          FBeans beans = new BReportKpi().getDataReportObject(1, 0, Utilities.getCurrentMonth(), Utilities.getCurrentYear(new Date()), 0);
      } catch (EException ex) {
          
      } catch (SQLException e) {
      
      }
    }
}
