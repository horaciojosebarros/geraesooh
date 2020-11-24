package br.com.jway.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.jway.geraesooh.model.Agencia;
import br.com.jway.geraesooh.model.BiSemana;
import br.com.jway.geraesooh.model.Cidade;
import br.com.jway.geraesooh.model.Pessoa;
import br.com.jway.geraesooh.model.Pi;
import br.com.jway.geraesooh.model.PiPonto;
import br.com.jway.geraesooh.model.Ponto;
import br.com.jway.geraesooh.model.Uf;
import br.com.jway.geraesooh.service.AgenciaService;
import br.com.jway.geraesooh.service.BiSemanaService;
import br.com.jway.geraesooh.service.CidadeService;
import br.com.jway.geraesooh.service.PessoaService;
import br.com.jway.geraesooh.service.PiPontoService;
import br.com.jway.geraesooh.service.PiService;
import br.com.jway.geraesooh.service.PontoService;
import br.com.jway.geraesooh.service.UfService;
import br.com.jway.util.FacesUtils;

@ManagedBean
@ViewScoped
public class PiBean extends SpringBeanAutowiringSupport implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(PiBean.class);

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

	@Inject
	private BiSemanaService biSemanaService;

	@Inject
	private AgenciaService agenciaService;

	@Inject
	private PiPontoService piPontoService;

	private String state;
	private List<Pi> items;
	private List<Ponto> pontos;
	private Pi item;
	private Pi itemFilter;
	private List<Pessoa> anunciantes;

	private String nomeExibidor;
	private List<Cidade> cidades;
	private List<Pessoa> exibidores;
	private List<BiSemana> biSemanas;

	private List<Ponto> pontosDoExibidor;
	private List<PiPonto> piPontos;

	private Agencia agenciaMaster;

	public PiBean() {
		log.info("Bean constructor called.");
		itemFilter = new Pi();
		itemFilter.setProduto("OUTDOOR");
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
			item = Pi.class.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			FacesUtils.addI18nError("generic.bean.unableToCleanViewData");
		}
	}

	public void create() throws Exception {
		item.setData(new Date());
		itemFilter.setProduto("OUTDOOR");
		incluiNumeroPi();
		incluiDetalhes();
		item.setTotalLiquido(item.getTotalBruto() - item.getComissao());
		service.create(item);
		limpaPesquisa();
		items = service.list();
		item = null;

	}

	private void incluiDetalhes() throws Exception {
		piPontos = new ArrayList<PiPonto>();

		for (Ponto ponto : pontosDoExibidor) {
			if (ponto.isCheckBox()) {
				PiPonto piPonto = new PiPonto();
				piPonto.setPi(item);
				piPonto.setPonto(ponto);
				piPonto.setValorLiquidoNegociado(0.00);
				piPonto.setValorTabela(0.00);
				piPontos.add(piPonto);
			}
		}
		item.setDetalhes(piPontos);
		if (piPontos.isEmpty()) {
			throw new Exception("Pontos devem ser escolhidos.");
		}

	}

	private void incluiNumeroPi() {
		this.item.setNumeroPi(
				getAgenciaMaster().getProxNumeroPi().toString().trim() + "/" + this.item.getBiSemana().getAno());
		agenciaMaster = agenciaService.atualizaProxNumeroPi(getAgenciaMaster());
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
		this.pontosDoExibidor = null;

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
		this.item.setDetalhes(piPontoService.buscaDetalhesPorPi(item.getId()));
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

	public List<Pessoa> getAnunciantes() {
		this.anunciantes = pessoaService.listAnunciante();
		return anunciantes;
	}

	public List<BiSemana> getBiSemanas() {
		this.biSemanas = biSemanaService.list();
		return biSemanas;
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

	public void setAnunciantes(List<Pessoa> anunciantes) {
		this.anunciantes = anunciantes;
	}

	public PontoService getPontoService() {
		return pontoService;
	}

	public void setService(PiService service) {
		this.service = service;
	}

	public void setBiSemanas(List<BiSemana> biSemanas) {
		this.biSemanas = biSemanas;
	}

	public BiSemanaService getBiSemanaService() {
		return biSemanaService;
	}

	public void setBiSemanaService(BiSemanaService biSemanaService) {
		this.biSemanaService = biSemanaService;
	}

	public AgenciaService getAgenciaService() {
		return agenciaService;
	}

	public void setAgenciaService(AgenciaService agenciaService) {
		this.agenciaService = agenciaService;
	}

	public Agencia getAgenciaMaster() {
		if (agenciaMaster == null || !agenciaMaster.getMaster().equals("S")) {
			agenciaMaster = agenciaService.buscaAgenciaMaster();
		}
		return agenciaMaster;
	}

	public List<Ponto> getPontosDoExibidor() {
		// Só se for inclusão que traz os pontos
		if (item != null && item.getId() == null && item.getPessoaExibidor() != null
				&& item.getPessoaExibidor().getId() != null) {
			pontosDoExibidor = pontoService.pesquisaPorExibidor(item.getPessoaExibidor().getId());
		}

		return pontosDoExibidor;
	}

	public void setPontosDoExibidor(List<Ponto> pontosDoExibidor) {
		this.pontosDoExibidor = pontosDoExibidor;
	}

	public void refreshPontosDoExibidor() {
		FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext= (ServletContext) context.getCurrentInstance().getExternalContext().getContext();
        String path=servletContext.getRealPath("/");
        
        
		 
		// Só se for inclusão que traz os pontos
		if (item != null && item.getId() == null && item.getPessoaExibidor() != null
				&& item.getPessoaExibidor().getId() != null) {
			pontosDoExibidor = pontoService.pesquisaPorExibidor(item.getPessoaExibidor().getId());
			for (Ponto p : pontosDoExibidor) {
				File file = new File(path + "/public/" + p.getPessoa().getId() + "_"  + p.getId() + ".png");
				FileOutputStream in;
				try {
					in = new FileOutputStream(file);
					in.write(p.getImagem());
					in.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
			}
		}
	}

	public List<PiPonto> getPiPontos() {
		return piPontos;
	}

	public void setPiPontos(List<PiPonto> piPontos) {
		this.piPontos = piPontos;
	}

	public void setAgenciaMaster(Agencia agenciaMaster) {
		this.agenciaMaster = agenciaMaster;
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

}
