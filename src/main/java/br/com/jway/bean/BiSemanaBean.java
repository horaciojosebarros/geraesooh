package br.com.jway.bean;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.BiSemana;
import br.com.jway.geraesooh.service.BiSemanaService;
import br.com.jway.util.FacesUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ManagedBean
@ViewScoped
public class BiSemanaBean extends SpringBeanAutowiringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(BiSemanaBean.class);

	@Inject
	private BiSemanaService service;

	private String state;
	private List<BiSemana> items;
	private BiSemana item;
	private BiSemana itemFilter;

	public BiSemanaBean() {
		log.info("Bean constructor called.");
		itemFilter = new BiSemana();
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
			item = BiSemana.class.newInstance();
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

	public List<BiSemana> getItems() {
		return items;
	}

	public void setItems(List<BiSemana> items) {
		this.items = items;
	}

	public BiSemana getItem() {
		return item;
	}

	public void setItem(BiSemana item) {
		this.item = item;
	}

	public BiSemana getItemFilter() {
		return itemFilter;
	}

	public void setItemFilter(BiSemana itemFilter) {
		this.itemFilter = itemFilter;
	}


	public BiSemanaService getService() {
		return service;
	}

	public void setService(BiSemanaService service) {
		this.service = service;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}


}
