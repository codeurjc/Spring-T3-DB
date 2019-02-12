package es.codeurjc.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablonController {
	
	//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
	//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
	//base de datos de la aplicación

	@Autowired
	private AnunciosRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola", "XXXX"));
	}

	@RequestMapping("/")
	public String tablon(Model model, Pageable page) {

		model.addAttribute("anuncios", repository.findAll(page));

		return "tablon";
	}

	@RequestMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		repository.save(anuncio);

		return "anuncio_guardado";

	}

	@RequestMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Anuncio anuncio = repository.findById(id).get();

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
}