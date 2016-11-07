package es.urjc.code.mongo.ejem4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
	@CompoundIndex(name="asunto_idx", def="{'nombre':1, 'asunto':-1}")
})
public class Anuncio {

	@Id
	private String id;
	@Indexed
	private String nombre;
	private String asunto;
	private String comentario;

	public Anuncio() {

	}

	public Anuncio(String nombre, String asunto, String comentario) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.comentario = comentario;
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + ", comentario=" + comentario + "]";
	}

}
