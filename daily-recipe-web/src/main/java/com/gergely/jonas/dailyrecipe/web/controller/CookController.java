package com.gergely.jonas.dailyrecipe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookController {
    @RequestMapping("/cook")
    public String getCookPage(Model model) {
        return "cook";
    }
}
