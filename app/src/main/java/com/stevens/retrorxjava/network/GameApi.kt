package com.stevens.retrorxjava.network

import io.reactivex.Single
import retrofit2.http.*


interface GameApi {

    @GET("/users")
    fun getUsers(): Single<UsersResponse>
}

data class UsersResponse(
    val users: List<User>
)

data class User(
    val userId: Int,
    val name: String,
    val golferIds: List<Int>
)

data class Golfer(
    val golferId: Int,
    val name: String? = null,
    val winnings: Long? = null
)