package ru.axmor.trial.tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainRoute() {
        return "redirect:/issues";
    }
}
