package dev.garlicbread.fetchhiring.repository

import dev.garlicbread.fetchhiring.data.FetchHiringApi
import dev.garlicbread.fetchhiring.data.response.FetchHiringResponse
import dev.garlicbread.fetchhiring.di.IoDispatcher
import dev.garlicbread.fetchhiring.model.Item
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchHiringRepositoryReal @Inject constructor(
    private val fetchHiringApi: FetchHiringApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FetchHiringRepository {
    override fun fetchHiringList(): Flow<FetchHiringResponse> = flow {
        val result = fetchHiringApi.fetchHiringList()
        if (result.isSuccessful) {
            val itemList = result.body().orEmpty()
            if (itemList.isEmpty()) {
                emit(FetchHiringResponse.Error)
            } else {
                emit(
                    FetchHiringResponse.Success(
                        itemList.filter { !it.name.isNullOrEmpty() }
                            .sortedWith(compareBy<Item> { it.listId }.thenBy { it.id })
                    )
                )
            }
        } else {
            emit(FetchHiringResponse.Error)
        }
    }.flowOn(ioDispatcher)
}