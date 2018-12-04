package projetoLP2;

import java.io.*;
import java.util.*;

public class UsuarioController {

	private Map<String, Usuario> usuarios;
	private Map<String, Integer> descritores;
	private int idItem = 0;
	private String[] classes = { "pessoa_fisica", "igreja", "orgao_publico_municipal", 
								 "orgao_publico_estadual", "orgao_publico_federal", 
								 "ong", "associacao", "sociedade" };
	
	/**
	 * construtor que inicializa os atributos
	 */
	public UsuarioController() {
		this.idItem = 0;
		this.usuarios = new HashMap<>();
		this.descritores = new TreeMap<>();
	}
	
	/**
	 * metodo que salva a ordem que os itens foram adicionados
	 * @return a quantidade de itens cadastrados no sistema
	 */
	private int numOrdem() {
		if (this.usuarios.isEmpty()) {
			return 0;
		}
		return this.usuarios.size();
	}

	// metodos para use case 1
	
	/**
	 * metodo para testar se uma string esta contida em um array
	 * @param cl a string a ser procurada
	 * @param list o array a ser analizado
	 * @return booleano que representa se esta ou nao contido
	 */
	private boolean in(String cl, String[] list) {
		for (String s : list) {
			if (cl.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * metodo para adicionar um doador no sistema
	 * @param id o cpf ou cnpj do doador
	 * @param nome o nome do doador
	 * @param email o email do doador
	 * @param celular o numero de celular do doador
	 * @param classe o tipo de doador
	 */
	public void adicionaDoador(String id, String nome, String email, String celular, String classe) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		} else if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		} else if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		} else if (classe == null || classe.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		} else if (!in(classe.toLowerCase(), this.classes)) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		} else if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		this.usuarios.put(id, new Usuario(id, nome, email, celular, classe, numOrdem(), "doador"));
	}
	
	/**
	 * metodo que retorna uma representacao do usuario usando o seu id
	 * @param id o id do usuario
	 * @return o usuario
	 */
	public String pesquisaUsuarioPorId(String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		return this.usuarios.get(id).toString();

	}

	/**
	 * metodo que retorna os usuarios com base no nome
	 * @param nome o nome que se quer pesquisar
	 * @return os usuarios com esse nome
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}

		String st = "";
		// lista que vai conter todos os toString de usuarios com o nome dado
		ArrayList<Usuario> users = new ArrayList<>();
		// procurando pelo nome nos usuarios
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome)) {
				users.add(u);
			}
		}
		// testando se a lista contem algum usuario com o nome dado
		if (users.isEmpty()) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
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

	/**
	 * metodo que cadastra os receptores a partir de um arquivo csv
	 * @param caminho o caminho do arquivo csv no sistema
	 * @throws IOException caso haja algum erro com o arquivo
	 */
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));

		String linha = null;
		linha = sc.nextLine();
		if (linha.equals("id,nome,e-mail,celular,classe")) {
			while (sc.hasNextLine()) {
				linha = sc.nextLine();
				String[] dados = linha.split(",");
				this.usuarios.put(dados[0],
						new Usuario(dados[0], dados[1], dados[2], dados[3], dados[4], numOrdem(), "receptor"));
			}
		}
		sc.close();
	}

	
	/**
	 * metodo que atualiza as informacoes de um usuario no sistema
	 * caso nao queira mudar algum dos atrubutos basta deixar o campo vazio
	 * @param id o id do usuario a ser atualizao
	 * @param nome o novo nome 
	 * @param email o novo email
	 * @param celular o novo numero de celular 
	 * @return a representacao atualizada do usuario
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		} else if (!(nome == null) && !(nome.trim().equals(""))) {
			this.usuarios.get(id).setNome(nome);
		} else if (!(email == null) && !(email.trim().equals(""))) {
			this.usuarios.get(id).setEmail(email);
		} else if (!(celular == null) && !(celular.trim().equals(""))) {
			this.usuarios.get(id).setCelular(celular);
		}
		return this.usuarios.get(id).toString();
	}

	/**
	 * metodo que remove um usuario do sistema
	 * @param id o id do usuario a ser removido
	 */
	public void removeUsuario(String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		this.usuarios.remove(id);
	}
	
	// Metodos para casos de teste 2

	/**
	 * Adiciona um descritor a partir de sua descricao.
	 * @param descricao A descricao de um descritor.
	 */
	public void adicionaDescritor(String descricao) {
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		} else if (this.descritores.containsKey(descricao.toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.toLowerCase() + ".");
		} else {
			this.descritores.put(descricao.toLowerCase(), 0);
		}
	}

	/**
	 * Adiciona um item para doacao a partir do id do usuario doador que deseja doar, alem da descricao,
	 * quantidade e lista de tags do Item a ser doado.
	 * @param idDoador O identificador unico do usuario doador.
	 * @param descricaoItem A descricao do Item a ser doado.
	 * @param quantidade A quantidade de itens a serem doados.
	 * @param tags A lista de tags do Item que o caracterizam.
	 * @return O identificador unico do Item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if(!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		} else if (descricaoItem == null || descricaoItem.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		} else if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		} else {
			if (!this.descritores.containsKey(descricaoItem.toLowerCase())) {
				this.descritores.put(descricaoItem.toLowerCase(), quantidade);
			} else {
				this.descritores.put(descricaoItem, quantidade);
			}
			
			String[] listaTags = tags.split(",");
			this.idItem = this.idItem + 1;
			return this.usuarios.get(idDoador).adicionaItem(descricaoItem, quantidade, listaTags, this.idItem);
		}
	}

	/**
	 * Exibe o Item de um determinado usuario doador a partir do id do item e do doador.
	 * @param idItem O identificador unico do Item.
	 * @param idDoador O identificador unico do usuario doador.
	 * @return A representacao textual do Item de um determinado usuario doador.
	 */
	public String exibeItem(int idItem, String idDoador) {
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (this.usuarios.containsKey(idDoador)) {
			return this.usuarios.get(idDoador).exibeItem(idItem);
		} else {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
	}
	
	/**
	 * Atualiza o Item de um determinado usuario doador a partir do id do item e do doador,
	 * alem da informacao da nova quantidade de itens e/ou nova lista de tags do Item.
	 * @param idItem O identificador unico do item.
	 * @param idDoador O identificado unico do usuario doador.
	 * @param quantidade A nova quantidade de itens do Item informado.
	 * @param tags A nova lista de tags do Item informado.
	 * @return A representacao textual atualizada do Item informado.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		if(idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if(quantidade  <  0) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		} else {
			
			String[] listaTags;
			if(tags != null) {
				listaTags = tags.split(",");
			} else {
				listaTags = null;
			}
		
			if(this.usuarios.containsKey(idDoador)) {
				if(quantidade != 0) {
					String descritor = this.usuarios.get(idDoador).getDescritor(idItem);
					this.descritores.put(descritor, quantidade);
				}
				return this.usuarios.get(idDoador).atualizaItemParaDoacao(idItem, quantidade, listaTags);
				
			} else {
				throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
			}
		}
	}

	/**
	 * Remove o Item de um determinado usuario doador a partir do id do Item e do doador.
	 * @param idItem O identificador unico do Item.
	 * @param idUsuario O identificador unico do usuario doador.
	 */
	public void removeItem(int idItem, String idUsuario) {
		if (idUsuario == null || idUsuario.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (idItem < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (this.usuarios.containsKey(idUsuario)) {
			if(this.usuarios.get(idUsuario).verificaIdItem(idItem)) {
				String descritor = this.usuarios.get(idUsuario).getDescritor(idItem);
				this.descritores.put(descritor, 0);
			}
			this.usuarios.get(idUsuario).removeItem(idItem);
		} else {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
	}
	
	// Metodos para casos de teste 3
	
	public String listaDescritorDeItensParaDoacao() {		
		String descritores = "";
		int cont = 0;
		for(String chave : this.descritores.keySet()) {
			if(cont == 0) {
				descritores += this.descritores.get(chave) + " - " + chave;
			} else {
				descritores += " | " + this.descritores.get(chave) + " - " + chave;
			}
			cont ++;
		}
		
		return descritores;
	}
	
	public String listaItensParaDoacao() {
		Map<Integer, Map<String, String>> mapaDeItens = new TreeMap<>(Collections.reverseOrder());
		HashMap<Integer, Item> itens = new HashMap<>();

        for (Usuario usuario : this.usuarios.values()) {
        	itens = usuario.getMapDeItens();
        	for (int idItem : itens.keySet()) {
                if (!mapaDeItens.containsKey(itens.get(idItem).getQuantidade())) {
                    mapaDeItens.put(itens.get(idItem).getQuantidade(), new TreeMap<>());
                }
                mapaDeItens.get(itens.get(idItem).getQuantidade()).put(itens.get(idItem).getDescritor(),
                        itens.get(idItem).toString() + ", doador: " + usuario.getNome()
                        + "/" + usuario.getId());
            }
        }
        
        int cont = 0;
        String todosOsItens = "";
        for (Integer quantidade : mapaDeItens.keySet()) {
        	for (String descritor : mapaDeItens.get(quantidade).keySet()) {
                if (cont == 0) {
                	todosOsItens += mapaDeItens.get(quantidade).get(descritor);
                } else {
                todosOsItens += " | " + mapaDeItens.get(quantidade).get(descritor);
                }
                cont++;
            }
        }

        return todosOsItens;
    }
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		if(descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		
		Map<String, String> mapaDeItens = new TreeMap<>();
		HashMap<Integer, Item> itens = new HashMap<>();

        for (Usuario usuario : this.usuarios.values()) {
        	itens = usuario.getMapDeItens();
        	for (int idItem : itens.keySet()) {
                String[] palavras = itens.get(idItem).getDescritor().split(" ");
                for(String palavra : palavras) {
                	if(palavra.toLowerCase().equals(descricao.toLowerCase())) {
                		if(!mapaDeItens.containsKey(itens.get(idItem).getDescritor())){
                			mapaDeItens.put(itens.get(idItem).getDescritor(),
                                    itens.get(idItem).toString());
                		}
                	}
                }
        	}
        }
        
        int cont = 0;
        String itensEncontrados = "";
        for (String descritor : mapaDeItens.keySet()) {
            if (cont == 0) {
            	itensEncontrados += mapaDeItens.get(descritor);
            } else {
            itensEncontrados += " | " + mapaDeItens.get(descritor);
            }
            cont++;
        }
        
		return itensEncontrados;
	}
		
	// Metodos para casos de teste 4
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		if (idReceptor == null || idReceptor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if(!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		} else if (descricaoItem == null || descricaoItem.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		} else if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		} else {
			if (!this.descritores.containsKey(descricaoItem.toLowerCase())) {
				this.descritores.put(descricaoItem.toLowerCase(), quantidade);
			} else {
				this.descritores.put(descricaoItem.toLowerCase(), quantidade);
			}
			
			String[] listaTags = tags.split(",");
			this.idItem = this.idItem + 1;
			return this.usuarios.get(idReceptor).adicionaItemNecessario(descricaoItem.toLowerCase(), quantidade, listaTags, this.idItem);
		}

	}

	public String listaItensNecessarios() {
		
		Map<Integer, String> mapaDeItens = new TreeMap<>();
		HashMap<Integer, Item> itens = new HashMap<>();

        for (Usuario usuario : this.usuarios.values()) {
        	if(usuario.getStatus().equals("receptor")) {
        		itens = usuario.getMapDeItens();
        		for (int idItem : itens.keySet()) {
        			if(!mapaDeItens.containsKey(itens.get(idItem).getId())){
        				mapaDeItens.put(itens.get(idItem).getId(), 
        						itens.get(idItem).toString() + ", Receptor: " + usuario.getNome() + "/" + usuario.getId());
        			}
        		}
            }
        }
        
        int cont = 0;
        String itensEncontrados = "";
        for (int descritor : mapaDeItens.keySet()) {
            if (cont == 0) {
            	itensEncontrados += mapaDeItens.get(descritor);
            } else {
            itensEncontrados += " | " + mapaDeItens.get(descritor);
            }
            cont++;
        }
        
		return itensEncontrados;
    }
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		if (idReceptor == null || idReceptor.equals("")) {
			throw new RuntimeException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!usuarios.containsKey(idReceptor)) {
			throw new RuntimeException("Usuario nao encontrado: " + idReceptor + "."); 
		} else if (idItem < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (usuarios.get(idReceptor).getItem(idItem) == null) {
			throw new RuntimeException("Item nao encontrado: " + idItem + ".");
		} else {
			
			String[] listaTags;
			if(novasTags == null) {
				listaTags = null;
			} else if(novasTags.equals("")){
				listaTags = null;
			} else {
				listaTags = novasTags.split(",");
			}
			
			if(this.usuarios.containsKey(idReceptor)) {
				if(novaQuantidade > 0) {
					String descritor = this.usuarios.get(idReceptor).getDescritor(idItem);
					this.descritores.put(descritor, novaQuantidade);
				}
				return this.usuarios.get(idReceptor).atualizaItemNecessario(idItem, novaQuantidade, listaTags);
				
			} else {
				throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
			}
			
		}
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		if (idReceptor == null || idReceptor.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!usuarios.containsKey(idReceptor)) {
			throw new RuntimeException("Usuario nao encontrado: " + idReceptor + "."); 
		} else if (idItem < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (usuarios.get(idReceptor).verificaItem()) {
			throw new RuntimeException("O Usuario nao possui itens cadastrados.");
		} else if (usuarios.get(idReceptor).getItem(idItem) == null) {
			throw new RuntimeException("Item nao encontrado: " + idItem + ".");
		}
		
		if (this.usuarios.containsKey(idReceptor)) {
			this.usuarios.get(idReceptor).removeItem(idItem);
		} else {
			throw new IllegalArgumentException("Usuário nao encontrado: " + idReceptor);
		}
	}

}
