package com.form.disability;

import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FTable extends FSeed {
    private String tableName;
    private String id;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
