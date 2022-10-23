# Marvel Characters 🦸
Um aplicativo de listagens de personagens da Marvel utilizando princípios de Clean Architecture com Jetpack Compose para construção da UI.

## Contexto
Super heróis é algo muito legal, não é mesmo? Quem nunca pensou em quais poderes teria caso fosse um super herói, ou então qual seria o super herói com a história mais interessante?


https://user-images.githubusercontent.com/49538805/164180291-314bd190-306b-4236-bf3e-a9b117739019.mp4


Pensando nisso escolhi desenvolver uma aplicação de listagem de personagens da Marvel utilizando a nova lib de construção de UI's da Google para desenvolvimento Android Nativo, o [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=pt-br). 🥰

A api utilizada foi a [Marvel Comics API](https://developer.marvel.com/docs).

## Como o app está estruturado? 🤔

O app é modularizado por feature, e cada feature contém outros 3 sub-módulos: 
- Data: Contém tudo que seja de relevante para dados, como por exemplo o acesso a API e banco de dados;
- Domain: Um tipo de camada de conexão, contém as nossas regras de negócio (divididas em use cases), como por exemplo a lógica de obtenção dos personagens, definição abstrata de repositórios e modelo de classes (como entidades de bancos de dados);
- Presentation: Contém a representação de UI: estados de carregamento, erro e demais componentes.

## Como o app funciona hoje?
Ao entrar no app a primeira tela a carregar será a home com 2 componentes principais, um carrossel com 5 personagens e logo após uma lista "infinita" de personagens da Marvel (sem repetir);

## Caso de erro
É possível que o app abra sem internet, nesse caso um botão para tentar novamente irá aparecer, também você pode simplesmente fazer um [swipe refresh](https://media.geeksforgeeks.org/wp-content/uploads/20200811000954/swipedowntorefresh-660x251.png) que a uma nova requisição será feita


## E os testes? 🤔
- O app contém testes unitários ✔

## O que vem pro futuro?
- Buscar personagem;
- Favoritar personagem;
- Filtrar o carregamento da home com base nas opções disponível na API;
- Login com firebase mantendo os personagens favoritos remotamente;
- Dark theme;
- Testes de interface.
