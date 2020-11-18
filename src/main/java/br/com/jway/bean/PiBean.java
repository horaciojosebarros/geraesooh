package br.com.jway.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.model.Pi;
import br.com.jway.geraesooh.model.Ponto;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.PessoaService;
import br.com.jway.geraesooh.service.PiService;
import br.com.jway.geraesooh.service.PontoService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;

@ManagedBean
@SessionScoped
public class PiBean extends SpringBeanAutowiringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PontoBean.class);

	@Inject
	private PiService service;
	
	@Inject
	private PontoService pontoService;


	@Inject
	private UfService ufService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private PessoaService pessoaService;

	private String state;
	private List<Pi> items;
	private List<Ponto> pontos;
	private Pi item;
	private Pi itemFilter;
	private List<Pessoa> anunciantes;
	
	private String nomeExibidor;
	private List<Cidade> cidades;
	private List<Pessoa> exibidores;

	public PiBean() {
		log.info("Bean constructor called.");
		itemFilter = new Pi();
		limpaPesquisa();
	}

	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");
		state = "READ";
		items = service.list();
	}

	public void clearItems() {
		if (items != null) {
			items.clear();
		}
	}

	public void clearItem() {
		try {
			// Instantiating via reflection was used here for generic purposes
			item = Pi.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			FacesUtils.addI18nError("generic.bean.unableToCleanViewData");
		}
	}

	public void create() {
		service.create(item);
		limpaPesquisa();
		items = service.list();
		item = null;
	}

	public void update() {
		service.update(item);
		limpaPesquisa();
		items = service.list();
		item = null;
	}

	public void delete() {
		service.delete(item.getId());
		limpaPesquisa();
		items = service.list();
		item = null;
	}

	public void pesquisa() {
		items = service.pesquisa(item);
	}

	public void limpaPesquisa() {

	}

	@PreDestroy
	private void preDestroy() {
		log.info("Bean @PreDestroy called.");
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Pi> getItems() {
		return items;
	}

	public void setItems(List<Pi> items) {
		this.items = items;
	}

	public Pi getItem() {
		return item;
	}

	public void setItem(Pi item) {
		this.item = item;
	}

	public Pi getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(Pi itemFilter) {
		this.itemFilter = itemFilter;
	}

	public List<Uf> getUfs() {
		return ufService.list();

	}

	public PontoService getService() {
		return pontoService;
	}

	public void setPontoService(PontoService pontoService) {
		this.pontoService = pontoService;
	}

	public UfService getUfService() {
		return ufService;
	}

	public void setUfService(UfService ufService) {
		this.ufService = ufService;
	}

	public CidadeService getCidadeService() {
		return cidadeService;
	}

	public void setCidadeService(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String getNomeExibidor() {
		return nomeExibidor;
	}

	public void setNomeExibidor(String nomeExibidor) {
		this.nomeExibidor = nomeExibidor;
	}

	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	public List<Pessoa> getExibidores() {
		this.exibidores = pessoaService.listExibidor();
		return exibidores;
	}

	public void setExibidores(List<Pessoa> exibidores) {

		this.exibidores = exibidores;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public List<Pessoa> getAnunciantes() {
		return anunciantes;
	}

	public void setAnunciantes(List<Pessoa> anunciantes) {
		this.anunciantes = anunciantes;
	}

	public PontoService getPontoService() {
		return pontoService;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setService(PiService service) {
		this.service = service;
	}
	
	

}
