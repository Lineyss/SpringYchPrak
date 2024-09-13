package com.praktic.Shish.Controller;

import com.praktic.Shish.DTO.UserDTO;
import com.praktic.Shish.Model.Help.Enum.ERole;
import com.praktic.Shish.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String Main(Model model)
    {
        return "fifthLab/block/main.html";
    }

    @GetMapping("/registration")
    public String Registration(Model model)
    {
        return "fifthLab/register.html";
    }

    @PostMapping("/registration")
    public String reg(Model model,
                      @RequestParam(name = "login") String login,
                      @RequestParam(name = "password") String password)
    {
        UserDTO userDTO = new UserDTO(login, password, true, Collections.singleton(ERole.USER));
        userService.Create(userDTO);
        return  "redirect:/register";
    }
}
