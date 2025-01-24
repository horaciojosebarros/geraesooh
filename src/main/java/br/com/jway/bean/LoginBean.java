package br.com.jway.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.StreamedContent;

import br.com.jway.geraesooh.model.Usuario;
import br.com.jway.geraesooh.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID1 = 1L;
	protected static final Log log = LogFactory.getLog(LoginBean.class);

	@Inject
	private UsuarioService usuarioService;

	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado = new Usuario();

	private String msg;
	private String senha1;
	private String senha2;
	private Boolean erro;


	public static long getSerialversionuid() {
		return serialVersionUID1;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	public LoginBean() {
		log.info("Bean @PostConstruct called.");
		setErro(false);

	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public String efetuaLogin() {

			Usuario usuarioAux = usuarioService.busca(usuarioLogado);

			if (usuarioAux != null && usuarioAux.getId() != null) {
				msg = "";
				usuarioLogado = usuarioAux;
				setErro(false);
				
				return "/private/index";
			}
			
			// Add error message to FacesContext
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Incorreto:", "Credenciais inválidas!"));

			
			return "/login.faces";

	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDataAtual() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = new Date();
		return dateFormat.format(dataAtual.getTime());
	}

	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}


	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	public static Log getLog() {
		return log;
	}

	public StreamedContent getImagem() {
		return null;
		
	}

}