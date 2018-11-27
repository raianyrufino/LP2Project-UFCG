package projetoLP2;

import easyaccept.EasyAccept;

public class usecaseTests {

	// classe main para rodar os testes

	public static void main(String[] args) {
		args = new String[] { "projetoLP2.Facade", "arquivos_sistema/use_case_1.txt", "arquivos_sistema/use_case_2.txt",
				"arquivos_sistema/use_case_3.txt" };

		EasyAccept.main(args);

	}
}