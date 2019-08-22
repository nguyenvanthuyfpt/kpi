package com.form.disability.jobs;

import com.form.FSeed;

import java.sql.Date;

public class FJobLog extends FSeed{
    private int id;
    private Date startExec;
    private Date endExec;
    private int jobId;
    private String msgExec;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStartExec(Date startExec) {
        this.startExec = startExec;
    }

    public Date getStartExec() {
        return startExec;
    }

    public void setEndExec(Date endExec) {
        this.endExec = endExec;
    }

    public Date getEndExec() {
        return endExec;
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
}
