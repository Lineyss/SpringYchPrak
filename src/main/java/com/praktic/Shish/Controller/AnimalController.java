package com.praktic.Shish.Controller;

import com.praktic.Shish.DTO.AnimalDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.IAnimalService;
import com.praktic.Shish.Model.Animal;
import com.praktic.Shish.Model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AnimalController {
    @Autowired
    IAnimalService i_animalService;
    @Autowired
    AService<Animal, AnimalDTO> a_animalAService;

    @GetMapping("/animal")
    public String Main(Model model,
                       @RequestParam(name = "ID", required = false) Integer ID,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "Name", required = false) String Name,
                       @RequestParam(name = "Type", required = false) String Type,
                       @RequestParam(name = "IsDeleted", required = false, defaultValue = "false") Boolean IsDeleted)
    {
        Pagination<Animal> p_animals = null;
        if(ID != null)
        {
            ArrayList<Animal> animals = new ArrayList<>();
            Animal animal = a_animalAService.GetByID(ID.longValue());
            if(animal != null)
            {
                animals.add(animal);
            }
            p_animals = new Pagination<Animal>(animals, page);
        }
        else
        {
            p_animals = i_animalService.GetAll(page, Name, Type, IsDeleted);
        }

        model.addAttribute("pagination_animals", p_animals);
        model.addAttribute("categoryes", i_animalService.GetAllCategory());
        model.addAttribute("fields_for_search", a_animalAService.GetAllFieldsForSearch());
        return "secondLab/animal";
    }

    @PostMapping("/animal")
    public String Create(Model model,
                         @ModelAttribute AnimalDTO animalDTO)
    {
        a_animalAService.Create(animalDTO);
        return "redirect:/animal";
    }

    @DeleteMapping("/animal/{id}")
    public String Delete(Model model,
                         @PathVariable Long id)
    {
        a_animalAService.Delete(id);
        return "redirect:/animal";
    }

    @PutMapping("/animal/{id}")
    public String Update(Model model,
                         @PathVariable Long id,
                         @ModelAttribute AnimalDTO animalDTO)
    {
        a_animalAService.Update(id, animalDTO);
        return "redirect:/animal";
    }
}
