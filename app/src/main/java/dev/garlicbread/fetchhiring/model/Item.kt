package dev.garlicbread.fetchhiring.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val id: Int,
    val listId: Int,
    val name: String?
)
