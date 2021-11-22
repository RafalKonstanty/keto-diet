package pl.ketodiet.app.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.ketodiet.app.model.Meal;
import pl.ketodiet.app.model.Product;

import java.time.LocalDate;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void addMealToUser() {
        //given
        Product product = new Product();
        product.setName("ProduktTestowy");
        product.setKcal(300);
        product.setProtein(50);
        product.setFat(33);
        product.setCarbohydrates(15);
        Meal meal = new Meal();
        meal.setCount(200);
        //when
        productService.addMealToUser(product, meal);
        //then
        assertEquals("ProduktTestowy", meal.getProduct().getName());
    }

    @Test
    void deleteProducts() {
    }

    @Test
    void getKcalFromNutrition() {
    }
}