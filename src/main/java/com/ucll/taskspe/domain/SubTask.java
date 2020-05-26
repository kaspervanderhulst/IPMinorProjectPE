package com.ucll.taskspe.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class SubTask {

@Id
@GeneratedValue
private long id;
    @NotEmpty
    private String name, description;

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

    @Override
    public String toString() {
        return "SubTask{" +
                "subName='" + name + '\'' +
                ", subDescription='" + description + '\'' +
                '}';
    }

}
