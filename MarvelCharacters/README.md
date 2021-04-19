#Marvel Characters

Aplicativo que exibe os personagens da Marvel utilizando a API da Marvel Comics.

##Tecnologias

Foi utilizado o padrão MVVM junto com liveData e coroutines. O koin para a injeção de dependência, o Retrofit para fazer as chamadas rest e o
Glide para carregar as imagens.Foi utilizado uma biblioteca para o uso do carrossel assim evitando um excesso de código.

##Melhorias

Melhoria no ui, utilizar cardView para a exibição de cada personagem. Avaliaria se teria outro carrossel disponível para que se tenha uma melhor 
experiencia com usuário caso não fosse encontrado poderia ser implementado.
Na parte do código seria interessante otimizar as chamada rest para uma chamada só evitando acesso a rede e melhorando a implementação do fluxo de carregamento dos dados.
Precisaria implementar a parte para funcionar offline e tratar os erros.
Melhoria nos testes implementando outros fluxos com os erros e não somente o caminho feliz


##Bibliotecas 

	//injeção de dependência
	implementation 'org.koin:koin-core:2.2.2'
	implementation "org.koin:koin-android-viewmodel:2.2.2"

	//retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
	//coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
	//Android lifecycle
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'
    implementation 'androidx.viewpager2:viewpager2:1.0.0'

	//Imagem
    implementation 'com.github.bumptech.glide:glide:4.12.0'
	
	//Carrossel
    implementation 'com.synnapps:carouselview:0.1.5'
	
	//Paginação
    implementation "androidx.paging:paging-runtime-ktx:3.0.0-beta03"
	
	//Inserção de log 
    implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"
    
	//Mock
    testImplementation "org.mockito:mockito-core:3.0.0"
    testImplementation 'org.mockito:mockito-inline:2.13.0'
	//test
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3"
    testImplementation 'android.arch.core:core-testing:2.1.0'
	testImplementation 'junit:junit:4.13'


