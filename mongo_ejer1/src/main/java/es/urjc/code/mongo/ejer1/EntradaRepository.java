package es.urjc.code.mongo.ejer1;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.urjc.code.mongo.ejer1.model.Entrada;

public interface EntradaRepository extends MongoRepository<Entrada, String> {

}
