package es.codeurjc.mongo.ejer2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import es.codeurjc.mongo.ejer2.model.Entrada;

public interface EntradaRepository extends MongoRepository<Entrada, String> {
	
	Page<Entrada> findByNombre(String nombre, Pageable page);

}
