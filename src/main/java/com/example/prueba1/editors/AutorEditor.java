package com.example.prueba1.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.prueba1.services.AutorService;

@Component
public class AutorEditor extends PropertyEditorSupport {
	@Autowired
	private AutorService autorService;
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(text != null && text.length()>0) {
			try {
				String id =text;
				this.setValue(autorService.findOne(id));
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
