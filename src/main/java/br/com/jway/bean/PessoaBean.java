package br.com.jway.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.service.PessoaService;
import br.com.jway.util.FacesUtils;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named

@ViewScoped
public class PessoaBean  implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PessoaBean.class);

	@Inject
	private PessoaService service;
	
	private String state;
	private List<Pessoa> items;
	private Pessoa item;
	private Pessoa itemFilter;
	
	
	public PessoaBean() {
		log.info("Bean constructor called.");
		itemFilter = new Pessoa();
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
			item = Pessoa.class.newInstance();
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
	
	public List<Pessoa> getItems() {
		return items;
	}
	
	public void setItems(List<Pessoa> items) {
		this.items = items;
	}
	
	public Pessoa getItem() {
		return item;
	}
	
	public void setItem(Pessoa item) {
		this.item = item;
	}
	public Pessoa getItemFilter() {
		return itemFilter;
	}
	
	public void setItemFilter(Pessoa itemFilter) {
		this.itemFilter = itemFilter;
	}



}
