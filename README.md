## Marvel App

<p align="center">
<img width="200" height="400" alt="image" src="https://github.com/JoaoPauloVenancio/marvel-app/assets/86843527/176c8530-4322-44f9-a060-9d70c07a9854">
<img width="200" height="400" alt="image" src="https://github.com/JoaoPauloVenancio/marvel-app/assets/86843527/b4cbba6a-b94b-460f-bdd3-25c300a7f502">
<img width="200" height="400" alt="image" src="https://github.com/JoaoPauloVenancio/marvel-app/assets/86843527/c4ec0bfe-05fe-4db6-903b-2e91d1038bdc">
</p>

## Introdução
Olá, este é um aplicativo desenvolvido para visualização de dados referente a API da Marvel.
Logo abaixo tem explicações referentes as tecnologias utilizadas, imagens do aplicativo, como foi pensado em relação a arquitetura e qual objetivo da escolha.
Este aplicativo ainda continua em desenvolvimento entao ao final deixarei minhas redes sociais para possíveis melhorias.

## Funcionalidades
1. **Listagem de Todos os Personagens da Marvel:** A tela principal do aplicativo no qual contem a listagem infinita de todos os personagens, n. de comics e series. No topo da tela uma barra de search para pesquisar algum heroi pelas iniciais. E na parte de baixo da tela contem uma bottom navigation com as sessoes de listagem, favoritos e as minhas redes.

2. **Visualizar Detalhes do Personagem:** Ao clicar em algum personagem da lista será redirecionado para tela de detalhes contendo alem da imagem a descricao do personagem.

3. **Listagem de revistas:** Ao acessar o detalhe de algum personagem a barra superior conta com as features de favoritar o personagem e listagem de revistas. Na tela de listagem de revistas o usuario consegue ver a lista com titulos e numero de paginas de cada revista. 

## Arquitetura
A arquitetura escolhida para essa aplicação é a indicada pela Google para aplicativos Android nativo (MVVM) seguindos principios como Separação de conceitos, Única fonte de informações, Fluxo de dados unidirecional e SOLID.

## Arquitetura
A aplicacao contem 1 modulo (app) organizado nos packages feature, framework e utils. O objetivo em simplificar apenas em 3 pacotes teve por objetivo facilitar entendimento de novos usuarios. O pacote features contem as subdivisoes de cada feature com suas camadas internas seguindo a arquitetura (Ui layer, Data layer).
Seguindo para o package de framework temos as tecnologias que serao utilizadas pelo app todo como Injecao de dependencia utilizando Hilt/Dagger, A camada de Service e o Banco de dados local (Room). 
E por ultimo temos o package de Utils para quaisquer utilitarios que possam surgir durante a aplicacao.


## Bibliotecas e Frameworks
Material Design - 1.9.0</p>
Navigation - 2.5.0</p>
Lifecycle - 2.5.1</p>
Coroutines - 1.6.4</p>
OkHTTP - 4.9.1</p>
Retrofit - 2.9.0</p>
Gson - 2.9.0</p>
Coil - 2.4.0</p>
Firebase Analytics & Crashlytics - 32.2.3</p>
Paging 3 - 3.1.1</p>
Dagger-Hilt - 2.43.2</p>
Facebook Shimmer - 0.5.0</p>
JUnit - 4.13.2</p>
Mockito - 2.2.0</p>

</p>
</p>
</p>

## Proximas implementações

1. Implementacao da feature de favoritos salvando o personagem no Room.


## Redes sociais para contato
https://www.linkedin.com/in/joao-paulo-venancio/
