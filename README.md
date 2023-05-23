# desafio android (importante, favor ler tudo !!)

Os requisitos minimos do projeto foram cumpridos completamente. Algumas escolhas foram tomadas considerando o tempo bastante limitado que tive para trabalhar na aplicacao. Todas essas escolhas foram justificadas e tiveram melhorias sugeridas no readme a seguir.

## Funcionamento e escolhas

Como usar: O app já vem pronto para rodar, com minhas chaves pessoais da API. Uma abordagem ideal envolveria colocar essas variaveis dentro de build variabels, para tornar mais dificil o uso da engenharia reversa, e tambem que essas chaves fossem adicionadas de forma customizada para cada usuário. As chaves ficaram hardcoded no código apenas por questao de ausencia de tempo e praticidade. A aplicacao utiliza o parametro offset da API para fazer novas chamadas na api e carregar mais dados. Um offset visual adicionar de 5 foi escolhido, ou seja, quando faltar 5 itens para terminar todos os itens visiveis, a api realiza uma nova chamada e carrega mais. O carousel superior mostra os personagens (do ultimo fetching) que mais apareceram em comics. Tambem foi incluida uma funcao de favoritos e um cache para trabalhar offline. Caso o usuário carregue todos os dados da API, um Toast é exibido dizendo que os dados esgotaram.

## Requisitos obrigatórios

Min sdk: 21

Testado em vários telefones pequenos, com o Pixel 4

100% em kotlin

Arquitetura: 
oi selecionada a arquitetura MVVM, combinada com alguns conceitos de Clean. A decisao foi tomada pensando em conceitos como escalabilidade,
separacao de propósitos de cada camada/classe, testabilidade e também em tirar o maior proveito possível de programacao reativa e dos componentes
de arquitetura do Android. A arquitetura MVVM tambem foi escolhida, utilizando-se do componente de arquitetura viewModel, que sobrevive a mudancas de orientacao, portanto é possível
aplicar mudancas de orientacao (como virar o celular para landscape) sem perder os dados presentes no viewModel (já carregados). Para o recyclewView Adapter, foi selecionada a abordagem com a utilizacao de um DiffUtils por ser uma das mais eficientes, pois notifica somente o que de fato mudou nos dados
Na ultima seção , há uma lista de possíveis melhorias e coisas que teriam sido feitas sem a limitacao de tempo

Ferramenta reativa escolhida: Coroutines + Flow

Testes unitários: Foram feitos testes completos para o viewModel da atividade principal. Dada a arquitetura da solução proposta, o ideal seria que os testes
unitários tivessem sido concentrados na camada de Domain (use cases ou managers) , que concentra a lógica de negócios mais pesada do nosso código 
(o viewmodel contem apenas uma lógica leve). O teste foi feito no viewModel, considerando que a presente soluçao tem caráter avaliativo e com tempo
limitado.

Caching de imagens: realizado através da ferramenta Glide

Tratamento de erros: O app exibe possiveis erros na tela (e inclui uma mensagem generica caso o erro nao seja identificado, e tambem fornece a opcao do usuário tentar recarregar os dados em caso de erro)

Alguns padroes de projeto foram aplicados, como singleton, facade, etc. Boas práticas básicas de reaproveitamento de código tambem (como por exemplo uma BaseActivity que incluir algumas padronizacoes básicas e tambem o uso de interfaces para que o código possa depender de abstracoes ao invés de objetos complexos e ficar mais desacoplado)

Foram usados diversos AAC, como ViewModel, Flow e Room Database.

Há um carousel superior com os 5 personagens (entre os carregados recentemente) que mais apareceram em comics, e tambem uma lista vertical com scroll infinito, sem repeticao. 

## Requisitos diferenciais

Todos os layouts foram construidos usando constraint layout

O aplicativo tem capacidade completa de lidar com dados de maneira offline. Foi colocado um cache local utilizando o RoomData base, permitindo que o aplicativo opere Offline, desde que os dados ja tenham sido pre carregados ao menos uma vez. Foi também utilizado um caching de imagens, através da biblioteca Glide.

Injecao de dependencia: Foi escolhido o Hilt, pois oferece uma compatibilidade maior com os componentes do Android como ViewModel e Activity, e também pois oferece uma abordagem mais eficiente e escalável se compararmos por exemplo com o Koin. 

## Requisitos adicionais

Foram implementados também alguns requisitos adicionais. Há um mecanismo de favorito na aplicacao, é possível clicar no icone de estrela para 
favoritar um repositório. Como nao era possível fazer um post ou um put na API, esses favoritos sao salvos localmente, e quando uma lista de 
de repositorios é recuperada da API, há uma vericacao, e se os posts existentes no cache existem e estao favoritados em cache, esse estado é considerado. 
Tambem é possível eliminar a visualizacao de um repositório, segurando por alguns segundos (apenas visual e temporário, nada é feito no cache ou na API)

## Melhorias e cenários nao ideais da aplicacao

Algumas strings da aplicacao e alguns outros campos (como parametros do request e url da api) estao hardcoded. Com o devido tempo, o ideal seria move-los
para os parametros de entrada dos métodos, variaveis de Build e tambem para arquivos de resource. Os conceitos de Clean e MVVM utilizados, poderiam
ser melhorados ainda mais, com uma separacao ainda maior das camadas. Há também alguns erros básicos no layout, que poderiam ter sido melhorados, tornando o design mais agradável. O ideal seria utilizar um MotionLayout combinado com um componente carousel nativo para criar o carousel, incluindo uma animacao suave de transicao. Isso nao foi incluido tambem pela limitacao de tempo. A nomenclatura e organizacao dos métodos tambem poderia ser melhorada, principalmente dentro da camada de Domain (manager files)e Model. O projeto foi concluido todo de uma vez em poucas horas, entao nao foi incluido uma estrutura de gitflow. O ideal seria que tivesse sido feito em partes, como sugerido no enunciado. 
