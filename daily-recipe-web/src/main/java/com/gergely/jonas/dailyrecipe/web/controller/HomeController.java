package com.gergely.jonas.dailyrecipe.web.controller;

import com.gergely.jonas.dailyrecipe.service.Joke;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ext-jonasg on 2017.09.07..
 */
@Controller
public class HomeController {

    private Joke joke;

    public HomeController(Joke joke) {
        this.joke = joke;
    }

    @RequestMapping({"/","/home"})
    public String getHomePage(Model model) {
        model.addAttribute("joke", joke.getJoke());
        return "home";
    }
}
