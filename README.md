# Descrição

Este app consome e exibe a lista de personagens retornada pelo endpoint da Marvel.

A tela de splash exibe uma intro da Marvel e é possível pular clicando na tela.

Na tela principal é exibido um carrossel com os 5 primeiros personagens da lista e um ScrollView abaixo com um RecyclerView exibindo todos os personagens restantes 
retornados pela api, ambos mostrando nome, thumbnail e descrição dos personagens, além de um SwipeRefreshLayout para recarregar a tela.

Ao clicar em qualquer personagem o usuário será redirecionado para a tela de descrição, onde será exibido nome, thumbnail e descrição (se houver) do personagem selecionado.
Todas as telas contam com acessibilidade, porém é necessário realizar algumas melhorias.

### Tela inicial

![ScreenShot](https://i.imgur.com/yIgRcSu.png) 

### Tela de descrição

![ScreenShot](https://i.imgur.com/lNnPG5U.png)

### Tela de erro

![ScreenShot](https://i.imgur.com/ye2i8Hu.png) 

### Vídeos dos fluxos

[Fluxo feliz](https://www.youtube.com/watch?v=hvgjcoH_bFw)

[Fluxo de erro](https://www.youtube.com/watch?v=vWfhn13bXjA)

### Apk

[marvel_app.apk](https://drive.google.com/file/d/18lNKV7HYA-CcZc4g9f_3EktYCed-1cci/view?usp=sharing)

### Tecnologias/Conhecimentos utilizados:  
  - Android Studio 4.1.2
  - Kotlin
  - Arquitetura MVVM
  - Coroutines
  - ConstraintLayouts
  - Retrofit2
  - Glide
  - CardSlider
  - SwipeRefreshLayout
  - RecyclerView
  - Testes unitários
  - Acessibilidade

### Melhorias que pretendo realizar com o tempo:
  - Busca por personagens específicos.
  - Exibir mais informações sobre o personagem na tela de descrição, como quadrinhos, filmes, etc.
  - Animações.
  - Imagens com mais qualidade.
  - Melhorar acessibilidade já implementada.
  - Layouts mais fluídos.
  - Melhorar e aumentar a cobertura de testes unitários.
