package com.ucll.taskspe;

import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.TaskDTO;
import com.ucll.taskspe.service.TaskService;
import com.ucll.taskspe.service.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
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
        List<Task> tasks = taskService.getTasks();

        //tests
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        Task task = tasks.get(0);
        assertNotNull(task);
    }

    public void testGetSubtaks(){

    }

    public void testAddTasks(){

    }


}
