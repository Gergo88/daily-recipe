package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.service.CookService;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;

@Controller
@RequestMapping("/cook")
public class CookController {

    private CookService cookService;

    public CookController(CookService cookService) {
        this.cookService = cookService;
    }

    @GetMapping("")
    public String getCookPage(Model model) {
        FullRecipe fullRecipe = new FullRecipe();
        fullRecipe.getFindingsList().add(cookService.getNewFindigsDTO());
        model.addAttribute("fullRecipe", fullRecipe);
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("recipeList", cookService.findAll());
        return "cook";
    }

    @PostMapping("")
    public String addRecipe(@ModelAttribute("fullRecipe") FullRecipe fullrecipe) {
        System.out.println(fullrecipe.toString());
        cookService.addRecipe(fullrecipe);
        return "redirect:/cook";
    }

    @GetMapping("/{id}")
    public String editRecipe(@PathVariable("id") Long id, Model model) {
        FullRecipe editedFullRecipe = cookService.findById(id);
        model.addAttribute("fullRecipe", editedFullRecipe);
        model.addAttribute("ingredients", cookService.getAllIngredient());
        model.addAttribute("units", cookService.getAllUnit());
        model.addAttribute("recipeList", cookService.findAll());
        return "cook";
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteRecipeById(@PathVariable("id") Long idToDelete) {
        cookService.deleteRecipeById(idToDelete);
        return "redirect:/cook";
    }
}
