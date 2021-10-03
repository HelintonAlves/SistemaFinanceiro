package br.com.financeiro.DAO;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import br.com.financeiro.dao.FornecedorDAO;
import br.com.financeiro.dao.ProdutoDAO;
import br.com.financeiro.domain.Fornecedor;
import br.com.financeiro.domain.Produto;

public class ProdutoDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		Fornecedor fornecedor = fornecedorDAO.buscar(new Long("1"));
		
		Produto produto = new Produto();
		produto.setDescricao("Curso de Java");
		produto.setFornecedor(fornecedor);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("7"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto salvo com sucesso");
	}

}
