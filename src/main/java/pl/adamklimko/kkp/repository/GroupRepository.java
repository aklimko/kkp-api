package pl.adamklimko.kkp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.adamklimko.kkp.model.entity.GroupEntity;

public interface GroupRepository extends MongoRepository<GroupEntity, String> {

}
