package com.form.disability;

import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;

import org.apache.struts.upload.FormFile;

public class FSupport extends FSeed {

    private int id;
    private int statusId;
    private String mode;
    private String createDate = Formater.date2str(getCurrentDate());
    
    private String supportSel;
    private int idNkt;
    private int userId;
    private String fullName;
    private String dateCreate = Formater.date2str(getCurrentDate()); 
    private String reson;
    private String nguonhotro;
    private String hotroIds;
    private int[] supportIds;
    private String dateForm = Formater.date2str(getCurrentDate());
    private String dateTo = Formater.date2str(getCurrentDate());
    
    private String nCauHtro;
    private int nguonhotroId;

    private String dangDuocHTCuThe;
    private String dangDuocHTNguonHT;
    private String dangDuocHTNgay;

    private String duocHTCuThe;
    private String duocHTNguonHT;
    private String duocHTNgay;

    // Add by ThuyNV
    private String macbenh;
    private String dungcuKhac;
    private String phauthuatKhac;
    private String yteKhac;
    private String trocapThuongXuyenKhac;
    private String trocapDotXuatKhac;
    private String caithienKhac;
    private String loaivayKhac;
    private String sotienvayKhac;
    private String mucdichvayKhac;
    private String tochucXaHoiKhac;
    private String nhucauDoiSongKhac;
    private String nhucauGiaoDucKhac;
    
    private String thoiDiemTK = "";
    private int diaDiemKham;

    // Add for KPI
    public int knChiTra;
    public int theBhyte;
    public int sdThe;
    public int sdThePhcn;
    public String mtieuGdinh;
    public String mtieuDtri;
    public String ctVltl;
    public String ctHdtl;
    public String ctAntl;
    public String mdoPtdl;
    public String mdoHlong;

    public void reset() {
        this.id = 0;
        this.dateCreate = Formater.date2str(getCurrentDate());
        this.createDate = Formater.date2str(getCurrentDate());
        this.dateForm = Formater.date2str(getCurrentDate());
        this.dateTo = Formater.date2str(getCurrentDate());
        this.reson = "";
        this.hotroIds = "";
        this.supportIds = null;
        this.macbenh = "";
        this.dungcuKhac = "";
        this.yteKhac = "";
        this.trocapDotXuatKhac = "";
        this.trocapThuongXuyenKhac = "";
        this.caithienKhac = "";
        this.loaivayKhac = "";
        this.sotienvayKhac = "";
        this.mucdichvayKhac = "";
        this.nhucauDoiSongKhac = "";
        this.nhucauGiaoDucKhac = "";    
        this.nguonhotroId = 0;
        this.nguonhotro = "";
        
        this.knChiTra = 0;
        this.theBhyte = 0;
        this.sdThe = 0;
        this.sdThePhcn = 0;        
        this.mtieuGdinh = "";
        this.mtieuDtri = "";
        this.ctVltl = "";
        this.ctHdtl = "";
        this.diaDiemKham = 0;
        this.thoiDiemTK = "";
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public String getHotroIds() {
        return hotroIds;
    }

    public void setHotroIds(String hotroIds) {
        if (hotroIds.length() > 3) {
            this.hotroIds = hotroIds.replaceAll("#0#", "#");
        } else {
            this.hotroIds = "";
        }
    }

    public int[] getSupportIds() {
        return supportIds;
    }

    public void setSupportIds(int[] supportIds) {
        if (supportIds != null && supportIds.length > 0) {
            String hotroIds = "#";
            for (int i = 0; i < supportIds.length; i++) {
                hotroIds += supportIds[i] + "#";
            }
            setHotroIds(hotroIds);
        }
        this.supportIds = supportIds;
    }
    */


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDateForm() {
        return dateForm;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getNguonhotro() {
        return nguonhotro;
    }

    public void setNguonhotro(String nguonhotro) {
        this.nguonhotro = nguonhotro;
    }

    public String getDangDuocHTCuThe() {
        return dangDuocHTCuThe;
    }

    public void setDangDuocHTCuThe(String dangDuocHTCuThe) {
        this.dangDuocHTCuThe = dangDuocHTCuThe;
    }

    public String getDangDuocHTNguonHT() {
        return dangDuocHTNguonHT;
    }

    public void setDangDuocHTNguonHT(String dangDuocHTNguonHT) {
        this.dangDuocHTNguonHT = dangDuocHTNguonHT;
    }

    public String getDangDuocHTNgay() {
        return dangDuocHTNgay;
    }

    public void setDangDuocHTNgay(String dangDuocHTNgay) {
        this.dangDuocHTNgay = dangDuocHTNgay;
    }

    public String getDuocHTCuThe() {
        return duocHTCuThe;
    }

    public void setDuocHTCuThe(String duocHTCuThe) {
        this.duocHTCuThe = duocHTCuThe;
    }

    public String getDuocHTNguonHT() {
        return duocHTNguonHT;
    }

    public void setDuocHTNguonHT(String duocHTNguonHT) {
        this.duocHTNguonHT = duocHTNguonHT;
    }

    public String getDuocHTNgay() {
        return duocHTNgay;
    }

    public void setDuocHTNgay(String duocHTNgay) {
        this.duocHTNgay = duocHTNgay;
    }

    public int getNguonHoTroId() {
        return nguonhotroId;
    }

    public void setNguonHoTroId(int nguonhotroId) {
        this.nguonhotroId = nguonhotroId;
    }

    public void setMacbenh(String macbenh) {
        this.macbenh = macbenh;
    }

    public String getMacbenh() {
        return macbenh;
    }

    public void setDungcuKhac(String dungcuKhac) {
        this.dungcuKhac = dungcuKhac;
    }

    public String getDungcuKhac() {
        return dungcuKhac;
    }

    public void setPhauthuatKhac(String phauthuatKhac) {
        this.phauthuatKhac = phauthuatKhac;
    }

    public String getPhauthuatKhac() {
        return phauthuatKhac;
    }

    public void setYteKhac(String yteKhac) {
        this.yteKhac = yteKhac;
    }

    public String getYteKhac() {
        return yteKhac;
    }

    public void setTrocapThuongXuyenKhac(String trocapThuongXuyenKhac) {
        this.trocapThuongXuyenKhac = trocapThuongXuyenKhac;
    }

    public String getTrocapThuongXuyenKhac() {
        return trocapThuongXuyenKhac;
    }

    public void setTrocapDotXuatKhac(String trocapDotXuatKhac) {
        this.trocapDotXuatKhac = trocapDotXuatKhac;
    }

    public String getTrocapDotXuatKhac() {
        return trocapDotXuatKhac;
    }

    public void setCaithienKhac(String caithienKhac) {
        this.caithienKhac = caithienKhac;
    }

    public String getCaithienKhac() {
        return caithienKhac;
    }

    public void setLoaivayKhac(String loaivayKhac) {
        this.loaivayKhac = loaivayKhac;
    }

    public String getLoaivayKhac() {
        return loaivayKhac;
    }

    public void setSotienvayKhac(String sotienvayKhac) {
        this.sotienvayKhac = sotienvayKhac;
    }

    public String getSotienvayKhac() {
        return sotienvayKhac;
    }

    public void setMucdichvayKhac(String mucdichvayKhac) {
        this.mucdichvayKhac = mucdichvayKhac;
    }

    public String getMucdichvayKhac() {
        return mucdichvayKhac;
    }

    public void setTochucXaHoiKhac(String tochucXaHoiKhac) {
        this.tochucXaHoiKhac = tochucXaHoiKhac;
    }

    public String getTochucXaHoiKhac() {
        return tochucXaHoiKhac;
    }

    public void setNhucauDoiSongKhac(String nhucauDoiSongKhac) {
        this.nhucauDoiSongKhac = nhucauDoiSongKhac;
    }

    public String getNhucauDoiSongKhac() {
        return nhucauDoiSongKhac;
    }

    public void setNhucauGiaoDucKhac(String nhucauGiaoDucKhac) {
        this.nhucauGiaoDucKhac = nhucauGiaoDucKhac;
    }

    public String getNhucauGiaoDucKhac() {
        return nhucauGiaoDucKhac;
    }

    public void setHotroIds(String hotroIds) {
        this.hotroIds = hotroIds;
    }

    public String getHotroIds() {
        return hotroIds;
    }

    public void setSupportIds(int[] supportIds) {
        this.supportIds = supportIds;
    }

    public int[] getSupportIds() {
        return supportIds;
    }

    public void setKnChiTra(int knChiTra) {
        this.knChiTra = knChiTra;
    }

    public int getKnChiTra() {
        return knChiTra;
    }

    public void setTheBhyte(int theBhyte) {
        this.theBhyte = theBhyte;
    }

    public int getTheBhyte() {
        return theBhyte;
    }

    public void setSdThe(int sdThe) {
        this.sdThe = sdThe;
    }

    public int getSdThe() {
        return sdThe;
    }

    public void setSdThePhcn(int sdThePhcn) {
        this.sdThePhcn = sdThePhcn;
    }

    public int getSdThePhcn() {
        return sdThePhcn;
    }

    public void setMtieuGdinh(String mtieuGdinh) {
        this.mtieuGdinh = mtieuGdinh;
    }

    public String getMtieuGdinh() {
        return mtieuGdinh;
    }

    public void setMtieuDtri(String mtieuDtri) {
        this.mtieuDtri = mtieuDtri;
    }

    public String getMtieuDtri() {
        return mtieuDtri;
    }

    public void setCtVltl(String ctVltl) {
        this.ctVltl = ctVltl;
    }

    public String getCtVltl() {
        return ctVltl;
    }

    public void setCtHdtl(String ctHdtl) {
        this.ctHdtl = ctHdtl;
    }

    public String getCtHdtl() {
        return ctHdtl;
    }

    public void setMdoPtdl(String mdoPtdl) {
        this.mdoPtdl = mdoPtdl;
    }

    public String getMdoPtdl() {
        return mdoPtdl;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMdoHlong(String mdoHlong) {
        this.mdoHlong = mdoHlong;
    }

    public String getMdoHlong() {
        return mdoHlong;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setNCauHtro(String nCauHtro) {
        this.nCauHtro = nCauHtro;
    }

    public String getNCauHtro() {
        return nCauHtro;
    }

    public void setSupportSel(String supportSel) {
        this.supportSel = supportSel;
    }

    public String getSupportSel() {
        return supportSel;
    }

    public void setCtAntl(String ctAntl) {
        this.ctAntl = ctAntl;
    }

    public String getCtAntl() {
        return ctAntl;
    }

    public void setThoiDiemTK(String thoiDiemTK) {
        this.thoiDiemTK = thoiDiemTK;
    }

    public String getThoiDiemTK() {
        return thoiDiemTK;
    }

    public void setDiaDiemKham(int diaDiemKham) {
        this.diaDiemKham = diaDiemKham;
    }

    public int getDiaDiemKham() {
        return diaDiemKham;
    }
}
