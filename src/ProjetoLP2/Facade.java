package ProjetoLP2;

import java.io.IOException;

public class Facade {
	private UsuarioController uc;
	
	public Facade() {
		this.uc = new UsuarioController();
	}
	
	public String adicionaDoador(String id,String nome,String email,String celular,String classe) {
		uc.adicionaDoador(id, nome, email, celular, classe);
		return id;
	}
	
	public void lerReceptores(String caminho) throws IOException {
		uc.lerReceptores(caminho);
	}

	public String pesquisaUsuarioPorId(String id) {
		return uc.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return uc.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return uc.atualizaUsuario(id, nome, email, celular);
		
	}
	
	public void removeUsuario(String id) {
		uc.removeUsuario(id);
	}
	
}
