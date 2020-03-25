package com.ucll.taskspe.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class SubTaskDTO {

    private int id;
    @NotEmpty
    private String name, description;


    public SubTaskDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SubTaskDTO(){}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "subName='" + name + '\'' +
                ", subDescription='" + description + '\'' +
                '}';
    }

}
