package com.ucll.taskspe.service;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.SubTaskDTO;
import com.ucll.taskspe.dto.TaskDTO;
import com.ucll.taskspe.repository.SubTaskRepository;
import com.ucll.taskspe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Task> getTasks() {
        return repository.findAll();
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
    public Task getTask(long id) {
       return repository.getOne(id);

        /*if(id < 0 || id > repository.getTaskService().size()){
            return null;
        }else return repository.getTask(id);*/
    }

    @Override
    public void replaceTask(long id, TaskDTO task) {
        repository.getOne(id).setTime(task.getTime());
        repository.getOne(id).setDate(task.getDate());
        repository.getOne(id).setName(task.getName());
        repository.getOne(id).setDescription(task.getDescription());
        repository.flush();
    }

    @Override
    public Map<Task, List<SubTask>> getSubTasks() {
        Map<Task,List<SubTask>> subTaskList = new HashMap<>();

        for(Task task : repository.findAll()){
            subTaskList.put(task, this.getTask(task.getId()).getSubTasks());
            System.out.println(subTaskList.toString());
        }
        return subTaskList;

    }



    @Override
    public void addSubtask(Task task, SubTaskDTO subTaskDTO) {
       Task t = getTask(task.getId());
       t.addSubTask(SubTask.DTOtoSubTask(subTaskDTO));
       repository.save(t);
       repository.flush();
    }
}
