package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetoLP2.Item;

class ItemTest {
	
	Item item1;
	Item item2;
	Item item3;
	Item outroItem;
	Item outroItem2;
	
	@BeforeEach
	void inicializaItens() {
		this.item1 = new Item("sapato", 2, new String[]{"marrom", "tamanho 42"}, 1, "necessario");
		this.item2 = new Item("blusa", 1, new String[]{"regata", "branca", "lisa"}, 2, "necessario");
		this.item3 = new Item("oculos", 2, new String[]{"de sol", "protecao uv"}, 3, "disponivel");
		this.outroItem = new Item("sapato", 5, new String[] {"marrom", "tamanho 42"}, 5, "disponivel");
		this.outroItem2 = new Item("blusa", 3, new String[] {"regata", "branca", "lisa"}, 6, "disponivel");
	}
	
	@Test
	void testToString() {
		assertEquals(this.item1.toString(), "1 - sapato, tags: [marrom, tamanho 42], quantidade: 2");
		assertEquals(this.item2.toString(), "2 - blusa, tags: [regata, branca, lisa], quantidade: 1");
		assertEquals(this.item3.toString(), "3 - oculos, tags: [de sol, protecao uv], quantidade: 2");
		assertEquals(this.outroItem.toString(), "5 - sapato, tags: [marrom, tamanho 42], quantidade: 5");
		assertEquals(this.outroItem2.toString(), "6 - blusa, tags: [regata, branca, lisa], quantidade: 3");
		
	}
	
	@Test
	void testHashCodeIguais() {
		assertTrue(this.item1.equals(this.outroItem));
		assertFalse(this.item1.equals(this.item2));
		assertTrue(this.item2.equals(this.outroItem2));
		assertFalse(this.item3.equals(this.outroItem));
		assertFalse(this.outroItem2.equals(this.outroItem));
	}

}
