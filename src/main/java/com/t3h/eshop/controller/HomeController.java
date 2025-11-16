package com.t3h.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Service
public class HomeController {

    @GetMapping(value = {"/", "/index"})
    public ModelAndView hello(){
        return new ModelAndView("index");
    }


}
