package com.example.demo.controller;

import com.example.demo.service.task.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private ITaskService taskService;

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("list", taskService.findAll());
        return modelAndView;
    }

    @GetMapping("/error-403")
    public ModelAndView e403() {
        return new ModelAndView("/error-403");
    }

}
