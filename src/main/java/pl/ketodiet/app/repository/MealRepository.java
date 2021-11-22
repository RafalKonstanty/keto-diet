package pl.ketodiet.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ketodiet.app.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.product.user.id = :user_id AND m.date = :date ")
    List<Meal> findMealByUserAndDate(@Param("user_id") int id, @Param("date") LocalDate localDate);

    @Query("SELECT m FROM Meal m WHERE m.product.product_id = :product_id")
    List<Meal> findMealByProduct(@Param("product_id")int product_id);
}
