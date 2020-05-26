package com.ucll.taskspe;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.SubTaskDTO;
import com.ucll.taskspe.dto.TaskDTO;
import com.ucll.taskspe.service.TaskService;
import com.ucll.taskspe.service.TaskServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    @Order(1)
    public void testGetTasks(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("TestTask");
        taskDTO.setDescription("This task is made for testing");
        taskDTO.setDate(LocalDate.of(2020,1,1));
        taskDTO.setTime(LocalTime.of(12,0));
       // taskDTO.setId(1);
        taskService.addTask(taskDTO);

        //method to be tested
        Map<Long,Task> tasks = taskService.getTasks();

        //tests
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        Task task = taskService.getTask(1L);
        assertNotNull(task);
    }

    @Test
    @Order(2)
    public void testGetSubtaks(){
        //setup
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("Subtasktest");
        taskDTO.setDescription("This task is made for testing the making of a subtask");
        taskDTO.setDate(LocalDate.of(2020,11,1));
        taskDTO.setTime(LocalTime.of(12,0));
        taskService.addTask(taskDTO);

        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setName("subtask");
        subTaskDTO.setDescription("description of subtask");

        //method to be tested
        taskService.addSubtask(taskService.getTask(2L),subTaskDTO);
        Task t2 = taskService.getTask(2L);
        SubTask subtask = t2.getSubTasks().get(0);

        //tests
        assertNotNull(subtask);
        assertEquals(subtask.getName(),"subtask");
        assertEquals(subtask.getDescription(), "description of subtask");

    }

    @Test
    @Order(3)
    public void editTaskTest(){
        //Setup op first task
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setName("First task");
        taskDTO1.setDescription("Description of First Task");
        taskDTO1.setTime(LocalTime.of(1,1));
        taskDTO1.setDate(LocalDate.of(2020, 3, 10));
        taskService.addTask(taskDTO1);

        //Setup of second task
        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setName("Second task");
        taskDTO2.setDescription("Description of Second Task");
        taskDTO2.setTime(LocalTime.of(2,2));
        taskDTO2.setDate(LocalDate.of(2020, 3, 11));

        //method to be tested
        taskService.replaceTask(4L,taskDTO2);
        Task task = taskService.getTasks().get(4L);

        //checks
        assertEquals(task.getName(), "Second task");
        assertEquals(task.getDescription(), "Description of Second Task");
        assertEquals(task.getDate(), LocalDate.of(2020, 3, 11));
        assertEquals(task.toString(),"Task{id=4, name='Second task', description='Description of Second Task', date=2020-03-11, time=02:02, subTasks=[]}");
    }


}
