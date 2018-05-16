package pl.adamklimko.kkp.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.adamklimko.kkp.model.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {

  Optional<UserEntity> findByUsername(String username);
}
