package com.ucll.taskspe.dto;


import javax.validation.constraints.NotEmpty;

public class SubTaskDTO {

    private long id;
    @NotEmpty
    private String name, description;


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
