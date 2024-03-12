package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FooResponse(
    val items: List<Item>
)

@Serializable
data class Item(
    val alpha_two_code: String,
    val name: String,
    val country: String,
)

@Serializable
data class Args(val fooId: String)

@Serializable
data class Headers(
    @SerialName("Accept")
    val accept: String,
    @SerialName("Accept-Encoding")
    val acceptEncoding: String,
)
