package com.praktic.Shish.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class MainController {
    @GetMapping("/")
    public String Main(Model model)
    {
        return "main";
    }
}
