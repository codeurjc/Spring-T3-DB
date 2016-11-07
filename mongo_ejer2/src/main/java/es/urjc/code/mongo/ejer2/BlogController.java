package es.urjc.code.mongo.ejer2;

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

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.mongo.ejer2.model.Comentario;
import es.urjc.code.mongo.ejer2.model.Entrada;

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
		return repository.findOne(id);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Entrada addEntrada(@RequestBody Entrada entrada) {
		return repository.save(entrada);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Entrada> updateEntrada(@PathVariable String id, @RequestBody Entrada entrada) {
		
		Entrada found = repository.findOne(id);
		
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Entrada saved = repository.save(entrada);
		return new ResponseEntity<Entrada>(saved, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Entrada> deleteEntrada(@PathVariable String id) {

		Entrada found = repository.findOne(id);
		
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		repository.delete(id);
		return new ResponseEntity<Entrada>(found, HttpStatus.OK);
	}
	
	@PostMapping("{id}/comentarios/")
	public ResponseEntity<Entrada> addComentario(@PathVariable(name="id") String entradaId, @RequestBody Comentario comentario) {
		
		Entrada found = repository.findOne(entradaId);
		System.out.println("El id es " + entradaId);
		
		if(found == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Comentario comentarioSaved = comentarioRepository.save(comentario);
		found.getComentarios().add(comentarioSaved);
		Entrada saved = repository.save(found);
		return new ResponseEntity<Entrada>(saved, HttpStatus.OK);
	}
}
