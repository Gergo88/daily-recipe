package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.model.model.Findings;
import com.gergely.jonas.dailyrecipe.service.CookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CookController {

    private CookService cookService;

    public CookController(CookService cookService) {
        this.cookService = cookService;
    }

    @RequestMapping(value = "/cook", method = RequestMethod.GET)
    public String getCookPage(Model model) {
        model.addAttribute("fullRecipe", new FullRecipe());
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("recipeList", cookService.findAll());
        return "cook";
    }

    @RequestMapping("/cook/addrow")
    public String addRow(Model model) {
        model.addAttribute("fullRecipe", new FullRecipe());
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("recipeList", cookService.findAll());
        System.out.println("addrow");
        return "cook";
    }

    @RequestMapping(value = "/cook", method = RequestMethod.POST)
    public String addRecipe(@ModelAttribute("fullRecipe") FullRecipe fullrecipe, Model model) {
        cookService.addRecipe(fullrecipe);
        model.addAttribute("recipeList", cookService.findAll());
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("fullRecipe", new FullRecipe());
        return "cook";
    }

    @RequestMapping("/cook/{id}")
    public String editRecipe(@PathVariable("id") Long id, Model model) {
        FullRecipe editedFullRecipe = cookService.findById(id);
        model.addAttribute("fullRecipe", editedFullRecipe);
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("recipeList", cookService.findAll());
        return "cook";
    }

}
