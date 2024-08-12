package nz.ac.auckland.se206.controllers;

public class Tasks {
    private String task;
    private Status status;
    public enum Status {
        NOT_STARTED,
        COMPLETED
    };
    
    public Tasks(String task) {
        this.task = task;
        status = Status.NOT_STARTED;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
