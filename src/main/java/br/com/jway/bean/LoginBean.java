package br.com.jway.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Usuario;
import br.com.jway.geraesooh.service.UsuarioService;


@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	protected static final Log log = LogFactory.getLog(LoginBean.class);

	@Inject
	private UsuarioService usuarioService;

	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado = new Usuario();

	private String msg;
	private String senha1;
	private String senha2;
	private Session session;
	private Boolean erro;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void setSession(Session session) {
		this.session = session;
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
			
			return "/public/login.faces";

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

	public Session getSession() {
		return session;
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
		/*if (tenancy!=null&&tenancy.getEscola()!=null&&tenancy.getEscola().getLogo() != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(tenancy.getEscola().getLogo()), "image/png");
		} else {
			return new DefaultStreamedContent();
		}*/
		return null;
	}

}