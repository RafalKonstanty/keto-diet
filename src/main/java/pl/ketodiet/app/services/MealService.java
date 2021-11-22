package pl.ketodiet.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.ketodiet.app.model.Meal;
import pl.ketodiet.app.model.User;
import pl.ketodiet.app.repository.MealRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class MealService {

    @Autowired
    private BmrService bmrService;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserService userService;

    public void getMealNutritionModels(Model model, HttpSession session, LocalDate currentDate) {
        try {
            User user = userService.getSessionUser(session);
            List<Meal> mealList = mealRepository.findMealByUserAndDate(user.getId(), currentDate);
            model.addAttribute("mealList", mealList);

            double kcal = mealList.stream().mapToDouble
                    (meal -> (meal.getProduct().getKcal() * meal.getCount()) / 100).sum();
            model.addAttribute("calories", (Math.round(kcal * 10) / 10));
            double maxKcal = user.getTdee();
            model.addAttribute("maxCalories", (Math.round(maxKcal * 10) / 10));

            double fat = mealList.stream().mapToDouble
                    (meal -> (meal.getProduct().getFat() * meal.getCount()) / 100).sum();
            model.addAttribute("fat", Math.round(fat * 10) / 10);
            model.addAttribute("goalFats", (Math.round(((maxKcal * 0.8)/9) * 10) / 10));

            double carbohydrates = mealList.stream().mapToDouble
                    (meal -> (meal.getProduct().getCarbohydrates() * meal.getCount()) / 100).sum();
            model.addAttribute("carbohydrates", (Math.round(carbohydrates * 10) / 10));
            model.addAttribute("goalCarbohydrates", (Math.round(((maxKcal * 0.05)/4) * 10) / 10));

            double protein = mealList.stream().mapToDouble
                    (meal -> (meal.getProduct().getProtein() * meal.getCount()) / 100).sum();
            model.addAttribute("protein", (Math.round(protein * 10) / 10));
            model.addAttribute("goalProteins", (Math.round(((maxKcal * 0.15)/4) * 10) / 10));

            double fatPercentage = ((fat * 9) / kcal * 100);
            model.addAttribute("fatPercentage", (Math.round(fatPercentage)));

            double carbohydratesPercentage = ((carbohydrates * 4) / kcal * 100);
            model.addAttribute("carbPercentage", (Math.round(carbohydratesPercentage)));

            double proteinPercentage = ((protein * 4) / kcal * 100);
            model.addAttribute("protPercentage", (Math.round(proteinPercentage)));


        } catch (Exception ignored) {

        }
    }
}
