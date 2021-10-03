package br.com.financeiro.Bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.financeiro.dao.UsuarioDAO;
import br.com.financeiro.domain.Pessoa;
import br.com.financeiro.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private Usuario usuario;
	
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@PostConstruct
	public void iniciar(){
		
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar(){
		
		try {
			
			UsuarioDAO usuariodao = new UsuarioDAO();
			usuarioLogado = usuariodao.autenticar(usuario.getPessoa().getCpf() , usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalError("CPF ou Senha incorretos");
				
				return;
			}
			
			Faces.redirect("./pages/principal.xhtml");
			
		} catch (IOException erro) {
			
			erro.printStackTrace();
		}		
		
	
	}
	
	public boolean temPermissoes(List<String> permissoes){
		
		for(String permissao : permissoes){
			
			if(usuarioLogado.getTipo() == permissao.charAt(0)){
				
				return true;
			}
		}
		
		return false;
		
	}

}
