package ProjetoLP2;

import java.util.HashMap;

public abstract class Usuario {
	
	private HashMap<String, Item> itens;
	
	// Metodos para caso de teste 2
	public void adicionaItemParaDoacao(Item item) {
		this.itens.put(item.toString(), item);
	}
	
}
