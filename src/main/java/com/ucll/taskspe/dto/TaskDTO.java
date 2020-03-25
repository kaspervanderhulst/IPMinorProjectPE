package com.ucll.taskspe.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class TaskDTO {
    private long id;
    private String name,description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
