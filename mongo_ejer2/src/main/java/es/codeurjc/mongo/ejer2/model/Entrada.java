package es.codeurjc.mongo.ejer2.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonView;

public class Entrada {
	
	@Id
	private String id;
	
	private String nombre;
	private String apodo;
	private String titulo;
	private String resumen;
	private String contenido;
	// Es necesario inicializar el atributo porque el mapeo de Spring Data para MongoDB no lo va a inicializar por nosotros.
	// En caso contrario, al intentar añadir un comentario en el controlador a esta lista, tendríamos un NullPointerException.
	@DBRef
	private List<Comentario> comentarios = new ArrayList<>(); 
	
	public Entrada() {
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

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
}
