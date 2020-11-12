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

import br.com.jway.geraesooh.model.Agencia;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.AgenciaService;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;

@ManagedBean
@ViewScoped
public class AgenciaBean extends SpringBeanAutowiringSupport implements Serializable {

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
	
	public void pesquisa(){
		items = service.pesquisa(item);
	}
	
	public void limpaPesquisa(){
	
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
		return cidades;
	}
	
	public void refreshUf() {
		cidades = cidadeService.findByUf(item.getUf());
		
	}
	
	
}
