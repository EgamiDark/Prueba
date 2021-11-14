package com.example.prueba1.models.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Libro {

	@NotNull
	private Integer id;
	@NotBlank
	@NotEmpty
	@Size(min=5,max=255)
	private String titulo;
	@NotNull
	private Autor autor;
	@NotNull
	private Categoria categoria;
	@NotEmpty
	private String descripcion;
	@NotNull
	private Integer anio;
	@NotEmpty
	@Pattern(regexp = "[0-9]{4}[-][0-9]{2}[-][0-9]{5}[-][0-9]{2}[-][0-9]{1}")
	private String isbn;
	@NotNull
	@Range(min=1)
	private Integer cantPaginas;
	
	public Libro() {

	}
	public Libro(Integer id, String titulo, Autor autor, Categoria categoria, String descripcion, Integer anio,
			String isbn, Integer cantPaginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.anio = anio;
		this.isbn = isbn;
		this.cantPaginas = cantPaginas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getCantPaginas() {
		return cantPaginas;
	}
	public void setCantPaginas(Integer cantPaginas) {
		this.cantPaginas = cantPaginas;
	}


}
