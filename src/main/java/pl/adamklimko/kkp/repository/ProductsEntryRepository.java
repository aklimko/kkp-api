package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.Products;

@Repository
public interface ProductsEntryRepository extends CrudRepository<Products, Long>{
}
