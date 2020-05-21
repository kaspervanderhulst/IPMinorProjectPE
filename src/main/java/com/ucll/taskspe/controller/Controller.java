package com.ucll.taskspe.controller;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.dto.SubTaskDTO;
import com.ucll.taskspe.dto.TaskDTO;
import com.ucll.taskspe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@org.springframework.stereotype.Controller
//@RequestMapping("(/tasks")
public class Controller {


    private final TaskService taskService;

    @Autowired
    public Controller(TaskService taskService) {
        this.taskService = taskService;
       /* Task task1 = new Task("task1","a task", LocalDate.of(2000,4,12), LocalTime.of(12,0));
        Task task2 = new Task("task2","an easy task", LocalDate.of(2020,8,2), LocalTime.of(2,0));
        Task task3 = new Task("task3","a difficult task", LocalDate.of(2021,11,25), LocalTime.of(13,30));
        TaskDTO dto1 = Task.toDTO(task1);
        TaskDTO dto2 = Task.toDTO(task2);
        TaskDTO dto3 = Task.toDTO(task3);
        taskService.addTask(dto1);
        taskService.addTask(dto2);
        taskService.addTask(dto3);*/
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tasks/{id}")
    public String getTask(Model model, @PathVariable("id") int id) {

        model.addAttribute("taskId", id);
        model.addAttribute("task", taskService.getTask(id));
        System.out.println(taskService.getTask(id).getSubTasks().toString());
        model.addAttribute("subtasks",taskService.getTask(id).getSubTasks());
        return "task";
    }


    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String addTaskPage(Model model) {
        model.addAttribute("taskdto", new TaskDTO());
        return "addTask";
    }

    @GetMapping("tasks/edit/{id:\\d}")
    public String editTaskPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("taskId", id);
        model.addAttribute("task", taskService.getTask(id));
        model.addAttribute("id", id);
        return "editTask";
    }

    @PostMapping("/tasks/new")
    public String addTask(@ModelAttribute("taskdto") @Valid TaskDTO task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/edit/{id:\\d}")
    public String editTask(@ModelAttribute TaskDTO task, @PathVariable("id") int id) {

        taskService.replaceTask(id, task);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id:\\d}/sub/create")
    public String showSubtask(Model model, @PathVariable("id") int id){
        model.addAttribute("task",taskService.getTask(id));
        model.addAttribute(new SubTask());
        return "subTask";
    }

    @PostMapping("/tasks/{id:\\d}/sub/create")
    public String addSubtask(@ModelAttribute SubTaskDTO subTask, @PathVariable("id") int id){
        taskService.addSubtask(taskService.getTask(id),subTask);
        System.out.println(taskService.getSubTasks());
        return "redirect:/tasks/"+ id;
    }
}
