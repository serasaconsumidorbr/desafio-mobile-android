<h1 align="center"> Desafio - Android Developer </h1>

<h3 align="center">Desafio - Android Developer </h3>

- Aplicação desenvolvida para mostrar detalhes sobre personagens da Marvel Comics.
- Endpoint  <strong>/v1/public/characters</strong>.
- Informações sobre a API disponíveis em https://developer.marvel.com/documentation/.

## :ok_hand: :ok_hand: Amostra do projeto
![ezgif com-gif-maker](https://user-images.githubusercontent.com/47400782/168346262-d93e3e69-af23-4657-8dc6-f7d1d150ff20.gif)


## :ok_hand: Tecnologias do projeto

- Scroll Infinito: Foi criada uma classe para gerenciamento de paginação através da biblioteca Paging 3.
- Carousel: Criado através do component material viewPager2 para simplificar e padronizar a implementação.
- Motion layout: Implementação feita através da Biblioteca MotionLayout que gerencia o movimento e widget de animação em apps.
- Retrofit: Implementação do serviço utilizando a Biblioteca Retrofit. para simplificar o código que é executado de forma assíncrona.
- Coroutines: Para simplificar o código que é executado de forma assíncrona.
- Injeção de dependência: Utilização da Biblioteca Koin para DI.
- Arquitetura: App Desenvolvido utilizando Clean Architecture, MVVM e Design Pattern Sealed.
- dot indicator: Biblioteca adicionada para personalizar o dot do viewoager2

<h1 align="center"> Proximas Features </h1>

- [ ] Adicionar Personagem favorito ao carousel.
- [ ] Testes Unitários
- [ ] Barra de pesquisa.
- [ ] Tela de Splash.
- [ ] Detalhes de personagens e suas referencias.
- [ ] Botão "Mostrar mais" para cards com descrições mais longas.
- [ ] Implementação Offline.

<h1 align="center"> Outras Tecnologias e Bibliotecas </h1>

- Versão mínima do SDK: 21
- Tela deve ajustar em devices menores.
- Utilizar Kotlin
- Boa arquitetura, pode ser (mvc, mvp, mvvm, clean etc)
- RxJava ou Coroutines
- Testes unitários
- Cache de imagens
- Tratamentos de erros
- Padrão de Projeto e boas práticas de Orientação a Objetos.
- Google AAC (Android Architecture Components)

<h3> Coroutines </h3>

```
 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
```

<h3> Navigation </h3>

```
 implementation 'androidx.navigation:navigation-fragment-ktx:2.4.2'
 implementation 'androidx.navigation:navigation-ui-ktx:2.4.2'
```
<h3> Retrofit </h3>

```
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

<h3> Gson </h3>

```
 implementation 'com.google.code.gson:gson:2.9.0'
```

<h3> Glide </h3>

```
  implementation 'com.github.bumptech.glide:glide:4.13.0'
```

<h3> Interceptor </h3>

```
  implementation 'com.squareup.okhttp3:okhttp:4.9.3'
  implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3'
```

<h3> Koin </h3>

```
  implementation "io.insert-koin:koin-android:3.1.3"
  testImplementation "io.insert-koin:koin-test:3.1.3"
```

<h3> Motion</h3>

```
  implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
```

<h3> Motion</h3>

```
  implementation 'androidx.paging:paging-runtime-ktx:3.1.1'
```

<h3> Dor Indicator</h3>

```
  implementation("com.tbuonomo:dotsindicator:4.3")
```

## :boom: Até mais.

