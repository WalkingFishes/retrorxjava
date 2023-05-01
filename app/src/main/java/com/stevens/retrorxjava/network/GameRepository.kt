package com.stevens.retrorxjava.network

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class GameRepository (
    private val gameApi: GameApi = GameApiFactory.gameApi,
    private val allUsersSubject: PublishSubject<UsersResponse> = PublishSubject.create()
) : GameRepo {
    override fun getUsers(): Completable {
        TODO("Not yet implemented")
    }

    override val allUsers: Observable<UsersResponse>
        get() = TODO("Not yet implemented")
}