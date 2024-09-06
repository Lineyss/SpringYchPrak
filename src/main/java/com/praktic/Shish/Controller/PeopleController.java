package com.praktic.Shish.Controller;

import com.praktic.Shish.DTO.PeopleDTO;
import com.praktic.Shish.Interface.AService;
import com.praktic.Shish.Interface.IPeopleService;
import com.praktic.Shish.Model.Animal;
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
    private IPeopleService i_peopleService;
    @Autowired
    private AService<People, PeopleDTO> a_peopleService;

    @GetMapping("/people")
    public String Main(Model model,
                       @RequestParam(name = "ID", required = false) Integer ID,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "LastName", required = false) String lastName,
                       @RequestParam(name = "FirstName", required = false) String firstName,
                       @RequestParam(name = "IsDeleted", required = false, defaultValue = "false") Boolean IsDeleted)
    {

        Pagination<People> p_peoples = null;
        if(ID != null)
        {
            ArrayList<People> peoples = new ArrayList<>();
            People people = a_peopleService.GetByID(ID.longValue());
            if(people != null)
            {
                peoples.add(people);
            }
            p_peoples = new Pagination<People>(peoples, page);
        }
        else
        {
            p_peoples = i_peopleService.GetAll(page, firstName, lastName, IsDeleted);
        }

        model.addAttribute("pagination_peoples", p_peoples);
        model.addAttribute("categoryes", i_peopleService.GetAllCategory());
        model.addAttribute("fields_for_search", a_peopleService.GetAllFieldsForSearch());
        return "secondLab/people";
    }

    @PostMapping("/people")
    public String Create(Model model,
                         @ModelAttribute PeopleDTO peopleDTO)
    {
        a_peopleService.Create(peopleDTO);
        return "redirect:/people";
    }

    @DeleteMapping("/people/{id}")
    public String Delete(Model model,
                         @PathVariable Long id)
    {
        a_peopleService.Delete(id);
        return "redirect:/people";
    }

    @PutMapping("/people/{id}")
    public String Update(Model model,
                         @PathVariable Long id,
                         @ModelAttribute PeopleDTO peopleDTO)
    {
        a_peopleService.Update(id, peopleDTO);
        return "redirect:/people";
    }
}
