package com.example.prueba1.models.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotEmpty
	@Size(min = 5, max = 255)
	private String titulo;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	private Autor autor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@NotEmpty
	private String descripcion;

	private String imagen;

	@NotNull
	private Integer anio;

	@NotEmpty
	@Pattern(regexp = "[0-9]{3}[-][0-9]{2}[-][0-9]{5}[-][0-9]{2}[-][0-9]{1}")
	private String isbn;

	@NotNull
	@Range(min = 1)
	private Integer cantPaginas;
	@NotNull
	@Range(min = 1)
	private Integer precio;
	public Libro() {
	}

	public Libro(Long id, String titulo, Autor autor, Categoria categoria, String descripcion, String imagen,
			Integer anio, String isbn, Integer cantPaginas,Integer precio) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.anio = anio;
		this.isbn = isbn;
		this.cantPaginas = cantPaginas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

}
