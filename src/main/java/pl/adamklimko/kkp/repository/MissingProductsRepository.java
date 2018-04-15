package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.products.MissingProducts;

@Repository
public interface MissingProductsRepository extends CrudRepository<MissingProducts, Long> {

}
