package dev.garlicbread.fetchhiring.repository

import dev.garlicbread.fetchhiring.data.response.FetchHiringResponse
import kotlinx.coroutines.flow.Flow

interface FetchHiringRepository {
    fun fetchHiringList(): Flow<FetchHiringResponse>
}