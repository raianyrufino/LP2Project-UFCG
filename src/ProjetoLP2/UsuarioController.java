package ProjetoLP2;

import java.util.HashMap;

public class UsuarioController {
	
	HashMap<String, Integer> descritores;

	// Metodos para casos de teste 2
	public void adicionaDescritor(String descricao) {
		if(descricao.trim().equals("") || descricao.trim().equals(null)) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if(this.descritores.containsKey(descricao)) {
			//throw new IllegalArgumentException("Descritor de Item ja existente: ") + descricao + ".";
		} else {
			this.descritores.put(descricao, 0);
		}
	}

	
}