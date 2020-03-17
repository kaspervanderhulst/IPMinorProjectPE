package com.ucll.taskspe.domain;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

public class SubTask{
    @NotEmpty
    private String name, description;


    public SubTask(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SubTask(){}

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "subName='" + name + '\'' +
                ", subDescription='" + description + '\'' +
                '}';
    }
}
