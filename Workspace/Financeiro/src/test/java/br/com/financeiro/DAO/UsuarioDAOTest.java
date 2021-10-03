package br.com.financeiro.DAO;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;
import br.com.financeiro.dao.PessoaDAO;
import br.com.financeiro.dao.UsuarioDAO;
import br.com.financeiro.domain.Pessoa;
import br.com.financeiro.domain.Usuario;

public class UsuarioDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		System.out.println("Pessoa Encontrada");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		usuario.setSenhaSemCriptografia("123456");
		
		//Criptografar a senha
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		usuario.setTipo('A');
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário salvo com sucesso.");
	}
	
	@Test
	
	public void autenticar(){
		String cpf = "222.222.222-22";
		String senha = "123456";
		
		UsuarioDAO usuariodao = new UsuarioDAO();
		Usuario usuario = usuariodao.autenticar(cpf, senha);
		
		System.out.print("Usuário autentica: " + usuario);
	}

}
