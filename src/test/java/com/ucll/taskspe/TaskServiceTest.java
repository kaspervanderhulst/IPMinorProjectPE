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
        taskDTO.setName("TestTaskd");
        taskDTO.setDescription("This task is made for testindg");
        taskDTO.setDate(LocalDate.of(2020,11,1));
        taskDTO.setTime(LocalTime.of(12,0));
        taskDTO.setId(2L);
        taskService.addTask(taskDTO);
       // assertEquals(taskDTO, Task.toDTO(taskService.getTask(taskDTO.getId())));
        Task t = taskService.getTask(2L);
        SubTaskDTO subTaskDTO = new SubTaskDTO();
       // Map<Task,List<SubTask>> d = taskService.getSubTasks();
        subTaskDTO.setName("subtask");
        subTaskDTO.setDescription("description of subtask");
        taskService.addSubtask(t,subTaskDTO);
        Task t2 = taskService.getTask(2L);
        //SubTask subtask = taskService.getSubTasks().get(t2).get(0);
        SubTask subtask = t2.getSubTasks().get(0);
        //tests
        assertNotNull(subtask);

    }

    @Test
    public void editTaskTest(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("test");
        taskDTO.setDescription("test description");
        taskDTO.setTime(LocalTime.of(12,0));
        taskDTO.setDate(LocalDate.of(2020, 3, 10));
        System.out.println("id in test: " + taskDTO.getId());
        taskService.addTask(taskDTO);


        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setName("test2");
        taskDTO2.setDescription("test description 2");
        taskDTO2.setTime(LocalTime.of(12,0));
        taskDTO2.setDate(LocalDate.of(2020, 3, 10));
        System.out.println(taskDTO.getId());
        taskService.replaceTask(4L, taskDTO2);

        //method to be tested
        Task task = taskService.getTasks().get(4L);

        //checks
        assertNotNull(task);
        assertEquals(task.getName(), "test");
        assertEquals(task.getDescription(), "test description 2");
        assertEquals(task.getDate(), LocalDate.of(2020, 03, 10));
        assertEquals(task.toString(), "• jaff: due March 10 2020 at 10 AM");

    }

    /*@Test
    public void tasktostringtest(){
        SubTask subTask = new SubTask("ts", "test");
        subTask.toString();

    }
*/

}
