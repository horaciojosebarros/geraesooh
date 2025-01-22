package br.com.jway.utils.converter; 

import java.io.Serializable;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;

import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.service.PessoaService;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@RequestScoped
public class PessoaConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.contains("--")||value.contains("Escolha")){
			return null;
		}
		try {
			long id = Long.parseLong(value);
			Object object = service.read(id);
			return object;
		} catch (Exception e) {
			
			throw new ConverterException("Não foi possível encontrar a Pessoa de id: " + value + ". " + e.getMessage());
		}
	
	
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null && value != "") {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getId() + "";
		}
		return null;
	}
}