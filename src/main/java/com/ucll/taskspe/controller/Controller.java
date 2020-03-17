package com.ucll.taskspe.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import com.ucll.taskspe.repository.TaskRepository;
import com.ucll.taskspe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
//@RequestMapping("(/tasks")
public class Controller {


    private final TaskService taskService;

    @Autowired
    public Controller(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tasks/{id:\\d}")
    public String getTask(Model model, @PathVariable("id") int id) {

        model.addAttribute("taskId", id);
        model.addAttribute("task", taskService.getTask(id));
        model.addAttribute("subtasks",taskService.getSubTasks().get(taskService.getTask(id)));
        return "task";
    }


    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getTasks());
        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String addTaskPage() {
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
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/edit/{id:\\d}")
    public String editTask(@ModelAttribute Task task, @PathVariable("id") int id) {

        taskService.replaceTask(id, task);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String showSubtask(Model model, @PathVariable("id") int id){
        model.addAttribute("task",taskService.getTask(id));
        model.addAttribute(new SubTask());
        return "subTask";
    }

    @PostMapping("/tasks/{id}/sub/create")
    public String addSubtask(@ModelAttribute SubTask subTask,@PathVariable("id") int id){
        taskService.addSubtask(taskService.getTask(id),subTask);
        System.out.println(taskService.getSubTasks());
        return "redirect:/tasks/"+ id;
    }
}
