package projetoLP2;

public class Receptor extends Usuario {
	
	public Receptor(String id,String nome,String email,String celular,String classe, int ordem) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.ordem = ordem;
		this.status = "receptor";
	}

}
