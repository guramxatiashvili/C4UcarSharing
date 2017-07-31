package c4uCarSharing.gurami.repositories;

import c4uCarSharing.gurami.domain.Userd;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Userd, Integer>{
    Userd findByUsername(String username);
}