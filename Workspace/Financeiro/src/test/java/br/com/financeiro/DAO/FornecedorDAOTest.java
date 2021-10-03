package br.com.financeiro.DAO;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.financeiro.dao.FornecedorDAO;
import br.com.financeiro.domain.Fornecedor;

public class FornecedorDAOTest {
	
		@Test
		@Ignore
		public void salvar() {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setDescricao("Aché");

			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedorDAO.salvar(fornecedor);
		}

		@Test
		@Ignore
		public void listar() {
			FornecedorDAO FornecedorDAO = new FornecedorDAO();
			List<Fornecedor> resultado = FornecedorDAO.listar();

			System.out.println("Total de Registros Encontrados: " + resultado.size());

			for (Fornecedor fornecedor : resultado) {
				System.out.println(fornecedor.getCodigo() + " - " + fornecedor.getDescricao());
			}
		}
		
		@Test
		@Ignore
		public void buscar(){
			Long codigo = 3L;
			
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			Fornecedor fornecedor = fornecedorDAO.buscar(codigo);
			
			if(fornecedor == null){
				System.out.println("Nenhum registro encontrado");
			}else{
				System.out.println("Registro encontrado:");
				System.out.println(fornecedor.getCodigo() + " - " + fornecedor.getDescricao());
			}
		}
}
