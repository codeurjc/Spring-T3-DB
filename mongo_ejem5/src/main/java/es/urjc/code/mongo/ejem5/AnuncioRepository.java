package es.urjc.code.mongo.ejem5;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnuncioRepository extends MongoRepository<Anuncio, String> {

}
