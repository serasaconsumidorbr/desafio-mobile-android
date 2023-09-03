package com.example.marvel_app.features.detail.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.features.details.usecase.GetCategoriesUseCase
import com.example.core.features.favorites.usecase.AddFavoriteUseCase
import com.example.marvel_app.R
import com.example.marvel_app.features.detail.response.DetailChildViewEntity
import com.example.marvel_app.features.detail.response.DetailParentViewEntity
import com.example.marvel_app.utils.args.DetailViewArg
import com.example.marvel_app.utils.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private val _favoriteUiState = MutableLiveData<FavoriteUiState>()
    val favoriteUiState: LiveData<FavoriteUiState> get() = _favoriteUiState

    init {
        _favoriteUiState.value = FavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
    }

    fun getCategories(characterId: Int) = viewModelScope.launch {
        getCategoriesUseCase(GetCategoriesUseCase.GetCategoriesParams(characterId))
            .watchStatus(
                loading = {
                    _uiState.value = UiState.Loading
                },
                success = { data ->
                    val detailParentList = mutableListOf<DetailParentViewEntity>()

                    val comics = data.first
                    if(comics.isNotEmpty()){
                        comics.map {
                            DetailChildViewEntity(it.id, it.imageUrl)
                        }.also {
                            detailParentList.add(
                                DetailParentViewEntity(R.string.details_comics_category, it)
                            )
                        }
                    }

                    val events = data.second
                    if(events.isNotEmpty()){
                        events.map {
                            DetailChildViewEntity(it.id, it.imageUrl)
                        }.also {
                            detailParentList.add(
                                DetailParentViewEntity(R.string.details_events_category, it)
                            )
                        }
                    }

                    _uiState.value = if(detailParentList.isNotEmpty()){
                        UiState.Success(detailParentList)
                    } else UiState.Empty
                },
                error = {
                    _uiState.value = UiState.Error
                }
            )
    }

    fun updateFavorite (detailViewArg: DetailViewArg) = viewModelScope.launch {
        detailViewArg.run {
            addFavoriteUseCase.invoke(
                AddFavoriteUseCase.ParamsAddFavorite(
                    characterId, name, imageUrl
                )
            ).watchStatus(
                loading = {
                    _favoriteUiState.value = FavoriteUiState.Loading
                },
                success = {
                    _favoriteUiState.value = FavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_checked)
                },
                error = {}
            )
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentViewEntity>) : UiState()
        object Empty: UiState()
        object Error : UiState()
    }

    sealed class FavoriteUiState {
        object Loading : FavoriteUiState()
        class FavoriteIcon(@DrawableRes val icon: Int) : FavoriteUiState()
    }
}