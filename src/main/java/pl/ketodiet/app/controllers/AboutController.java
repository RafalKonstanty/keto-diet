package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ketodiet.app.services.LoginService;

import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    LoginService loginService;

    @GetMapping()
    public String getBMR(Model model, HttpSession session){
       loginService.checkSession(model, session);
        return "about";
    }

}
