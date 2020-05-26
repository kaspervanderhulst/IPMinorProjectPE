package com.ucll.taskspe.domain;

import com.ucll.taskspe.dto.SubTaskDTO;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
