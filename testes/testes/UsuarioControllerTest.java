package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetoLP2.UsuarioController;

class UsuarioControllerTest {

	UsuarioController uc;
	
	@BeforeEach
	void inicializaUsuarios() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
	}

	@Test
	void criarDoador() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("", "pedro", "pedro@email", "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador(null, "pedro", "pedro@email", "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "", "pedro@email", "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", null, "pedro@email", "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "", "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", null, "00008888", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", "", "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", null, "pessoa_fisica");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", null);});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "aleatorio");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");});
		
	}

	@Test
	void testPesquisaUsuarioPorId() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertEquals("pedro/123123123, pedro@email, 00008888, status: doador", uc.pesquisaUsuarioPorId("123123123"));
		assertEquals("joao/1111, joao@email, 124578, status: doador", uc.pesquisaUsuarioPorId("1111"));
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorId("");});
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorId(null);});
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		UsuarioController uc = new UsuarioController();
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorNome("jose");});
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertEquals("pedro/123123123, pedro@email, 00008888, status: doador", uc.pesquisaUsuarioPorNome("pedro"));
		assertEquals("joao/1111, joao@email, 124578, status: doador", uc.pesquisaUsuarioPorNome("joao"));
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorNome("");});
		assertThrows(IllegalArgumentException.class, () -> {uc.pesquisaUsuarioPorNome(null);});
		
	}
	
	@Test
	void testAdicionaDescritor() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		uc.adicionaDescritor("travesseiro");
		
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDescritor("");});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDescritor(null);});
		assertThrows(IllegalArgumentException.class, () -> {uc.adicionaDescritor("travesseiro");});
		
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
	
	@Test
	void testAdicionaItem() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(1, "1111");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao(null, "sapato", 2, "marrom, tamanho 42");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao("1111", null, 2, "marrom, tamanho 42");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao("1111", "sapato", -1, "marrom, tamanho 42");
        });
		assertThrows(NullPointerException.class, 	() ->{
			uc.adicionaItemParaDoacao("1111", "sapato", 2, null);
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao("", "sapato", 2, "marrom, tamanho 42");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao("1111", "", 2, "marrom, tamanho 42");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.adicionaItemParaDoacao("0000", "sapato", 2, "marrom, tamanho 42");
        });
		
		uc.adicionaItemParaDoacao("1111", "sapato", 2, "marrom, tamanho 42");
		assertEquals(uc.exibeItem(1, "1111"), "1 - sapato, tags: [marrom,  tamanho 42], quantidade: 2");
		uc.adicionaItemParaDoacao("1111", "blusa", 3, "branca, regata");
		assertEquals(uc.exibeItem(2, "1111"), "2 - blusa, tags: [branca,  regata], quantidade: 3");
	}
	
	@Test
	void testExibeItem() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("01", "joao", "joao@email", "124578", "ong");
		uc.adicionaItemParaDoacao("01", "meia", 1, "de algodao, branca");
		uc.adicionaItemParaDoacao("01", "sapatilha", 4, "rosa, confortavel");
		uc.adicionaItemNecessario("01", "chapeu", 2, "de palha");
		
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [de algodao,  branca], quantidade: 1");
		assertEquals(uc.exibeItem(2, "01"), "2 - sapatilha, tags: [rosa,  confortavel], quantidade: 4");
		assertEquals(uc.exibeItem(3, "01"), "3 - chapeu, tags: [de palha], quantidade: 2");
		
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(-1, "01");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(1, null);
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(1, "");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(1, "5");
        });
	}
	
	@Test
	void testAtualizaItem() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("01", "joao", "joao@email", "124578", "ong");
		uc.adicionaItemParaDoacao("01", "meia", 1, "de algodao, branca");
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [de algodao,  branca], quantidade: 1");
		
		uc.atualizaItemParaDoacao(1, "01", 4, "branca");
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [branca], quantidade: 4");
		uc.atualizaItemParaDoacao(1, "01", 2, null);
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [branca], quantidade: 2");
		uc.atualizaItemParaDoacao(1, "01", 0, null);
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [branca], quantidade: 2");
		
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.atualizaItemParaDoacao(-1,"meia", 1, "grande");
        });
		assertThrows(NullPointerException.class, 	() ->{
			uc.atualizaItemParaDoacao(2,"01", 5, "grande");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.atualizaItemParaDoacao(1,"00", 5, "grande");
        });
		assertThrows(NullPointerException.class, 	() ->{
			uc.atualizaItemParaDoacao(0,"01", 5, "grande");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.atualizaItemParaDoacao(2,"", 5, "grande");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.atualizaItemParaDoacao(1,"01", -1, "grande");
        });
	}
	
	@Test
	void testRemoveItem(){
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("01", "joao", "joao@email", "124578", "ong");
		uc.adicionaItemParaDoacao("01", "meia", 1, "de algodao, branca");
		assertEquals(uc.exibeItem(1, "01"), "1 - meia, tags: [de algodao,  branca], quantidade: 1");
		
		uc.removeItem(1, "01");
		assertThrows(RuntimeException.class, 	() ->{
			uc.removeItem(-1, "01");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.exibeItem(1, "01");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.removeItem(1, "");
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.removeItem(1, null);
        });
		assertThrows(IllegalArgumentException.class, 	() ->{
			uc.removeItem(1, "02");
        });
		
	}
	
	@Test
	public void testListaDescritorDeItensParaDoacao() {

		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1", "joao", "joao@email", "124578", "ong");
		uc.adicionaDoador("2", "maria", "maria@email", "875421", "ong");
		uc.adicionaItemParaDoacao("1", "cadeira de rodas", 5, "roda grande,cadeira");
		uc.adicionaItemParaDoacao("2", "cobertor", 10, "lencol,conforto");
		uc.adicionaItemParaDoacao("1", "travesseiro", 10, "travesseiro de pena");
		uc.adicionaItemParaDoacao("2", "jaqueta de couro", 5, "outfit,couro de jacare");

		assertEquals(uc.listaDescritorDeItensParaDoacao(), "5 - cadeira de rodas | 10 - cobertor | 5 - jaqueta de couro | 10 - travesseiro");

	}
	
	@Test
	public void testListaItensParaDoacao() {
		
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1", "joao", "joao@email", "124578", "ong");
		uc.adicionaDoador("2", "maria", "maria@email", "875421", "ong");
		uc.adicionaItemParaDoacao("1", "cadeira de rodas", 5, "roda grande,cadeira");
		uc.adicionaItemParaDoacao("2", "cobertor", 10, "lencol,conforto");
		uc.adicionaItemParaDoacao("1", "travesseiro", 10, "travesseiro de pena");
		uc.adicionaItemParaDoacao("2", "jaqueta de couro", 5, "outfit,couro de jacare");
		
		assertEquals(uc.listaItensParaDoacao(), "2 - cobertor, tags: [lencol, conforto], quantidade: 10, doador: maria/2 | "
				+ "3 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: joao/1 | "
				+ "1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, doador: joao/1 | "
				+ "4 - jaqueta de couro, tags: [outfit, couro de jacare], quantidade: 5, doador: maria/2");
	}
	
	@Test
	public void testPesquisaItemParaDoacaoPorDescricao() {
		
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1", "joao", "joao@email", "124578", "ong");
		uc.adicionaDoador("2", "maria", "maria@email", "875421", "ong");
		uc.adicionaItemParaDoacao("1", "cadeira de rodas", 2, "roda grande,cadeira");
		uc.adicionaItemParaDoacao("2", "cadeira de alimentacao", 5, "35kg, infantil");
		uc.adicionaItemParaDoacao("1", "cadeira de praia", 15, "dobravel");
		uc.adicionaItemParaDoacao("2", "cadeira reclinavel", 4, "couro");
		
		assertEquals(uc.pesquisaItemParaDoacaoPorDescricao("cadeira"), "2 - cadeira de alimentacao, tags: [35kg,  infantil], quantidade: 5 | 3 - cadeira de praia, tags: [dobravel], quantidade: 15 | 1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2 | 4 - cadeira reclinavel, tags: [couro], quantidade: 4");
		assertThrows(IllegalArgumentException.class,	() ->{ 
			uc.pesquisaItemParaDoacaoPorDescricao("");
		});
		assertThrows(IllegalArgumentException.class,	() ->{ 
			uc.pesquisaItemParaDoacaoPorDescricao(null);
		});
	}
	
	
	@Test
    void testAdicionaItemNecessario() throws IOException {

        UsuarioController uc = new UsuarioController();
        uc.lerReceptores("arquivos_sistema/novosReceptores.csv");

        assertThrows(NullPointerException.class,     () ->{
            this.uc.exibeItem(1, "84473712044");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario(null, "oculos", 2, "sol, skr skr");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("", "oculos", 2, "sol, skr skr");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("84473712044", null, 2, "sol, skr skr");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("84473712044", "", 2, "sol, skr skr");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("84473712044", "oculos", -1, "sol, skr skr");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("84473712044", "oculos", 2, null);
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("84473712044", "oculos", 2, "");
        });
        assertThrows(NullPointerException.class,     () ->{
            this.uc.adicionaItemNecessario("0000", "oculos", 2, "sol, skr skr");
        });

        uc.adicionaItemNecessario("84473712044", "oculos", 2, "sol, skr skr");
        assertEquals(uc.exibeItem(1, "84473712044"), "1 - oculos, tags: [sol,  skr skr], quantidade: 2");
        uc.adicionaItemNecessario("84473712044", "blusa", 3, "branca, regata");
        assertEquals(uc.exibeItem(2, "84473712044"), "2 - blusa, tags: [branca,  regata], quantidade: 3");

    }
	
	@Test
    public void testaRemoveItemNecessario() throws IOException {
        UsuarioController uc = new UsuarioController();
        uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
        
        assertThrows(RuntimeException.class,     () ->{
            uc.removeItemNecessario("84473712044", 1);
        });
        
        uc.adicionaItemNecessario("84473712044", "oculos", 2, "sol, skr skr");
        assertEquals(uc.exibeItem(1, "84473712044"), "1 - oculos, tags: [sol,  skr skr], quantidade: 2");
        uc.removeItem(uc.adicionaItemNecessario("84473712044", "oculos", 2, "sol, skr skr"), "84473712044");
        
        assertThrows(IllegalArgumentException.class,     () ->{
            uc.removeItemNecessario("", 1);
        });
        assertThrows(IllegalArgumentException.class,     () ->{
            uc.removeItemNecessario(null, 1);
        });
        assertThrows(RuntimeException.class,     () ->{
            uc.removeItemNecessario("84473712044", -1);
        });
        assertThrows(RuntimeException.class,     () ->{
            uc.removeItemNecessario("84473712044", 3);
        });
      
    }
	
	@Test
	public void match() throws IOException {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("1", "joao", "joao@email", "124578", "ong");
		uc.adicionaDoador("2", "maria", "maria@email", "875421", "ong");
		uc.adicionaDoador("3", "jose", "jose@email", "129578", "ong");
		uc.adicionaDoador("4", "marta", "marta@email", "895421", "ong");
		
        uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
		
		uc.adicionaItemParaDoacao("1", "cadeira de rodas", 10, "roda grande, cadeira");
		uc.adicionaItemParaDoacao("2", "cadeira de rodas", 5, "cadeira, roda grande");
		uc.adicionaItemParaDoacao("3", "cadeira de rodas", 15, "azul");
		uc.adicionaItemParaDoacao("4", "cadeira de rodas", 20, "roda grande, verde");
		uc.adicionaItemNecessario("72859801000118", "cadeira de rodas", 3, "roda grande, cadeira");
		assertEquals(uc.match("72859801000118", 5), "1 - cadeira de rodas, tags: [roda grande,  cadeira], quantidade: 10, doador: joao/1 | "
				+ "4 - cadeira de rodas, tags: [roda grande,  verde], quantidade: 20, doador: marta/4 | "
				+ "2 - cadeira de rodas, tags: [cadeira,  roda grande], quantidade: 5, doador: maria/2 | "
				+ "3 - cadeira de rodas, tags: [azul], quantidade: 15, doador: jose/3");
		
		assertThrows(IllegalArgumentException.class,	() ->{
			uc.match("", 5);
		});
		assertThrows(IllegalArgumentException.class,	() ->{
			uc.match(null, 5);
		});		
		assertThrows(RuntimeException.class,	() ->{
			uc.match("72859801000118", -5);
		});		
		assertThrows(RuntimeException.class,	() ->{
			uc.match("72859801000118", 1);
		});
		
	}
	
	@Test
    public void testaListaItensNecessarios() throws IOException {
        UsuarioController uc = new UsuarioController();
        uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
        uc.adicionaItemNecessario("57091431030", "cama", 2, "branca, deitavel");
        uc.adicionaItemNecessario("84473712044", "oculos", 2, "sol, skr skr");
        assertEquals(uc.listaItensNecessarios(),"1 - cama, tags: [branca,  deitavel], quantidade: 2, Receptor: Lucca Iago/57091431030 "
                + "| 2 - oculos, tags: [sol,  skr skr], quantidade: 2, Receptor: Murilo Luiz Brito/84473712044" );

    }
	
	@Test
	public void atualizaItemNecessario() throws IOException {
		UsuarioController uc = new UsuarioController();
		uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
		uc.adicionaItemNecessario("84473712044", "cama", 3, "branca, deitavel");
		uc.atualizaItemNecessario("84473712044", 1, 3, "branca, deitavel");
		assertEquals(uc.exibeItem(1 ,"84473712044"), "1 - cama, tags: [branca,  deitavel], quantidade: 3");
		
		assertThrows(RuntimeException.class, 	() ->{
			uc.atualizaItemNecessario("", 1, 3, "branca, deitavel");
        });
		assertThrows(RuntimeException.class, 	() ->{
			uc.atualizaItemNecessario(null, 1, 3, "branca, deitavel");
        });
		assertThrows(RuntimeException.class, 	() ->{
			uc.atualizaItemNecessario("10000000", 1, 3, "branca, deitavel");
        });
		assertThrows(RuntimeException.class, 	() ->{
			uc.atualizaItemNecessario("84473712044", -1, 3, "branca, deitavel");
        });
		assertThrows(RuntimeException.class, 	() ->{
			uc.atualizaItemNecessario("84473712044", 2, 3, "branca, deitavel");
        });
		
	}
	
	@Test 
	void testRealizaDoacao() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		int id1 = uc.adicionaItemParaDoacao("1111", "computador", 3, "velho, ruim, branco");
		int id2 = uc.adicionaItemNecessario("123123123", "computador", 1, "novo, bom, branco");
		int id3 = uc.adicionaItemNecessario("123123123", "ventilador", 3, "refrescante");
		int id4 = uc.adicionaItemParaDoacao("1111", "ventilador", 2, "qualquer");
		
		assertEquals(id1 +" - computador, tags: [velho,  ruim,  branco], quantidade: 3", uc.exibeItem(id1, "1111"));
		assertEquals(id2 +" - computador, tags: [novo,  bom,  branco], quantidade: 1", uc.exibeItem(id2, "123123123"));

	
		uc.realizaDoacao(id2, id1, "11/11/2011");
		uc.realizaDoacao(id3, id4, "24/12/2012");
		assertThrows(IllegalArgumentException.class, ()->uc.realizaDoacao(id3, id1, "12/12/2012"));
		assertThrows(IllegalArgumentException.class, ()->uc.realizaDoacao(id3, id1, ""));
		assertThrows(IllegalArgumentException.class, ()->uc.realizaDoacao(id3, id1, null));
		assertThrows(IllegalArgumentException.class, ()->uc.realizaDoacao(-1, id1, "12/12/2012"));		
	
		assertEquals(id3 + " - ventilador, tags: [refrescante], quantidade: 1", uc.exibeItem(id3, "123123123"));
		assertEquals(id1 + " - computador, tags: [velho,  ruim,  branco], quantidade: 2", uc.exibeItem(id1, "1111"));
		assertThrows(IllegalArgumentException.class, ()->uc.exibeItem(id2, "123123123"));
		assertThrows(IllegalArgumentException.class, ()->uc.exibeItem(id4, "1111"));
		
	}
	
	@Test
	void testListaDoacoes() {
		UsuarioController uc = new UsuarioController();
		uc.adicionaDoador("123123123", "pedro", "pedro@email", "00008888", "pessoa_fisica");
		uc.adicionaDoador("1111", "joao", "joao@email", "124578", "ong");
		
		int id1 = uc.adicionaItemParaDoacao("1111", "computador", 3, "velho, ruim, branco");
		int id2 = uc.adicionaItemNecessario("123123123", "computador", 1, "novo, bom, branco");
		int id3 = uc.adicionaItemNecessario("123123123", "ventilador", 3, "refrescante");
		int id4 = uc.adicionaItemParaDoacao("1111", "ventilador", 2, "qualquer");
		uc.realizaDoacao(id2, id1, "11/11/2011");
		uc.realizaDoacao(id3, id4, "24/12/2012");
		
		assertEquals(uc.listaDoacoes(), "11/11/2011 - doador: joao/1111, item: computador, quantidade: 1, receptor: pedro/123123123 | 24/12/2012 - doador: joao/1111, item: ventilador, quantidade: 2, receptor: pedro/123123123");
		
	}
	
	
}