package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ketodiet.app.model.UserEntity;
import pl.ketodiet.app.repository.UserRepository;
import pl.ketodiet.app.services.LoginService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    public static final String LOG_STATUS = "logStatus";

    @GetMapping()
    public String loginView(Model model, UserEntity userEntity) {
        model.addAttribute("userEntity", new UserEntity());
        return "login";
    }

    @PostMapping()
    public String loginPost(HttpServletRequest request, Model model, UserEntity userEntity) {
        if (loginService.isLoginCorrect(userEntity)) {
            userEntity = userRepository.getUserByName(userEntity.getName());
            request.getSession().setAttribute(LOG_STATUS, userEntity);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Błędny login lub hasło!");
            log.info("Bad username or password!");
        }
        return "login";
    }
}
