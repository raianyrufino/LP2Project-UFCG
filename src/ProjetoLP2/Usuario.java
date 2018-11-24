package ProjetoLP2;

import java.util.HashMap;

public abstract class Usuario implements Comparable<Usuario> {
	
	protected String nome;
	protected String id;
	protected String email;
	protected String celular;
	protected String classe;
	protected String status;
	protected int ordem;
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	private HashMap<String, Item> itens;
	
	public int getOrdem() {
		return this.ordem;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	// Metodos para caso de teste 2
	public void adicionaItemParaDoacao(Item item) {
		this.itens.put(item.toString(), item);
	}
	
	


	//comparando pela ordem
	@Override
	public int compareTo(Usuario us2) {
		
		return Integer.compare(this.getOrdem(), us2.getOrdem()) ;
	}
	
	@Override
	public String toString() {
		return this.nome + "/" + this.id +  ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}
	
}
