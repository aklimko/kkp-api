package pl.adamklimko.kkp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adamklimko.kkp.model.user.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long>{
}
