package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ketodiet.app.model.Meal;
import pl.ketodiet.app.repository.MealRepository;
import pl.ketodiet.app.services.LoginService;
import pl.ketodiet.app.services.MealService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealService mealService;
    @Autowired
    private LoginService loginService;

    @GetMapping()
    public String mainView(Model model, HttpSession session,
                           @RequestParam(value = "date", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        loginService.checkSession(model, session);
        LocalDate currentDate = localDate == null ? LocalDate.now() : localDate;
        log.info("curretnDate: " + currentDate);
        model.addAttribute("localDate", currentDate);
        mealService.getMealNutritionModels(model, session, currentDate);
        return "home";
    }

    @PostMapping("/deleteMeal")
    public String deleteProduct(Meal meal) {
        mealRepository.deleteById(meal.getMeal_id());
        return "redirect:/";
    }
}
