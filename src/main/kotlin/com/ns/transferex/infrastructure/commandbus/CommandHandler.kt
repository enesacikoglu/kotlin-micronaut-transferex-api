package com.ns.transferex.infrastructure.commandbus

interface CommandHandler<TResponse, TCommand : Command<TResponse>> {
    fun handle(command: TCommand): TResponse
}

