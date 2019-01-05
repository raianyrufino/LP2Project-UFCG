package projetoLP2;

import java.io.Serializable;

public class Doacao implements Comparable<Doacao>, Serializable{
	
	private static final long serialVersionUID = 5014417009444420412L;
	
	/**
	 * A data em que a doacao foi solicitada.
	 */
	private String data;
	
	/**
	 * O nome do Doador.
	 */
	private String nomeDoa;
	
	/**
	 * O identificador unico do Doador.
	 */
	private String idDoa;
	
	/**
	 * O descritor do Item.
	 */
	private String descritor;
	
	/**
	 * A quantidade de itens.
	 */
	private int qtd;
	
	/**
	 * O nome do Receptor.
	 */
	private String nomeRec;
	
	/**
	 * O identificador unico do Receptor.
	 */
	private String idRecp;
	
	/**
	 * A descricao do Item.
	 */
	private String descricao;

	/**
	 * Constroi uma doacao a patir da data, nome do Doador e seu id, descritor, quantidade, 
	 * nome do Receptor e seu id, e descrica do Item.
	 * 
	 * @param data A data em que a doacao foi solicitada.
	 * @param nomeDoa O nome do Doador.
	 * @param idDoa O identificador unico do Doador.
	 * @param descritor O descritor do Item.
	 * @param qtd A quantidade de itens.
	 * @param nomeRec O nome do Receptor.
	 * @param idRecp o identificador unico do Receptor.
	 * @param descricao A descricao do Item.
	 */
	public Doacao(String data, String nomeDoa, String idDoa, String descritor, int qtd, String nomeRec, String idRecp, String descricao) {
		this.data = data;
		this.nomeDoa = nomeDoa;
		this.idDoa = idDoa;
		this.descritor = descritor;
		this.qtd = qtd;
		this.nomeRec = nomeRec;
		this.idRecp = idRecp;
		this.descricao = descricao;
	}
 
	/**
	 * Metodo get para a data de solicitacao da doacao.
	 * @return A data da solicitacao.
	 */
	public String getData() {
		return this.data;
	}
	
	/**
	 * Metodo get para o nome do Doador.
	 * @return O nome do Doador.
	 */
	public String getNomeDoa() {
		return this.nomeDoa;
	}
	
	/**
	 * Metodo get para a descricao do Item.
	 * @return A descricao do Item.
	 */
	public String getDescricao() {
		return this.descricao;
	}
	
	/**
	 * A representacao textual de uma Doacao no seguinte formato:
	 * Data - doador: nome do Doador/id do Doador, item: descritor do item, quantidade: quantidade, 
	 * receptor: nome do Receptor/id do Receptor
	 */
	public String toString() {
		return this.data + " - doador: " + this.nomeDoa + "/" + this.idDoa + ", item: " + 
	    this.descritor + ", quantidade: "  + this.qtd + ", receptor: " + this.nomeRec + "/" + this.idRecp;
	}
	
	/**
     * Retorna a informacao a respeito da comparacao
     * entre duas Doacoes, que sao comparados a partir
     * de suas datas.
     * 
     * @return Informacao a respeito da comparacao das Doacoes.
     */
	@Override
	public int compareTo(Doacao d2) {
		if (this.getData().equals(d2.getData())) {
			return this.descricao.compareTo(d2.getDescricao());
		}
		
		String ano1 = this.getData().substring(6, 9);
		String ano2 = d2.getData().substring(6, 9);
		String mes1 = this.getData().substring(3, 4);
		String mes2 = d2.getData().substring(3, 4);
		String dia1 = this.getData().substring(1, 2);
		String dia2 = d2.getData().substring(1, 2);
		
		return (ano1+mes1+dia1).compareTo(ano2+mes2+dia2);
	}
}