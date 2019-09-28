package com.trendyol.productcontenteditorapi.infra.commandbus

import kotlinx.coroutines.Deferred


interface CommandBus {
    suspend fun <TResponse, TCommand : Command<TResponse>> executeCommandAsync(command: TCommand): Deferred<TResponse>

    suspend fun <R, Q : Query<R>> executeQueryAsync(query: Q): Deferred<R>
}

