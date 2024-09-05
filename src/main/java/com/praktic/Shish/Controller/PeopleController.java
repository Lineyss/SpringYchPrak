package com.praktic.Shish.Controller;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Interface.IPeopleService;
import com.praktic.Shish.Model.Pagination;
import com.praktic.Shish.Model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@Controller
public class PeopleController {

    @Autowired
    private IPeopleService peopleService;

    @GetMapping("/people")
    public String Main(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "lastName", required = false) String lastName,
                       @RequestParam(name = "firstName", required = false) String firstName)
    {

        Pagination<People> peoples = peopleService.GetAll(page, firstName, lastName);
        model.addAttribute("pagination_peoples", peoples);
        model.addAttribute("categoryes", peopleService.GetAllCategory());
        return "secondLab/people";
    }

    @PostMapping("/people")
    public String Create(Model model,
                         @ModelAttribute PeopleDTO peopleDTO)
    {
        peopleService.Create(peopleDTO);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String Delete(Model model,
                         @PathVariable int id)
    {
        peopleService.Delete(id);
        return "redirect:/people";
    }

    @PutMapping("/people/{id}")
    public String Update(Model model,
                         @PathVariable int id,
                         @ModelAttribute PeopleDTO peopleDTO)
    {
        peopleService.Update(id, peopleDTO);
        return "redirect:/people";
    }
}
