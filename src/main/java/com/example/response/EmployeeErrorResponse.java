package com.example.response;

public class EmployeeErrorResponse {

    private long timeStamp;

    private int status;

    private String message;

    public EmployeeErrorResponse(long timeStamp, int status, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
