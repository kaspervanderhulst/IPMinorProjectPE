package com.ucll.taskspe.domain;

import com.ucll.taskspe.dto.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name of Task cannot be empty")
    @NotEmpty(message = "Name of Task cannot be empty")
    private String name;

    @NotEmpty(message = "Description of Task cannot be empty")
    @NotNull(message = "Description of Task cannot be empty")
    private String description;

    @NotNull(message = "Date needs to be filled in")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    private List<SubTask> subTasks = new ArrayList<>();


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addSubTask(SubTask subTask){
        subTasks.add(subTask);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }


    public static TaskDTO toDTO(Task task){
        TaskDTO dto = new TaskDTO();
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setDate(task.getDate());
        dto.setTime(task.getTime());
        dto.setId(task.getId());
        return dto;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", subTasks=" + subTasks +
                '}';
    }
}
