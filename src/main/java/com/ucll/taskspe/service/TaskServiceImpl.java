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
        System.out.println(taskDTO.getName());
        task.setName(taskDTO.getName());
        System.out.println(task.getName() + "name");
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setTime(taskDTO.getTime());
        task.setId(taskDTO.getId());
        System.out.println("adding task with id: " + task.getId());
        repository.save(task);
        repository.flush();
    }

    @Override
    public Task getTask(long id) {
        System.out.println("got here");
        System.out.println(repository.getOne(id).getId() + "id in gettaskt");
        System.out.println(repository.getOne(id).getDescription() + "name in gettask");
       return repository.getOne(id);

        /*if(id < 0 || id > repository.getTaskService().size()){
            return null;
        }else return repository.getTask(id);*/
    }

    @Override
    public void replaceTask(long id, TaskDTO task) {
        Task task1 = new Task();
        task1.setDescription(task.getDescription());
        task1.setName(task.getName());
        task1.setDate(task.getDate());
        task1.setTime(task.getTime());
        repository.save(task1);
        repository.flush();

       /* System.out.println("id: " + id);
        System.out.println(repository.getOne(id).getId() + " " + task.getTime() + "in replacing");
        repository.getOne(id).setTime(task.getTime());
        System.out.println(task.getDate() + "date in replacing");
        repository.getOne(id).setDate(task.getDate());
        repository.getOne(id).setName(task.getName());
        repository.getOne(id).setDescription(task.getDescription());
        repository.flush();*/
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
        SubTask st = new SubTask();
        st.setId(subTaskDTO.getId());
        st.setName(subTaskDTO.getName());
        st.setDescription(subTaskDTO.getDescription());
       Task t = getTask(task.getId());

        System.out.println(t.getId() + " " +t.getName());

       t.addSubTask(st);
        System.out.println(t.getSubTasks().get(0));
      // repository.save(t);
       //repository.flush();


    }
}
