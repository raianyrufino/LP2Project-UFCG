package projetoLP2;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Representacao de um Item. Um Item deve possuir um identificador unico(Id), uma descricao,
 * uma quantidade e uma lista de tags que caracterizam o Item.
 *
 */
public class Item implements Comparable<Item>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6214797384302662066L;

	/**
	 * O identificador unico do Item.
	 */
	private int id;
	
	/**
	 * A descricao do Item. 
	 */
	private String descricao;
	
	/**
	 * A quantidade de um determinado item a ser doado.
	 */
	private int quantidade;
	
	/**
	 * A lista de tags que caracterizam o Item.
	 */
	private String[] tags;
	
	/**
	 * Identificador do item que informa se ele e necessario por receptores ou disponivel para doacao.
	 */
	private String tipo;
	
	/**
	 * Nivel de similaridade entre os itens.
	 */
	private int pontos;

	/**
     * Constroi o Item a partir da sua descricao, quantidade, lista de tags, id e tipo. 
     * 
     * @param descricao A descricao do Item.
     * @param quantidade A quantidade de itens.
     * @param tags A lista de caracteristicas do Item.
     * @param id O identificador unico do Item.
     * @param tipo O identificador do item quanto a ser necessario ou para doacao.
     */
	public Item(String descricao, int quantidade, String[] tags, int id, String tipo) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.id = id;
		this.pontos = 0;
		this.tipo = tipo;
	}
	
	/**
	 * Metodo get para a quantidade de itens.
	 * @return A quantidade de itens.
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	/**
	 * Metodo get para a lista de tags do Item.
	 * @return A lista de tags do Item.
	 */
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
	 * Metodo set para atualizar os pontos que representam o nivel de similaridade entre os itens.
	 * @param pontos Nivel de similaridade entre os itens. 
	 */
	public void setPontos(int pontos) {
		this.pontos += pontos;
	}

	/**
	 * Metodo get para o id do Item.
	 * @return O identificador unico do Item.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Metodo get para o descritor do Item;
	 * @return O descritor do Item.
	 */
	public String getDescritor() {
		return this.descricao;
	}
	
	/**
	 * Metodo get para o tipo do Item.
	 * @return O tipo do Item.
	 */
	public String getTipo() {
		return this.tipo;
	}
	
	/**
	 * Metodo get para a quantidade de pontos.
	 * @return a quantidade de pontos do Item.
	 */
	public int getPontos() {
		return this.pontos;
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

	/**
     * Retorna a informacao a respeito da comparacao
     * entre dois itens, que sao comparados a partir
     * de suas quantidades.
     * 
     * @return Informacao a respeito da comparacao dos Itens.
     */
	@Override
	public int compareTo(Item item) {
		return this.quantidade - item.getQuantidade();
	}


	/**
	 *Gera um inteiro que representa hashCode de Item a partir de seu descritor e lista de tags.
	 *
	 * @return o inteiro representando o hashCode do Item.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + Arrays.hashCode(tags);
		return result;
	}

	/**
	 * Metodo equals, que compara o objeto com outro a partir de seu número de seu descritor e lista de tags.
	 * 
	 * @param obj Objeto a ser comparado.
	 * @return um valor booleano que indica se os objetos tem o mesmo descritor e lista de tags ou nao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (!Arrays.equals(tags, other.tags))
			return false;
		return true;
	}
	
	
}