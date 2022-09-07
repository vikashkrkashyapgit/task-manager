package com.phonepe.model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private  final String id;
    private  String name;
    private Date deadline;
    private boolean isCompleted;

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Task(String name, Date deadline, boolean isCompleted) {
        this.id = UUID.randomUUID().toString();
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

//    public User getAssignee() {
//        return assignee;
//    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
