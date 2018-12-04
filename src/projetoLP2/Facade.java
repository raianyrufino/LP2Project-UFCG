package projetoLP2;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {

	private UsuarioController uc;
	
	public static void main(String[] args) {
		args = new String[] { "projetoLP2.Facade", "arquivos_sistema/use_case_1.txt", 
												   "arquivos_sistema/use_case_2.txt",
												   "arquivos_sistema/use_case_3.txt",
												   "arquivos_sistema/use_case_4.txt"};

		EasyAccept.main(args);

	}

	public Facade() {
		this.uc = new UsuarioController();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
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
	
	/**
	 * Adiciona um descritor a partir de sua descricao.
	 * @param descricao A descricao de um descritor.
	 */
	public void adicionaDescritor(String descricao) {
		uc.adicionaDescritor(descricao);
	}
	
	/**
	 * Adiciona um item para doacao a partir do id do usuario doador que deseja doar, alem da descricao,
	 * quantidade e lista de tags do Item a ser doado.
	 * @param idDoador O identificador unico do usuario doador.
	 * @param descricaoItem A descricao do Item a ser doado.
	 * @param quantidade A quantidade de itens a serem doados.
	 * @param tags A lista de tags do Item que o caracterizam.
	 * @return O identificador unico do Item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return uc.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}
	
	/**
	 * Exibe o Item de um determinado usuario doador a partir do id do item e do doador.
	 * @param idItem O identificador unico do Item.
	 * @param idDoador O identificador unico do usuario doador.
	 * @return A representacao textual do Item de um determinado usuario doador.
	 */
	public String exibeItem(int id, String idDoador) {
		return uc.exibeItem(id, idDoador);
	}
	
	/**
	 * Atualiza o Item de um determinado usuario doador a partir do id do item e do doador,
	 * alem da informacao da nova quantidade de itens e/ou nova lista de tags do Item.
	 * @param idItem O identificador unico do item.
	 * @param idDoador O identificado unico do usuario doador.
	 * @param quantidade A nova quantidade de itens do Item informado.
	 * @param tags A nova lista de tags do Item informado.
	 * @return A representacao textual atualizada do Item informado.
	 */
	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		return uc.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
	}
	
	/**
	 * Remove o Item de um determinado usuario doador a partir do id do Item e do doador.
	 * @param idItem O identificador unico do Item.
	 * @param idUsuario O identificador unico do usuario doador.
	 */
	public void removeItemParaDoacao(int id, String idDoador) {
		uc.removeItem(id, idDoador);
	}
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return uc.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	public String listaItensNecessarios() {
		return uc.listaItensNecessarios();
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novastag) {
		return uc.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novastag);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		uc.removeItemNecessario(idReceptor, idItem);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return uc.listaDescritorDeItensParaDoacao();
	}
	
	public String listaItensParaDoacao() {
		return uc.listaItensParaDoacao();
	} 
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return uc.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
}
