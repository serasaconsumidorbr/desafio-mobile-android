package com.example.marvel_app.features.detail.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.features.details.usecase.GetCategoriesUseCase
import com.example.core.features.favorites.usecase.AddFavoriteUseCase
import com.example.core.features.favorites.usecase.RemoveFavoriteUseCase
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
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    private val _addFavoriteUiState = MutableLiveData<AddFavoriteUiState>()
    val addFavoriteUiState: LiveData<AddFavoriteUiState> get() = _addFavoriteUiState

    private val _removeFavoriteUiState = MutableLiveData<RemoveFavoriteUiState>()
    val removeFavoriteUiState: LiveData<RemoveFavoriteUiState> get() = _removeFavoriteUiState

    init {
        _addFavoriteUiState.value = AddFavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
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
                    _addFavoriteUiState.value = AddFavoriteUiState.Loading
                },
                success = {
                    _addFavoriteUiState.value = AddFavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_checked)
                },
                error = {
                    _addFavoriteUiState.value = AddFavoriteUiState.Error
                }
            )
        }
    }

    fun removeFavorite (detailViewArg: DetailViewArg) = viewModelScope.launch {
        detailViewArg.run {
            removeFavoriteUseCase.invoke(
                RemoveFavoriteUseCase.ParamsRemoveFavorite (
                    characterId, name, imageUrl
                )
            ).watchStatus(
                loading = {
                    _removeFavoriteUiState.value = RemoveFavoriteUiState.Loading
                },
                success = {
                    _removeFavoriteUiState.value = RemoveFavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
                },
                error = {
                    _removeFavoriteUiState.value = RemoveFavoriteUiState.Error
                }
            )
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val detailParentList: List<DetailParentViewEntity>) : UiState()
        object Empty: UiState()
        object Error : UiState()
    }

    sealed class AddFavoriteUiState {
        object Loading : AddFavoriteUiState()
        class FavoriteIcon(@DrawableRes val icon: Int) : AddFavoriteUiState()
        object Error: AddFavoriteUiState()
    }

    sealed class RemoveFavoriteUiState {
        object Loading : RemoveFavoriteUiState()
        class FavoriteIcon(@DrawableRes val icon: Int) : RemoveFavoriteUiState()
        object Error: RemoveFavoriteUiState()
    }
}