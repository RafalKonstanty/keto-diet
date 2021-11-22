package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.ketodiet.app.repository.UserRepository;
import pl.ketodiet.app.services.BmrService;
import pl.ketodiet.app.services.LoginService;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/bmr")
@Validated
public class BMRController {

    @Autowired
    LoginService loginService;

    @Autowired
    BmrService bmrService;

    @GetMapping()
    public String getBMR(Model model, HttpSession session) {
        if (loginService.checkSession(model, session)) {
            return "bmr";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping()
    @ExceptionHandler
    public String saveBMR(Model model, HttpSession session,
                          @RequestParam(value = "weight", required = false) double weight,
                          @RequestParam(value = "height", required = false) double height,
                          @RequestParam(value = "age", required = false) int age,
                          @RequestParam(value = "sex", required = false) String sex,
                          @RequestParam(value = "activity", required = false) int activity,
                          @RequestParam(value = "minutes", required = false) int minutes,
                          @RequestParam(value = "goal", required = false) int goal) {

            if (bmrService.fieldsCheck(model, weight, height, age)) return "bmr";
            loginService.checkSession(model, session);
            double tdee = Math.round(bmrService.calculateTdee(weight, height, age, sex, activity, minutes, goal, model) * 10.0 / 10.0);
            bmrService.tdeeMacro(tdee, model);
            bmrService.saveTdee(session, tdee);

        return "bmr";
    }

}
