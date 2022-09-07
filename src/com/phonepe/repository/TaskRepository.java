package com.phonepe.repository;

import com.phonepe.constans.Action;
import com.phonepe.constans.Statistics;
import com.phonepe.filter.Filter;
import com.phonepe.model.Task;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.*;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private List<Task> todo = new ArrayList<>();
    private HashMap<Task, List<Action>> activities  = new HashMap<>();
    private HashMap<Statistics, List<Task>> statistics  = new HashMap<>();


    public String addTask(Task task) {
        this.tasks.add(task);
        this.todo.add(task);
        this.addActivity(Action.ADDED, task);
        this.addStatistics(Statistics.ADDED, task);
        return task.getId();
    }

    public Task getTask(String taskId) {
        Task currentTask = null;
        if (this.tasks.size() == 0) {
            return null;
        }

        for (Task task: this.tasks) {
            if (task.getId().equals(taskId)) {
                currentTask = task;
                break;
            }
        }
        return currentTask;
    }

    public void removeTask(String taskId) {
        if (this.tasks.size() == 0) {
            return;
        }

        for (Task task: this.tasks) {
            if (task.getName().equals(taskId)) {
                this.tasks.remove(task);
                this.addActivity(Action.DELETED, task);
            }
        }
    }

    public void modifyTask(Task modifyTask) {
        if (this.tasks.size() == 0) {
            return;
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getId().equals(modifyTask.getId())) {
                this.tasks.set(i, modifyTask);
                this.addActivity(Action.MODIFIED, modifyTask);
            }
        }
    }

    public List<Task> listTask(Filter f1) {
        Collections.sort(this.tasks, new Filter());
        return this.tasks;
    }



    public HashMap<Task, List<Action>> getActivityLog() {
        return this.activities;
    }

    public HashMap<Task, List<Action>> getActivityLog(Date start, Date end) {
        HashMap<Task, List<Action>> filterActivity = new HashMap<>();

        if(this.activities.size() == 0) {
            return filterActivity;
        }

        for (Map.Entry<Task, List<Action>> activity: this.activities.entrySet()) {
            Task task = activity.getKey();
            if(task.getDeadline().after(start) && task.getDeadline().before(end)) {
                filterActivity.put(task, activity.getValue());
            }
        }

        return filterActivity;
    }

    public HashMap<Statistics, List<Task>>  getStatistics() {
        if(this.tasks.size() == 0) {
            return this.statistics;
        }

        for (Task task: this.tasks) {
            if(task.isCompleted()) {
                addStatistics(Statistics.COMPLETED, task);
            }
            else {
                addStatistics(Statistics.SPILLED, task);
            }
        }

        return this.statistics;
    }

    private void addStatistics(Statistics type, Task task) {
        List<Task> stats = new ArrayList<>();
        if(!this.statistics.containsKey(type)) {
            stats.add(task);
        }
        else {
            stats = this.statistics.get(type);
            stats.add(task);
        }
        this.statistics.put(type, stats);
    }

    private void addActivity(Action type, Task task) {
        List<Action> action = new ArrayList<>();
        if(!this.activities.containsKey(task)) {
            action.add(type);
        }
        else {
            action = this.activities.get(task);
            action.add(type);
        }
        this.activities.put(task, action);
    }
}
