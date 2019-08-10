# projeto_LP2
Equipe: Diego Ribeiro, Gabriel Carvalho, Lucas Abrantes, Raiany Rufino

# Caso de uso 1: CRUD de usuários

O sistema deve permitir o CRUD (Create/criação, read/pesquisa/leitura, update/atualização e delete/remoção) de usuários doadores e receptores. O que é preciso informar sobre cada usuário? Nome, e-mail, celular, classe e um documento de identificação. As seguintes classes de usuários são permitidas: pessoa fisica, igreja, órgão público municipal, órgão público estadual, órgão público federal, ONG, associação e sociedade. O documento de identificação deve ser o cpf para pessoas físicas ou o cnpj para as demais classes de usuários[1].

Receptores são usuários especiais que tem a autorização para receber as doações e serão criados pelo sistema eDoe.com através de um arquivo de configuração do sistema. Na inicialização do sistema o arquivo de usuários  receptores é lido e estes usuários são automaticamente criados. Veja aqui um exemplo do arquivo de configuração de receptores. Os demais usuários inseridos no sistema são criados como apenas doador. Os doadores apenas doam, os usuários autorizados a receber podem apenas receber itens. Veja aqui dicas sobre como usar o Scanner para ler arquivos CSV.

Os usuários que podem receber doações guardam exatamente as mesmas informações que os usuários doadores, mas são marcados como usuários que podem receber doações. Isso é importante para impedir usuários que recebam doações e depois vendam o que receberam como uma fonte de renda (esse não é o objetivo do eDoe.com). Dois usuários são iguais se tiverem o mesmo número de documento de identificação.

Com base nessas informações, deve ser possível:

    Cadastrar novos usuários doadores;
    Pesquisar usuários pelo seu documento de identificação;
    Pesquisar usuários pelo seu nome completo. Se existirem vários usuários com o mesmo nome todos eles devem ser retornados na ordem em que foram inseridos no sistema (do mais antigo para o mais novo). Usar o caractere “|” como o separador dos usuários nesse retorno. Ex. usuario 1 | usuario 2 | usuario 3...;
    Atualizar nome, email e celular de usuários. O usuário a ser atualizado deve ser encontrado com base em seu documento de identificação;
    Remover usuários do sistema localizados pelo seu documento de identificação;
    Cadastrar novos usuários receptores via leitura de arquivo;
    Atualizar receptores. O arquivo de receptores deve ser lido e os usuários atualizados para refletir a lista de receptores encontrada nesse arquivo. Nesse momento novos usuários podem ser cadastrados. Usuários repetidos não devem existir no sistema.

A representação em String de um usuário é como segue:

nome/documento, email, celular, status: {doador|receptor}

Exemplos:

Raquel Lopes/01234567899, raquel@computacao.ufcg.edu.br, (83) 9990-9999, status: doador

Luciano Hulck/12345678900, ograndehulck@hulck.com,  (21) 9888-0021, status: doador

Lar da garota/01234567000189, lardagarota@gmail.com, (83) 98762-4554, status: receptor

Convento Ipuarana/12345678000190, ipuarana@hotmail.com, (83) 98777-9856, status: receptor

 Testes de aceitação disponíveis aqui.
Caso de uso 2: CRUD de itens a serem doados

Usuários doadores podem inserir itens a serem doados. Para garantir que os itens a serem doados vão compartilhar a mesma descrição, é importante manter uma coleção de descritores de itens no sistema. Um descritor de item é apenas uma descrição genérica do item. O sistema mantém um conjunto de descritores de itens genéricos para doação, assim não podemos ter descrições repetidas. Por exemplo, um descritor de item pode ter a descrição "cadeira de rodas", outro descritor pode ser "curso de programação", ou "cobertor". Ao tentar inserir um item para doação o usuário deve primeiro verificar se um descritor de item que represente bem o item que ele tem para doar já está no conjunto de descritores de itens do sistema.

Se o usuário tem "roupa de frio" para doar ele deve poder usar o sistema para primeiro cadastrar o novo descritor de item (caso não exista ainda no sistema). Para cadastrar um novo descritor de item o usuário precisa apenas descrever o item, de preferência sem usar acentuação. O sistema vai reduzir a descrição para usar apenas letras minúsculas e retirar espaços extras que o usuário possa ter digitado. Então o novo descritor é criado e adicionado ao sistema. Descritores de itens com a mesma descrição não podem existir, pois a descrição deve ser única no sistema.

Uma vez que o sistema possui o descritor do item a ser doado, o usuário pode adicionar o item para doação associado à sua conta. O item para doação associado ao usuário deve ter um descritor de item já previamente inserido no sistema, uma coleção de tags que caracterizam o item, um identificador numérico que identifica esse item no sistema de forma única e a quantidade destes itens para serem doados. Esta lista de tags pode ser vazia. Caso não seja vazia é interessante que as tags sejam adicionadas por ordem de importância.

A representação em String de um item para doação é como segue:

<id> - <descrição>, tags: [tag1, tag2, …], quantidade: n

Por exemplo:

1 - cadeira de rodas, tags: [manual, adulto], quantidade: 2

2 - curso sobre cuidados com o bebê, tags: [saude, 12 horas, maternidade], quantidade: 1

Deve ser possível atualizar as tags e a quantidade dos itens. Para a identificar o item a ser atualizado, deve ser informado o  usuário (identificado pelo seu documento) dono do item e o identificador único do item. Dois itens para doação são iguais se eles tiverem o mesmo descritor de item e as mesmas tags (na mesma ordem).

Os itens a serem doados devem estar sempre associados a um usuário doador. Se o descritor do item a ser associado a um item a ser doado não existir no sistema, este descritor deve ser inserido automaticamente no sistema no momento que o item para doação for inserido.

Itens a serem doados podem ser removidos. Para isso é preciso identificar o dono do item usando o documento de identificação e o item através de seu identificador único. Ao remover um item a ser doado, o descritor de item associado não é removido do sistema, mesmo que nenhum item daquele tipo exista para doação.

Com base nessas informações, deve ser possível:

    Cadastrar novos descritores de itens;
    Cadastrar itens para doação associados a usuários (identificados pelo seu documento). EXEMPLO dos parâmetros de entrada desse método: (idDoador, descricaoItem, quantidade, “tag1, tag2, tag3”)

    (123456789, cadeira de rodas, 1,  "manual, adulto")
    (987654321, curso sobre cuidados com o bebê, 1, "maternidade, duracao 12h")

Se o usuário em questão já tiver um item para doação idêntico ao item que está sendo cadastrado, então essa função vai modificar a quantidade do item em questão;

    Exibir informações de um item já cadastrado através do seu identificador único;
    Atualizar tags e quantidade de um item a ser doado por um usuário com um determinado documento. O item a ser atualizado é identificado pelo seu identificador único;
    Remover itens a serem doados de um usuário identificado pelo seu documento. O item é identificado pelo seu id único.

 Testes de aceitação disponíveis aqui.
Caso de uso 3: pesquisa dos itens a serem doados

Uma vez que o sistema já conhece os itens para serem doados, chegou a hora de escrever algumas funcionalidades interessantes.

    O sistema deve permitir uma listagem de todos os descritores de itens cadastrados no sistema, ordenada em ordem alfabética pela descrição do item. A descrição do item e sua quantidade devem ser apresentados nessa lista, por exemplo:

... |13 - cadeira de rodas | 1 - caminhao pipa | 20 - cobertor de la | 3 - curso de programacao | …

    O sistema deve permitir uma listagem de todos os itens inseridos no sistema ordenada pela quantidade do item no sistema. Não é necessário verificar itens iguais em usuários diferentes. Os itens em maior quantidade aparecem primeiro e os com menos quantidades aparecem por último. Itens com a mesma quantidade devem ser ordenados pela ordem alfabética de descrição. Também deve ser apresentado o identificador único de cada item e o doador do item com seu documento. Saída: <idItem - descrição, tags: [tag1, tag2, …], quantidade: <qtde>, doador: <nomeDoador>/<docDoador>

Exemplo:

… | 67 - cobertor, tags: [la, solteiro], quantidade: 13, doador: Raquel Lopes/01234567899 | 4 - filtro solar, tags: [FPS50], quantidade: 4, doador: Lívia Sampaio/23456789011 | 20 - casaco de frio, tags: [masculino, GG],  quantidade: 3, doador: Eliane Araújo/34567890122 | 41 - cadeira de rodas, tags: [manual, adulto], quantidade: 2, doador: Matheus Gaudencio/03406709001 | 23 - Cadeira de alimentacao, tags: [25kg, infantil], quantidade: 1, Raquel Lopes/01234567899 | 68 - Curso sobre cuidados com o bebê, tags: [saude, 2h, maternidade], quantidade: 1, doador: Soraya Furtado/45678901233

    O sistema deve listar todos os itens relacionados a uma dada string de pesquisa. Se por exemplo, o usuário digitar "cadeira", então todos os itens cuja descrição contém a string "cadeira" devem ser listados. Essa pesquisa não deve ser sensível a maiúscula/minúscula, assim, se a pesquisa foi da string "cadeira", mas no nome do item está armazenado "Cadeira", esse item deve ser selecionado na pesquisa. Essa listagem deve ocorrer em ordem alfabética considerando os descritores dos itens. 

Saída: <idItem1 - item para doação 1 | idItem2 - item para doação 2 ...>

Ex.:

… | 41 - Cadeira de alimentacao, tags: [], quantidade: 2 | 23 - Cadeira de alimentacao, tags: [25kg, infantil], quantidade: 1 | 11 - Cadeira de rodas, tags: [manual, adulto], quantidade: 2 | 58 - cadeira para automovel, tags: [16kg, preta], quantidade: 1 | 13 - cadeira reclinavel, tags: [couro],  quantidade: 2| ...

Implemente a parte da classe de fachada e o(s) controlador(es) necessário(s) para lidar com as classes/funcionalidades criadas neste caso de uso.

 Testes de aceitação disponíveis aqui.
Caso de uso 4: cadastro, atualização, listagem e remoção de itens necessários

Os usuários do sistema que são receptores devem poder indicar os itens que estão precisando receber, estamos chamando aqui de itens necessários. Um item necessário deve ter um identificador único e estar associado a um descritor de item, tags com características relacionadas ao item (por ordem de importância) e a quantidade necessária. Note que esses itens necessários trazem as mesmas informações que os itens para doação. Deve ser possível atualizar alguns campos de um item necessário (tags e quantidade). Dois itens necessários são iguais se eles tiverem o mesmo descritor genérico de item e as mesmas tags (na mesma ordem).

A representação em String de um item necessário é como segue:

<id> - <descrição>, tags: [tag1, tag2, ...], quantidade: n

Por exemplo:

1 - cadeira de rodas, tags: [manual, adulto], quantidade: 2

2 - curso sobre cuidados com o bebê, tags: [saude, 2 horas, maternidade], quantidade: 1

Os itens necessários devem estar sempre associados a um usuário receptor. Se o descritor do item a ser associado a um item necessário não existir no sistema, este item deve ser inserido automaticamente no sistema no momento que o item necessário for inserido.

Deve ser possível listar todos os itens necessários, ordenando-os pelo identificador único do item. Nesta listagem deve ser apresentado o usuário que necessita do item e seu documento. Exemplo:

… | 12 - cobertor, tags: [la, solteiro], quantidade: 13, Receptor: Caso do menino/04341571000109 | 14 - filtro solar, tags: [FPS50], quantidade: 10, receptor: Lar da garota/01234567000189 | 27 - casaco de frio, tags: [masculina],  quantidade: 5, receptor: Convento São Vicente de Paula/01347577000179 | 32 - cadeira de rodas, tags: [adulto], quantidade: 2, receptor: Convento Ipuarana/12345678000190 | ...

Items necessários podem ser removidos ou atualizados no sistema pelo seu usuário receptor. Para isso é necessário identificar o usuário que necessita do item através de seu documento de identificação, e o item através de seu identificador único. Ao remover um item necessário, o descritor de item associado não é removido do sistema, mesmo que nenhum item daquele tipo seja necessário. Apenas as tags e quantidade dos itens pode ser atualizadas.

Com base nessas informações, deve ser possível:

    Cadastrar novos itens necessários associados a usuários receptores (identificados pelo seu documento);

EXEMPLO dos parâmetros de entrada desse método: (idReceptor, descricaoItem, quantidade, “tag1, tag2, tag3”)

    (123456789, Cadeira de rodas, 1, "manual, adulto")
    (987654321, Curso sobre cuidados com o bebê, 1, "maternidade, duracao 12h")

    Listar todos os itens necessário cadastrados no eDoe.com ordenados pelo identificador único dos itens;
    Atualizar tags e quantidade de um item necessário associado a um usuário com um determinado documento. O item a ser atualizado é identificado pelo identificador único do item;
    Remover itens a serem doados de um usuário identificado pelo seu documento. O item é identificado pelo seu id único.

Testes de aceitação disponíveis aqui.
Caso de uso 5: identificar possíveis matches  de itens para doação e itens necessários

Esta é a funcionalidade mais importante deste sistema: encontrar casamentos (matches) entre itens a serem doados e itens necessários. Quem inicia esta funcionalidade é o receptor, e portanto o documento do usuário receptor deve ser conhecido pela função de matching. Além disso, deve ser informado o item necessário a ser considerado. A estratégia para realizar esse casamento considera  o descritor dos itens e as tags da seguinte forma: essa estratégia olha o descritor de item e as tags dos itens a serem doados para fazer o casamento com o item necessário. Apenas os itens para doação que tem o mesmo descritor que o item necessário especificado são selecionados e em seguida as tags são avaliadas. Os itens para doação que tem o mesmo descritor do item necessário iniciam o processo com 20 pontos. Tags iguais na mesma posição somam 10 pontos, tags iguais em posições diferentes somam 5 pontos. O sistema não é sensível a letras maiúsculas e minúsculas. Esta funcionalidade retorna os itens a serem doados que pontuarem nesse processo (zero ou mais itens podem ser retornados), ordenados da maior para a menor pontuação. Se as pontuações forem iguais deve-se ordenar pelo identificador dos itens.

O identificador dos itens selecionados devem também ser apresentados. Exemplo de saída dessa funcionalidade para um usuário receptor que busca um casamento para o item cobertor, tags: "la, solteiro", quantidade: 10 (mais uma vez, usamos “|” como separador):

67 - cobertor, tags: [la, solteiro], quantidade: 13, doador: Raquel Lopes/01234567899  |  22 - cobertor, tags: [la], quantidade: 4, doador: Lívia Sampaio/23456789011 |  45 - cobertor, tags: [casal], quantidade: 3, doador: Eliane Araújo/34567890122

 Testes de aceitação disponíveis aqui.
Caso de uso 6: realizar doações e manter o histórico de doações

Conhecendo os possíveis casamentos entre itens necessários e para doação, um usuário receptor pode solicitar uma doação. Para isso, o usuário deve indicar o identificador do item necessário e do item a ser doado. O sistema deve validar o pedido de doação olhando se os descritores de itens são mesmo iguais.  Caso o pedido seja validado, o sistema deve atualizar a quantidade de itens a serem doados e de itens necessários dos itens envolvidos nesta doação. Se uma dessas quantidades cair para zero, o item específico (para doação ou necessário) deve ser removido do sistema. O descritor do item, no entanto não deve ser removido do sistema.

Uma vez realizada uma doação, devem ser mantidas no sistema as seguintes informações:

Data da doação - doador: usuário doador (nome/documento), item: descrição do item doado, quantidade: <qtde>, receptor: usuário receptor (nome/documento)

Exemplo:

02/12/2018 - doador: Raquel Lopes/01234567899, item: cobertor, quantidade: 2, receptor: Lar da garota/01234567000189

Se Raquel tinha 13 cobertores para doar, então depois dessa doação ela tem só 11. O lar da garota, se precisava de apenas 2 cobertores, então passa a ser um usuário que não precisa mais receber cobertores.

Deve ser possível listar o histórico de doações pela ordem em que as mesmas foram realizadas (da mais antiga para a mais nova). Caso as datas sejam iguais deve ser listado pela ordem alfabética das descrições dos itens doados.
