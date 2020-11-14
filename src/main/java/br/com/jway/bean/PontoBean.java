package br.com.jway.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Ponto;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.PontoService;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;

@ManagedBean
@ViewScoped
public class PontoBean extends SpringBeanAutowiringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PontoBean.class);

	@Inject
	private PontoService service;

	@Inject
	private UfService ufService;

	@Inject
	private CidadeService cidadeService;

	private String state;
	private List<Ponto> items;
	private Ponto item;
	private Ponto itemFilter;
	private List<Ponto> listaPonto;

	private List<Cidade> cidades;

	public PontoBean() {
		log.info("Bean constructor called.");
		itemFilter = new Ponto();
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
			item = Ponto.class.newInstance();
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
	
	public void pesquisaPorNome() {
		items = service.pesquisaPorExibidor(itemFilter.getPessoa().getId());
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

	public List<Ponto> getItems() {
		return items;
	}

	public void setItems(List<Ponto> items) {
		this.items = items;
	}

	public Ponto getItem() {
		return item;
	}

	public void setItem(Ponto item) {
		this.item = item;
	}

	public Ponto getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(Ponto itemFilter) {
		this.itemFilter = itemFilter;
	}

	public List<Uf> getUfs() {
		return ufService.list();

	}

	public List<Cidade> getCidades() {
		if (cidades == null && item != null) {
			refreshUf();
		}
		return cidades;
	}

	public void refreshUf() {
		cidades = cidadeService.findByUf(item.getUf());

	}

	public PontoService getService() {
		return service;
	}

	public void setService(PontoService service) {
		this.service = service;
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

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public void setListaPonto(List<Ponto> listaPonto) {
		this.listaPonto = listaPonto;
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
	
	

}
