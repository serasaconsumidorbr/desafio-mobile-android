Projeto para apresentar personagens Marvel!

Arquitetura: 
construcao de 4 camadas, as duas primeiras responsaveis por coisas externas ao app:
data -> dados utilizados pelo app, podendo ser vindos do banco de dados local ou consumidos por
uma API.
services -> servicos do dispositivo
A terceira camada domain, eh onde ficam as regras da aplicacao. Os modelos de negocio e o
repository que faz a gestao dos data sources locais ou externos e faz uso dos servicos.
A ultima camada presentation eh onde ficam as views e controles de estado das views.

Bibliotecas:
para consumo da API foi utlizado o Retrofit, e para controle do banco de dados o Room.
Utilizamos coroutines para gerenciamento das threads de execucao.
Utilizamos viewmodel para gerenciamento de views, utilizando mutablestatelist do Jetpack Compose,
que tambem foi a ferramenta utlizada para construcao da UI, substituindo o XML.
Para injecao de dependencias utilizamos o koin, para reproducao das imagens utlizamos o coin.
O app foi construido no modelo single activity, onde as views ficam em fragments,
sendo utilizado o Navigation Component para navegacao das telas.
Para os testes unitarios utilizamos JUnit 4 + mockK do kotlin.

Funcionamento:
As views apenas observam as alteracoes dos valores nos viewmodels e sao responsaveis por
interagir com o usuario, fazendo navegacoes ou acionando os viewmodels.
Os viewmodels sao responsaveis pelas informacoes, solicitando ao repository e emitindo
para a UI.
O repository por sua vez, ao ser solicitado, envia as informacoes ao viewmodel, 
utilizando os servicos e os data sources segundo a logica necessaria,
inclusive realizando as conversoes dos modelos quando necessario.
Os services e data sources apenas realizam o que lhes solicitam.

Melhorias futuras:
- A interface como um todo precisa ser ajustada (principamente o enquadramento das imagens). 
- Falta tratativa de erro no caso da falta de internet durante o scroll infinito. 
- A construcao de telas de erro para melhorar a exp do usuario.
- Scroll infinito tambem para consumo de dados armazenados no banco de dados local.
- Loading individual das imagens
- Search view superior para busca de personagens
- Botao de reload para fazer uma nova chamada de API