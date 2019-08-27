package com.dao.disability.jobs;


import com.dao.disability.DSqlDisability;
import com.exp.EException;
import com.form.FSeed;
import com.form.disability.categorys.FDangTat;
import com.form.disability.jobs.FJobLog;
import com.form.disability.jobs.FJobScheduler;
import com.lib.AppConfigs;

import java.sql.Array;
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
        
    public int insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {           
            List params = setParams(seed);
            ps = prepareStatement(cnn, SQL_INSERT_KPI_JOB_LOG, params);
            rs = ps.executeQuery();
            if (rs!=null && rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = new ArrayList();
            FJobLog bean = (FJobLog)seed;
            String SQL_UPDATE_KPI_JOB_LOG = "UPDATE kpi_job_log SET end_exec=?, msg_exec=? WHERE id=?";
            params.add(bean.getEndExec());
            params.add(bean.getMsgExec());
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_JOB_LOG, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FJobLog bean = (FJobLog)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getStartExec());
            params.add(bean.getEndExec());
            params.add(bean.getJobId());
            params.add(bean.getMsgExec());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

    public FJobLog getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FJobLog bean = new FJobLog();
        try {
            bean.setId(rs.getInt(TABLE_KPI_JOB_LOG_ID));
            bean.setStartExec(rs.getTimestamp(TABLE_KPI_JOB_LOG_START_EXEC));
            bean.setEndExec(rs.getTimestamp(TABLE_KPI_JOB_LOG_END_EXEC));
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
