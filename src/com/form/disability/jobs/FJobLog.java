package com.form.disability.jobs;

import com.form.FSeed;

import java.sql.Date;
import java.sql.Timestamp;

public class FJobLog extends FSeed{
    private int id;
    private Timestamp startExec;
    private Timestamp endExec;
    private int jobId;
    private String msgExec;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setMsgExec(String msgExec) {
        this.msgExec = msgExec;
    }

    public String getMsgExec() {
        return msgExec;
    }

    public void setStartExec(Timestamp startExec) {
        this.startExec = startExec;
    }

    public Timestamp getStartExec() {
        return startExec;
    }

    public void setEndExec(Timestamp endExec) {
        this.endExec = endExec;
    }

    public Timestamp getEndExec() {
        return endExec;
    }
}
