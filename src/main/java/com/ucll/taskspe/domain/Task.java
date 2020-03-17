package com.ucll.taskspe.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Task {
    private int id;
    private static int count = 0;
    private String name,description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    public Task(String name, String description, LocalDate date, LocalTime time){
        setDate(date);
        setName(name);
        setTime(time);
        count++;
        id = count;
        setDescription(description);

    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }


}
