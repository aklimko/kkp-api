package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.BoughtProducts;

@Repository
public interface BoughtProductsRepository extends CrudRepository<BoughtProducts, Integer> {
}