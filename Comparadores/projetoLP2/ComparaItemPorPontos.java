package projetoLP2;

import java.util.Comparator;

public class ComparaItemPorPontos implements Comparator<Item> {

	/**
     * Retorna a informacao a respeito da comparacao
     * entre dois itens, que sao comparados a partir
     * de seus pontos.
     * 
     * @return Informacao a respeito da comparacao dos Itens.
     */
	@Override
	public int compare(Item item1, Item item2) {
		if(item1.getPontos() != item2.getPontos()) {
			return (item1.getPontos() - item2.getPontos()) * -1;
		} else {
			return item1.getId() - item2.getId();
		}
	}

}