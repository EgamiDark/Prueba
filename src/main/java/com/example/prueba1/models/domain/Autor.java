package com.example.prueba1.models.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "autores")
public class Autor {
	@Id
	private String id;

	@NotEmpty
	@Size(min = 4, max = 255)
	private String nombre;

	@NotEmpty
	@Size(min = 4, max = 255)
	private String apellido;

	@NotEmpty
	private String nacionalidad;

	private String imagen;

	@NotEmpty
	private String fechaNacimiento;

	@NotEmpty
	@NotBlank
	@Size(min = 3, max = 255)
	private String seudonimo;

	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Libro> libros;

	public Autor() {
		this.libros = new ArrayList<Libro>();
	}

	public Autor(String id, String nombre, String apellido, String nacionalidad, String imagen, String fechaNacimiento,
			String seudonimo, List<Libro> libros) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.imagen = imagen;
		this.fechaNacimiento = fechaNacimiento;
		this.seudonimo = seudonimo;
		this.libros = libros;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSeudonimo() {
		return seudonimo;
	}

	public void setSeudonimo(String seudonimo) {
		this.seudonimo = seudonimo;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

}
