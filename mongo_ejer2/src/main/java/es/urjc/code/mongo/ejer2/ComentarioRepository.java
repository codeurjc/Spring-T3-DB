package es.urjc.code.mongo.ejer2;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.urjc.code.mongo.ejer2.model.Comentario;

public interface ComentarioRepository extends MongoRepository<Comentario, String> {

}
