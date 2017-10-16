package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNumberFormatException(NumberFormatException e, Model model) {
        model.addAttribute("exception", e);
        log.error("Wrong recipe id - NumberFormatException! " + e.getMessage());
        return "error/400error";
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRecipeNotFoundException(RecipeNotFoundException e, Model model) {
        model.addAttribute("exception", e);
        log.error("NotFoundException - " + e.getMessage());
        return "error/404error";
    }
}
