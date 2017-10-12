package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.dto.FullRecipe;
import com.gergely.jonas.dailyrecipe.service.CookService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        Byte[] image = cookService.getRecipeImageById(Long.valueOf(id));
        if (image != null) {
            byte[] byteArray = new byte[image.length];
            int i = 0;

            for (Byte wrappedByte : image) {
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
