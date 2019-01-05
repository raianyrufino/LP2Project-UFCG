package projetoLP2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Comparable<Usuario>, Serializable {


	private static final long serialVersionUID = 3216078662217096784L;
	
	/**
	 * O nome do usuario.
	 */
	private String nome;
	
	/**
	 * O id do Usuario.
	 */
	private String id;
	
	/**
	 * O email do Usuario.
	 */
	private String email;
	
	/**
	 * O numero de celular do Usuario.
	 */
	private String celular;
	
	@SuppressWarnings("unused")
	/**
	 * A classe do Usuario.
	 */
	private String classe;
	
	/**
	 * O status do Usuario.
	 */
	private String status;
	
	/**
	 * A ordem em que o Usuario esta.
	 */
	private int ordem;
	
	/**
	 * A colecao de itens onde serao armazenados.
	 */
	private HashMap<Integer, Item> itens;
	
	
	/**
	 * Constroi um Usuario a partir de seu id, nome, email, celular, classe, ordem e status.
	 * @param id O identificador unico do Usuario.
	 * @param nome O nome do Usuario.
	 * @param email O email do Usuario.
	 * @param celular O celular do Usuario.
	 * @param classe O tipo do Usuario.
	 * @param ordem A ordem em que o Usuario foi adicionado.
	 * @param status A funcao do Usuario.
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, int ordem, String status) {
		this.celular = celular;
		this.classe = classe;
		this.email = email;
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.ordem = ordem;
		this.itens = new HashMap<>();
	}
	
	/**
	 * Metodo set para o celular do Usuario.
	 * @param O numero do celular do Usuario.
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Metodo set para o nome do Usuario.
	 * @param nome O nome do Usuario.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo set para o email do Usuario.
	 * @param email O email do Usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo get para a ordem do Usuario.
	 * @return A ordem em que o Usuario esta.
	 */
	public int getOrdem() {
		return this.ordem;
	}

	/**
	 * Metodo get para o nome do Usuario.
	 * @return O nome do Usuario.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo set para o status do Usuario.
	 * @param status O status do Usuario.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Metodo get para o id do Item.
	 * @param idItem O identificador unico do Item.
	 * @return O id do Item.
	 */
	public Item getItem(int idItem) {
		return this.itens.get(idItem);
	}
	
	/**
	 * Metodo get para o id do Usuario.
	 * @return O identificador unico do Usuario.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Metodo get para o status do Usuario.
	 * @return O status do Usuario.
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Metodo get para o descritor do Item.
	 * @param idItem O identificador unico do Item.
	 * @return O descritor do Item passado como parametro.
	 */
	public String getDescritor(int idItem) {
		return this.itens.get(idItem).getDescritor();
	}
	
	/**
	 * 
	 * @return Uma lista contendo todos os itens do Usuario em questao.
	 */
	public ArrayList<Item> getItensUsuario() {
		ArrayList<Item> itens = new ArrayList<>(this.itens.values());
		return itens;
	}
	
	/**
	 * 
	 * @return O mapa de itens do Usuario.
	 */
	public HashMap<Integer, Item> getMapDeItens() {
		return this.itens;
	}
	
	/**
	 * Adiciona o Item construido a partir de sua descricao, quantidade, lista de Tags e id.
	 * @param descricaoItem A descricao do Item.
	 * @param quantidade A quantidade de itens.
	 * @param listaTags A lista de tags que caracterizam o Item.
	 * @param idItem O identificador unico do Item.
	 * @return O identificador unico do Item.
	 */
	public int adicionaItem(String descricaoItem, int quantidade, String[] listaTags, int idItem, String tipo) {
		
		Item item = new Item(descricaoItem, quantidade, listaTags, idItem, tipo);
		
		for(Item teste : this.itens.values()) {
			if(descricaoItem.equals(teste.getDescritor())) {
				teste.setQuantidade(quantidade);
				teste.setTags(listaTags);
				return teste.getId();
			}
		}
		
		this.itens.put(idItem, item);
		return idItem;
	}
	
	/**
	 * Busca um item no mapa de itens pelo seu identificador unico.
	 * @param idItem O identificado unico do item;
	 * @return A representacao textual de um determinado item.
	 */
	public String exibeItem(int idItem) {
		if (!this.itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return this.itens.get(idItem).toString();
	}
	
	/**
	 * Atualiza a quantidade de itens e/ou a lista de tags do Item.
	 * @param id O identificador unico do Item.
	 * @param quantidade A nova quantidade de itens do Item a ser atualizada. 
	 * @param tags A nova lista de tags do Item a ser atualizada.
	 * @return A representacao textual atualizada do Item informado.
	 */
	public String atualizaItemParaDoacao(int id, int quantidade, String[] tags) {
		if(this.itens.containsKey(id)) {
			if(quantidade != 0){
				this.itens.get(id).setQuantidade(quantidade);
			} if (tags != null) {
				this.itens.get(id).setTags(tags);
			}
			return this.itens.get(id).toString();
		} else {
			throw new IllegalArgumentException("Item nao encontrado: " + id);
		}
	}

	/**
	 * Remove o Item a partir de seu identificador unico.
	 * @param id O identificador unico do Item.
	 */
	public void removeItem(int id) {
		if(this.itens.values().size() == 0) {
			throw new NullPointerException("O Usuario nao possui itens cadastrados.");
		} else if (this.itens.containsKey(id)) {
			this.itens.remove(id);
		} else {
			throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
		}
	}
	
	/**
	 * Metodo get para a quantidade de itens. 
	 * @param idItem O identificador unico do Item.
	 * @return A quantidade de itens de um determinado Item.
	 */
	public int getQuantidadeDoItem(int idItem) {
		return this.itens.get(idItem).getQuantidade();
	}
	
	/**
	 * Adiciona um item necessario a partir de sua descricao, quantidade, lista de tags, id do item e tipo.
	 * @param descricaoItem A descricao do Item.
	 * @param quantidade A quantidade de itens.
	 * @param listaTags A lista de tags que caracterizam o Item.
	 * @param idItem O identificador unico do Item.
	 * @param tipo O tipo do Item.
	 * @return O id do Item.
	 */
	public int adicionaItemNecessario(String descricaoItem, int quantidade, String[] listaTags, int idItem, String tipo) {
		
		Item item = new Item(descricaoItem, quantidade, listaTags, idItem, tipo);
		
		for(Item itemTeste : this.itens.values()) {
			if(descricaoItem.equals(itemTeste.getDescritor())) {
				boolean verificaIgualdade = true;
				for(int i = 0; i < listaTags.length; i++) {
					if(!listaTags[i].equals(itemTeste.getTags()[i])) {
						verificaIgualdade = false;
						break;
					}
				}
				if(verificaIgualdade == true) {
					if (itemTeste.getQuantidade() == quantidade) {
						return itemTeste.getId();
					} else {
						itemTeste.setQuantidade(quantidade);
						return itemTeste.getId();
					}	
				} 
						
			}
		}
		this.itens.put(idItem, item);
		return idItem;
	}
	
	/**
	 * Atualiza um Item Necessario a partir de seu id, nova quantidade e/ou nova lista de tags.
	 * @param id O identificador unico do Item.
	 * @param quantidade A nova quantidade de itens.
	 * @param tags A nova lista de tags que caracterizam o Item.
	 * @return A representacao textual do Item atualizada.
	 */
	public String atualizaItemNecessario(int id, int quantidade, String[] tags) {
		if(this.itens.containsKey(id)) {
			if(quantidade > 0){
				this.itens.get(id).setQuantidade(quantidade);
			} if (tags != null) {
				this.itens.get(id).setTags(tags);
			}
			return this.itens.get(id).toString();
		} else {
			throw new IllegalArgumentException("Item nao encontrado: " + id);
		}
	}
	
	/**
	 * Busca o Item no mapa de itens a partir de seu id.
	 * @param idItem O identificador do Item.
	 * @return Um boolean representando se o item esta ou nao contido.
	 */
	public boolean verificaIdItem(int idItem) {
		if(this.itens.containsKey(idItem)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return Um boolean representando a verificacao de um Item.
	 * 
	 */
	public boolean verificaItem() {
		boolean flag = true;
		for (Integer id : this.itens.keySet())  {
			if (id != null) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
     * Retorna a informacao a respeito da comparacao
     * entre dois usuarios, que sao comparados a partir
     * de sua respectiva ordem.
     * 
     * @return Informacao a respeito da comparacao dos Usuarios.
     */
	@Override
	public int compareTo(Usuario us2) {
		return Integer.compare(this.getOrdem(), us2.getOrdem());
	}

	/**
	 * Retorna a representacao textual de um Usuario no seguinte formato:
	 * Nome/id, email, celular, status: status
	 */
	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}

	/**
	 *Gera um inteiro que representa hashCode de Usuario a partir de seu id.
	 *
	 * @return o inteiro representando o hashCode do Usuario.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Metodo equals, que compara o objeto com outro a partir de seu id.
	 * 
	 * @param obj Objeto a ser comparado.
	 * @return um valor booleano que indica se os objetos tem o mesmo id ou nao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}