package com.phonepe.factory;

import com.phonepe.repository.TaskRepository;
import com.phonepe.service.TaskService;

public class TaskFactory {
    private static TaskService factory;

    private TaskFactory() {
    }

    public static TaskService getTaskService() {
        if (factory == null) {
            factory = new TaskService(new TaskRepository());
        }
        return factory;
    }
}
