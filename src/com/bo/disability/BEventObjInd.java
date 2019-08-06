package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DDataNkt;
import com.dao.disability.DDataPer;
import com.dao.disability.DEventInd;
import com.dao.disability.DEventObjInd;
import com.dao.disability.categorys.DIndicator;
import com.dao.disability.DIndicatorKpi;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataNkt;
import com.form.disability.FIndicatorKpi;
import com.form.disability.FInforNKT;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;

public class BEventObjInd {
    DEventObjInd dao = new DEventObjInd();

    public boolean insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            result = dao.insert(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
}
