package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.rooms.DirtyRooms;

@Repository
public interface DirtyRoomsRepository extends CrudRepository<DirtyRooms, Long> {
}
