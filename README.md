<br />
<p align="center">
  <h3 align="center">App Marvel Heroes</h3>
</p>
<p align="center">
  <img alt="Marvel App Demo" src=".github/marvel.gif" width="auto">
</p>

<br>

---
### :zap: Instruções

* Criar conta e gerar chave de acesso a api Marvel em: <a href="https://developer.marvel.com/documentation/getting_started">API Marvel</a>.
* Para compilação com sucesso da aplicação adicionar as chaves da api *Public key e Private key* no arquivo *apiKey.properties*.

---
### :clock1: Desenvolvimento

O processo de desenvolvimento foi dividido em algumas etapas, utilizando o padrão MVVM, repository, DI:

* Analisado a documentação Marvel.
* Gerado chave de acesso.
* Utilizado Insominia para testes na API Marvel. 
* Iniciado a configuração do projeto:  

  * Realizado processo de conversão para Utilização de biblioteca AndroidX.  
  * Configuração de dependencias utilizando versões mais recentes:

    * Glide
    * Retrofit
    * OkHttp
    * Hilt
    * Room
    * JeetPack
    * Kotlin

* Iniciado Desenvolvimento das funcionalidades:
  * Criação da estrutura MVVM.
  * Criação de fragments e ViewModels.
  * Desenvolvimento de integração com a API da Marvel utilizando padrão Repository e a biblioteca Retrofit.
  * Configuração de API-KEY utilizando BuildConfig.
  * Criação de layouts utilizando ConstraintLayout.
  * Listagem dos personagens utilizando-se RecycleView e DiffUtil para melhora de performace.
  * Cache de Imagens utilizando o próprio Glide.
  * Implementação do Carrossel utilizando o ViewPager2 e TabLayout.
  * Implementado recurso de Collapsing e Parallex para melhorar o processo de usabilidade. 
  * Implementado style para Tema Dark e Light.
  * Desenvolvimento de Icone da aplicação.
  * Migrado kotlin synthetic para viewbiding.
  * Desenvolvimento Scroll infinito.
  * Criação de tela de SplashScreen utilizando a biblioteca splashscreen do AndroidX por questão de compatibilidade e seguindo o padrão do Android 12.
  * Implementação de cache de dados OffLine utilizando a biblioteca Room.
  * Tratamento de erros de repository para Local e Remote.
  * Testes em dispositivos reais com android 11, 10 e 5, e no emulador.  
  * Criação do ReadMe do projeto.
---
### :star: Bibliotecas e tecnologias
Tecnologias utilizadas no desenvolvimento da aplicação :

* <b><a href="https://kotlinlang.org/">Kotlin</a></b> - Linguagem de programação recomendada pelo Google.
* <b><a href="https://square.github.io/retrofit/">Retrofit2 (2.9.0)</a></b> - Biblioteca para comunicação com a API.
* <b><a href="https://github.com/square/okhttp">OKHttp</a></b> - Utilizada em conjunto com Retrofit para captura dos logs das requisições
* <b><a href="https://dagger.dev/hilt/">Hilt(Dagger2)</a></b> - Biblioteca de injecão de depêndencia recomendada pela Google.
* <b><a href="https://developer.android.com/jetpack/androidx/releases/viewpager2">ViewPager2</a></b> - Utilizada para o desenvolvimento da feature de carrossel, sem a necessidade de usar bibliotecas de terceiros.
* <b><a href="https://github.com/bumptech/glide">Glide 4.12</a></b> - Utilizada para o download e cache das imagens.
* <b><a href="https://developer.android.com/jetpack/androidx/releases/room">Room</a></b> - Biblioteca de persistência que oferece uma camada de abstração sobre o SQLite.
* <b><a href="https://material.io/develop/android">Material Design</a></b> - Utilizado components do Material Design.
* <b><a href="https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies">Lifecycle Components</a></b> - Bibliotecas para a implementação da arquitetura MVVM (ViewModel, LiveData).
* <b><a href="https://developer.android.com/jetpack">Android JetPack</a></b> - Conjunto de bibliotecas com práticas recomendadas, que ajudam a reduzir códigos boilerplate e programar códigos que funcionam de maneira consistente em diferentes dispositivos e versões do Android.
* <b><a href="https://developer.marvel.com/documentation/getting_started">API Marvel</a></b> - Api utilizada para obter os personagens da marvel.


---
### :black_square_button: Para o Futuro
Novas funcionalidades e melhorias são sempre bem vindas:

* Desenvolvimento de loading animado de Heroes da Marvel.
* Possibilidade de alternar entre tema Dark e Light.
* Utilização de outros recursos da API para disponibilização de novas funcionalidades no aplicativo.
* Nova tela para apresentar detalhes do personagem, utilizar a biblioteca de Pallete para trabalhar em cima da cor predominante na apresentação.
* Utilização de MotionLayout para melhorar o fluxo de animação e o UX e UI da aplicação.
* Possibilidade de favoritar o personagem.
* Funcionalidade de pesquisa dos personagens.
* Funcionalidade de selecionar a ordenação.
* Filtro de personagens.
* Navegação entre tela utilizando Navigation.
* Testes automatizados.

---

Feito com :heart: por <b><a href="https://github.com/josiasmaceda">Josias Maceda</a></b>
