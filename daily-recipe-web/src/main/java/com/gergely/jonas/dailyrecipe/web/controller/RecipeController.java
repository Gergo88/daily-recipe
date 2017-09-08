package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.dao.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ext-jonasg on 2017.09.07..
 */
@Controller
public class RecipeController {
    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("/recipe")
    public String getRecipePage(Model model) {
        model.addAttribute("recipeList", recipeRepository.findAll());
        return "recipe";
    }
}
