package pl.ketodiet.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ketodiet.app.model.User;
import pl.ketodiet.app.repository.UserRepository;

import javax.servlet.http.HttpSession;

import static pl.ketodiet.app.controllers.LoginController.LOG_STATUS;

@Slf4j
@Service
public class BmrService {

    @Autowired
    private UserRepository userRepository;

    public double calculateTdee(@RequestParam(value = "weight", required = false) double weight,
                                @RequestParam(value = "height", required = false) double height,
                                @RequestParam(value = "age", required = false) int age,
                                @RequestParam(value = "sex", required = false) String sex,
                                @RequestParam(value = "activity", required = false) int activity,
                                @RequestParam(value = "minutes", required = false) int minutes,
                                @RequestParam(value = "goal", required = false) int goal, Model model) {

        if (sex.equals("male")) {
            double bmr = (9.99 * weight) + (6.25 * height) - (4.92 * age) + 5;
            double eat = (minutes * activity) / 7.0;
            double sum = bmr + eat + 500;
            double tdee = sum + (sum * 0.1) + goal;
            log.info("TDEE for Male: " + tdee);

            return getTdeeMacro(model, tdee);

        } else {
            double bmr = (9.99 * weight) + (6.25 * height) - (4.92 * age) - 161;
            double eat = (minutes * activity) / 7.0;
            double sum = bmr + eat + 480;
            double tdee = sum + (sum * 0.1) + goal;
            log.info("TDEE for Female: " + tdee);

            return getTdeeMacro(model, tdee);
        }
    }

    public double getTdeeMacro(Model model, double tdee) {
        double tdeeFat = (tdee / 9) * 0.8;
        model.addAttribute("tdeeFat", tdeeFat);

        double tdeeProt = (tdee / 4) * 0.15;
        model.addAttribute("tdeeProt", tdeeProt);

        double tdeeCarb = (tdee / 4) * 0.05;
        model.addAttribute("tdeeCarb", tdeeCarb);

        return tdee;
    }

    public void tdeeMacro(double tdee, Model model) {
        model.addAttribute("TDEE", "Twój wynik zapotrzebowania kalorycznego: " + tdee);
    }


    public void saveTdee(HttpSession session, double tdee) {
        User sessionUser = (User) session.getAttribute(LOG_STATUS);
        User user = userRepository.getUserByName(sessionUser.getName());
        user.setTdee(tdee);
        userRepository.save(user);
    }

    public boolean fieldsCheck(Model model,
                               @RequestParam(value = "weight", required = false) double weight,
                               @RequestParam(value = "height", required = false) double height,
                               @RequestParam(value = "age", required = false) int age) {

        if (weight <= 0 || height <= 0 || age <= 0) {
            model.addAttribute("fieldsEmptyCheck", "Proszę uzupełnić wszystkie pola");
            return true;
        }
        return false;
    }
}
