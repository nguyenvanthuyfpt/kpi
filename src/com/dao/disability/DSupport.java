package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FSupport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DSupport extends DSqlDisability {


    public FBeans getAllByIdNkt(Connection cnn, int idNkt,
                                int statusId) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT a.id_nkt, a.datecreate, a.reson, u.fullname, get_ncau_htro(a.id_nkt, a.datecreate::date,"+statusId+") ncau_htro, a.diadiem_kham, to_char(a.thoidiem_taikham,'mm/YYYY') thoidiem_taikham \n" +
            " FROM dr_support a LEFT JOIN users u ON a.user_id=u.user_id WHERE a.id_nkt=? AND a.status_id=? ORDER BY a.datecreate DESC";
        try {
            List params = new ArrayList();
            params.add(idNkt);
            params.add(statusId);
            prstm = prepareStatement(cnn, sql, params);
            rs = prstm.executeQuery();
            FSupport bean = null;
            while (rs != null && rs.next()) {
                bean = new FSupport();
                bean = getInforList(rs, 0);
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

    public FBeans getAllByParentId(Connection cnn,
                                   int parentId) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(parentId);

            prstm =
                    prepareStatement(cnn, SQL_SELECT_DM_HOTRO_BY_PARENT_ID, params);
            rs = prstm.executeQuery();
            FSupport bean = null;
            while (rs != null && rs.next()) {
                bean = new FSupport();
                bean = getInformation(rs, 0);
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

    public FSupport getById(Connection cnn, int nktId, int statusId, String datecreate) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FSupport bean = new FSupport();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();            
            params.add(nktId);
            params.add(statusId);            
            params.add(bean.stringToSqlDate(datecreate));
            params.add(nktId);
            params.add(statusId);
            params.add(bean.stringToSqlDate(datecreate));
            String SQL = "select distinct id_nkt, datecreate, reson, \n" +
                "'#'||array_to_string(array(select dm_hotro_ids from dr_support where 1=1 and id_nkt=? and status_id=? and datecreate=?),'#')||'#' as dm_hotro_ids, \n" +
                "status_id, nguonhotro, nguonhotro_id, dateform, dateto, \n" +
                "case when kn_chitra is null then -1 else kn_chitra end, the_bhyt, \n" +
                "case when sd_the is null then -1 else sd_the end, \n" +
                "case when sd_the_phcn is null then -1 else sd_the_phcn end, mtieu_gdinh, mtieu_dtri, ct_vltl, ct_hdtl, ct_antl, mdo_ptdl, mdo_hlong, dungcu_khac, \n" +
                "diadiem_kham, thoidiem_taikham " + 
                "from dr_support where 1=1 and id_nkt=? and status_id=? and datecreate=?";
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FSupport();
                bean = getInformationKpi(rs);
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
    
    public int countById(Connection cnn, int nktId, int statusId, String datecreate) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        int retval = 0;
        FSupport bean = new FSupport();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();            
            params.add(nktId);
            params.add(statusId);            
            params.add(bean.stringToSqlDate(datecreate));
            params.add(nktId);
            params.add(statusId);
            params.add(bean.stringToSqlDate(datecreate));
            String SQL = "select count(1) from (select distinct id_nkt, datecreate, reson, \n" +
                "'#'||array_to_string(array(select dm_hotro_ids from dr_support where 1=1 and id_nkt=? and status_id=? and datecreate=?),'#')||'#' as dm_hotro_ids, \n" +
                "status_id, nguonhotro, nguonhotro_id, dateform, dateto, \n" +
                "case when kn_chitra is null then -1 else kn_chitra end, the_bhyt, \n" +
                "case when sd_the is null then -1 else sd_the end, \n" +
                "case when sd_the_phcn is null then -1 else sd_the_phcn end, mtieu_gdinh, mtieu_dtri, ct_vltl, ct_hdtl, mdo_ptdl, mdo_hlong \n" +
                "from dr_support where 1=1 and id_nkt=? and status_id=? and datecreate=? ) a";
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                retval = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return retval;
    }


    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_HOTRO_DANGTAT, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException,
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FSupport bean = (FSupport)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_HOTRO, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean updateSupport(Connection cnn, FSeed seed) throws SQLException,
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FSupport bean = (FSupport)seed;
            List params = new ArrayList();
            params.add(bean.getMdoHlong());
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.getIdNkt());
            params.add(bean.getStatusId());
            params.add(bean.getHotroIds());
            prstm = prepareStatement(cnn, SQL_UPDATE_HOTRO_DANHGIA, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean delete(Connection cnn, int nktId, int statusId,
                          Date createDate) throws EException {
        boolean result =
            delete(cnn, TABLE_HOTRO, HOTRO_ID_NKT + EQUAL + nktId + 
                            AND + HOTRO_STATUS_ID + EQUAL + statusId +
                            AND + HOTRO_DATECREATE + EQUAL + "'" + createDate + "'") > 0;
        
            
        
        return result;
    }

    public FSupport getSupportByNktID_HotroID(Connection cnn, int nktId,
                                              int hotroId) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FSupport bean = new FSupport();
        PreparedStatement prstm = null;
        ResultSet rs = null;

        String SQL =
            "SELECT b.datecreate, b.reson, b.status_id, b.dateform, b.dateto, \n" +
            "b.nguonhotro, b.nguonhotro_id, b.dm_hotro_ids \n" +
            "FROM DR_HOTRO a \n" +
            "LEFT JOIN DR_SUPPORT b ON b.dm_hotro_ids LIKE '%#'||a.hotro_id||'#%' AND b.id_nkt=" +
            nktId + " \n" +
            //"LEFT JOIN DR_SUPPORT c ON b.dm_hotro_ids LIKE '%#'||a.hotro_id||'#%' AND c.id_nkt=" + nktId + " AND (c.status_id=1) \n" +
            "WHERE a.hotro_id=" + hotroId + " ORDER BY b.id DESC LIMIT 1";

        try {

            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();

            if (rs != null && rs.next()) {
                bean = new FSupport();

                if (rs.getDate(PARAM_01) != null)
                    bean.setDateCreate(bean.dateToString(rs.getDate(PARAM_01)));

                bean.setDuocHTCuThe(rs.getString(PARAM_02));
                bean.setStatusId(rs.getInt(PARAM_03));

                if (bean.getStatusId() == 1) {
                    if (rs.getDate(PARAM_04) != null) {
                        bean.setDateForm(bean.dateToString(rs.getDate(PARAM_04)));
                    }

                    if (rs.getDate(PARAM_05) != null) {
                        bean.setDateTo(bean.dateToString(rs.getDate(PARAM_05)));
                    }

                    if (bean.getDateForm() != null &&
                        bean.getDateTo() != null) {
                        bean.setDuocHTNgay(bean.getDateForm() + " - " +
                                           bean.getDateTo());
                    }
                }

                if (rs.getString(PARAM_06) != null) {
                    bean.setDuocHTNguonHT(rs.getString(PARAM_06));
                }

                if (rs.getInt(PARAM_07) != 0) {
                    bean.setNguonHoTroId(rs.getInt(PARAM_07));
                }

                if (rs.getString(PARAM_08) != null) {
                    bean.setHotroIds(rs.getString(PARAM_08));
                }

                if (bean.getStatusId() == 1) {
                    if (rs.getDate(PARAM_04) != null ||
                        rs.getDate(PARAM_05) != null) {
                        bean.setDuocHTNgay(bean.dateToString(rs.getDate(PARAM_04)) +
                                           " - " +
                                           bean.dateToString(rs.getDate(PARAM_05)));
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
        return bean;
    }


    public FSupport getByIdNkt_IdTypeSupport(Connection cnn, int idNkt,
                                             int idTypeSupport, int ky,
                                             int nam) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FSupport bean = new FSupport();
        bean.setStatusId(1);
        PreparedStatement prstm = null;
        ResultSet rs = null;
        //      String SQL="select b.datecreate,c.reson||' '||(select name from dr_hotro where parent_id=a.hotro_id and  c.dm_hotro_ids like '%#'||hotro_id||'#%')\n" +
        //      ",c.nguonhotro,c.dateform, c.dateto,d.reson||' '||(select name from dr_hotro where parent_id=a.hotro_id and  d.dm_hotro_ids like '%#'||hotro_id||'#%'),d.nguonhotro,d.dateform, d.dateto from DR_HOTRO a\n" +
        //      "left join dr_support b on b.dm_hotro_ids like '%#'||a.hotro_id||'#%' and b.id_nkt="+idNkt+" and b.status_id=0 and b.datecreate BETWEEN ? and ?\n" +
        //      "left join dr_support c on c.dm_hotro_ids like '%#'||a.hotro_id||'#%' and c.id_nkt="+idNkt+" and c.status_id=1 and c.dateform BETWEEN ? and ? and c.dateto BETWEEN ? and ?\n" +
        //      "left join dr_support d on d.dm_hotro_ids like '%#'||a.hotro_id||'#%' and d.id_nkt="+idNkt+" and d.status_id=1 and c.dateform BETWEEN ? and ? and (d.dateto is null or d.dateto >?)\n" +
        //      "where a.hotro_id="+idTypeSupport;


        String SQL = "SELECT c.datecreate,c.reson, \n" +
            "c.nguonhotro, c.dateform, c.dateto, d.reson as masd, \n" +
            "c.macbenh, c.dungcu_khac, c.phauthuat_khac, c.yte_khac, c.trocap_thuongxuyen_khac, \n" +
            "c.trocap_dotxuat_khac, c.caithien_khac, c.loaivay_khac, c.sotienvay_khac, c.mucdichvay_khac, \n" +
            "c.tochucxahoi_khac, c.nhucau_doisong_khac, c.nhucau_giaoduc_khac, \n" +
            "c.nguonhotro, c.nguonhotro_id, d.dateform, d.dateto from DR_HOTRO a \n" +
            "LEFT JOIN DR_SUPPORT b ON b.dm_hotro_ids LIKE '%#'||a.hotro_id||'#%' AND b.id_nkt=" +
            idNkt +

            " AND (b.status_id=0) AND (b.datecreate BETWEEN ? AND ?) \n" +
            "LEFT JOIN DR_SUPPORT c ON c.dm_hotro_ids LIKE '%#'||a.hotro_id||'#%' AND c.id_nkt=" +
            idNkt +
            " AND (c.status_id=1) AND (c.dateform BETWEEN ? AND ?) AND (c.dateto BETWEEN ? AND ?) \n" +
            "LEFT JOIN DR_SUPPORT d ON d.dm_hotro_ids LIKE '%#'||a.hotro_id||'#%' AND d.id_nkt=" +
            idNkt +
            " AND (d.status_id=1) AND (c.dateform BETWEEN ? AND ?) AND (d.dateto IS NULL OR d.dateto >?) \n" +
            "WHERE a.hotro_id=" + idTypeSupport;


        String tuNgay = "01/01/" + nam;
        String denNgay = "01/07/" + nam;

        if (ky == 2) {
            tuNgay = "01/07/" + nam;
            denNgay = "01/01/" + (nam + 1);
        }

        try {
            List params = new ArrayList();

            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            params.add(bean.stringToSqlDate(tuNgay));
            params.add(bean.stringToSqlDate(denNgay));
            params.add(bean.stringToSqlDate(denNgay));
            prstm = prepareStatement(cnn, SQL, params);

            System.out.println(SQL);

            rs = prstm.executeQuery();

            if (rs != null && rs.next()) {
                bean = new FSupport();
                if (rs.getDate(PARAM_01) != null)
                    bean.setDateCreate(bean.dateToString(rs.getDate(PARAM_01)));

                bean.setDuocHTCuThe(rs.getString(PARAM_02));
                bean.setDuocHTNguonHT(rs.getString(PARAM_03));

                if (rs.getDate(PARAM_04) != null ||
                    rs.getDate(PARAM_05) != null) {
                    bean.setDuocHTNgay(bean.dateToString(rs.getDate(PARAM_04)) +
                                       " - " +
                                       bean.dateToString(rs.getDate(PARAM_05)));

                    bean.setDateForm(bean.dateToString(rs.getDate(PARAM_04)));
                    bean.setDateTo(bean.dateToString(rs.getDate(PARAM_05)));
                }

                bean.setReson(rs.getString(PARAM_06));
                bean.setMacbenh(rs.getString(PARAM_07));
                bean.setDungcuKhac(rs.getString(PARAM_08));
                bean.setPhauthuatKhac(rs.getString(PARAM_09));
                bean.setYteKhac(rs.getString(PARAM_10));
                bean.setTrocapThuongXuyenKhac(rs.getString(PARAM_11));
                bean.setTrocapDotXuatKhac(rs.getString(PARAM_12));
                bean.setCaithienKhac(rs.getString(PARAM_13));
                bean.setLoaivayKhac(rs.getString(PARAM_14));
                bean.setSotienvayKhac(rs.getString(PARAM_15));
                bean.setMucdichvayKhac(rs.getString(PARAM_16));
                bean.setTochucXaHoiKhac(rs.getString(PARAM_17));
                bean.setNhucauDoiSongKhac(rs.getString(PARAM_18));
                bean.setNhucauGiaoDucKhac(rs.getString(PARAM_19));
                bean.setNguonhotro(rs.getString(PARAM_20));
                bean.setNguonHoTroId(rs.getInt(PARAM_21));
                if (rs.getDate(PARAM_22) != null)
                    bean.setDangDuocHTNgay(bean.dateToString(rs.getDate(PARAM_22)) +
                                           " - " +
                                           bean.dateToString(rs.getDate(PARAM_23)));

                if (rs.getDate(PARAM_04) != null ||
                    rs.getDate(PARAM_05) != null) {
                    bean.setDateForm(bean.dateToString(rs.getDate(PARAM_04)));
                    bean.setDateTo(bean.dateToString(rs.getDate(PARAM_05)));
                }
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

    public FSupport getInformation(ResultSet rs, int check) throws EException {
        final String LOCATION = "->getInformation()";
        FSupport bean = new FSupport();
        try {
            bean.setId(rs.getInt(HOTRO_ID));
            bean.setIdNkt(rs.getInt(HOTRO_ID_NKT));
            if (check == 0)
                bean.setFullName(rs.getString(USERS_FULLNAME));
            if (rs.getDate(HOTRO_DATECREATE) != null &&
                !rs.getDate(HOTRO_DATECREATE).equals("")) {
                bean.setDateCreate(bean.dateToString(rs.getDate(HOTRO_DATECREATE)));
                bean.setCreateDate(bean.dateToString(rs.getDate(HOTRO_DATECREATE)));
            }

            bean.setReson(rs.getString(HOTRO_RESON));
            bean.setHotroIds(rs.getString(HOTRO_DM_HOTRO_IDS));
            bean.setStatusId(rs.getInt(HOTRO_STATUS_ID));

            if (bean.getStatusId() == 1) {
                if (rs.getDate(HOTRO_DATEFORM) != null &&
                    !rs.getDate(HOTRO_DATEFORM).equals("")) {
                    bean.setDateForm(bean.dateToString(rs.getDate(HOTRO_DATEFORM)));
                }
                if (rs.getDate(HOTRO_DATETO) != null &&
                    !rs.getDate(HOTRO_DATETO).equals("")) {
                    bean.setDateTo(bean.dateToString(rs.getDate(HOTRO_DATETO)));
                }
                bean.setNguonhotro(rs.getString(HOTRO_NGUONHOTRO));
                bean.setNguonHoTroId(rs.getInt(HOTRO_NGUONHOTRO_ID));
            }

            bean.setMacbenh(rs.getString(HOTRO_MACBENH));
            bean.setDungcuKhac(rs.getString(HOTRO_DUNGCU_KHAC));
            bean.setPhauthuatKhac(rs.getString(HOTRO_PHAUTHUAT_KHAC));
            bean.setYteKhac(rs.getString(HOTRO_YTE_KHAC));
            bean.setTrocapThuongXuyenKhac(rs.getString(HOTRO_TROCAP_THUONGXUYEN_KHAC));
            bean.setTrocapDotXuatKhac(rs.getString(HOTRO_TROCAP_DOTXUAT_KHAC));
            bean.setCaithienKhac(rs.getString(HOTRO_CAITHIEN_KHAC));
            bean.setLoaivayKhac(rs.getString(HOTRO_LOAIVAY_KHAC));
            bean.setSotienvayKhac(rs.getString(HOTRO_SOTIENVAY_KHAC));
            bean.setMucdichvayKhac(rs.getString(HOTRO_MUCDICHVAY_KHAC));
            bean.setTochucXaHoiKhac(rs.getString(HOTRO_TOCHUCXAHOI_KHAC));
            bean.setNhucauDoiSongKhac(rs.getString(HOTRO_NHUCAU_DOISONG_KHAC));
            bean.setNhucauGiaoDucKhac(rs.getString(HOTRO_NHUCAU_GIAODUC_KHAC));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public FSupport getInformationKpi(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FSupport bean = new FSupport();
        try {

            bean.setIdNkt(rs.getInt(HOTRO_ID_NKT));
            bean.setCreateDate(bean.dateToString(rs.getDate(HOTRO_DATECREATE)));
            bean.setDateCreate(bean.dateToString(rs.getDate(HOTRO_DATECREATE)));
            bean.setReson(rs.getString(HOTRO_RESON));
            bean.setHotroIds(rs.getString(HOTRO_DM_HOTRO_IDS));
            bean.setStatusId(rs.getInt(HOTRO_STATUS_ID));

            if (bean.getStatusId() == 1) {
                if (rs.getDate(HOTRO_DATEFORM) != null &&
                    !rs.getDate(HOTRO_DATEFORM).equals("")) {
                    bean.setDateForm(bean.dateToString(rs.getDate(HOTRO_DATEFORM)));
                }
                if (rs.getDate(HOTRO_DATETO) != null &&
                    !rs.getDate(HOTRO_DATETO).equals("")) {
                    bean.setDateTo(bean.dateToString(rs.getDate(HOTRO_DATETO)));
                }
                bean.setNguonhotro(rs.getString(HOTRO_NGUONHOTRO));
                bean.setNguonHoTroId(rs.getInt(HOTRO_NGUONHOTRO_ID));
            }

            bean.setKnChiTra(rs.getInt(HOTRO_KN_CHITRA));
            bean.setTheBhyte(rs.getInt(HOTRO_THE_BHYT));
            bean.setSdThe(rs.getInt(HOTRO_SD_THE));
            bean.setSdThePhcn(rs.getInt(HOTRO_SD_THE_PHCN));
            bean.setMtieuGdinh(rs.getString(HOTRO_MTIEU_GDINH));
            bean.setMtieuDtri(rs.getString(HOTRO_MTIEU_DTRI));
            bean.setCtVltl(rs.getString(HOTRO_CT_VLTL));
            bean.setCtHdtl(rs.getString(HOTRO_CT_HDTL));
            bean.setCtAntl(rs.getString(HOTRO_CT_ANTL));
            bean.setMdoPtdl(rs.getString(HOTRO_MDO_PTDL));
            bean.setMdoHlong(rs.getString(HOTRO_MDO_HLONG));
            bean.setDungcuKhac(rs.getString("dungcu_khac"));
            if (rs.getDate(HOTRO_THOIDIEM_TAIKHAM) != null &&
                !rs.getDate(HOTRO_THOIDIEM_TAIKHAM).equals("")) {
                bean.setThoiDiemTK(bean.dateToString(rs.getDate(HOTRO_THOIDIEM_TAIKHAM)));
            }
  
            bean.setDiaDiemKham(rs.getInt(HOTRO_DIADIEM));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }


    public FSupport getInforList(ResultSet rs, int check) throws EException {
        final String LOCATION = "->getInforList()";
        FSupport bean = new FSupport();
        try {
            bean.setIdNkt(rs.getInt(HOTRO_ID_NKT));
            bean.setDateCreate(bean.dateToString(rs.getDate(HOTRO_DATECREATE)));
            bean.setReson(rs.getString(HOTRO_RESON));
            bean.setFullName(rs.getString(USERS_FULLNAME));
            bean.setNCauHtro(rs.getString("ncau_htro"));
            bean.setThoiDiemTK(rs.getString(HOTRO_THOIDIEM_TAIKHAM));
            bean.setDiaDiemKham(rs.getInt(HOTRO_DIADIEM));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FSupport bean = (FSupport)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getIdNkt());
            params.add(bean.getUserId());
            params.add(bean.stringToSqlDate(bean.getDateCreate()));
            params.add(bean.getReson());
            params.add(bean.getHotroIds());
            params.add(bean.getStatusId());
            params.add(bean.stringToSqlDate(bean.getDateForm()));
            params.add(bean.stringToSqlDate(bean.getDateTo()));
            params.add(bean.getNguonhotro());
            params.add(bean.getNguonHoTroId());

            params.add(bean.getMacbenh());
            params.add(bean.getDungcuKhac());
            params.add(bean.getPhauthuatKhac());
            params.add(bean.getYteKhac());
            params.add(bean.getTrocapThuongXuyenKhac());
            params.add(bean.getTrocapDotXuatKhac());
            params.add(bean.getCaithienKhac());
            params.add(bean.getLoaivayKhac());
            params.add(bean.getSotienvayKhac());
            params.add(bean.getMucdichvayKhac());
            params.add(bean.getTochucXaHoiKhac());
            params.add(bean.getNhucauDoiSongKhac());
            params.add(bean.getNhucauGiaoDucKhac());

            params.add(bean.getKnChiTra());
            params.add(bean.getTheBhyte());
            params.add(bean.getSdThe());
            params.add(bean.getSdThePhcn());
            params.add(bean.getMtieuGdinh());
            params.add(bean.getMtieuDtri());
            params.add(bean.getCtVltl());
            params.add(bean.getCtHdtl());          
            params.add(bean.getMdoPtdl());
            params.add(bean.getMdoHlong());
            params.add(bean.getCtAntl());
            
            params.add(bean.stringToSqlDate("01/"+bean.getThoiDiemTK()));
            params.add(bean.getDiaDiemKham());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }


    public boolean check1to6From6To12(Connection cnn,
                                      FSeed seed) throws EException {
        final String LOCATION = this.toString() + "check1to6From6To12()";
        PreparedStatement prstm = null;
        FSupport bean = (FSupport)seed;
        int year = bean.getYear(bean.stringToSqlDate(bean.getDateCreate()));
        int month = bean.getMonth(bean.stringToSqlDate(bean.getDateCreate()));
        String startDate = "01/01/" + year;
        String endDate = "01/07/" + year;
        if (month > 6) {
            startDate = "01/07/" + year;
            endDate = "01/01/" + (year + 1);
        }
        ResultSet rs = null;
        boolean result = false;
        try {
            List params = new ArrayList();
            params.add(bean.stringToSqlDate(startDate)); //>=
            params.add(bean.stringToSqlDate(endDate)); //<
            params.add(bean.getIdNkt());
            params.add(bean.getStatusId());
            prstm = prepareStatement(cnn, SQL_CHECK_INSERT_1_12, params);
            rs = prstm.executeQuery();
            result = rs != null && rs.next();

        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return result;
    }

    public String getStringAdd(Connection cnn, int id_nkt,
                               int status_id) throws EException {
        final String LOCATION = this.toString() + "getStringAdd()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String resutl = "";
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_HOTRO_STRIMG);
            prstm.setInt(PARAM_01, id_nkt);
            prstm.setInt(PARAM_02, status_id);

            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                if (!resutl.equals(""))
                    resutl += ",";
                resutl += rs.getString(PARAM_01);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return resutl;
    }


    public FSupport getNguonHt(Connection cnn, int idNKT) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FSupport bean = new FSupport();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(idNKT);
            prstm =
                    prepareStatement(cnn, SQL_SELECT_ALL_HOTRO_NGUON_HOTRO_BY, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FSupport();
                bean = getInformation(rs, idNKT);
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
}
