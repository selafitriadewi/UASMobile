package com.mfr414.uasproject.Model;

public class Task {
    public String jobTitle;
    public String jobDesc;
    public String Status;

    public Task(String jobTitle, String jobDesc, String status) {
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        Status = status;
    }

    public Task() {

    }
}
