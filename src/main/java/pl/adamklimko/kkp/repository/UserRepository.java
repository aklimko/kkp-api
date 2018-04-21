package pl.adamklimko.kkp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.adamklimko.kkp.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {


}
