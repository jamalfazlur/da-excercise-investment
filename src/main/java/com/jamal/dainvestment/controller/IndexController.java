package com.jamal.dainvestment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping
    public ModelAndView getUserView() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
