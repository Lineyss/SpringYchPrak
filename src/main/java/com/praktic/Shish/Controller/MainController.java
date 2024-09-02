package com.praktic.Shish.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class MainController {
    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "Hello, World!");
        return "gretting";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(name="a", required = false, defaultValue = "0") int a,
                             Model model) {
        return "calculator";
    }

    @PostMapping("/calculation")
    public String calculation(@RequestParam(name="a") int a,
                              @RequestParam(name="b") int b,
                              @RequestParam(name="action") String action,
                              Model model) {
        String _action = "";
        int result = 0;
        if(Objects.equals(action, "plus"))
        {
            _action = "+";
            result = a + b;
        }
        else if(Objects.equals(action, "minus"))
        {
            _action = "-";
            result = a - b;
        }
        else if(Objects.equals(action, "umn"))
        {
            _action = "*";
            result = a * b;
        }
        else if(Objects.equals(action, "del"))
        {
            _action = "/";
            result = a / b;
        }

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("action", _action);
        model.addAttribute("result", result);

        return "calculator_result";
    }
}
