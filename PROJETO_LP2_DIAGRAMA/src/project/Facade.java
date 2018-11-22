package project;

import java.util.Map;

public class facade {
	
	private Map<String, usuario> mapa_de_usuarios;
	private Map<String, Item> mapa_de_itens;
	// US1
	
	public facade() {
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
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return "representacao atualizada";
	}
	
	public void removeUsuario(String id) {
		
	}
	
	// US2 
	
	public void adicionaDescritor(String descricao) {
		
	}
	
	public void adicionaItemParaDoacao(String id, String descricaoItem, int quantidade, String[] tags ) {
		
	}
	
	public void exibeItem(String id, String idDoador) {
		
	}
	
	public void atualizaItemParaDoacao(String id, String idDoador, int quantidade, String[] tags) {
		
	}
	
	public void removerItemParaDoacao(String id, String idDoador) {
		
	}
	
	
}
 