package com.stevens.retrorxjava.network

object GameRepositoryFactory {
    var gameRepository: GameRepo = GameRepository()
}

object GameApiFactory {
    var gameApi: GameApi = NetworkClient.retrofit.create(GameApi::class.java)
}