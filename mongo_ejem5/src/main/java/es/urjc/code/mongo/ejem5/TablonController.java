package es.urjc.code.mongo.ejem5;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablonController {

	@Autowired
	private AnuncioRepository anunciosRepository;
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		List<Anuncio> anunciosPepe = new ArrayList<>();
		List<Anuncio> anunciosJuan = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			Anuncio a = 
				anunciosRepository.save(new Anuncio("hola", "anuncio "+i, "..."));
			anunciosPepe.add(a);
			Anuncio b = 
				anunciosRepository.save(new Anuncio("adios", "anuncio "+i, "..."));
			anunciosJuan.add(b);
		}
		userRepository.save(new User("pepe", anunciosPepe));
		userRepository.save(new User("Juan", anunciosJuan));
	}

	@GetMapping("/users/{nombre}")
	public Page<User> anuncios(@PathVariable String nombre, Pageable page) {
		return userRepository.findByNombre(nombre, page);
	}
	
	@GetMapping("/anuncios")
	public Page<Anuncio> tablon(Pageable page) {

		return anunciosRepository.findAll(page);
		
	}

}