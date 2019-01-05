package projetoLP2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistencia implements Serializable{
	
	
	private static final long serialVersionUID = -8563434444697286913L;

	/** Cria arquivo que irá salvar os dados a partir da facade.
	 *
	 * @throws IOException lanca excecao de entrada/saida.
	 * @throws ClassNotFoundException trata excecao de classe nao encontrada.
	 */
	public Facade iniciaSistema() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fis = new FileInputStream("arquivos_sistema/system.log");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Facade saida = (Facade) ois.readObject();
			ois.close();
			return saida;
		} catch (FileNotFoundException e) {
			return new Facade();
		}

	}

	/**
	 * Salva os dados quando o projeto for rodado.(uma instancia do sistema em um arquivo .log).
	 * 
	 * 
	 * @throws IOException lanca excecao de entrada/saida.
	 */
	public void fechasistema(Facade facade) throws IOException {
		FileOutputStream fos = new FileOutputStream("arquivos_sistema/system.log");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(facade);
		oos.close();
	}
}
