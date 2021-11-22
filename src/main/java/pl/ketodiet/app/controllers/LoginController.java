package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ketodiet.app.model.User;
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
    public String loginView(Model model, User user) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping()
    public String loginPost(HttpServletRequest request, Model model, User user) {
        if (loginService.isLoginCorrect(user)) {
            user = userRepository.getUserByName(user.getName());
            request.getSession().setAttribute(LOG_STATUS, user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Błędny login lub hasło!");
            log.info("Bad username or password!");
        }
        return "login";
    }
}
