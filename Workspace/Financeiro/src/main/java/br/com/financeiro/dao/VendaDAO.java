package br.com.financeiro.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.financeiro.domain.ItemVenda;
import br.com.financeiro.domain.Produto;
import br.com.financeiro.domain.Venda;
import br.com.financeiro.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {
	public void salvar(Venda venda, List<ItemVenda> itensVenda){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
		
			sessao.save(venda);
			
			for(int posicao = 0; posicao < itensVenda.size(); posicao++){
				ItemVenda itemVenda = itensVenda.get(posicao);
				itemVenda.setVenda(venda);
				
				sessao.save(itemVenda);
				
				Produto produto = itemVenda.getProduto();
				
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				
				if(quantidade >= 0){
				
				produto.setQuantidade(new Short((quantidade) + ""));
				
				sessao.update(produto);
				}else{
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}
			}
			
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}