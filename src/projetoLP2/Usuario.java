package projetoLP2;

import java.util.HashMap;

public abstract class Usuario implements Comparable<Usuario> {

	protected String nome;

	protected String id;

	protected String email;

	protected String celular;

	protected String classe;

	protected String status;

	protected int ordem;

	private HashMap<String, Item> itens;

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

	// Metodos para caso de teste 2

	public String adicionaItemParaDoacao(Item item) {

		if (this.itens.containsValue(item)) {

			// vou adicionar a quantidade do item anterior + quantidade do item dado;

			return "n sei";

		} else {

			this.itens.put(item.toString(), item);

			return "adicionado";

		}

	}

	public String toStringItem(String id) {

		if (this.itens.containsKey(id)) {

			return this.itens.get(id).toString();

		} else {

			throw new IllegalArgumentException("Item nao encontrado: " + id);

		}

	}

	public void removeItem(String id) {

		if (this.itens.containsKey(id)) {

			this.itens.remove(id);

		} else {

			throw new IllegalArgumentException("Item nao encontrado: " + id);

		}

	}

	// comparando pela ordem

	@Override

	public int compareTo(Usuario us2) {

		return Integer.compare(this.getOrdem(), us2.getOrdem());

	}

	@Override

	public String toString() {

		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + this.status;

	}

}