package com.phonepe.service;

import com.phonepe.constans.Action;
import com.phonepe.constans.Statistics;
import com.phonepe.filter.Filter;
import com.phonepe.model.Task;
import com.phonepe.repository.TaskRepository;

import java.util.HashMap;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String addTask(Task task) {
        return this.taskRepository.addTask(task);
    }

    public Task getTask(String taskId) {
        return this.taskRepository.getTask(taskId);
    }

    public void modifyTask(Task task) {
        this.taskRepository.modifyTask(task);
    }

    public void removeTask(String taskId) {
        this.taskRepository.removeTask(taskId);
    }

    public List<Task> listTasK(Filter filter) {
        return this.taskRepository.listTask(filter);
    }

    public HashMap<Statistics, List<Task>> getStatistics() {
       return this.taskRepository.getStatistics();
    }

    public HashMap<Task, List<Action>> getActivityLog() {
        return this.taskRepository.getActivityLog();
    }

}
