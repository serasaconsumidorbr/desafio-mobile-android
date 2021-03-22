<br />
<p align="center">
  <h3 align="center">Marvel's Characters</h3>
</p>
<p align="center">
  <a href="#fluxo">Fluxo</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8Froadmap">Roadmap</a>
</p>

<p align="center">
  <img alt="Mokup" src=".github/img.png" width="100%">
</p>

<br>

## 🌱Fluxo

Eu dividi o fluxo utilizando o kaban+trello, as tarefas foram em cards ,com a checklists das D.o.R (Definition of Ready), durante o desenvolvimento segui o modelo git flow criando feture branchs e merjando na developer:

* Listar os requisitos.
* Criar o trello com todos os requisitos.
* Puxar os cards na ordem que fazia sentido/prioridade 
* Meu fluxo foi:
  * Setup do projeto(arquiteturam injeção de dependencias e principais libs 📚)
  * Setup dos services(autenticão com api 🔐 e mapeamento de models)
  * Carrousel 🎠 - Logo no começo por ser a feature mais simples, pra me familiarizar com a api.
  * Lista de personagens 🦸 - Sem a paginação em um primeiro momento pra deixar as coisas simples
  * Tratamento de erros❗ estilo sanduiche 🥪
  * Scroll infinito ♾️
  * Testes unitarios/integração ☑️
  * Ultima checada em tudo ☑️
  * Resolver erros do git 😧 
  * Escrever a documentação INCEPTION HAHA ➿
  * Fazer pull request -> [THE END] 🗓️

## 🚀Tecnologias
Eu usei as seguintes tecnologias durante :

* <b><a href="https://square.github.io/retrofit/">Retrofit2</a></b> - A lib padrão quando o assunto é http no android e o que eu uso(e a melhor na minha humilde opinião).
* <b><a href="https://dagger.dev/hilt/">Hilt(Dagger2)</a></b> - Escoli o hilt pra usar o dagger2 e não precisar ficar escrevendo muito boilerplate.
* <b><a href="https://developer.android.com/jetpack/androidx/releases/viewpager2">ViewPager2</a></b> - A melhor lib pra fazer paginação/carousels e é facil de usar.
* <b><a href="https://developer.android.com/guide/navigation/navigation-getting-started">Jetpack Navigation</a></b> - Utilizei o naviagtion para navegar entre os fragmentos.
* <b><a href="https://github.com/bumptech/glide">Glide 4</a></b> - Rápida e facil de usar e faz o caching das imagens pra mim.
* <b><a href="https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor">Logging Interceptor</a></b> - Pra me ajudar no processo de desenvolvimento logando as requests pra mim.
* <b><a href="https://material.io/develop/android">Material Components</a></b> - Vários components prontos e também tinha o tab layout que eu precisava pro carousel.
* <b><a href="https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies">Lifecycle Components</a></b> - Os components nescessários pra minha arquitetura MVVM, ViewModel e LiveData.

## 🗺️Roadmap
Gostei do resultado mais eu gostaria de adicionar mais algumas coisas nele, pra dar aquele toque especial:

* Refatorar esse design (mudar paleta de cores interação, listagem etc).
* Tela de detalhes dos personagens(com direito a hero animation).
* Usar um shimmer loading bonito.
* Usar a paging library, no scroll ininito.
* Explorar mais as outras áreas da api como quadrinhos e a busca por nome.

---

Feito com ♥ por <b><a href="https://github.com/Drawiin">Drawiin</a></b> 👋
