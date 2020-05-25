package com.ucll.taskspe.domain;

import com.ucll.taskspe.dto.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private long id;

    private static int count = 0;

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
    @OneToMany(cascade = {CascadeType.ALL})
    private List<SubTask> subTasks;

    public Task(String name, String description, LocalDate date, LocalTime time){
        setDate(date);
        setName(name);
        setTime(time);
        setDescription(description);
        subTasks = new ArrayList<>();
        count++;
        id = count;
    }

    public Task(){
        subTasks = new ArrayList<>();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addSubTask(SubTask subTask){
        System.out.println("got here");
        long id = 0;
        if(this.subTasks.size() > 0){
            id = this.subTasks.get(subTasks.size()-1).getId()+1;
        }
        subTask.setId(id);
        this.subTasks.add(subTask);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
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

}
