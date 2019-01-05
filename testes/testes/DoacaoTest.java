package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import projetoLP2.Doacao;

class DoacaoTest {
	
	Doacao doacao1;
	Doacao doacao2;
	Doacao doacao3;
	Doacao outraDoacao1;
	Doacao outraDoacao2;
	
	@BeforeEach
	void inicializa() {
		this.doacao1 = new Doacao("13/12/2018", "Thiago", "1", "Cubo", 2, "Alasca", "01", "cubo magico, colorido");
		this.doacao2 = new Doacao("13/12/2018", "Edelly", "2", "Atendimento psicologico", 3, "grupo ean", "02", "conselhos, cuidados");
		this.doacao3 = new Doacao("11/12/2018", "Luffy", "3", "Frutas", 4, "Tripulacao", "03", "poderes");
		this.outraDoacao1 = new Doacao("11/12/2018", "Zoro", "4", "Espadas", 5, "Sanji", "04", "de brinquedo, reluzente");
	}

	@Test
	void testToString() {
		assertEquals(this.doacao1.toString(), "13/12/2018 - doador: Thiago/1, item: Cubo, quantidade: 2, receptor: Alasca/01");
		assertEquals(this.doacao2.toString(), "13/12/2018 - doador: Edelly/2, item: Atendimento psicologico, quantidade: 3, receptor: grupo ean/02");
		assertEquals(this.doacao3.toString(), "11/12/2018 - doador: Luffy/3, item: Frutas, quantidade: 4, receptor: Tripulacao/03");
		assertEquals(this.outraDoacao1.toString(), "11/12/2018 - doador: Zoro/4, item: Espadas, quantidade: 5, receptor: Sanji/04");
	}
	
	
}
