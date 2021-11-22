package pl.ketodiet.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ketodiet.app.model.Meal;
import pl.ketodiet.app.model.Product;
import pl.ketodiet.app.repository.ProductRepository;
import pl.ketodiet.app.services.LoginService;
import pl.ketodiet.app.services.ProductService;
import pl.ketodiet.app.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @ModelAttribute(name = "productList")
    public List<Product> productsList(HttpSession session) {
        return productRepository.findProductByUser(userService.getSessionUser(session));
    }

    @ModelAttribute(name = "product")
    public Product product() {
        return new Product();
    }

    @ModelAttribute(name = "meal")
    public Meal meal() {
        return new Meal();
    }

    @GetMapping()
    public String productView(Model model, HttpSession session) {
        if (loginService.checkSession(model, session)) {
            return "products";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping()
    public String createProduct(@Valid Product product, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            loginService.checkSession(model, session);
            return "products";
        } else {
            productService.getKcalFromNutrition(product);
            product.setUser(userService.getSessionUser(session));
            productRepository.save(product);
            log.info("Dodano produkt: " + product);
            return "redirect:/product";
        }
    }

    @PostMapping("/user")
    public String createDailyProduct(Product product, Meal meal) {
        productService.addMealToUser(product, meal );
        log.info("Dodano product do Usera: " );
        return "redirect:/";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(Product product) {
        productService.deleteProducts(product);
        return "redirect:/product";
    }

}
