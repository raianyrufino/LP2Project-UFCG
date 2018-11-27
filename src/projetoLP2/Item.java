package projetoLP2;



public class Item {

	

	private String id;

	private String descricao;

	private int quantidade;

	private String[] tags;



	public Item(String descricao, int quantidade, String[] tags) {

		this.descricao = descricao;

		this.quantidade = quantidade;

		this.tags = tags;

	}

	

	public void setQuantidade(int novaQuantidade) {

		this.quantidade = novaQuantidade;

	}

	

	public void setTags(String[] novasTags) {

		this.tags = novasTags;

	}

	

	public String getId() {

		return this.id;

	}

	

	public String toString() {

		return this.id + " - " + this.descricao + ", tagas: " + this.tags + ", quantidade: " + this.quantidade;

	}

	



}