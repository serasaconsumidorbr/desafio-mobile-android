package com.updeveloped.desafiomarvel.modules

import com.updeveloped.desafiomarvel.domain.repository.GetAllCharacters
import com.updeveloped.desafiomarvel.domain.repository.GetAllCharactersUseCase
import com.updeveloped.desafiomarvel.views.ui.fragment.HomeFragment
import com.updeveloped.desafiomarvel.views.viewModel.ListCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<ListCharactersViewModel> { ListCharactersViewModel(get(), get()) }
}

val repositoryModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCase(get()) }
    single<GetAllCharacters> { GetAllCharacters(get()) }
}

val uiModule = module {
    factory<HomeFragment> { HomeFragment() }
}



