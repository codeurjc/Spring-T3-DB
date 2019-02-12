package es.codeurjc.mongo.ejer2.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Comentario {

	@Id
	private String id;
	private String apodo;
	private String contenido;
	private Date fecha; // Ojo! El formato de fechas aceptadas es "yyyy-mm-dd" entre otros

	public Comentario() {
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
