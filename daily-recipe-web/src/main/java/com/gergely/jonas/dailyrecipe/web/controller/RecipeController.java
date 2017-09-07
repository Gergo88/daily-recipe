package com.gergely.jonas.dailyrecipe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ext-jonasg on 2017.09.07..
 */
@Controller
public class RecipeController {
    @RequestMapping("/recipe")
    public String getRecipePage(Model model) {
        return "recipe";
    }
}