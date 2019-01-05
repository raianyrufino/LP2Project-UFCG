package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projetoLP2.Item;
import projetoLP2.Usuario;

class UsuarioTest {

	@Test
	void testUsuario() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		assertEquals("01", u1.getId());
		assertEquals("beto", u1.getNome());
		assertEquals("doador", u1.getStatus());
		
		
	}

	@Test
	void testGetItem() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertEquals(new Item("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel"), u1.getItem(i1));
		
		u1.adicionaItem("pc", 3, ("bons, lentos").split(", "), 1, "disponivel");
		
		assertEquals(new Item("pc", 3, ("bons, lentos").split(", "), 1, "disponivel"), u1.getItem(i1));
		
		
	}

	@Test
	void testGetDescritor() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		assertEquals("pc", u1.getDescritor(i1));
		
	}


	@Test
	void testExibeItem() {
		
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 2", u1.exibeItem(i1));
		assertThrows(IllegalArgumentException.class, ()->u1.exibeItem(98));
		
	}

	@Test
	void testAtualizaItemParaDoacao() {

		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 2", u1.exibeItem(i1));
		u1.atualizaItemParaDoacao(i1, 7, null);
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 7", u1.exibeItem(i1));
		u1.atualizaItemParaDoacao(i1, 0, ("verdes, azuis").split(", "));
		assertEquals(i1 + " - pc, tags: [verdes, azuis], quantidade: 7", u1.exibeItem(i1));
		assertThrows(IllegalArgumentException.class, ()->u1.atualizaItemParaDoacao(878, 7, ("verdes, azuis").split(", ")));
		
		
		
	}

	@Test
	void testRemoveItem() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 2", u1.exibeItem(i1));
		
		assertThrows(IllegalArgumentException.class, ()->u1.exibeItem(124));
		
		assertThrows(IllegalArgumentException.class, ()->u1.removeItem(1247));
		
		u1.removeItem(i1);
		
		assertThrows(IllegalArgumentException.class, ()->u1.exibeItem(i1));
		assertThrows(NullPointerException.class, ()->u1.removeItem(i1));
		
		
	}

	@Test
	void testGetQuantidadeDoItem() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "doador");
		
		int i1 = u1.adicionaItem("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertEquals(2, u1.getQuantidadeDoItem(i1));
	}

	@Test
	void testAtualizaItemNecessario() {
		
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		
		int i1 = u1.adicionaItemNecessario("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 2", u1.exibeItem(i1));
		u1.atualizaItemNecessario(i1, 7, null);
		assertEquals(i1 + " - pc, tags: [bons, rapidos], quantidade: 7", u1.exibeItem(i1));
		u1.atualizaItemNecessario(i1, 0, ("verdes, azuis").split(", "));
		assertEquals(i1 + " - pc, tags: [verdes, azuis], quantidade: 7", u1.exibeItem(i1));
		assertThrows(IllegalArgumentException.class, ()->u1.atualizaItemNecessario(878, 7, ("verdes, azuis").split(", ")));
		
	}

	@Test
	void testVerificaIdItem() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		
		int i1 = u1.adicionaItemNecessario("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertTrue(u1.verificaIdItem(i1));
		assertFalse(u1.verificaIdItem(12));
		
	}

	@Test
	void testVerificaItem() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		
		assertTrue(u1.verificaItem());
		
		u1.adicionaItemNecessario("pc", 2, ("bons, rapidos").split(", "), 1, "disponivel");
		
		assertFalse(u1.verificaItem());
		
	}

	@Test
	void testCompareTo() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		Usuario u2 = new Usuario("02", "joao", "joao@email", "124578", "ong", 2, "receptor");
		Usuario u3 = new Usuario("03", "pedro", "pedro@email", "00008888", "pessoa_fisica", 2, "doador");
		
		assertTrue(u1.compareTo(u2)<0);
		assertTrue(u2.compareTo(u3)==0);
		
	}

	@Test
	void testToString() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		Usuario u2 = new Usuario("02", "joao", "joao@email", "124578", "ong", 2, "receptor");

		assertEquals("beto/01, beto@email, 88888888, status: receptor", u1.toString());
		assertEquals("joao/02, joao@email, 124578, status: receptor", u2.toString());
	}

	@Test
	void testEqualsObject() {
		Usuario u1 = new Usuario("01", "beto", "beto@email", "88888888", "pessoa_fisica", 1, "receptor");
		Usuario u2 = new Usuario("02", "joao", "joao@email", "124578", "ong", 2, "receptor");
		Usuario u3 = new Usuario("01", "pedro", "pedro@email", "00008888", "pessoa_fisica", 3, "doador");
		
		assertTrue(u1.equals(u1));
		assertEquals(false, u1.equals(null));
		assertEquals(false, u1.equals(u2));
		assertTrue(u1.equals(u3));
		
		
	}

}
