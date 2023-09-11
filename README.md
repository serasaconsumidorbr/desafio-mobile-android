# Personagens da Marvel

Um aplicativo para consultar uma lista de personagens da Marvel e algumas de suas informações

[![Min Sdk](https://img.shields.io/badge/minSdk-21-green.svg)](https://developer.android.com/about/versions/android-5.0)


## Recursos

- Exibe personagens da Marvel e sua respectiva descrição e imagem (se disponíveis)
- Permite favoritar personagens para consulta offline 


## Arquitetura

Este projeto segue a arquitetura MVVM. A navegação entre diferentes telas do aplicativo é gerenciada pelo componente de Navegação do Jetpack.

## Bibliotecas

Algumas das bibliotecas utilizadas neste projeto são:

### Android Jetpack
- Jetpack Compose - para definição de UI de maneira programática
- Activity - para o uso de APIs baseadas na Activity
- Navigation  - para navegação
- Room - para armazenamento local
- ViewModel - para uso de e acesso a ViewModels
- Compose Material 3 - para uso dos componentes e recursos do Material 3

### Outras Bibliotecas
- Koin - para injeção de dependência
- Retrofit - para fazer solicitações HTTP para a API de personagens
- Coil - para carregamento de imagens
- JUnit - para testes automatizados
- Accompanist System UI Controller  - para definir as cores das barras do sistema

Para mais informações sobre as bibliotecas utilizadas consulte as [configurações de compilação](https://github.com/italocw/marvel-characters/blob/develop/first-version/app/build.gradle).

## Capturas de Tela


## Executando o Projeto

1. Clone o repositório
2. Selecione a branch develop/first-version
3. Importe-o no Android Studio
4. Adicione suas próprias chave da API da Marvel e a hash resultante ao arquivo local.properties:
   `public_api_key=SUA_CHAVE_PUBLICA_AQUI`
   `private_api_key=SUA_CHAVE_PRIVADA_AQUI`
   `hash=SUA_HASH_AQUI` 

  Nota: Crie uma conta em [Marvel Developer Portal](https://developer.marvel.com/) para as obter as chaves e consulte [Authorizing and Signing Requests (marvel.com)](https://developer.marvel.com/documentation/authorization) para saber como gerar a hash. 

5. Compile e execute o aplicativo.
