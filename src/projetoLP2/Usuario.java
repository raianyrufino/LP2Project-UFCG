package projetoLP2;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario implements Comparable<Usuario> {

	private String nome;
	private String id;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int ordem;
	private HashMap<Integer, Item> itens;
	
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
	
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOrdem() {
		return this.ordem;
	}

	public String getNome() {
		return this.nome;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Item getItem(int idItem) {
		return this.itens.get(idItem);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getDescritor(int idItem) {
		return this.itens.get(idItem).getDescritor();
	}
	
	public ArrayList<Item> getItensUsuario() {
		ArrayList<Item> itens = new ArrayList<>(this.itens.values());
		return itens;
	}
	
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
	public int adicionaItem(String descricaoItem, int quantidade, String[] listaTags, int idItem) {
		
		Item item = new Item(descricaoItem, quantidade, listaTags, idItem);
		
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
	 * @param id O identificador unico do Item 
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
	 * @param id O identificador unico do Item;
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
		
	public int getQuantidadeDoItem(int idItem) {
		return this.itens.get(idItem).getQuantidade();
	}
	
	public int getIdItem(String descritor) {

		for(Item item : this.itens.values()) {
			if(item.getDescritor().equals(descritor)) {
				return item.getId();
			}
		}
		return -1;
	}
	
	public int adicionaItemNecessario(String descricaoItem, int quantidade, String[] listaTags, int idItem) {
		
		Item item = new Item(descricaoItem, quantidade, listaTags, idItem);
		
		for(Item teste : this.itens.values()) {
			if(descricaoItem.equals(teste.getDescritor())) {
				boolean verificaIgualdade = true;
				for(int i = 0; i < listaTags.length; i++) {
					if(!listaTags[i].equals(teste.getTags()[i])) {
						verificaIgualdade = false;
						break;
					}
				}
				if(verificaIgualdade == true) {
					if (teste.getQuantidade() == quantidade) {
						return teste.getId();
					} else {
						teste.setQuantidade(quantidade);
						return teste.getId();
					}	
				} 
						
			}
		}
		this.itens.put(idItem, item);
		return idItem;
	}
	
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
	
	public boolean verificaIdItem(int idItem) {
		if(this.itens.containsKey(idItem)) {
			return true;
		}
		return false;
	}
	
	public boolean verificaItem() {
		boolean flag = true;
		for (Integer id : this.itens.keySet())  {
			if (id != null) {
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public int compareTo(Usuario us2) {
		return Integer.compare(this.getOrdem(), us2.getOrdem());
	}

	@Override
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}
	
	
}
