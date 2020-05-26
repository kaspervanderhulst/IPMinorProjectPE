package com.ucll.taskspe.service;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.SubTaskDTO;
import com.ucll.taskspe.dto.TaskDTO;
import com.ucll.taskspe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository){
        this.repository = repository;
    }

    @Override
    public Map<Long,Task> getTasks() {
        return repository.findAll().stream().collect(Collectors.toMap(Task::getId, Function.identity()));
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setTime(taskDTO.getTime());
        repository.save(task);
        repository.flush();
    }

    @Override
    public Task getTask(Long id) {
       return getTasks().get(id);
    }

    @Override
    public void replaceTask(Long id, TaskDTO task) {
        Task task1 = new Task();
        task1.setDescription(task.getDescription());
        task1.setName(task.getName());
        task1.setDate(task.getDate());
        task1.setTime(task.getTime());
        task1.setId(id);
        repository.save(task1);
        repository.flush();
    }

    @Override
    public Map<Task, List<SubTask>> getSubTasks() {
        Map<Task,List<SubTask>> subTaskList = new HashMap<>();

        for(Task task : repository.findAll()){
            subTaskList.put(task, this.getTask(task.getId()).getSubTasks());
        }
        return subTaskList;

    }



    @Override
    public void addSubtask(Task task, SubTaskDTO subTaskDTO) {
        SubTask st = new SubTask();
        st.setId(subTaskDTO.getId());
        st.setName(subTaskDTO.getName());
        st.setDescription(subTaskDTO.getDescription());
       Task t = getTask(task.getId());
       t.addSubTask(st);
       repository.save(t);
       repository.flush();
    }
}
