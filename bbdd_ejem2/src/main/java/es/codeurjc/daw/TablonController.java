package es.codeurjc.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TablonController {

	@Autowired
	private AnuncioRepository repository;

	@PostConstruct
	public void init() {
		repository.save(new Anuncio("Pepe", "Hola caracola", "XXXX"));
		repository.save(new Anuncio("Juan", "Hola caracola2", "XXXX2"));
	}

	@GetMapping("/")
	public String tablon(Model model) {

		model.addAttribute("anuncios", repository.findAll());

		return "tablon";
	}

	@PostMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		repository.save(anuncio);

		return "anuncio_guardado";

	}

	@GetMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {

		Anuncio anuncio = repository.findById(id).get();

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
}