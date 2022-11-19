## Marvel - Desafio

## Arquitetura

- data -> dados utilizados pelo app, nesse caso somente o consumo da API da Marvel mas podendo modificar para atender também banco de dados.
- domain -> camada de regras de negócio.
- di -> camada de injeção de dependência.
- presentation -> camada de views, adapters e viewmodels.
- utilities -> camada de utilidades em geral para o app.

## Bibliotecas

- Retrofit -> utilizado para o consumo da API.
- Coroutines -> programação reativa.
- Viewmodel -> gerenciamento do controle de dados para a view.
- Paging -> carregamento dos dados em cada lista.
- Dagger/Hilt -> injeção de dependência.
- Testes unitários -> JUnit + Mockito.
- Jackson -> parser json.

## Próximas features

- Adicionar opção para ver detalhes dos personagens
- Melhorar o layout aproveitando melhor o espaço da tela
- Utilizar banco de dados local (Room) para utilização offline 
- Adicionar modo pesquisa para busca de personagens
