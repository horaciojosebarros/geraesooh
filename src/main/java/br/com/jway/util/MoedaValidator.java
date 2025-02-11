package br.com.jway.util;
import java.math.BigDecimal;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
/**
* Efetua a validação de um valor monetário.
*/
@FacesValidator("MoedaValidator")
public class MoedaValidator implements Validator {
   /**
    * Método responsável por validar os campos com moeda. Caso ocorra algum erro lança uma ValidatorException.
    */
   public void validate(FacesContext ctx, UIComponent comp, Object val) throws ValidatorException {
	   BigDecimal valor = (BigDecimal) val;
       if(val == null)
			return;				
        if (valor.intValue() == 0) {
    	   FacesMessage message = new FacesMessage("Preencha com o valor válido maior que zero");
           message.setSeverity(FacesMessage.SEVERITY_ERROR);
           throw new ValidatorException(message);
       }
   }
}