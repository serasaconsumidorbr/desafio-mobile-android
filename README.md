**MARVEL VERSE**


**SOBRE**
Hello everyone! Esse aplicativo foi feito no intuito de consumir os dados da API da MArvel trazendo seus character e algumas informações sobre eles e possibilitar criar uma lista de favoritos, aqueles personagens que você mais curtiu 💙. Ainda está em processo de desenvolvimento, podendo surgir novas funcionalidades e features pra você aproveitar ao máximo essa experiência pelo Marvel Verse.


**PROJETO**
Falando um pouco sobre o background do projeto, ele foi desenvolvido utilizando Clean Architecture e MVVN, com o intuito de trazer uma boa arquitetura e manter a casa em ordem conforme for crescendo o produto. 
Já sobre a escolha da divisão que realizei do projeto, a fiz da seguinte forma:

data: Sendo a camada de infraestrutura para onde vão as implementações externas do meu domínio, são os adapters; como implementações de repositório, fontes de dados, etc.

di: Uma camada de injeção de dependência (baseada em Hilt)

domínio: É aqui que vai a lógica do aplicativo, tais como modelos, interfaces de repositório, gerenciamento de rede customizado.

ui: A camada responsável pelas interações da UI.

Como o projeto em si é bem simplórido, uma arquitetura clean deixa mais agradável de se trabalhar e sem dúvida de manter e compreender o funcionamento da aplicação como um todo.
Sobre interface tentei me inspirar principalmente no app da Netlflix, porém com o scroll infinito e os characters vindo em cards para trazer a "the best experience ever" ao usuário e esporto fortemente que curta o resultado.


**TECHs UTILIZADAS **
navigation:  2.3.5
mock:        1.12.2
hilt:        2.40.5
retrofit:    2 2.9.0
picasso:     2.8
roomVersion: 2.4.1
junit:       4.13.2
appcompat:   1.4.1


**IMPLEMENTAÇÕES FUTURAS**
- Implementar para trazer os comics de cada character
- Implementar alguns skeletons para melhorar o design 
- Avaliar a necessidade de trazer apenas characters que tenham imagem na API.
