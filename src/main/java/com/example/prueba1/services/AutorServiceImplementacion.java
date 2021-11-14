package com.example.prueba1.services;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.prueba1.models.domain.Autor;
import java.time.LocalDate;

@Service
public class AutorServiceImplementacion implements AutorService{


	
	private List<Autor> autores;

	public AutorServiceImplementacion() {
		
		this.autores = new ArrayList<Autor>(Arrays.asList(
				new Autor("MAT1998","Matias","San Martin","Chileno",LocalDate.of(1998,5,28).toString(),"Darkegami",null),
				new Autor("BRA1980","Brayan","Aros","Chileno",LocalDate.of(1980,4,06).toString(),"AroBra",null)
				));
	}

	@Override
	public List<Autor> obtenerAutores() {
		// TODO Auto-generated method stub
		return autores;
	}

	@Override
	public Autor obtenerAutor(String id) {
		Autor result = null;
		for(Autor autor: this.autores) {
			if(id.equals(autor.getId())) {
				result = autor;
				break;
			}
		}
		return result;
	}

	@Override
	public void eliminarAutor(String id) {
		// TODO Auto-generated method stub
		for(Autor autor: this.autores) {
			if(id.equals(autor.getId())) {
				this.autores.remove(autor);
				break;
			}
		}
	}

	@Override
	public void crearAutor(Autor autor) {
		// TODO Auto-generated method stub
		String tres = autor.getNombre().substring(0, 3).toUpperCase();
		String anio = autor.getFechaNacimiento().substring(0,4);
		autor.setId(tres+anio);
		this.autores.add(autor);
	}

	@Override
	public void modificarAutor(Autor autor) {
		// TODO Auto-generated method stub
		for(Autor au: this.autores) {
			if(au.getId().equals(autor.getId())) {
				au.setApellido(autor.getApellido());
				au.setFechaNacimiento(autor.getFechaNacimiento().toString());
				au.setNacionalidad(autor.getNacionalidad());
				au.setNombre(autor.getNombre());
				au.setSeudonimo(autor.getSeudonimo());
			}
		}
	}

}
