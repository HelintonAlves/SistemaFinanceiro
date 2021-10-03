package br.com.financeiro.Bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.financeiro.dao.HistoricoDAO;
import br.com.financeiro.dao.ProdutoDAO;
import br.com.financeiro.domain.Historico;
import br.com.financeiro.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutoPesquisaBean {

	private Produto produto;
	private Boolean exibir;
	private Historico historico;
	
	public Historico getHistorico() {
		return historico;
	}
	
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	
	public Boolean getExibir() {
		return exibir;
	}
	
	public void setExibir(Boolean exibir) {
		this.exibir = exibir;
	}
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@PostConstruct
	public void novo() {
		
		historico = new Historico();
		produto = new Produto();
		exibir = false;

	}

	public void buscar() {

		try {
			ProdutoDAO proddao = new ProdutoDAO();

			Produto resultado = proddao.buscar(produto.getCodigo());

			if (resultado == null) {
				Messages.addGlobalWarn("Não existe este produto");
				exibir = false;
			} else {
				exibir = true;
				produto = resultado;
				
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar buscar o produto");
			erro.printStackTrace();
		}

	}
	
	public void salvar(){
		
		try {
			
			historico.setHorario(new Date());
			historico.setProduto(produto);
			
			HistoricoDAO historicodao = new HistoricoDAO();
			historicodao.salvar(historico);
			
			Messages.addGlobalInfo("Histórico salvo com sucesso");
			
		} catch (RuntimeException erro) {
			
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o histórico");
			erro.printStackTrace();
		}
	}

}
