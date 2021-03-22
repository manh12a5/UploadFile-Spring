package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Task;
import com.example.demo.model.TaskForm;
import com.example.demo.service.task.ITaskService;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    @Autowired
    Environment environment;

    @ModelAttribute("user")
    public AppUser appUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/task/list");
        List<Task> tasks = taskService.findAll();
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("mess", "Xin chao");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/task/create");
        modelAndView.addObject("task", new TaskForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public RedirectView creatTask(@ModelAttribute TaskForm taskForm){
        Task task = new Task(taskForm.getName(), taskForm.getDescription());
        MultipartFile multipartFile = taskForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        String newFile = fileUpload+ fileName;
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        task.setAvatar(fileName);
        taskService.save(task);
        return new RedirectView("");
    }

}
