package es.codeurjc.mongo.ejer2;

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

import es.codeurjc.mongo.ejer2.model.Comentario;
import es.codeurjc.mongo.ejer2.model.Entrada;

@RestController
@RequestMapping("/entradas/")
public class BlogController {
	
	@Autowired
	private EntradaRepository repository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping
	public Page<Entrada> allEntradas(Pageable page) {
		return repository.findAll(page);
	}
	
	@GetMapping("{id}")
	public Entrada getEntrada(@PathVariable String id) {
		return repository.findById(id).get();
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

		Comentario comentarioSaved = comentarioRepository.save(comentario);
		found.get().getComentarios().add(comentarioSaved);
		Entrada saved = repository.save(found.get());
		return new ResponseEntity<Entrada>(saved, HttpStatus.OK);
	}
}
