package es.codeurjc.mongo.ejer1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.mongo.ejer1.model.Comentario;
import es.codeurjc.mongo.ejer1.model.Entrada;

@RestController
@RequestMapping("/entradas/")
public class BlogController {
	
	@Autowired
	private EntradaRepository repository;
	
	@GetMapping
	public Page<Entrada> allEntradas(Pageable page) {
		return repository.findAll(page);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Entrada addEntrada(@RequestBody Entrada entrada) {
		return repository.save(entrada);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Entrada> updateEntrada(@PathVariable String id, @RequestBody Entrada entrada) {
		
		Optional<Entrada> found = repository.findById(id);
		
		if(!found.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Entrada saved = repository.save(entrada);
		return new ResponseEntity<Entrada>(saved, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Entrada> deleteEntrada(@PathVariable String id) {

		Optional<Entrada> found = repository.findById(id);
		
		if(!found.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		repository.deleteById(id);
		return new ResponseEntity<Entrada>(found.get(), HttpStatus.OK);
	}
	
	@PostMapping("{id}/comentarios/")
	public ResponseEntity<Entrada> addComentario(@PathVariable(name="id") String entradaId, @RequestBody Comentario comentario) {
		
		Optional<Entrada> found = repository.findById(entradaId);
		
		if(!found.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		found.get().getComentarios().add(comentario);
		Entrada saved = repository.save(found.get());
		return new ResponseEntity<Entrada>(saved, HttpStatus.OK);
	}
}
