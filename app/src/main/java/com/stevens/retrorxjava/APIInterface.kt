package com.stevens.retrorxjava

import io.reactivex.Single
import retrofit2.http.GET

interface APIInterface {

    @GET("/users")
    fun getUsers(): Single<UsersResponse>
}

data class UsersResponse(
    val users: List<User>
)

data class User(
    val userId: Int,
    val name: String,
    val golferIds: List<Golfer>
)

data class Golfer(
    val golferId: Int,
    val name: String? = null,
    val winnings: Long? = null
)