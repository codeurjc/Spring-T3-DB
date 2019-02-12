package es.codeurjc.mongo.ejem5;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	Page<User> findByNombre(String nombre, Pageable page);
}
