package projetoLP2;

import java.io.*;
import java.util.*;

public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 5087549075730366717L;
	private Map<String, Usuario> usuarios;
	private Map<String, Integer> descritores;
	private ArrayList<Doacao> doacoes;
	private int idItem = 0;
	private String[] classes = { "pessoa_fisica", "igreja", "orgao_publico_municipal", "orgao_publico_estadual",
			"orgao_publico_federal", "ong", "associacao", "sociedade" };

	/**
	 * Constroi um controller e inicializa seu mapa de usuarios, descritores e o arrayList de doacoes.
	 */
	public UsuarioController() {
		this.idItem = 0;
		this.usuarios = new HashMap<>();
		this.descritores = new TreeMap<>();
		this.doacoes = new ArrayList<>();
	}

	/**
	 * Salva a ordem em que os itens foram adicionados sequencialmente.
	 * 
	 * @return a quantidade de itens cadastrados no sistema
	 */
	private int numOrdem() {
		if (this.usuarios.isEmpty()) {
			return 0;
		}
		return this.usuarios.size();
	}

	/**
	 * Verifica se a String passada como atributo esta contida em um array ou nao.
	 * 
	 * @param cl   A string a ser procurada.
	 * @param list O array a ser analisado.
	 * @return Um boolean informando se a string passada esta contida ou nao.
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
	 * Adiciona um novo doador a partir de seu id, nome, email, celular e classe.
	 * 
	 * @param id      O numero do cpf ou do cnpj como identificador do doador.
	 * @param nome    O nome do doador.
	 * @param email   O email do doador.
	 * @param celular O numero de celular do doador.
	 * @param classe  O tipo de doador.
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
	 * Busca um usuario no mapa de usuarios a partir de seu id.
	 * 
	 * @param id O identificador unico do Usuario.
	 * @return A representacao textual do Usuario.
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
	 * Pesquisa um usuario no mapa de usuarios a partir de seu nome para altera-lo caso exista.
	 * 
	 * @param nome O nome do Usuario.
	 * @return A representacao textual de todos os usuarios com este nome.
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}

		String st = "";
		ArrayList<Usuario> users = new ArrayList<>();
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome)) {
				users.add(u);
			}
		}
		
		if (users.isEmpty()) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}
		Collections.sort(users);

		for (int i = 0; i < users.size() - 1; i++) {
			st += users.get(i).toString() + " | ";
		}

		st += users.get(users.size() - 1).toString();
		return st;
	}


	/**
	 * metodo que cadastra os receptores a partir de um arquivo csv
	 * 
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
	 * Busca um Usuario no mapa de usuarios a partir do seu id e caso exista, atualiza conforme os parametros passados.
	 * 
	 * @param id      O identificador unico do Usuario.
	 * @param nome    O novo nome do Usuario.
	 * @param email   O novo email do Usuario.
	 * @param celular O novo numero de celular do Usuario.
	 * @return A representacao textual do Usuario atualizada.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
        if (id == null || id.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }  if (!this.usuarios.containsKey(id)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
        }  if (!(nome == null) && !(nome.trim().equals(""))) {
            this.usuarios.get(id).setNome(nome);
        }  if (!(email == null) && !(email.trim().equals(""))) {
            this.usuarios.get(id).setEmail(email);
        }  if (!(celular == null) && !(celular.trim().equals(""))) {
            this.usuarios.get(id).setCelular(celular);
        }
        return this.usuarios.get(id).toString();
    }

	/**
	 * Busca um Usuario no mapa de usuarios a partir de seu id, caso exista o remove do mapa de usuarios.
	 * 
	 * @param id O identificador unico do Usuario.
	 */
	public void removeUsuario(String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		this.usuarios.remove(id);
	}

	/**
	 * Adiciona um descritor a partir de sua descricao.
	 * 
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
	 * Adiciona um item para doacao a partir do id do usuario doador que deseja
	 * doar, alem da descricao, quantidade e lista de tags do Item a ser doado.
	 * 
	 * @param idDoador      O identificador unico do usuario doador.
	 * @param descricaoItem A descricao do Item a ser doado.
	 * @param quantidade    A quantidade de itens a serem doados.
	 * @param tags          A lista de tags do Item que o caracterizam.
	 * @return O identificador unico do Item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(idDoador)) {
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
			return this.usuarios.get(idDoador).adicionaItem(descricaoItem, quantidade, listaTags, this.idItem, "disponivel");
		}
	}

	/**
	 * Exibe o Item de um determinado usuario doador a partir do id do item e do
	 * doador.
	 * 
	 * @param idItem   O identificador unico do Item.
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
	 * Atualiza o Item de um determinado usuario doador a partir do id do item e do
	 * doador, alem da informacao da nova quantidade de itens e/ou nova lista de
	 * tags do Item.
	 * 
	 * @param idItem     O identificador unico do item.
	 * @param idDoador   O identificado unico do usuario doador.
	 * @param quantidade A nova quantidade de itens do Item informado.
	 * @param tags       A nova lista de tags do Item informado.
	 * @return A representacao textual atualizada do Item informado.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		if (idItem < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (idDoador == null || idDoador.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (quantidade < 0) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		} else {

			String[] listaTags;
			if (tags != null) {
				listaTags = tags.split(",");
			} else {
				listaTags = null;
			}

			if (this.usuarios.containsKey(idDoador)) {
				if (quantidade != 0) {
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
	 * Remove o Item de um determinado usuario doador a partir do id do Item e do
	 * doador.
	 * 
	 * @param idItem    O identificador unico do Item.
	 * @param idUsuario O identificador unico do usuario doador.
	 */
	public void removeItem(int idItem, String idUsuario) {
		if (idUsuario == null || idUsuario.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (idItem < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} else if (this.usuarios.containsKey(idUsuario)) {
			if (this.usuarios.get(idUsuario).verificaIdItem(idItem)) {
				String descritor = this.usuarios.get(idUsuario).getDescritor(idItem);
				this.descritores.put(descritor, 0);
			}
			this.usuarios.get(idUsuario).removeItem(idItem);
		} else {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
	}
	
	/**
	 * Efetua a listagem de todos os descritores de itens cadastrados no sistema, 
	 * ordenada em ordem alfabetica pela descricao do item, no formato:
	 * "quantidade - descritor | quantidade - descritor | ..."
	 * 
	 * @return Uma representacao textual dos descritores de itens para doacao
	 */
	public String listaDescritorDeItensParaDoacao() {
		String descritores = "";
		int cont = 0;
		for (String chave : this.descritores.keySet()) {
			if (cont == 0) {
				descritores += this.descritores.get(chave) + " - " + chave;
			} else {
				descritores += " | " + this.descritores.get(chave) + " - " + chave;
			}
			cont++;
		}

		return descritores;
	}
	
	/**
	 * Efetua a listagem de todos os itens inseridos no sistema, ordenados pela quantidade.
	 * Os itens em maior quantidade aparecem primeiro e os com menos quantidades aparecem por último. 
	 * Itens com a mesma quantidade são ordenados pela ordem alfabetica da descrição.
	 * A listagem obedece ao formato: <idItem - descricao, tags: [tag1, tag2, …], quantidade: 'qtde', doador: 'nomeDoador'/'docDoador'
	 * 
	 * @return Uma representacao textual dos itens disponiveis para doacao.
	 */ 
	public String listaItensParaDoacao() {
		List<Item> todosOsItens = new ArrayList<>();
		HashMap<Integer, Item> itensDeCadaUsuario = new HashMap<>();
		
		for(Usuario usuario : this.usuarios.values()) {
			itensDeCadaUsuario = usuario.getMapDeItens();
			for (Item item : itensDeCadaUsuario.values()) {
				todosOsItens.add(item);
			}
		}
		Collections.sort(todosOsItens, new ComparaItemPorQuantidade());
		
		String itensParaDoacao = "";
		int cont = 0;
		for (Item item1 : todosOsItens) {
			for(Usuario usuario : this.usuarios.values()) {
				itensDeCadaUsuario = usuario.getMapDeItens();
				if(itensDeCadaUsuario.containsKey(item1.getId()) && item1.getTipo().equals("disponivel")) {
					if(cont == 0) {
						itensParaDoacao += item1.toString() + ", doador: " + usuario.getNome() + "/" + usuario.getId();
						cont ++;
					} else {
						itensParaDoacao += " | " + item1.toString() + ", doador: " + usuario.getNome() + "/" + usuario.getId();
					}
				}
			}
		}
		
        return itensParaDoacao;
    }
	
	/**
	 * Efetua a listagem de todos os itens relacionados a uma dada string de pesquisa, em ordem alfabética considerando os descritores dos itens.
	 * A listagem obedece ao formato: idItem1 - item para doação 1 | idItem2 - item para doação 2 ...
	 * 
	 * @return Uma representacao textual dos itens relacionados a descricao pesquisada.
	 */ 
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		if(descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		
		List<Item> todosOsItens = new ArrayList<>();
		HashMap<Integer, Item> itensDeCadaUsuario = new HashMap<>();
		
		for(Usuario usuario : this.usuarios.values()) {
			itensDeCadaUsuario = usuario.getMapDeItens();
			for (Item item : itensDeCadaUsuario.values()) {
				todosOsItens.add(item);
			}
		}
		
		Collections.sort(todosOsItens, new ComparaItemPorDescritor());
		
		String itensEncontrados = "";
		int cont = 0;
		for(Item item : todosOsItens) {
			String[] palavrasDoDescritor = item.getDescritor().split(" ");
			for(String palavra : palavrasDoDescritor) {
                if(palavra.toLowerCase().equals(descricao.toLowerCase())) {
                	if (cont == 0) {
                    	itensEncontrados += item.toString();
                    } else {
                    	itensEncontrados += " | " + item.toString();
                    }
                    cont++;
                }
			}
		}
		
		return itensEncontrados;
	}

	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		if (idReceptor == null || idReceptor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!this.usuarios.containsKey(idReceptor)) {
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
			return this.usuarios.get(idReceptor).adicionaItemNecessario(descricaoItem.toLowerCase(), quantidade,
					listaTags, this.idItem, "necessario");
		}

	}

	/**
	 * Lista os itens necessarios a partir do seu id.
	 * 
	 * @return A lista de itens necessarios cadastrados ordenados pelo id do item.
	 */
	public String listaItensNecessarios() {

		List<Item> todosOsItens = new ArrayList<>();
		HashMap<Integer, Item> itensDeCadaUsuario = new HashMap<>();
		
		for(Usuario usuario : this.usuarios.values()) {
			if (usuario.getStatus().equals("receptor")) {
				itensDeCadaUsuario = usuario.getMapDeItens();
				for (Item item : itensDeCadaUsuario.values()) {
					todosOsItens.add(item);
				}
			}
		}
		Collections.sort(todosOsItens, new ComparaItemPorId());
		
		String itensNecessarios = "";
		int cont = 0;
		for (Item item1 : todosOsItens) {
			for(Usuario usuario : this.usuarios.values()) {
				itensDeCadaUsuario = usuario.getMapDeItens();
				if(itensDeCadaUsuario.containsKey(item1.getId())) {
					if(cont == 0) {
						itensNecessarios += item1.toString() + ", Receptor: " + usuario.getNome() + "/" + usuario.getId();
						cont ++;
					} else {
						itensNecessarios += " | " + item1.toString() + ", Receptor: " + usuario.getNome() + "/" + usuario.getId();
					}
				}
			}
		}
        return itensNecessarios;
	}
	
	/**
	 * Atualiza tag e quantidade do item necessario identificado pelo seu id unico.
	 * 
	 * @param idReceptor  O id unico do usuario receptor.
	 * @param idItem O identificador unico do item.
	 * @param novaQuantidade A nova quantidade de itens.
	 * @param novasTags A nova tag.
	 * @return
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		if (idReceptor == null || idReceptor.equals("")) {
			throw new RuntimeException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} if (!usuarios.containsKey(idReceptor)) {
			throw new RuntimeException("Usuario nao encontrado: " + idReceptor + ".");
		} if (idItem < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} if (usuarios.get(idReceptor).getItem(idItem) == null) {
			throw new RuntimeException("Item nao encontrado: " + idItem + ".");
		} else {

			String[] listaTags;
			if (novasTags == null) {
				listaTags = null;
			} else if (novasTags.equals("")) {
				listaTags = null;
			} else {
				listaTags = novasTags.split(",");
			}

			if (novaQuantidade > 0) {
				String descritor = this.usuarios.get(idReceptor).getDescritor(idItem);
				this.descritores.put(descritor, novaQuantidade);
			}		
			return this.usuarios.get(idReceptor).atualizaItemNecessario(idItem, novaQuantidade, listaTags);

		}
	}

	/**
	 * Remove itens a serem doados de um usuario a partir do id unico do item.
	 * 
	 * @param idReceptor O id unico do usuario receptor.
	 * @param idItem O identificador unico do item.
	 */
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

		this.usuarios.get(idReceptor).removeItem(idItem);
	}
	
	/**
	 * Encontra casamentos (matches) entre itens a serem doados e itens necessarios. Uma pontuacao e calculada, seguindo as seguintes regras:
	 * Os itens para doacao que tem o mesmo descritor do item necessario iniciam o processo com 20 pontos. Tags iguais na mesma posicao somam 10 pontos, 
	 * tags iguais em posicoes diferentes somam 5 pontos. Esta funcionalidade retorna os itens a serem doados, ordenados da maior para a menor pontuacao. 
	 * Se as pontuacoes forem iguais ordena-se pelo identificador dos itens.
	 * 
	 * @param idReceptor Identificador do usuario receptor.
	 * @param idItemNecessario Identificador do item necessario.
	 * @return Uma representacao textual dos itens que casam com o item pesquisado.
	 */
	public String match(String idReceptor, int idItemNecessario) {
		if (idReceptor == null || idReceptor.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		} else if (!usuarios.containsKey(idReceptor)) {
			throw new RuntimeException("Usuario nao encontrado: " + idReceptor + ".");
		} else if (idItemNecessario < 0) {
			throw new RuntimeException("Entrada invalida: id do item nao pode ser negativo.");
		} else if(usuarios.get(idReceptor).getStatus().equals("doador")) {
			throw new RuntimeException("O Usuario deve ser um receptor: " + idReceptor + ".");
		} else {
			procuraItem(idItemNecessario);
		}
		
		Item itemNecessario = this.usuarios.get(idReceptor).getItem(idItemNecessario);
				
		List<Item> itensComMesmoDescritor = new ArrayList<>();
		HashMap<Integer, Item> itensDeCadaUsuario = new HashMap<>();
		
		for(Usuario usuario : this.usuarios.values()) {
			itensDeCadaUsuario = usuario.getMapDeItens();
			for(Item item : itensDeCadaUsuario.values()) {
				if(itemNecessario.getDescritor().equals(item.getDescritor()) && item.getTipo().equals("disponivel")) {
					itensComMesmoDescritor.add(item);
					item.setPontos(20);
				}
			}
		}
		
		itensComMesmoDescritor = setPontos(itensComMesmoDescritor, itemNecessario);
		Collections.sort(itensComMesmoDescritor, new ComparaItemPorPontos());
		
		String itensNecessarios = "";
		int cont = 0;
		for (Item item1 : itensComMesmoDescritor) {
			for(Usuario usuario : this.usuarios.values()) {
				itensDeCadaUsuario = usuario.getMapDeItens();
				if(itensDeCadaUsuario.containsKey(item1.getId())) {
					if(cont == 0) {
						itensNecessarios += item1.toString() + ", doador: " + usuario.getNome() + "/" + usuario.getId();
						cont ++;
					} else {
						itensNecessarios += " | " + item1.toString() + ", doador: " + usuario.getNome() + "/" + usuario.getId();
					}
				}
			}
		}
		return itensNecessarios;
	}
	
	/**
	 * Efetua o calculo da pontuacao referente a similaridade entre o item pesquisado, e os itens disponiveis.
	 * Os itens para doacao que tem o mesmo descritor do item necessario iniciam o processo com 20 pontos. Tags iguais na mesma posicao somam 10 pontos, 
	 * tags iguais em posicoes diferentes somam 5 pontos. Esta funcionalidade retorna uma lista de itens com seus pontos modificados. 
	 * 
	 * @param itensComMesmoDescritor Lista de itens similares ao item pesquisado.
	 * @param itemNecessario Item necesario.
	 * @return Lista de itens similares ao item pesquisado, com seus pontos devidamente setados.
	 */
	private List<Item> setPontos(List<Item> itensComMesmoDescritor, Item itemNecessario) {
		String [] tagsDoItemNecessario = itemNecessario.getTags();
		
		for(Item item : itensComMesmoDescritor) {
			if(item.getTags().length < tagsDoItemNecessario.length) {
				for(int i = 0; i < item.getTags().length; i++) {
					for(int j = 0; j < tagsDoItemNecessario.length; j++) {
						if(i == j && item.getTags()[i].equals(tagsDoItemNecessario[j])) {
							item.setPontos(10);
						} else if(i != j && item.getTags()[i].equals(tagsDoItemNecessario[j])) {
							item.setPontos(5);
						}
					}
				}
			} else {
				for(int i = 0; i < tagsDoItemNecessario.length; i++) {
					for(int j = 0; j < item.getTags().length; j++) {
						if(i == j && item.getTags()[j].equals(tagsDoItemNecessario[i])) {
							item.setPontos(10);
						} else if(i != j && item.getTags()[j].equals(tagsDoItemNecessario[i])) {
							item.setPontos(5);
						}
					}
				}
			}
		}		
		return itensComMesmoDescritor;
	}

	
	private Usuario procuraItem(int id) {
		for (Usuario u : this.usuarios.values()) {
			if (u.verificaIdItem(id)) {
				return u;
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	/**
	 * Solicita uma doacao a partir do id do item necessario e o do item a ser doado, alem da data
	 * quando solicitou.
	 * @param idItemNecessario O identificador unico do item necessario.
	 * @param idItemDoado O identificador unico do item a ser doado.
	 * @param data A data da solicitacao de doacao.
	 * @return A representacao textual da doacao realizada no seguinte formato:
	 * Data - doador: nome do doador/ id do doador, item: descritor, 
	 * quantidade: quantidade, receptor: nome do receptor/ id do receptor
	 */
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		
		if (idItemNecessario < 0 || idItemDoado < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}if(data == null||data.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}
		
		Usuario doador = procuraItem(idItemDoado);
		Usuario recep = procuraItem(idItemNecessario);

		Item itemNec = recep.getItem(idItemNecessario);
		Item itemdoado = doador.getItem(idItemDoado);

		if (!itemNec.getDescritor().equals(itemdoado.getDescritor())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}

		int qtd = (itemNec.getQuantidade() < itemdoado.getQuantidade()) ? itemNec.getQuantidade()
				: itemdoado.getQuantidade();

		if(recep.getItem(idItemNecessario).getQuantidade() - qtd == 0) {
			this.usuarios.get(recep.getId()).removeItem(idItemNecessario);
		}else{
			this.usuarios.get(recep.getId()).atualizaItemNecessario(idItemNecessario, (itemNec.getQuantidade() - qtd), null);
		}
		if(doador.getItem(idItemDoado).getQuantidade() - qtd ==0) {
			this.usuarios.get(doador.getId()).removeItem(idItemDoado);
		}else {
			this.usuarios.get(doador.getId()).atualizaItemParaDoacao(idItemDoado, (itemdoado.getQuantidade() - qtd), null);
			
		}

		Doacao doacao = new Doacao(data, doador.getNome(), doador.getId(), itemdoado.getDescritor(), qtd, recep.getNome(), recep.getId(), itemdoado.getDescritor());
		this.doacoes.add(doacao);
	    return doacao.toString();
	}
	
	/**
	 * Lista a representacao de todas as doacoes realizadas ordenadas pela data
	 * e descricao respectivamente
	 * 
	 * @return uma string com a representacao de todas as doacoes realizadas
	 */
	public String listaDoacoes() {
		String st = "";
		Collections.sort(this.doacoes);
		for(int i=0;i<this.doacoes.size()-1;i++) {
			st += this.doacoes.get(i).toString() + " | ";
		}
		st += this.doacoes.get(this.doacoes.size()-1);
		return st;
	}

}