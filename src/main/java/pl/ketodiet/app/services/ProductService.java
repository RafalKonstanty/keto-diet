package pl.ketodiet.app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import pl.ketodiet.app.model.Meal;
import pl.ketodiet.app.model.Product;
import pl.ketodiet.app.repository.MealRepository;
import pl.ketodiet.app.repository.ProductRepository;
import pl.ketodiet.app.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private ProductRepository productRepository;

    public void deleteProducts(Product product) {
        List<Meal> mealList = mealRepository.findMealByProduct(product.getProduct_id());
        if (mealList.isEmpty()) {
            productRepository.deleteById(product.getProduct_id());
        } else {
            mealList.forEach(mealRepository::delete);
            productRepository.deleteById(product.getProduct_id());
        }
    }

    public void addMealToUser(Product product, Meal newMeal) {
        try {
            newMeal.setDate(LocalDate.now());
            newMeal.setProduct(product);
            mealRepository.save(newMeal);
        } catch (NullPointerException e) {
            log.info("Nullpointer handled");
        }
    }

    public void getKcalFromNutrition(@Valid Product product) {
        product.setKcal(Math.round(product.getFat() * 9 + product.getCarbohydrates() * 4 + product.getProtein() * 4) * 10.0 / 10.0);
    }
}

