package br.com.financeiro.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.financeiro.Bean.AutenticacaoBean;
import br.com.financeiro.domain.Usuario;

public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		
		String paginaAtual = Faces.getViewId();
		
		
		boolean paginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		if(!paginaAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			
			if(autenticacaoBean == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				
				return;
			}
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}
	
	

}
