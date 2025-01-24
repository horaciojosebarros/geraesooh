package br.com.jway.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Agencia;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.AgenciaService;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;

@Named
@ViewScoped
public class AgenciaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(AgenciaBean.class);

	@Inject
	private AgenciaService service;

	@Inject
	private UfService ufService;

	@Inject
	private CidadeService cidadeService;

	private String state;
	private List<Agencia> items;
	private Agencia item;
	private Agencia itemFilter;
	private List<Agencia> listaAgencia;

	private List<Cidade> cidades;

	public AgenciaBean() {
		log.info("Bean constructor called.");
		itemFilter = new Agencia();
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
			item = Agencia.class.newInstance();
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
		items = service.pesquisaPorNome(itemFilter.getRazaoSocial());
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

	public List<Agencia> getItems() {
		return items;
	}

	public void setItems(List<Agencia> items) {
		this.items = items;
	}

	public Agencia getItem() {
		return item;
	}

	public void setItem(Agencia item) {
		this.item = item;
	}

	public Agencia getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(Agencia itemFilter) {
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

	public AgenciaService getService() {
		return service;
	}

	public void setService(AgenciaService service) {
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

	public List<Agencia> getListaAgencia() {
		return listaAgencia;
	}

	public void setListaAgencia(List<Agencia> listaAgencia) {
		this.listaAgencia = listaAgencia;
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
