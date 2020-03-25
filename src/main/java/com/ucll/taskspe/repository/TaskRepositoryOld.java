package com.ucll.taskspe.repository;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.service.TaskService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryOld {
    private List<Task> tasks;
    private Map<Task,List<SubTask>> subTasks;

    public TaskRepositoryOld(){
        this.tasks = new ArrayList<>();
        this.subTasks = new HashMap<>();
        Task task1 = new Task("Task 1","een makkelijke taak", LocalDate.of(2020,3,6), LocalTime.of(15,0));
        Task task2 = new Task("Task 2","een  taak", LocalDate.of(2020,3,5), LocalTime.of(2,0));
        Task task3 = new Task("Task 3","een moeilijke taak", LocalDate.of(2021,12,31), LocalTime.of(10,0));
        SubTask subTask1 = new SubTask("subtasks 1","subtask of task 1");
        addSubtask(task1,subTask1);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

    }

    public List<Task> getTaskService() {
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Task getTask(int id){
       return tasks.get(id-1);
    }

    public void replaceTask(int id,Task task){
        tasks.get(id-1).setTime(task.getTime());
        tasks.get(id-1).setDate(task.getDate());
        tasks.get(id-1).setDescription(task.getDescription());
        tasks.get(id-1).setName(task.getName());
    }

    public Map<Task, List<SubTask>> getSubTasks() {
        return subTasks;
    }

    public void addSubtask(Task task, SubTask subtask) {
        if(subTasks.keySet().contains(task)){
            subTasks.get(task).add(subtask);
        }
        else{
            List<SubTask> list = new ArrayList<>();
            list.add(subtask);
            subTasks.put(task, list);
        }
    }


}
