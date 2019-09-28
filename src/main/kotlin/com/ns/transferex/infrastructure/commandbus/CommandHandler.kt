package com.ns.transferex.infrastructure.commandbus

interface CommandHandler<TResponse, TCommand : Command<TResponse>> {
    suspend fun handleAsync(command: TCommand): TResponse
}

