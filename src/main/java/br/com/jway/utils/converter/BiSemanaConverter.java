package br.com.jway.utils.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jway.geraesooh.model.BiSemana;
import br.com.jway.geraesooh.service.BiSemanaService;

@ManagedBean
@ViewScoped
@Named
public class BiSemanaConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BiSemanaService service;
	
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
			e.printStackTrace();
			throw new ConverterException("N�o foi poss�vel encontrar o Pessoa de id: " + value + ". " + e.getMessage());
		}
	
	
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if (value != null && value != "") {
			BiSemana biSemana = (BiSemana) value;
			return biSemana.getId() + "";
		}
		return null;
	}

}
