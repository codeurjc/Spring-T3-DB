package es.codeurjc.mongo.ejem3;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablonController {

	@Autowired
	private AnuncioRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola", "XXXX"));
	}

	@GetMapping("/anuncios")
	public Page<Anuncio> tablon(Pageable page) {

		return repository.findAll(page);
		
	}

}