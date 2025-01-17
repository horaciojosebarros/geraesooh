package br.com.jway.util;
 
//import java.io.PrintWriter;
//import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;
 
//Inicialmente devemos implementar a classe CustomExceptionHandler que extende a classe ExceptionHandlerWrapper
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    //Obtém uma instância do FacesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    //Obtém um mapa do FacesContext
    final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    //Obtém o estado atual da navegação entre páginas do JSF
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    //Declara o construtor que recebe uma exceptio do tipo ExceptionHandler como parâmetro
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    //Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    //Sobrescreve o método handle que é responsável por manipular as exceções do JSF
    @Override
    public void handle() throws FacesException {
 
        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
 
            // Recupera a exceção do contexto
            Throwable exception = context.getException();
 
            // Aqui tentamos tratar a exeção
            try {
 
                // Coloca uma mensagem de exceção no mapa da request
                requestMap.put("exceptionMessage", exception.getMessage());
 
                // Avisa o usuário do erro
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, trataMsg(exception), ""));
 
                // Tranquiliza o usuário para que ele continue usando o sistema
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                   // (FacesMessage.SEVERITY_INFO, "Você pode continuar usando o sistema normalmente!", ""));
 
                // Seta a navegação para uma página padrão.
                //navigationHandler.handleNavigation(facesContext, null, "/public/login.faces");
 
                // Renderiza a pagina de erro e exibe as mensagens
                facesContext.renderResponse();
                
                exception.printStackTrace();
            } finally {
            	
                // Remove a exeção da fila
                iterator.remove();
            }
        }
        // Manipula o erro
        getWrapped().handle();
    }

	private String trataMsg(Throwable exception) {
		String msg = exception.getMessage();
		if (exception.getMessage().contains("NoResultException")) {
			msg = "Registro não encontrado!";
		}
		if (exception.getMessage().contains("ConnectException")) {
			msg = "Erro de conexão ao banco de dados!";
		}
		if (exception.getMessage().contains("GenericJDBCException")) {
			msg = "Erro de conexão ao banco de dados!";
		}
		if (exception.getMessage().contains("ConstraintViolationException")) {
			msg = "Registro já sendo utilizado!";
		}
		if (exception.getMessage().contains("ViewExpiredException")) {
			msg = "Página ou Sessão expirada!";
		}
		if (exception.getMessage().contains("DataIntegrityViolationException")) {
			msg = "Integridade de dados violada ou Tamanho do dado maior que capacidade de armazenamento do campo!";
		}
		
		if (exception.getMessage().contains("NullPointerException")) {
			msg = "Dado inválido ou nulo!";
		}
		
		return msg;
	}
}