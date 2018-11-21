package project;

import java.util.Map;

public class Facade {
	
	private Map<String, Usuario> mapa_de_usuarios;
	
	public Facade() {
	}
	
	public void lerReceptores(String caminho) {
		
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return id;
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return "representacao";
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return "representacao";
	}
	
}
