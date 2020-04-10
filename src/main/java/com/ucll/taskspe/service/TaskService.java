package com.ucll.taskspe.service;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.SubTaskDTO;
import com.ucll.taskspe.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TaskService {
    public List<Task> getTasks();
    void addTask(TaskDTO taskDTO);
    Task getTask(long id);
    void replaceTask(long id,TaskDTO taskDTO);
    Map<Task, List<SubTask>> getSubTasks();
    void addSubtask(Task task, SubTaskDTO subtask);

}
