package projetoLP2;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;

import java.util.Scanner;

public class UsuarioController {

	private HashMap<String, Usuario> usuarios;

	private HashMap<String, Integer> descritores;

	private int numOrdem() {

		if (this.usuarios.isEmpty()) {

			return 0;

		}

		return this.usuarios.size();

	}

	public UsuarioController() {

		this.usuarios = new HashMap<>();

		this.descritores = new HashMap<>();

	}

	// metodos para use case 1

	public void adicionaDoador(String id, String nome, String email, String celular, String classe) {

		this.usuarios.put(id, new Doador(id, nome, email, celular, classe, numOrdem()));

	}

	public String pesquisaUsuarioPorId(String id) {

		return this.usuarios.get(id).toString();

	}

	public String pesquisaUsuarioPorNome(String nome) {

		String st = "";

		// lista que vai conter todos os toString de usuarios com o nome dado

		ArrayList<Usuario> users = new ArrayList<>();

		// procurando pelo nome nos usuarios

		for (Usuario u : this.usuarios.values()) {

			if (u.getNome().equals(nome)) {

				users.add(u);

			}

		}

		// comparando pela ordem em que foram adicionados

		Collections.sort(users);

		// imprime os usuarios separados por " | " (nao sei se precisa pular linha)

		for (int i = 0; i < users.size() - 1; i++) {

			st += users.get(i).toString() + " | ";

		}

		st += users.get(users.size() - 1).toString();

		return st;

	}

	// algoritmo para ler os arquivos csv

	public void lerReceptores(String caminho) throws IOException {

		Scanner sc = new Scanner(new File(caminho));

		String linha = null;
		linha = sc.nextLine();
		if (linha.equals("id,nome,e-mail,celular,classe")) {
			while (sc.hasNextLine()) {

				linha = sc.nextLine();

				String[] dados = linha.split(",");

				this.usuarios.put(dados[0], new Receptor(dados[0], dados[1], dados[2], dados[3], dados[4], numOrdem()));

			}
		}
		sc.close();
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {

		if (!(nome == null)) {

			this.usuarios.get(id).setNome(nome);

		}

		if (!(email == null)) {

			this.usuarios.get(id).setEmail(email);

		}

		if (!(celular == null)) {

			this.usuarios.get(id).setCelular(celular);

		}

		return this.usuarios.get(id).toString();

	}

	public void removeUsuario(String id) {

		this.usuarios.remove(id);

	}

	// Metodos para casos de teste 2

	public void adicionaDescritor(String descricao) {

		if (descricao.trim().equals("") || descricao.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");

		}

		if (this.descritores.containsKey(descricao)) {

			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao + ".");

		} else {

			this.descritores.put(descricao, 0);

		}

	}

	public String adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String[] tags) {

		if (idDoador.trim().equals("") || idDoador.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

		}
		if (descricaoItem.trim().equals("") || descricaoItem.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazio ou nulo.");

		}
		if (quantidade <= 0) {

			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");

		}

		if (this.usuarios.containsKey(idDoador)) {

			Item item = new Item(descricaoItem, quantidade, tags);

			return this.usuarios.get(idDoador).adicionaItemParaDoacao(item);

		} else {

			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");

		}

	}

	public String exibeItem(String id, String idDoador) {

		if (idDoador.trim().equals("") || idDoador.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

		}
		if (id.trim().equals("") || id.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser vazio ou nulo.");

		}

		if (this.usuarios.containsKey(idDoador)) {

			return this.usuarios.get(idDoador).toStringItem(id);

		} else {

			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador);

		}

	}

	public void removeItem(String id, String idDoador) {

		if (idDoador.trim().equals("") || idDoador.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

		}
		if (id.trim().equals("") || id.trim().equals(null)) {

			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser vazio ou nulo.");

		}

		if (this.usuarios.containsKey(idDoador)) {

			this.usuarios.get(idDoador).removeItem(id);

		} else {

			throw new IllegalArgumentException("Usuário nao encontrado: " + idDoador);

		}

	}

}
