package com.dao.disability.jobs;


import com.dao.disability.DSqlDisability;
import com.exp.EException;
import com.form.FSeed;
import com.form.disability.jobs.FJobLog;
import com.form.disability.jobs.FJobScheduler;
import com.lib.AppConfigs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DJobLog extends DSqlDisability {
    
    public FJobLog getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FJobLog bean = (FJobLog)seed;
        String SQL = "SELECT log.* FROM kpi_job_log log WHERE log.id=?";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformation(rs);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }
    
    public boolean updateScheduler(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FJobScheduler bean = (FJobScheduler)seed;
            String SQL_UPDATE_KPI_RANK = "UPDATE kpi_job_scheduler SET job_status=? WHERE id=?";
            List params = new ArrayList();
            params.add(bean.getJobStatus());
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_RANK, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;    
    }

    public FJobLog getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FJobLog bean = new FJobLog();
        try {
            bean.setId(rs.getInt(TABLE_KPI_JOB_LOG_ID));
            bean.setStartExec(rs.getDate(TABLE_KPI_JOB_LOG_START_EXEC));
            bean.setEndExec(rs.getDate(TABLE_KPI_JOB_LOG_END_EXEC));
            bean.setJobId(rs.getInt(TABLE_KPI_JOB_LOG_JOB_ID));
            bean.setMsgExec(rs.getString(TABLE_KPI_JOB_LOG_MSG_EXEC));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }
}
