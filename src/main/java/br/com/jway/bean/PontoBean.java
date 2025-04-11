package br.com.jway.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.IOUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.model.Ponto;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.PessoaService;
import br.com.jway.geraesooh.service.PontoService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class PontoBean  implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PontoBean.class);

	@Inject
	private PontoService service;

	@Inject
	private UfService ufService;

	@Inject
	private CidadeService cidadeService;

	@Inject
	private PessoaService pessoaService;

	private String state;
	private List<Ponto> items;
	private Ponto item;
	private Ponto itemFilter;
	private List<Pessoa> listaPessoa;
	private String nomeExibidor;
	private List<Cidade> cidades;
	private List<Pessoa> exibidores;

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
		items = service.pesquisaPorNomeExibidor(getNomeExibidor());
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
		} else {
			if (item.getCidade() != null) {
				refreshUf();
			}
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
	

	public StreamedContent getImagem() {
		if (item != null && item.getImagem() != null) {
			return DefaultStreamedContent.builder()
			        .stream(() -> new ByteArrayInputStream(item.getImagem())) // Fornece o InputStream corretamente
			        .contentType("image/png") // Tipo do conteúdo
			        .name(item.getCodigo()) // Nome correto para a imagem
			        .build();
		} else {
			return new DefaultStreamedContent();
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputStream());
			this.item.setImagem(foto);
		} catch (IOException ex) {
			System.out.println("Erro em evento de upload");
		}
	}

}
