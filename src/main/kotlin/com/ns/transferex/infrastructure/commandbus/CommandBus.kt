package com.ns.transferex.infrastructure.commandbus


interface CommandBus {
    fun <TResponse, TCommand : Command<TResponse>> executeCommand(command: TCommand): TResponse

    fun <R, Q : Query<R>> executeQuery(query: Q): R
}

