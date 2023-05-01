package com.stevens.retrorxjava.network

import com.google.gson.annotations.SerializedName
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
    val golferIds: List<Golfer>
)

data class Golfer(
    val golferId: Int,
    val name: String? = null,
    val winnings: Long? = null
)
class MultipleResource {
    @SerializedName("page")
    var page: Int? = null

    @SerializedName("per_page")
    var perPage: Int? = null

    @SerializedName("total")
    var total: Int? = null

    @SerializedName("total_pages")
    var totalPages: Int? = null

    @SerializedName("data")
    var data: List<Datum>? = null

    inner class Datum {
        @SerializedName("id")
        var id: Int? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("year")
        var year: Int? = null

        @SerializedName("pantone_value")
        var pantoneValue: String? = null
    }
}