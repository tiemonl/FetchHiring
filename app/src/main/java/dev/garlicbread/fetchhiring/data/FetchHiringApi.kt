package dev.garlicbread.fetchhiring.data

import dev.garlicbread.fetchhiring.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface FetchHiringApi {
    @GET("hiring.json")
    suspend fun fetchHiringList(): Response<List<Item>>
}