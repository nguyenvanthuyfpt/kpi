package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FRank extends FSeed {
    private int idNkt;
    private int dtlId;
    private int userId;
    private String userName;
    private String breadcrumb;
    private int level;
    private String fullName;
    private String danhgiaIds;
    private int id;
    private int parentID;
    private String code;
    private String name;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());
    private int total = 0;
    private int result = 0;
    private FBeans ranks;
    private int pageIndex;
    private int totalResult;
    
    private String reson;
    private String tochucKhac;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void reset() {
        this.id = 0;
        this.createDate = dateToString(getCurrentDate());
        this.modifyDate = dateToString(getCurrentDate());
        this.parentID = 0;
        this.code = "";
        this.name = "";
    }

    public void setRanks(FBeans ranks) {
        this.ranks = ranks;
    }

    public FBeans getRanks() {
        return ranks;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setDanhgiaIds(String danhgiaIds) {
        this.danhgiaIds = danhgiaIds;
    }

    public String getDanhgiaIds() {
        return danhgiaIds;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getReson() {
        return reson;
    }

    public void setTochucKhac(String tochucKhac) {
        this.tochucKhac = tochucKhac;
    }

    public String getTochucKhac() {
        return tochucKhac;
    }
    
    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
