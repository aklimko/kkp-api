package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.rooms.CleanedRooms;

@Repository
public interface CleanedRoomsRepository extends CrudRepository<CleanedRooms, Long> {
}
