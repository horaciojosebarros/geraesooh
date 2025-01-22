package br.com.jway.utils.converter; 

import java.io.Serializable;

import jakarta.annotation.ManagedBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.view.ViewScoped;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.UfService;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ViewScoped

public class UfConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UfService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.contains("--")||value.contains("Escolha")){
			return null;
		}
		try {
			String id = value;
			Object object = service.read(id);
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException("N�o foi poss�vel encontrar a Ufl de id: " + value + ". " + e.getMessage());
		}
	
	
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null && value != "") {
			Uf uf = (Uf) value;
			return uf.getUf() + "";
		}
		return null;
	}
}