package dev.garlicbread.fetchhiring.data.response

import dev.garlicbread.fetchhiring.model.Item

sealed interface FetchHiringResponse {
    data class Success(val items: List<Item>) : FetchHiringResponse
    data object Error : FetchHiringResponse
}