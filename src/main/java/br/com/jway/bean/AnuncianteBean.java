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

import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.PessoaService;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;

@ManagedBean
@ViewScoped
public class AnuncianteBean extends SpringBeanAutowiringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PessoaBean.class);

	@Inject
	private PessoaService service;

	@Inject
	private UfService ufService;

	@Inject
	private CidadeService cidadeService;

	private String state;
	private List<Pessoa> items;
	private Pessoa item;
	private Pessoa itemFilter;
	private List<Pessoa> listaPessoa;

	private List<Cidade> cidades;

	public AnuncianteBean() {
		log.info("Bean constructor called.");
		itemFilter = new Pessoa();
		limpaPesquisa();
	}

	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");
		state = "READ";
		items = service.listAnunciante();
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
		item.setAnunciante("S");
		service.create(item);
		limpaPesquisa();
		items = service.listAnunciante();
		item = null;
	}

	public void update() {
		item.setAnunciante("S");
		service.update(item);
		limpaPesquisa();
		items = service.listAnunciante();
		item = null;
	}

	public void delete() {
		service.delete(item.getId());
		limpaPesquisa();
		items = service.listAnunciante();
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

	public PessoaService getService() {
		return service;
	}

	public void setService(PessoaService service) {
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

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
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
