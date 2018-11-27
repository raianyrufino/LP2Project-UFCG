package projetoLP2;

public class Doador extends Usuario {
	
	public Doador(String id,String nome,String email,String celular,String classe, int ordem) {
		this.celular = celular;
		this.classe = classe;
		this.email = email;
		this.id = id;
		this.nome = nome;
		this.status = "doador";
		this.ordem = ordem;
		
	}

}
