package com.stevens.retrorxjava.network

import io.reactivex.Completable
import io.reactivex.Observable

interface GameRepo {
    fun getUsers(): Completable
    val allUsers: Observable<UsersResponse>
    companion object
}