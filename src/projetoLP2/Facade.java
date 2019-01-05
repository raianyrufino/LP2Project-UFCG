package projetoLP2;

import java.io.IOException;
import java.io.Serializable;
import easyaccept.EasyAccept;

public class Facade implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1342267897865658589L;
	private UsuarioController uc;
	private Persistencia persistencia;
	
	public static void main(String[] args) {
		args = new String[] { "projetoLP2.Facade", "arquivos_sistema/use_case_1.txt", 
												   "arquivos_sistema/use_case_2.txt",
												   "arquivos_sistema/use_case_3.txt",
												   "arquivos_sistema/use_case_4.txt",
												   "arquivos_sistema/use_case_5.txt",
												   "arquivos_sistema/use_case_6.txt",
												   "arquivos_sistema/use_case_7.txt"};

		EasyAccept.main(args);

	}
	
	/**
	  * Constroi a Facade e inicializa o sistema de Usuario e a Persistencia de dados.
	  */
	public Facade() {
		this.uc = new UsuarioController();
		this.persistencia = new Persistencia();
	}

	/** Cria arquivo que ira salvar os dados a partir da facade.
	 * 
	 *
	 * @throws IOException lanca excecao de entrada/saida.
	 * @throws ClassNotFoundException trata excecao de classe nao encontrada.
	 */
	public void iniciaSistema() throws ClassNotFoundException, IOException {
		this.persistencia.iniciaSistema();
    }
	
	/**
	 * Salva os dados quando o projeto for rodado.(uma instancia do sistema em um arquivo .log).
	 * 
	 *  
	 * @throws IOException lanca excecao de entrada/saida.
	 */
	public void finalizaSistema() throws IOException {
		this.persistencia.fechasistema(this);
    }
	
	/**
	 * Adiciona um novo doador a partir de seu id, nome, email, celular e classe.
	 * 
	 * @param id      O numero do cpf ou do cnpj como identificador do doador.
	 * @param nome    O nome do doador.
	 * @param email   O email do doador.
	 * @param celular O numero de celular do doador.
	 * @param classe  O tipo de doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		uc.adicionaDoador(id, nome, email, celular, classe);
		return id;
	}
	
	/**
	 * metodo que cadastra os receptores a partir de um arquivo csv
	 * 
	 * @param caminho o caminho do arquivo csv no sistema
	 * @throws IOException caso haja algum erro com o arquivo
	 */
	public void lerReceptores(String caminho) throws IOException {
		uc.lerReceptores(caminho);
	}
	
	/**
	 * Busca um usuario no mapa de usuarios a partir de seu id.
	 * 
	 * @param id O identificador unico do Usuario.
	 * @return A representacao textual do Usuario.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return uc.pesquisaUsuarioPorId(id);
	}
	
	/**
	 * Pesquisa um usuario no mapa de usuarios a partir de seu nome para altera-lo caso exista.
	 * 
	 * @param nome O nome do Usuario.
	 * @return A representacao textual de todos os usuarios com este nome.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return uc.pesquisaUsuarioPorNome(nome);
	}
	
	/**
	 * Busca um Usuario no mapa de usuarios a partir do seu id e caso exista, atualiza conforme os parametros passados.
	 * 
	 * @param id      O identificador unico do Usuario.
	 * @param nome    O novo nome do Usuario.
	 * @param email   O novo email do Usuario.
	 * @param celular O novo numero de celular do Usuario.
	 * @return A representacao textual do Usuario atualizada.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return uc.atualizaUsuario(id, nome, email, celular);
	}
	
	/**
	 * Busca um Usuario no mapa de usuarios a partir de seu id, caso exista o remove do mapa de usuarios.
	 * 
	 * @param id O identificador unico do Usuario.
	 */
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
	
	/**
	 * Adiciona itens necessarios associados ao usuario receptor.
	 * 
	 * @param idReceptor O id unico do usuario receptor.
	 * @param descricaoItem A descricao do item a ser doado.
	 * @param quantidade A quantidade de itens do item informado.
	 * @param tags A lista de tags do item.
	 * @return Adicao de item necessario com descricao, quantidade e tags.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return uc.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	/**
	 * Lista os itens necessarios a partir do seu id.
	 * 
	 * @return A lista de itens necessarios cadastrados ordenados pelo id do item.
	 */
	public String listaItensNecessarios() {
		return uc.listaItensNecessarios();
	}

	/**
	 * Atualiza tag e quantidade do item necessario identificado pelo seu id unico.
	 * 
	 * @param idReceptor  O id unico do usuario receptor.
	 * @param idItem O identificador unico do item.
	 * @param novaQuantidade A nova quantidade de itens.
	 * @param novasTags A nova tag.
	 * @return
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novastag) {
		return uc.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novastag);
	}
	
	/**
	 * Remove itens a serem doados de um usuario a partir do id unico do item.
	 * 
	 * @param idReceptor O id unico do usuario receptor.
	 * @param idItem O identificador unico do item.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		uc.removeItemNecessario(idReceptor, idItem);
	}
	
	/**
	 * Encontra casamentos (matches) entre itens a serem doados e itens necessarios. Uma pontuacao e calculada, seguindo as seguintes regras:
	 * Os itens para doacao que tem o mesmo descritor do item necessario iniciam o processo com 20 pontos. Tags iguais na mesma posição somam 10 pontos, 
	 * tags iguais em posições diferentes somam 5 pontos. Esta funcionalidade retorna os itens a serem doados, ordenados da maior para a menor pontuação. 
	 * Se as pontuações forem iguais ordena-se pelo identificador dos itens.
	 * 
	 * @param idReceptor Identificador do usuario receptor.
	 * @param idItemNecessario Identificador do item necessario.
	 * @return Uma representacao textual dos itens que casam com o item pesquisado.
	 */
	public String match(String idReceptor, int idItemNecessario) {
		return uc.match(idReceptor, idItemNecessario);
	}
	
	/**
	 * Efetua a listagem de todos os descritores de itens cadastrados no sistema, 
	 * ordenada em ordem alfabética pela descrição do item, no formato:
	 * "quantidade - descritor | quantidade - descritor | ..."
	 * 
	 * @return Uma representacao textual dos descritores de itens para doacao
	 */
	public String listaDescritorDeItensParaDoacao() {
		return uc.listaDescritorDeItensParaDoacao();
	}
	
	/**
	 * Efetua a listagem de todos os itens inseridos no sistema, ordenados pela quantidade.
	 * Os itens em maior quantidade aparecem primeiro e os com menos quantidades aparecem por último. 
	 * Itens com a mesma quantidade são ordenados pela ordem alfabetica da descrição.
	 * A listagem obedece ao formato: <idItem - descricao, tags: [tag1, tag2, …], quantidade: 'qtde', doador: 'nomeDoador'/'docDoador'
	 * 
	 * @return Uma representacao textual dos itens disponiveis para doacao.
	 */ 
	public String listaItensParaDoacao() {
		return uc.listaItensParaDoacao();
	} 
	
	/**
	 * Efetua a listagem de todos os itens relacionados a uma dada string de pesquisa, em ordem alfabética considerando os descritores dos itens.
	 * A listagem obedece ao formato: idItem1 - item para doação 1 | idItem2 - item para doação 2 ...
	 * 
	 * @return Uma representacao textual dos itens relacionados a descricao pesquisada.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return uc.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	/**
	 * Solicita uma doacao a partir do id do item necessario e o do item a ser doado, alem da data
	 * quando solicitou.
	 * @param idItemNecessario O identificador unico do item necessario.
	 * @param idItemDoado O identificador unico do item a ser doado.
	 * @param data A data da solicitacao de doacao.
	 * @return A representacao textual da doacao realizada no seguinte formato:
	 * Data - doador: nome do doador/ id do doador, item: descritor, 
	 * quantidade: quantidade, receptor: nome do receptor/ id do receptor
	 */
	
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		return uc.realizaDoacao(idItemNecessario, idItemDoado, data);
	}
	
	/**
	 * Lista a representacao de todas as doacoes realizadas ordenadas pela data
	 * e descricao respectivamente
	 * 
	 * @return uma string com a representacao de todas as doacoes realizadas
	 */
	public String listaDoacoes() {
		return uc.listaDoacoes();
	}

}