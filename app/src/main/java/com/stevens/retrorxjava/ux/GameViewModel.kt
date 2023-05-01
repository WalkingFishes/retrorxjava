package com.stevens.retrorxjava.ux

import android.util.Log
import androidx.lifecycle.ViewModel
import com.stevens.retrorxjava.network.GameRepo
import com.stevens.retrorxjava.network.GameRepositoryFactory
import com.stevens.retrorxjava.network.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class GameViewModel (
    private val gameRepository: GameRepo = GameRepositoryFactory.gameRepository,
    private val scheduler: Scheduler = Schedulers.computation(),
    private var compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val usersDataSubject: BehaviorSubject<UserData> = BehaviorSubject.create(),
    val usersDataObservable: Observable<UserData> = usersDataSubject
) : ViewModel() {

    init {
        wireUpSubject()
    }

    private fun wireUpSubject() {
        compositeDisposable.add(
            gameRepository.allUsers
                .subscribeOn(scheduler)
                .observeOn(scheduler)
                .map {
                    UserData(it.users)
                }
                .subscribe(usersDataSubject::onNext) { t ->
                    Log.e("@@", "GameViewModel.gameRepository.allUsers", t)
                }
        )

    }

    fun getUsers(): Completable = gameRepository.getUsers().subscribeOn(scheduler)

}

data class UserData (
    val users: List<User>
) {
    companion object
}
