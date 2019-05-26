package com.mfr414.uasproject.Model;

public class Task {
    public String jobTitle;
    public String jobDesc;
    public String status;

    public Task(String jobTitle, String jobDesc, String status) {
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.status = status;
    }

    public Task() {

    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
