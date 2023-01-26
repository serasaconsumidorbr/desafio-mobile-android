<!-- Header-->
<br />
<p align="center">
  <a href="https://github.com/serasaconsumidorbr/desafio-mobile-iOS">
    <img src="https://turismoemfoco.com.br/v1/wp-content/uploads/2020/05/serasa-logo-nova22.png" alt="Logo" width="180" height="80">
  </a>

  <h3 align="center">Desafio - Android Developer </h3>

  <p align="center">
    O nosso aplicativo é uma das nossas soluções para mudar a vida financeira de milhões de brasileiros. <b>Venha fazer parte desse time</b> muito engajado que
  trabalha para que esse aplicativo chegue da melhor forma na mão dos consumidores.
  </p>
</p>

## Sobre
<p> Marvel App, um aplicativo desenvolvido, inicialmente, em Android Nativo, que exibe todos os personagens, por ordem alfabética, do Universo Marvel.</p>

## Componentes
<p>Esses componentes e padrões de projetos foram pensados e escolhidos visando uma futura escalabilidade de features.</p>


- Versão mínima do SDK: 21
- Tela deve ajustar em devices menores.
- Linguage Kotlin
- Clean Architecture (Essa arquitetura foi escolhida devido ao desacoplamento entre as camadas, em relação a outras arquiteturas, facilitando a escrita de testes, possíveis refactor e novas features.)
- Coroutines (Trabalhando com o conceito uni thread, foi utilizado Coroutines para transformar todo o código assincrono em síncrono, diminuido a criação de novas threads a cada requisição, e diminuindo as chances da main thread está bloqueada e causar um ANR, e com isso o app fechar durante a utilização do usuário.)
- Testes unitários (Utilizando a biblioteca MockK, que se adapta muito bem ao Kotlin e Flow. Foi testado, unitáriamente, todas as camadas da arquitetura, desde o Remote Data Source até a View Model. Garantindo assim uma maior confiabilidade do app.)
- Cache de imagens (O cache utilizado não foi apenas para a imagem, mas para a requisição do endpoint por completo, duranto 5 segundos. O cache foi feito por meio do interceptor anexando os dados da chamada no próprio Retrofit)
- Tratamentos de erros (Foi escolhida o Remote Data Source como camada responsável por receber os erros e converte-los em erros customizáveis do proprio app. A View Model é a camada que vai receber os erros customizáveis, e reagir a UI segundo cada tipo de erro que ocorreu e que o Remote Data Source enviou. Com isso, cada tipo de erro gerará uma resposta visual diferente.) 
- Koin Dependeny Injection (Koin é uma injeção de dependencia que trabalha em tempo de execução e possui uma curva de aprendizado e escrita muito curta. Embora o app se torne um pouco mais lento devido as criações das dependencias ocorrerem em tempo de execução, como é um app pequeno o usuário não é tão afetado, e o tempo de build do app é menor.)
- Princípios SOLID
- Teste de Interface (Foi utilizado o Espresso para realizar os testes de interface do app. Obs: A Home Fragment ainda falta ser testada)
- Custom Views (Criação de componentes internos para facilitar e desacoplar reponsabilidade da UI e deixar com os proprios componentes.)
- Layouts de fragments, activitys e custom view construidos com Constraint Layout.
- DTO Pattern (Data Transfer Object, design pattern que fala que cada camada da arquitetura deve ter seu próprio modelo, com isso, qualquer alteração que ocorrer no banco de dados ou resposta da API, a UI do usuário não é impactada devido a separação de modelos por camadas e utilização de mappers.)
- Criação de extensions e tratamento de erro
- Single Activity Pattern (O app é composto por activitys e fragments, porém separado por contexto, cada contexto existirá um container maior, activity que será populada por vários fragments, resultando em uma fonte maior de verdade, um container maior do contexto e deixando o app mais fluido já que fragment é menos custoso para construir que uma activity, compensando a utilização do Koin e facilitando animações)

## Novas Features
- Criação da tela Details (Uma tela que mostrará todos os detalhes de cada usuário clicado)
- Search de personagens
- Utilização do Paging 3 (Inicialmente foi desenvolvido com Paging 3, porém como ele trabalha assincrono não existia uma forma de capturar a lista retornada da API pois o objeto de retorno era um PagingData que o único momento que existia uma lista era no adapter. Com isso não daria para separar uma lista em duas para dois adapters diferentes)
- Finalizar os testes de interface
- Migrar para Jetpack Compose (A única razão para não ser desenvolvido em Jetpack Compose foi um dos requisitos utilizar constraints, porém ainda foi pensado em usar constraint em compose)





https://user-images.githubusercontent.com/59460244/214677329-03774bdf-924d-4bd5-8d2d-ebc4391e9266.mp4

