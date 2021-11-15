package com.example.prueba1.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.prueba1.services.CategoriaService;

@Component
public class CategoriaEditor extends PropertyEditorSupport{
	@Autowired
	private CategoriaService categoriaService;
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text != null && text.length()>0) {
			try {
				Long id = Long.parseLong(text);
				this.setValue(categoriaService.findOne(id));
			}
			catch(NumberFormatException e){
				setValue(null);
			}
		}
		else {
			setValue(null);
		}

	}
	
}
