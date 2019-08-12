package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FRank;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DRank extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FRank bean = (FRank)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_RANK_INFORMATION);
            pstmt.setString(PARAM_01, bean.getCode());
            pstmt.setInt(PARAM_02, bean.getId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public FRank getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        bean = (FRank)seed;
        String SQL = "select rank.*, 0 total from kpi_rank rank where rank.id=?";
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

    public FRank getRankById(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRankById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        bean = (FRank)seed;
        String SQL = "SELECT * FROM kpi_v_rank WHERE id= ?";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformationById(rs);
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
    
    public FRank getRankByDtlId(Connection cnn, int rankId, int nktId, int dtlId) throws EException {
        final String LOCATION = this.toString() + "getRankById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        String SQL = "SELECT r.*, case when p0=1 then 0 \n" + 
        "when p1=1 then 1 \n" + 
        "when p2=1 then 2 \n" + 
        "when p3=1 then 3 \n" + 
        "when p4=1 then 4 \n" + 
        "end result, v.parent_id, v.breadcrumb, u.fullname FROM kpi_data_rank r, kpi_v_rank v, users u " +
        "WHERE r.rank_id=v.id AND r.user_id=u.user_id AND r.rank_id=? and r.nkt_id=? AND r.id=? ORDER BY r.create_date DESC";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, rankId);
            prstm.setInt(PARAM_02, nktId);
            prstm.setInt(PARAM_03, dtlId);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInforByDtlId(rs);
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FRank bean = (FRank)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_RANK_HAVECHILD);
            pstmt.setInt(PARAM_01, bean.getId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_KPI_RANK, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FRank bean = (FRank)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_RANK, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FRank bean = (FRank)seed;
        return 0 < delete(cnn, TABLE_KPI_RANK, KPI_RANK_ID + EQUAL + bean.getId());
    }
    
    public FRank getInforByDtlId(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setDtlId(rs.getInt("id"));
            rank.setId(rs.getInt("rank_id"));
            rank.setCreateDate(rank.dateToString(rs.getDate("create_date")));
            rank.setResult(rs.getInt("result"));
            rank.setParentID(rs.getInt("parent_id"));
            rank.setBreadcrumb(rs.getString("breadcrumb"));
            rank.setUserName(rs.getString("fullname"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    } 

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FRank bean = (FRank)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID()); 
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getCode());
            params.add(bean.getName());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FBeans getRanksByParent(Connection cnn, int parentId) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT rank.*, 0 total FROM kpi_rank rank";
        sql += " WHERE rank.parent_id = ? ";
        sql += " ORDER BY code";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, parentId);
            rs = prstm.executeQuery();
            FRank bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInformation(rs);
                  beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getRanksByDis(Connection cnn, int parentId, int nktId) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT rank.*, data.total FROM kpi_rank rank LEFT JOIN (select * from kpi_v_data_rank where 1=1 ";
        sql += " AND nkt_id = ? ) data on rank.id=data.rank_id WHERE rank.parent_id = ? ";
        sql += " ORDER BY code";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, nktId);
            prstm.setInt(2, parentId); 
            rs = prstm.executeQuery();
            FRank bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInformation(rs);
                  beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getRanksByR_D(Connection cnn, int rankId, int nktId) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT r.*, case when p0=1 then 0 \n" + 
                      "when p1=1 then 1 \n" + 
                      "when p2=1 then 2 \n" + 
                      "when p3=1 then 3 \n" + 
                      "when p4=1 then 4 \n" + 
                      "end result, u.fullname FROM kpi_data_rank r, users u WHERE r.user_id=u.user_id AND rank_id=? and nkt_id=? ORDER BY create_date DESC";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, rankId);
            prstm.setInt(2, nktId); 
            rs = prstm.executeQuery();
            FRank bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInforByR_D(rs);
                  beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "";        
        try {
            sql = SQL_SELECT_RANK;
            prstm = cnn.prepareStatement(sql);            
            rs = prstm.executeQuery();
            String members = ",";
            FRank bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FRank();
                    bean.setId(id);
                    bean.setCode(rs.getString(PARAM_02));
                    bean.setName(rs.getString(PARAM_03));
                    bean.setParentID(rs.getInt(PARAM_04));
                    if (id > 0) {
                        members += id + ",";
                        beans.add(bean);
                    }
                }
                if (start) {
                    if (all || members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_05);
                        bean = new FRank();
                        bean.setId(id);
                        bean.setCode(rs.getString(PARAM_06));
                        bean.setName(rs.getString(PARAM_07));
                        bean.setParentID(rs.getInt(PARAM_08));
                        if (id > 0) {
                            members += id + ",";
                            beans.add(bean);
                        } else {
                            all = true;
                        }
                    } else if (!all) {
                        members = null;
                        start = false;
                    }
                }
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FRank getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setParentID(rs.getInt(KPI_RANK_PARENT_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setModifyDate(rank.dateToString(rs.getDate(KPI_RANK_MODIFY_DATE)));
            rank.setCode(rs.getString(KPI_RANK_CODE));
            rank.setName(rs.getString(KPI_RANK_NAME));
            rank.setTotal(rs.getInt("total"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInforByR_D(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setUserName(rs.getString("fullname"));
            rank.setResult(rs.getInt("result"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInformationById(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt("id"));
            rank.setParentID(rs.getInt("parent_id"));
            rank.setName(rs.getString("name"));      
            rank.setLevel(rs.getInt("level"));
            rank.setBreadcrumb(rs.getString("breadcrumb"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    } 
}
