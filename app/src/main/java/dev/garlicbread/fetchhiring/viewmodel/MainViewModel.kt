package dev.garlicbread.fetchhiring.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.garlicbread.fetchhiring.data.response.FetchHiringResponse
import dev.garlicbread.fetchhiring.model.Item
import dev.garlicbread.fetchhiring.repository.FetchHiringRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    fetchHiringRepository: FetchHiringRepository
) : ViewModel() {
    val hiringListState: StateFlow<ItemListUiState> = fetchHiringRepository.fetchHiringList().map {
        when (it){
            FetchHiringResponse.Error -> ItemListUiState.Error
            is FetchHiringResponse.Success -> ItemListUiState.Success(it.items)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = ItemListUiState.Loading,
        started = SharingStarted.WhileSubscribed(5000L)
    )

}

sealed interface ItemListUiState {
    data object Loading : ItemListUiState
    data object Error : ItemListUiState
    data class Success(val itemList: List<Item>) : ItemListUiState
}