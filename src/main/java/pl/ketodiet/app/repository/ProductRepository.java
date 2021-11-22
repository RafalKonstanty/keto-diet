package pl.ketodiet.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ketodiet.app.model.Product;
import pl.ketodiet.app.model.User;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findProductByUser(User user);
}
