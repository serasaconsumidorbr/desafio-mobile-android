<h1 align="center"> Desafio - Android Developer </h1>

- Aplicação desenvolvida para mostrar detalhes sobre diversos personagens da Marvel Comics.
- Endpoint  <strong>/v1/public/characters</strong>.
- Mais informações sobre a API disponíveis em https://developer.marvel.com/documentation/.

## :hammer: Tecnologias do projeto

- `Scroll Infinito`: Foi criada uma classe para gerenciamento de paginação através da biblioteca Paging 3 que ajuda a carregar e exibir páginas de um conjunto de dados maior do armazenamento local ou da rede.
- `Carousel`: Implementação feita através da Biblioteca MotionLayout que gerencia o movimento e widget de animação em apps.
- `Retrofit`: Implementação do serviço utilizando a Biblioteca Retrofit. para simplificar o código que é executado de forma assíncrona.
- `Coroutines`: Para simplificar o código que é executado de forma assíncrona.
- `Testes Unitários`: Implementação de Testes através da Biblioteca JUnit4.
- `Injeção de dependência`: Utilização da Biblioteca Koin para DI.
- `Arquitetura`: App Desenvolvido utilizando Clean Architecture, MVVM e Design Pattern Sealed.

<h1 align="center"> Demonstração </h1>
<p align="center">
  <img src="https://user-images.githubusercontent.com/67174577/164967345-0381ee14-77de-4796-8f34-2f18ed102090.gif" alt="animated" />
</p>

<h1 align="center"> Proximas Features </h1>

- [ ] Adicionar Personagem favorito ao carousel.
- [ ] Barra de pesquisa.
- [ ] Tela de Splash.
- [ ] Detalhes de personagens e suas referencias.
- [ ] Botão "Mostrar mais" para cards com descrições mais longas.
- [ ] Filtro de personagens.
- [ ] Implementação Offline.

<h1 align="center"> Outras Tecnologias e Bibliotecas </h1>

<h3> Coroutines </h3>

```
 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
```

<h3> Navigation </h3>

```
 implementation 'androidx.navigation:navigation-fragment-ktx:2.4.2'
 implementation 'androidx.navigation:navigation-ui-ktx:2.4.2'
```

<h3> Retrofit - Gson </h3>

```
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
 implementation 'com.google.code.gson:gson:2.9.0'
 implementation 'com.squareup.okhttp3:okhttp:4.9.3'
 implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3'
```

<h3> Glide </h3>

```
  implementation 'com.github.bumptech.glide:glide:4.13.0'
```

<h3> Koin </h3>

```
  implementation "io.insert-koin:koin-android:3.1.3"
  testImplementation "io.insert-koin:koin-test:3.1.3"
```

<h3> Motion e Paging </h3>

```
  implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
  implementation 'androidx.paging:paging-runtime-ktx:3.1.1'
```

