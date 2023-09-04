## Introdução
Este README apresenta uma visão geral da arquitetura utilizada no desenvolvimento do aplicativo Marvel APP.
O intuito é capturar e transmitir as decisões significativas do ponto de vista da arquitetura que foram tomadas em relação ao app.

## Propósito
Este documento foi criado para dar uma visão de alto nível da solução técnica seguida, enfatizando os componentes e frameworks que foram utilizados e desenvolvidos, além das interfaces e integrações entre os mesmos.

## Público Alvo
Esse documento destina-se a equipe de avaliação.

## Funcionalidades
1. **Listagem de Todos os Personagens da Marvel:** Os usuários podem navegar e visualizar uma lista completa de personagens da Marvel. Essa lista inclui nomes e imagens dos personagens.

2. **Visualizar Detalhes do Personagem:** Os usuários podem selecionar um personagem da lista para visualizar um carrocel com os quadrinhos em que ele é mencionado ou nos eventos em que ele está presente.

3. **Cache de Dados para Utilização Offline:** O aplicativo utiliza um mecanismo de cache para armazenar os dados dos personagens. Isso permite que os usuários acessem as informações mesmo quando estão sem conexão com a internet. O cache garante uma experiência mais contínua e rápida, mesmo em condições de conectividade intermitente.

4. **Salvar Personagens Favoritos em um Banco de Dados Local:** Os usuários têm a capacidade de marcar personagens como favoritos, e essas seleções são armazenadas em um banco de dados local no dispositivo do usuário. 

## Arquitetura
Essa arquitetura é baseada na Clean Architecture proposta por Robert C. Martin no livro “Arquitetura Limpa: O Guia do Artesão para Estrutura e Design de Software”, mais o desing patter MVVM proposto pela google no seu [Guia de Arquitetura do App](https://developer.android.com/jetpack/guide?hl=pt-br).

- Camadas da Clean Architecture + MVVM

<p align="center">
  <img width="400" height="600" alt="image" src="https://github.com/DevEgF/desafio-mobile-android/assets/104474051/17c1ce02-8d0f-4360-8887-aa4d81ee612d">
</p>

   1. Presentation
      - A camada de apresentação é responsavel por lidar com a interface do usuário e apresentar os dados ao usuário final. Essa camada é dividida em duas partes principais View e ViewModel.
   2. View
      - A camada View é a interface do usuário, que exibe informações e interações com o usuário. Ela é responsavel por receber eventros e repassá-los para a ViewModel correspondente, bem como exibir os dados fornecidos pela ViewModel. A view não deve conter lógica de negócio, apenas manipulação da interface do usuário. Ela pode ser implementeada utilizando tecnologias especificas, como um Fragment ou uma Activity no Android.
   3. ViewModel
      - A camada ViewModel é responsável por fornecer dados e comportamentos para a View. Ela atua como um intermediário entre a View e a camada de domínio. A ViewModel recebe os eventos da View, executa a lógica de negócio necessária e atualiza os dados que serão exibidos na View. Ela também pode fornecer comandos que são acionados pelas View para executar ações especificas. Na ViewModel não deve conter lógica de apresentação, como formatação de dados para exibição. Deve ser implementada como uma classe separada, geralmente usando ligação de dados utilizando o desing pattern LiveData para atualização dos estados presentes na View do usuário. Nesta camada iremos realizar a comunicação com os casos de uso presentes na camada de Domain.
   4. Domain
      - A camada de domínio contém a lógica de negócio principal do aplicativo. Ela encapsula as regras e as operações que definem o comportamento central da aplicação. A camada de domínio é independente de qualquer tecnologia específica e é composta por entidades, casos de uso e interfaces.    
   5. Data
      - Camada responsável por acomodar os repositórios da aplicação, nele iremos solicitar as informações necessárias da API através de métodos e captar as informações vinda da API através de um response. Nela também deve ficar as fontes de dados.

## Bibliotecas e Frameworks

| Bibliotecas        | Justificativa de uso      | Versão |
|----------|----------|-------|
| Material Design   | Conjunto de diretrizes e componentes de design desenvolvidos pelo Google.  | 1.6.1 |
| Navigation | Biblioteca que facilita a implementação de navegação entre diferentes telas e fluxos.  | 2.5.2 |
| Lifecycle   | Fornece componentes e classes que ajudam a gerenciar o ciclo de vida dos componentes.   | 2.5.1 |
| Coroutines   | Responsável pela configuração e implementação de programação assíncrona e concorrente.   | 1.6.4 |
| OkHTTP   | Cliente HTTP que fornece uma API simples para fazer solicitações de rede.  | 4.9.0 |
| Retrofit   | Plataforma Google que auxilia no monitoramento e expansão dos apps de maneira rápida e com alta qualidade.   | 2.9.0 |
| Gson   | Responsável por fazer serializações e desserializações de objetos Java/Kotlin em formato JSON.   | 2.9.0 |
| Glide   | Responsável pelo carregamento e exibição de imagens.   | 4.12.0 |
| Firebase Analytics & Crashlytics   | Responsável pelo monitoramento do uso do aplicativo pelos usuários.   | 32.2.0 |
| Paging &  RemoteMediator  | Auxilia na configuração da forma de carregamento e exibição de dados paginados.   | 3.1.1 |
| Dagger-Hilt   | Facilita a injeção de dependências no aplicativo.   | 2.43.2 |
| Room   | Biblioteca de persistência de dados que facilita o acesso e a manipulação de dados em uma abstração SQLite.   | 2.4.3 |
| Facebook Shimmer   | Fornece um efeito de carregamento de dados agradável ao usuário.   | 0.5.0 |
| JUnit   | Framework de teste para Java e Kotlin que facilita a criação e execução de testes.   | 4.13.2 |
| Mockito   | Biblioteca de mocking para testes.   | 2.2.0 |
| MockWebServer   | Biblioteca de mocking para testes.   | 4.9.3 |
| Espresso   | Responsável por auxiliar na criação de testes de interface de usuários.   | 3.4.0 |


## Boas Práticas
S.O.L.I.D

## Visão do aplicativo

<p align="center">
<img width="200" height="400" alt="image" src="https://github.com/DevEgF/desafio-mobile-android/assets/104474051/9aec083b-1091-4849-bca1-b9bb7c14fd87">
<img width="200" height="400" alt="image" src="https://github.com/DevEgF/desafio-mobile-android/assets/104474051/43aca908-e3c6-40c3-98f8-72a932b01b10">
<img width="200" height="400" alt="image" src="https://github.com/DevEgF/desafio-mobile-android/assets/104474051/48a07f3a-e03a-4f92-8633-e8fe25d6ea18">
<img width="200" height="400" alt="image" src="https://github.com/DevEgF/desafio-mobile-android/assets/104474051/539e1f9c-c185-482b-8c6f-d0ebee5575d3">
</p>

## Monitoramento do app
<p align="center">
  
![image](https://github.com/DevEgF/desafio-mobile-android/assets/104474051/e279f7b9-e4c9-47a2-9513-00441db86f58)

Ob.: A versão do app exibida acima não reflete o periodo de desenvolvimento. O intuito da imagem é evidênciar a integração do aplicativo com o Firebase Analytics

</p>

## Roadmap para futuras implementações

1. Adicionar o comando de "Swipe to refresh" na tela de listagem de personagens.

2. Adicionar carrocel a tela inicial, logo a cima a listagem de personagens, que exiba os personagens favoritados.

3. Adicionar "Searchbar" para procura de personagens especificos e adicionar opção para ordenação por nome.

## Raciocinio para desenvolvimento do projeto

O aplicativo foi estruturado conforme a Clean Arc, em dois módulos: módulo Core e o módulo App. No módulo Core ficam todas as classes e padrões de projeto relacionado a camada de domain e data, como por exemplo: fontes de dados remotas e locais, além dos repositórios, models e casos de uso. Já no módulo App, ficam todas as classes e padrões de projeto que se relacionam ao framework do Android, sendo assim: injeção de dependência, implementações dos repositórios e fontes de dados, dtos, view models e fragmentos. Toda a aplicação foi modularizada por funcionalidade, são elas characters e details. Cada pacote segue o padrão da arquitetura adotada facilitando assim, a escalabididade e a manutenção de cada funcionalidade.

Após o processo de desenvolvimento adicionei o aplicativo com versão de realese ao firebase, com o intuito de monitorar a utilização do app pelos usuário, capturando assim problemas de performance e crashs caso ocorram.

## Referências

- [1] MARTIN, Robert C. The Clean Architecture Artigo web. Ano: 2012. Disponível em: https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
- [2] Storing Secret Keys in Android. Disponível em: https://guides.codepath.com/android/Storing-Secret-Keys-in-Android
- [3] Encoding, Hashing e Encryption: Qual a diferença?. Disponível em: https://dev.to/kotlinautas/encoding-hashing-e-encryption-qual-a-diferenca-29gg
- [4] Paging library overview. Disponível em: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
- [5] Android Design: Best Way To Show Progress using Shimmer Effect. Disponível em: https://medium.com/mindorks/android-design-shimmer-effect-fa7f74c68a93
- [6] Gerenciar e exibir estados de carregamento. Disponível em: https://developer.android.com/topic/libraries/architecture/paging/load-state?hl=pt-br
- [7] Advanced Android in Kotlin 05.3: Testing Coroutines and Jetpack integrations. Disponível em: https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-survey#3
- [8] Reduzir, ofuscar e otimizar o app. Disponível em: https://developer.android.com/studio/build/shrink-code?hl=pt-br
- [9] Fundamentals of testing Android apps. Disponível em: https://developer.android.com/training/testing/fundamentals
- [10] Test your fragments. Disponível em: https://developer.android.com/guide/fragments/test
- [11] Guia de teste do Hilt. Disponível em: https://developer.android.com/training/dependency-injection/hilt-testing?hl=pt-br
- [12] Provide multiple bindings for the same type. Disponível em: https://developer.android.com/training/dependency-injection/hilt-android#multiple-bindings
- [13] AndroidJUnitRunner. Disponível em: https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/runner#using-android-test-orchestrator
- [14] Nested RecyclerView In Android. Disponível em: https://medium.com/nerd-for-tech/nested-recyclerview-in-android-e5afb2b9771a
- [15] InstantTaskExecutorRule. Disponível em: https://developer.android.com/guide/fragments/animate#shared
- [16] Use shared element transitions. Disponível em: https://developer.android.com/guide/fragments/animate#shared
- [17] Write asynchronous DAO queries. Disponível em: https://developer.android.com/training/data-storage/room/async-queries
- [18] Android Room with a View - Kotlin. Disponível em: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
- [19] Implement a RemoteMediator - Kotlin. Disponível em: https://developer.android.com/topic/libraries/architecture/paging/v3-network-db#implement-remotemediator
