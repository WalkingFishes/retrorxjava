package com.stevens.retrorxjava.network

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class GameRepository (
    private val gameApi: GameApi = GameApiFactory.gameApi,
    private val allUsersSubject: PublishSubject<UsersResponse> = PublishSubject.create(),
    private val scheduler: Scheduler = Schedulers.io()
) : GameRepo {

    override fun getUsers(): Completable =
        gameApi.getUsers()
            .subscribeOn(scheduler)
            .doOnSuccess(allUsersSubject::onNext)
            .doOnError { t ->
                Log.e("@@", "GameRepository.gameApi.getUsers", t)
            }
            .ignoreElement()

    override val allUsers: Observable<UsersResponse>
        get() = allUsersSubject.distinctUntilChanged().subscribeOn(scheduler)

}
