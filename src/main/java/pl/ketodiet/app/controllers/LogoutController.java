package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping()
    public String headerView(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
