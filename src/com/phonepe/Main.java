package com.phonepe;

import com.phonepe.factory.TaskFactory;
import com.phonepe.filter.Filter;
import com.phonepe.model.Task;
import com.phonepe.model.User;
import com.phonepe.service.TaskService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        User u1 = new User("1", "vikash.k@gmail.com", "9090909090");
        User u2 = new User("2", "rames.k@gmail.com", "9090909091");
        User u3 = new User("3", "suresh.k@gmail.com", "9090909092");
        User u4 = new User("4", "ganesh.k@gmail.com", "9090909093");

        TaskService taskService = TaskFactory.getTaskService();

        Task task1 = new Task("task1", getDate("01-11-2021"), false);
        Task task2 = new Task("task2", getDate("01-11-2024"), false);
        Task task3 = new Task("task3", getDate("01-11-2022"), false);
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        task2.setCompleted(true);
        task1.setDeadline(getDate("01-11-2023"));
        taskService.modifyTask(task2);
        taskService.modifyTask(task1);

        System.out.println("printing task list");
        System.out.println(taskService.listTasK(new Filter()));

        taskService.removeTask("task1");
        System.out.println("printing task list after removable");
        System.out.println(taskService.listTasK(new Filter()));

        System.out.println("printing task stats");
        System.out.println(taskService.getStatistics());

        System.out.println("printing task activity log");
        System.out.println(taskService.getActivityLog());
    }

    public static Date getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
