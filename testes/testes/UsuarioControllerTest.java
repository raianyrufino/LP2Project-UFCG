package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import projetoLP2.UsuarioController;

class UsuarioControllerTest {


	@Test
	void criarDoador() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("", "", "", "", "");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("aa", "aa", "a", "aa", "");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador(null, null, null, null, null);});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("a", "aa", "aaaa", "a", null);});
	}

	@Test
	void testPesquisaUsuarioPorId() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertEquals("pedro/123123123, pedro@email, 00008888, status: doador", uc.pesquisaUsuarioPorId("123123123"));
		assertEquals("joao/1111, joao@email, 124578, status: doador", uc.pesquisaUsuarioPorId("1111"));
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertEquals("pedro/123123123, pedro@email, 00008888, status: doador", uc.pesquisaUsuarioPorNome("pedro"));
		assertEquals("joao/1111, joao@email, 124578, status: doador", uc.pesquisaUsuarioPorNome("joao"));
	
		
	}

	@Test
	void testAtualizaUsuario() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertEquals("pedro/123123123, pedro@email, 00008888, status: doador", uc.pesquisaUsuarioPorId("123123123"));
		assertEquals("joao/1111, joao@email, 124578, status: doador", uc.pesquisaUsuarioPorId("1111"));

		uc.atualizaUsuario("1111", "augusto", "email2@email", "");
		uc.atualizaUsuario("123123123", "peter", "teste@email", "88778877");
		
		assertThrows(IllegalArgumentException.class, () -> {uc.atualizaUsuario("87878457", "mauro", "email@ewe", "8787878454");});
		assertThrows(IllegalArgumentException.class, () -> {uc.atualizaUsuario(null, "mauro", "email@ewe", "8787878454");});
		assertThrows(IllegalArgumentException.class, () -> {uc.atualizaUsuario("", "mauro", "email@ewe", "8787878454");});
		
		assertEquals("augusto/1111, email2@email, 124578, status: doador", uc.pesquisaUsuarioPorId("1111"));
		assertEquals("peter/123123123, teste@email, 88778877, status: doador", uc.pesquisaUsuarioPorId("123123123"));


		
	}

	@Test
	void testRemoveUsuario() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
	
		uc.removeUsuario("1111");
		assertThrows(IllegalArgumentException.class, () -> {uc.removeUsuario(null);});
		assertThrows(IllegalArgumentException.class, () -> {uc.removeUsuario("");});
		assertThrows(IllegalArgumentException.class, () -> {uc.removeUsuario("22222");});
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorId("1111");});
		
		
	}
}