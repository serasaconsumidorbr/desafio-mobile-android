## Fastlane documentação

## Instalação

Certifique-se de ter a versão mais recente das ferramentas estão instaladas

- Pré-requisitos:
    - Ruby 2.7.1
    - Android API 31
        - Android SDK Build Tools 31.0.0
    - Android NDK 21.4.7075529

Instalação do fastlane

```sh
gem install fastlane
```

Para _fastlane_ mais detalhes de como instalar leia - [Installing _
fastlane_](https://docs.fastlane.tools/#installing-fastlane)

## Ações disponíveis

### android beta

Envie uma nova versão beta para o Firebase Distribute

```sh
[bundle exec] fastlane android beta
```

Para passar parâmetros, use o símbolo (dois pontos) :

Por exemplo:

```sh
[bundle exec] fastlane android beta app_id:"value1" notes:"value2" build_type:"value3"
```

#### Parâmetros

| Parâmetro | Descrição                                                                                                                                                                                  | Valor Padrão                                  |
| :-------: | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------------------------------- |
|  app_id   | Identificador do aplicativo no Firebase, encontra-se nas [Configurações do projeto](https://console.firebase.google.com/project/clickbus-app/settings/general/android:com.clickbus.mobile) |  |
|  firebase_token   | Identificador do usuário admin do aplicativo no Firebase
|   notes   | Descrição da tarefa, título do PR. _Observação não use caracteres especiais_                                                                                                               |                                               |
| build_type | Ambiente que será provedor das informações para o aplicativo.                                                                                                                              | Debug                                       |

### android deploy

Implantar uma nova versão no Google Play

```sh
[bundle exec] fastlane android deploy
```

---

A documentação de _fastlane_ pode ser encontrado
em [docs.fastlane.tools](https://docs.fastlane.tools).