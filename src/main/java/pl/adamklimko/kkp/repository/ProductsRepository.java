package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.products.Products;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {
}
