package com.praktic.Shish.Controller;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Interface.IAnimalService;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnimalController {
    @Autowired
    IAnimalService animalService;


    @GetMapping("/animal")
    public String Main(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "Name", required = false) String Name,
                       @RequestParam(name = "Type", required = false) String Type)
    {

        Pagination<Animal> animals = animalService.GetAll(page, Name, Type);
        System.out.println(page);
        model.addAttribute("pagination_animals", animals);
        model.addAttribute("categoryes", animalService.GetAllCategory());
        return "secondLab/animal";
    }

    @PostMapping("/animal")
    public String Create(Model model,
                         @ModelAttribute AnimalDTO animalDTO)
    {
        animalService.Create(animalDTO);
        return "redirect:/animal";
    }

    @DeleteMapping("/animal/{id}")
    public String Delete(Model model,
                         @PathVariable int id)
    {
        animalService.Delete(id);
        return "redirect:/animal";
    }

    @PutMapping("/animal/{id}")
    public String Update(Model model,
                         @PathVariable int id,
                         @ModelAttribute AnimalDTO animalDTO)
    {
        animalService.Update(id, animalDTO);
        return "redirect:/animal";
    }
}
