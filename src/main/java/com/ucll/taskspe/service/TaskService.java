package com.ucll.taskspe.service;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public List<Task> getTasks();
    void addTask(Task task);
    Task getTask(int id);
    void replaceTask(int id,Task task);
    Map<Task, List<SubTask>> getSubTasks();
    void addSubtask(Task task, SubTask subtask);

}
