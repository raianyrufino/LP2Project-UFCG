package projetoLP2;

import java.util.Comparator;

public class ComparaItemPorDescritor implements Comparator <Item> {
	
	/**
     * Retorna a informacao a respeito da comparacao
     * entre dois itens, que sao comparados a partir
     * de seus descritores.
     * 
     * @return Informacao a respeito da comparacao dos Itens.
     */
	@Override
	public int compare(Item item1, Item item2) {
		return item1.getDescritor().compareTo(item2.getDescritor());
	}
	
}
