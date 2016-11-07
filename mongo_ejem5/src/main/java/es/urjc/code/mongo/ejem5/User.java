package es.urjc.code.mongo.ejem5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class User {
	
	@Id
	private String id;
	
	private String nombre;
	@DBRef
	private List<Anuncio> anuncios = new ArrayList<>();
	
	public User() {
	}

	public User(String nombre, List<Anuncio> anuncios) {
		this.nombre = nombre;
		this.anuncios = anuncios;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	
}
