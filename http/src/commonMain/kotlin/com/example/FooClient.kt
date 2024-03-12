package com.example

import com.example.model.Item
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class FooClient(private val client: HttpClient) {
    suspend fun getFooResponse(): Result<List<Item>> {
        println("http getFooResponse")
        val call = client.get(urlString = "") {
            parameter("country", "Brazil")
        }

        return if (call.status.isSuccess()) {
            println("call success")
            Result.success(call.body<List<Item>>())
        } else {
            println("server status code: ${call.status.value}")

            Result.failure(Exception("server error"))
        }
    }
}
