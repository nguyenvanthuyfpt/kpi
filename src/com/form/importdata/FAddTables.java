package com.form.importdata;

import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FAddTables extends FSeed
{
    private int id;
    private String nameTable;
    
    public void reset(){
        this.setId(0);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

}
