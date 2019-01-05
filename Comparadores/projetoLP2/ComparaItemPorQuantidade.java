package projetoLP2;

import java.util.Comparator;

public class ComparaItemPorQuantidade implements Comparator <Item> {

	/**
     * Retorna a informacao a respeito da comparacao
     * entre dois itens, que sao comparados a partir
     * de suas quantidades.
     * 
     * @return Informacao a respeito da comparacao dos Itens.
     */
	@Override
	public int compare(Item item1, Item item2) {
		if(item1.getQuantidade() != item2.getQuantidade()) {
			return (item1.getQuantidade() - item2.getQuantidade()) * -1;
		} else {
			return item1.getDescritor().compareTo(item2.getDescritor());
		}
	}
}