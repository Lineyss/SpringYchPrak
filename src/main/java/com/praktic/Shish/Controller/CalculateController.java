package com.praktic.Shish.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculateController {

    @GetMapping("/calculator")
    public String calculatorBase(@RequestParam(name="a", required = false, defaultValue = "0") int a,
                                 Model model) {
        return "firstLab/calculator";
    }

    @PostMapping("/calculator/result")
    public String calculatorBase_result(@RequestParam(name="a") int a,
                                        @RequestParam(name="b") int b,
                                        @RequestParam(name="action") String action,
                                        Model model) {

        int result = switch (action)
        {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("action", action);
        model.addAttribute("result", result);

        return "firstLab/calculator_result";
    }

    @GetMapping("/calculator_currency")
    public String calculator_currency_result(Model model)
    {
        return "firstLab/calculator_currency";
    }

    @PostMapping("/calculator_currency/result")
    public String calculator_currency_result(@RequestParam(name="out_currency") String out_currency,
                                             @RequestParam(name="in_currency") String in_currency,
                                             @RequestParam(name="out") double out,
                                             Model model)
    {
        double rub_eur = 0.011;
        double rub_kzt = 5.35;
        double eur_rub = 90.00;
        double eur_kzt = 550.00;
        double kzt_rub = 0.19;
        double kzt_eur = 0.0018;

        double result = switch (out_currency + "&" + in_currency)
        {
            case "rub&eur"-> out * rub_eur;
            case "rub&kzt"-> out * rub_kzt;

            case "eur&rub"-> out * eur_rub;
            case "eur&kzt"-> out * eur_kzt;

            case "kzt&eur"-> out * kzt_eur;
            case "kzt&rub"-> out * kzt_rub;

            default->out;
        };

        model.addAttribute("result", result);

        return "firstLab/calculator_currency_result";
    }
}
