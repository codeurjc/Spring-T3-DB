package es.codeurjc.mongo.ejem1;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findByLastName(String lastName);
	
	List<Customer> findByFirstName(String firstName);
	
}