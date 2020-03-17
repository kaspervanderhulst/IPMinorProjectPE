package com.ucll.taskspe.service;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Task> getTasks() {
        return repository.getTaskService();
    }

    @Override
    public void addTask(Task task) {
repository.addTask(task);
    }

    @Override
    public Task getTask(int id) {
        if(id < 0 || id > repository.getTaskService().size()){
            return null;
        }else return repository.getTask(id);
    }

    @Override
    public void replaceTask(int id, Task task) {
        repository.replaceTask(id,task);
    }

    @Override
    public Map<Task, List<SubTask>> getSubTasks() {
        return repository.getSubTasks();
    }

    @Override
    public void addSubtask(Task task, SubTask subtask) {
        repository.addSubtask(task, subtask);
    }
}
