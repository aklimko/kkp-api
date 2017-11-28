package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.History;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
}
