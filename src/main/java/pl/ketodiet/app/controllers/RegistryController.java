package pl.ketodiet.app.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ketodiet.app.model.User;
import pl.ketodiet.app.repository.UserRepository;
import pl.ketodiet.app.services.LoginService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/registry")
public class RegistryController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginService loginService;

    @GetMapping()
    public String registryView(Model model, User user) {
        model.addAttribute("user", new User());
        return "registry";
    }

    @PostMapping
    public String registerUser(@Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "registry";
        } else {
            if (loginService.checkIfUserExist(user.getName())) {
                log.info("Taki użytkownik już istnieje !");
                model.addAttribute("userExist", "Taki użytkownik już istnieje, proszę sprobówać z inną nazwą");
                return "registry";
            } else {
                userRepository.save(user);
                log.info("Dodano usera: " + user);
            }
            return "redirect:/login";
        }
    }
}
