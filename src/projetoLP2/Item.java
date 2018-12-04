package projetoLP2;

/**
 * Representacao de um Item. Um Item deve possuir um identificador unico (Id), uma descricao,
 * uma quantidade e uma lista de tags que caracterizam o Item.
 *
 */
public class Item implements Comparable<Item> {

	/**
	 * O identificador unico do Item.
	 */
	private int id;
	
	/**
	 * A descricao do Item. 
	 */
	private String descricao;
	
	/**
	 * A quantidade de um determinado item a ser doado;
	 */
	private int quantidade;
	
	/**
	 * A lista de tags que caracterizam o Item.
	 */
	private String[] tags;

	/**
     * Constroi o Item a partir da sua descricao, quantidade, lista de tags e id. 
     * 
     * @param descricao A descricao do Item.
     * @param quantidade A quantidade de itens.
     * @param tags A lista de caracteristicas do Item.
     * @param id O identificador unico do Item.
     */
	public Item(String descricao, int quantidade, String[] tags, int id) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.id = id;
	}
	
	/**
	 * Metodo get para a quantidade de itens.
	 * @return A quantidade de itens.
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public String[] getTags() {
		return this.tags;
	}

	/**
	 * Metodo set para atualizar a quantidade de itens.
	 * @param novaQuantidade A nova quantidade de itens a ser atualizada.
	 */
	public void setQuantidade(int novaQuantidade) {
		this.quantidade = novaQuantidade;
	}

	/**
	 * Metodo set para atualizar a lista de tags.
	 * @param novasTags A lista de tags a serem atualizadas.
	 */
	public void setTags(String[] novasTags) {
		this.tags = novasTags;
	}

	/**
	 * Metodo get para o id do Item.
	 * @return O identificador unico do Item;
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Metodo get para o descritor do Item;
	 * @return O descritor do Item;
	 */
	public String getDescritor() {
		return this.descricao;
	}
	

	/**
	 * Retorna a representacao textual do Item construido no seguinte formato:
	 * id - descricao "tags: []" quantidade
	 */
	public String toString() {
		String msg = "";
		int cont = 0;
		for(String tag : this.tags) {
			if(cont == 0) {
				msg += tag;
			} else {
				msg += ", " + tag;
			}
			cont++;
		}
		return this.id + " - " + this.descricao + ", tags: " + "[" + msg + "]" + ", quantidade: " + this.quantidade;
	}

	@Override
	public int compareTo(Item item) {
		return this.quantidade - item.getQuantidade();
	}
}
